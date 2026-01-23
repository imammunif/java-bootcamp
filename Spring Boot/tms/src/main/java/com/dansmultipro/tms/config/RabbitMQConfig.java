package com.dansmultipro.tms.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EMAIL_EX_TICKET = "email.notification.exchange.ticket";
    public static final String EMAIL_QUEUE_TICKET = "email.notification.queue.ticket";
    public static final String EMAIL_KEY_TICKET = "email.notification.key.ticket";

    public static final String EMAIL_EX_MESSAGE = "email.notification.exchange.message";
    public static final String EMAIL_QUEUE_MESSAGE = "email.notification.queue.message";
    public static final String EMAIL_KEY_MESSAGE = "email.notification.key.message";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange emailExchangeTicket() {
        return new DirectExchange(EMAIL_EX_TICKET);
    }

    @Bean
    public DirectExchange emailExchangeMessage() {
        return new DirectExchange(EMAIL_EX_MESSAGE);
    }

    @Bean
    public Queue emailQueueTicket() {
        return QueueBuilder.durable(EMAIL_QUEUE_TICKET).build();
    }

    @Bean
    public Queue emailQueueMessage() {
        return QueueBuilder.durable(EMAIL_QUEUE_MESSAGE).build();
    }

    @Bean
    public Binding categoryBindingTicket() {
        return BindingBuilder.bind(emailQueueTicket())
                .to(emailExchangeTicket())
                .with(EMAIL_KEY_TICKET);
    }

    @Bean
    public Binding categoryBindingMessage() {
        return BindingBuilder.bind(emailQueueMessage())
                .to(emailExchangeMessage())
                .with(EMAIL_KEY_MESSAGE);
    }
}