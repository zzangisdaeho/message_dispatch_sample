package co.coinvestor.notification_dispatcher.dto;

import co.coinvestor.notification_dispatcher.document.Notification;
import co.coinvestor.notification_dispatcher.enums.Destination;
import co.coinvestor.notification_dispatcher.enums.NotificationSource;
import co.coinvestor.notification_dispatcher.enums.NotificationType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

class NSRequestDtoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    }

    @Test
    public void makeJsonObjectOfNotification() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


        NSRequestDto build = NSRequestDto.builder()
                .source(NotificationSource.INVESTOR)
                .destination(Destination.WEB_NOTIFICATION)
                .receiverIdList(List.of(1L, 2L, 3L))
                .senderInfo(NSRequestDto.SenderInfo.builder()
                        .senderId(100L)
                        .senderImgUrl("imgUrl")
                        .senderProfileUrl("profileUrl")
                        .build())
                .type(NotificationType.STRATEGY_PURCHASE)
                .dateTime(ZonedDateTime.now())
                .payload(NSRequestDto.Payload.builder()
                        .target("StrategyA")
                        .targetUrl("targetUrl")
                        .content("this is message baby >,<")
                        .build())
                .build();

        String s = objectMapper.writeValueAsString(build);

        System.out.println("s = " + s);

        NSRequestDto nsRequestDto = objectMapper.readValue(s, NSRequestDto.class);

        System.out.println("nsRequestDto = " + nsRequestDto);
    }

    @Test
    public void makeJsonObjectOfMail() throws JsonProcessingException {
        NSRequestDto build = NSRequestDto.builder()
                .source(NotificationSource.INVESTOR)
                .destination(Destination.EMAIL)
                .receiverIdList(List.of(1L, 2L, 3L))
                .senderInfo(NSRequestDto.SenderInfo.builder()
                        .senderId(100L)
                        .senderImgUrl("imgUrl")
                        .senderProfileUrl("profileUrl")
                        .build())
                .type(NotificationType.STRATEGY_PURCHASE)
                .dateTime(ZonedDateTime.now())
                .payload(NSRequestDto.Payload.builder()
                        .target("StrategyA")
                        .targetUrl("targetUrl")
                        .content("this is message baby >,<")
                        .receiverEmailAddressList(List.of("issac.kim@bclabs.co.kr", "issac.kim@bclabs.co.kr", "issac.kim@bclabs.co.kr"))
                        .codes(new String[]{"code1", "code2", "code3"})
                        .templateName("welcome")
                        .title("hi there~")
                        .build())
                .build();

        String s = objectMapper.writeValueAsString(build);

        System.out.println("s = " + s);
    }
}