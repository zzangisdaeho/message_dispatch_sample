package co.coinvestor.notification_dispatcher.enums;

import co.coinvestor.notification_dispatcher.dto.MailSendDto;

import java.util.Arrays;

public enum NotificationSource {

    INVESTOR, BABBLE, ADMIN;

    public static NotificationSource matcher(String name){
        return Arrays.stream(NotificationSource.values())
                .filter(notificationSource -> notificationSource.name().equals(name))
                .findAny().get();
    }
}
