package co.coinvestor.notification_dispatcher.dispatch.factory;

import co.coinvestor.notification_dispatcher.document.Notification;
import co.coinvestor.notification_dispatcher.dto.MailSendDto;
import co.coinvestor.notification_dispatcher.dto.NSRequestDto;
import co.coinvestor.notification_dispatcher.enums.NotificationSource;
import co.coinvestor.notification_dispatcher.enums.NotificationType;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class MailFactory implements ConsumerFormFactory<MailSendDto> {
    @Override
    public MailSendDto createConsumerForm(NSRequestDto dto, int index, String transactionNo) {
        return MailSendDto.builder()
                .source(NotificationSource.matcher(dto.getSource().name()))
                .receiverId(dto.getReceiverIdList().get(index))
                .senderInfo(MailSendDto.SenderInfo.builder()
                        .senderId(dto.getSenderInfo().getSenderId())
                        .senderImgUrl(dto.getSenderInfo().getSenderImgUrl())
                        .senderProfileUrl(dto.getSenderInfo().getSenderProfileUrl())
                        .build())
                .type(NotificationType.matcher(dto.getType().name()))
                .dateTime(dto.getDateTime())
                .payload(MailSendDto.Payload.builder()
                        .target(dto.getPayload().getTarget())
                        .targetUrl(dto.getPayload().getTargetUrl())
                        .content(dto.getPayload().getContent())
                        .receiverEmailAddress(dto.getPayload().getReceiverEmailAddressList().get(index))
                        .templateName(dto.getPayload().getTemplateName())
                        .title(dto.getPayload().getTitle())
                        .codes(dto.getPayload().getCodes())
                        .build())
                .build();
    }
}
