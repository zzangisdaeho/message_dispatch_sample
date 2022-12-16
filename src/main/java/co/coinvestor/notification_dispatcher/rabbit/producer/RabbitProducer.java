package co.coinvestor.notification_dispatcher.rabbit.producer;

import co.coinvestor.notification_dispatcher.rabbit.RabbitConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public void sendMessageToMail(Object payload){
        rabbitTemplate.convertAndSend("ns.exchange-topic.mail.v0", "ns.mail.cmd.v0", payload);
    }

    public void sendMessageToWebNotification(Object payload){
        rabbitTemplate.convertAndSend("ns.exchange-fanout.wn.v0", "", payload);
//        try {
//            rabbitTemplate.convertAndSend("ns.exchange-fanout.wn.v0", "", objectMapper.writeValueAsString(payload));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }
}
