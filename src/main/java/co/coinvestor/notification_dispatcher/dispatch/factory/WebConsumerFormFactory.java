package co.coinvestor.notification_dispatcher.dispatch.factory;

import co.coinvestor.notification_dispatcher.document.Notification;
import co.coinvestor.notification_dispatcher.dto.NSRequestDto;
import co.coinvestor.notification_dispatcher.enums.NotificationSource;
import co.coinvestor.notification_dispatcher.enums.NotificationType;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class WebConsumerFormFactory implements ConsumerFormFactory<Notification> {
    @Override
    public Notification createConsumerForm(NSRequestDto dto, int index, String transactionNo) {
        return Notification.builder()
                .transactionId(transactionNo)
                .receiverId(dto.getReceiverIdList().get(index))
                .payload(Notification.Payload.builder()
                        .target(dto.getPayload().getTarget())
                        .targetUrl(dto.getPayload().getTargetUrl())
                        .content(dto.getPayload().getContent())
                        .build())
                .senderInfo(Notification.SenderInfo.builder()
                        .senderId(dto.getSenderInfo().getSenderId())
                        .senderImgUrl(dto.getSenderInfo().getSenderImgUrl())
                        .senderProfileUrl(dto.getSenderInfo().getSenderProfileUrl())
                        .build())
                .type(NotificationType.matcher(dto.getType().name()))
                .createdAt(Date.from(dto.getDateTime().toInstant()))
                .source(NotificationSource.matcher(dto.getSource().name()))
                .build();
    }

}
