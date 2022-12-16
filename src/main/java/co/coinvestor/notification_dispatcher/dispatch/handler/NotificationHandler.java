package co.coinvestor.notification_dispatcher.dispatch.handler;

import co.coinvestor.notification_dispatcher.dto.NSRequestDto;

public interface NotificationHandler {

    boolean able(NSRequestDto requestDto);

    void handle(NSRequestDto requestDto);
}
