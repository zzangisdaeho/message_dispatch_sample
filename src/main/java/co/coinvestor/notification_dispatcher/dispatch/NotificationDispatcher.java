package co.coinvestor.notification_dispatcher.dispatch;

import co.coinvestor.notification_dispatcher.dispatch.handler.NotificationHandler;
import co.coinvestor.notification_dispatcher.dto.NSRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationDispatcher {

    private final List<NotificationHandler> handlerList;

    public void dispatch(NSRequestDto requestDto) {

        handlerList.stream()
                .filter(each -> each.able(requestDto)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("핸들러가 없 ㅋ 엌 ㅋ"))
                .handle(requestDto);


    }
}
