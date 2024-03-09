package com.curso.dcortes.AccountsService.infrastructure.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue queue() {
        return new Queue("accounts", false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("users");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("register");
    }
}
