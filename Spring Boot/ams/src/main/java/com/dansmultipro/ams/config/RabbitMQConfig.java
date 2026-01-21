package com.dansmultipro.ams.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EMAIL_EX = "email.notification.exchange";
    public static final String EMAIL_QUEUE = "email.notification.queue";
    public static final String EMAIL_KEY = "email.notification.key";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(EMAIL_EX);
    }

    @Bean
    public Queue emailQueue() {
        return QueueBuilder.durable(EMAIL_QUEUE).build();
    }

    @Bean
    public Binding categoryBinding() {
        return BindingBuilder.bind(emailQueue())
                .to(emailExchange())
                .with(EMAIL_KEY);
    }
}