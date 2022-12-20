package co.coinvestor.notification_dispatcher.dispatch.handler;

import co.coinvestor.notification_dispatcher.dispatch.factory.ConsumerFormFactory;
import co.coinvestor.notification_dispatcher.document.Notification;
import co.coinvestor.notification_dispatcher.dto.NSRequestDto;
import co.coinvestor.notification_dispatcher.enums.Destination;
import co.coinvestor.notification_dispatcher.rabbit.producer.RabbitProducer;
import co.coinvestor.notification_dispatcher.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WebNotificationHandler implements NotificationHandler{

    private final ConsumerFormFactory<Notification> webConsumerFormFactory;
    private final NotificationRepository notificationRepository;
    private final RabbitProducer rabbitProducer;

    @Override
    public boolean able(NSRequestDto requestDto) {
        return requestDto.getDestination().equals(Destination.WEB_NOTIFICATION);
    }

    @Override
    public void handle(NSRequestDto requestDto) {
        //0. receiverList size 만큼 message split
        List<Notification> notifications = webConsumerFormFactory.generateConsumerFormList(requestDto);

        //1. mongodb에 저장
        notificationRepository.saveAll(notifications);

        //2. mongodb에 매핑된 값을 기본으로 WN의 dto형식과 일치하게 전송(개별)
        notifications.forEach(rabbitProducer::sendMessageToWebNotification);
    }
}
