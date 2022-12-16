package co.coinvestor.notification_dispatcher.rabbit.consumer;

import co.coinvestor.notification_dispatcher.dispatch.NotificationDispatcher;
import co.coinvestor.notification_dispatcher.dto.NSRequestDto;
import co.coinvestor.notification_dispatcher.rabbit.RabbitConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RabbitConsumer {

    private final NotificationDispatcher dispatcher;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveDispatch(Message message){
        String payload = new String(message.getBody());
        try {
            dispatcher.dispatch(objectMapper.readValue(payload, NSRequestDto.class));
        } catch (JsonProcessingException e) {
            log.error("wrong message form. received Message : \n{} \n error : {}", payload, e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            log.error("message handling fail " + e);
        }
    }
}
