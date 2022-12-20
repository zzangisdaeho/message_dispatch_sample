package co.coinvestor.notification_dispatcher.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitConfig {

    public static final String EXCHANGE_NAME = "ns.exchange-topic.dispatch.v0";
    public static final String QUEUE_NAME = "ns.queue.dispatch.v0";
    public static final String ROUTING_KEY = "ns.dispatch.cmd.v0";


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    public Binding binding (Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter(null));
//        return rabbitTemplate;
//    }
//
//    @Bean
//    public MessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper){
//        return new Jackson2JsonMessageConverter(objectMapper);
//    }
}
