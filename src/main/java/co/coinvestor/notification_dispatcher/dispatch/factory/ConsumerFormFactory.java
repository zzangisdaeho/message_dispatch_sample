package co.coinvestor.notification_dispatcher.dispatch.factory;

import co.coinvestor.notification_dispatcher.dto.NSRequestDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

public interface ConsumerFormFactory<T> {

    default List<T> generateConsumerFormList(NSRequestDto requestDto){
        String transactionNo = prepareTransactionId();

        return IntStream.range(0, requestDto.getReceiverIdList().size())
                .mapToObj(index -> this.createConsumerForm(requestDto, index, transactionNo)).toList();

    }

    T createConsumerForm(NSRequestDto dto, int index, String transactionNo);

    private String prepareTransactionId(){
        return UUID.randomUUID().toString();
    }

    default boolean able(Class<T> clazz, Class<?> target){
        return target.isInstance(clazz);
    }

}
