package co.coinvestor.notification_dispatcher.dispatch.factory;

import co.coinvestor.notification_dispatcher.document.Notification;
import co.coinvestor.notification_dispatcher.dto.NSRequestDto;

import java.util.List;
import java.util.UUID;

public interface NotificationFactory {

    default List<Notification> generateNotifications(NSRequestDto requestDto){
        String transactionNo = prepareTransactionId();
        List<Notification> collect = requestDto.getReceiverIdList().stream()
                .map(eachReceiverId -> this.createNotification(requestDto, eachReceiverId, transactionNo))
                .toList();

        return collect;
    }

    Notification createNotification(NSRequestDto dto, long receiverId, String transactionNo);

    private String prepareTransactionId(){
        return UUID.randomUUID().toString();
    }
}
