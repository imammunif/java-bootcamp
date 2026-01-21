package com.dansmultipro.ams.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EMAIL_EX_CI = "email.notification.exchange.ci";
    public static final String EMAIL_QUEUE_CI = "email.notification.queue.ci";
    public static final String EMAIL_KEY_CI = "email.notification.key.ci";

    public static final String EMAIL_EX_CO = "email.notification.exchange.co";
    public static final String EMAIL_QUEUE_CO = "email.notification.queue.co";
    public static final String EMAIL_KEY_CO = "email.notification.key.co";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange emailExchangeCi() {
        return new DirectExchange(EMAIL_EX_CI);
    }

    @Bean
    public DirectExchange emailExchangeCo() {
        return new DirectExchange(EMAIL_EX_CO);
    }

    @Bean
    public Queue emailQueueCi() {
        return QueueBuilder.durable(EMAIL_QUEUE_CI).build();
    }

    @Bean
    public Queue emailQueueCo() {
        return QueueBuilder.durable(EMAIL_QUEUE_CO).build();
    }

    @Bean
    public Binding categoryBindingCi() {
        return BindingBuilder.bind(emailQueueCi())
                .to(emailExchangeCi())
                .with(EMAIL_KEY_CI);
    }

    @Bean
    public Binding categoryBindingCo() {
        return BindingBuilder.bind(emailQueueCo())
                .to(emailExchangeCo())
                .with(EMAIL_KEY_CO);
    }
}