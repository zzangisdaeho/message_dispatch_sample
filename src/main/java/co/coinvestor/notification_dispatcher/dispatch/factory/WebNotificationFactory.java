package co.coinvestor.notification_dispatcher.dispatch.factory;

import co.coinvestor.notification_dispatcher.document.Notification;
import co.coinvestor.notification_dispatcher.dto.NSRequestDto;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.UUID;

@Component
public class WebNotificationFactory implements NotificationFactory {
    @Override
    public Notification createNotification(NSRequestDto dto, long receiverId, String transactionNo) {
        return Notification.builder()
                .transactionId(transactionNo)
                .content(dto.getContent())
                .receiverId(receiverId)
                .senderInfo(Notification.SenderInfo.builder()
                        .senderId(dto.getSenderInfo().getSenderId())
                        .senderImgUrl(dto.getSenderInfo().getSenderImgUrl())
                        .senderProfileUrl(dto.getSenderInfo().getSenderProfileUrl())
                        .build())
                .type(Notification.NotificationType.matcher(dto.getType().name()))
                .target(dto.getTarget())
                .targetUrl(dto.getTargetUrl())
                .content(dto.getContent())
                .createdAt(Date.from(dto.getDateTime().toInstant()))
                .build();
    }

}
