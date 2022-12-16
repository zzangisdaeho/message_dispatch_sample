package co.coinvestor.notification_dispatcher.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

class NSRequestDtoTest {

    @Test
    public void makeJsonObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


        NSRequestDto build = NSRequestDto.builder()
                .source(NSRequestDto.NotificationSource.INVESTOR)
                .destination(NSRequestDto.Destination.WEB_NOTIFICATION)
                .receiverIdList(List.of(1L, 2L, 3L))
                .senderInfo(NSRequestDto.SenderInfo.builder()
                        .senderId(100L)
                        .senderImgUrl("imgUrl")
                        .senderProfileUrl("profileUrl")
                        .build())
                .type(NSRequestDto.NotificationType.STRATEGY_PURCHASE)
                .target("StrategyA")
                .targetUrl("targetUrl")
                .dateTime(ZonedDateTime.now())
                .content("this is message baby >,<")
                .build();

        String s = objectMapper.writeValueAsString(build);

        System.out.println("s = " + s);

        NSRequestDto nsRequestDto = objectMapper.readValue(s, NSRequestDto.class);

        System.out.println("nsRequestDto = " + nsRequestDto);

    }
}