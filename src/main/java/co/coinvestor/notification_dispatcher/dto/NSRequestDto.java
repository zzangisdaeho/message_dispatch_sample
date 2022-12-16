package co.coinvestor.notification_dispatcher.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NSRequestDto {

    private NotificationSource source;
    private Destination destination;

    private List<Long> receiverIdList;

    private SenderInfo senderInfo;

    private NotificationType type;

    private String target;

    private String targetUrl;

    private ZonedDateTime dateTime;

    private Object content;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SenderInfo{
        private Long senderId;

        private String senderImgUrl;

        private String senderProfileUrl;
    }

    public enum NotificationType{
        STRATEGY_PURCHASE, STRATEGY_LIKE, NEW_POST, POST_REPLY, POST_LIKE,
        MENTION, FOLLOW;
    }

    public enum NotificationSource{
        INVESTOR, BABBLE, ADMIN;
    }

    public enum Destination{
        WEB_NOTIFICATION, EMAIL;
    }
}
