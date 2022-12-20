package co.coinvestor.notification_dispatcher.enums;

import co.coinvestor.notification_dispatcher.dto.MailSendDto;

import java.util.Arrays;

public enum NotificationType {
    STRATEGY_PURCHASE, STRATEGY_LIKE, NEW_POST, POST_REPLY, POST_LIKE,
    MENTION, FOLLOW;

    public static NotificationType matcher(String name){
        return Arrays.stream(NotificationType.values())
                .filter(notificationType -> notificationType.name().equals(name))
                .findAny().get();
    }

}
