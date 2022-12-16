package co.coinvestor.notification_dispatcher.dispatch.factory;

import co.coinvestor.notification_dispatcher.document.Notification;
import co.coinvestor.notification_dispatcher.dto.NSRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MailFactory implements NotificationFactory{
    @Override
    public Notification createNotification(NSRequestDto dto, long receiverId, String transactionNo) {
        return null;
    }
}
