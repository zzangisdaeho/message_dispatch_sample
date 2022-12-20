package co.coinvestor.notification_dispatcher.dto;

import co.coinvestor.notification_dispatcher.enums.NotificationSource;
import co.coinvestor.notification_dispatcher.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
public class MailSendDto {

    private NotificationSource source;

    private long receiverId;

    private SenderInfo senderInfo;

    private NotificationType type;

    private ZonedDateTime dateTime;

    private Payload payload;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SenderInfo{
        private Long senderId;

        private String senderImgUrl;

        private String senderProfileUrl;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Payload{

        private String target;

        private String targetUrl;

        private String receiverEmailAddress;

        private String templateName;

        private String title;

        private String[] codes;

        private Object content;
    }
}
