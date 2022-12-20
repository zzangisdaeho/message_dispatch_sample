package co.coinvestor.notification_dispatcher.dispatch.handler;

import co.coinvestor.notification_dispatcher.dispatch.factory.ConsumerFormFactory;
import co.coinvestor.notification_dispatcher.document.Notification;
import co.coinvestor.notification_dispatcher.dto.MailSendDto;
import co.coinvestor.notification_dispatcher.dto.NSRequestDto;
import co.coinvestor.notification_dispatcher.enums.Destination;
import co.coinvestor.notification_dispatcher.rabbit.producer.RabbitProducer;
import co.coinvestor.notification_dispatcher.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MailHandler implements NotificationHandler{

    private final ConsumerFormFactory<MailSendDto> mailFactory;
    private final RabbitProducer rabbitProducer;

    @Override
    public boolean able(NSRequestDto requestDto) {
        return requestDto.getDestination().equals(Destination.EMAIL);
    }

    @Override
    public void handle(NSRequestDto requestDto) {
        //0. receiverList size 만큼 message split
        List<MailSendDto> mailSendDtoList = mailFactory.generateConsumerFormList(requestDto);

        //2. mongodb에 매핑된 값을 기본으로 WN의 dto형식과 일치하게 전송(개별)
        mailSendDtoList.forEach(rabbitProducer::sendMessageToMail);

    }
}
