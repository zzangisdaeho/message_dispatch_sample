package co.coinvestor.notification_dispatcher.dispatch.handler;

import co.coinvestor.notification_dispatcher.dto.NSRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MailHandler implements NotificationHandler{
    @Override
    public boolean able(NSRequestDto requestDto) {
        return requestDto.getDestination().equals(NSRequestDto.Destination.EMAIL);
    }

    @Override
    public void handle(NSRequestDto requestDto) {

    }
}
