package co.coinvestor.notification_dispatcher.dto;

import co.coinvestor.notification_dispatcher.enums.Destination;
import co.coinvestor.notification_dispatcher.enums.NotificationSource;
import co.coinvestor.notification_dispatcher.enums.NotificationType;
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

        private List<String> receiverEmailAddressList;

        private String templateName;

        private String title;

        private String[] codes;

        private Object content;
    }
}
