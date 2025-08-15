package com.ayushrawat.chat.config;

import com.ayushrawat.chat.payload.event.UserRegisteredEvent;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitMQConfig {

  @Value("${auth.user.rmq.exchange}")
  private String userExchange;

  @Value("${auth.user.rmq.keys.registered}")
  private String userRegisteredRegisterKey;

  @Value("${auth.user.rmq.queue}")
  private String userRegisterQueue;

  @Bean
  public Queue queue() {
    return new Queue(userRegisterQueue, false);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(userExchange);
  }

  @Bean
  public Binding registeredUserBinding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(userRegisteredRegisterKey);
  }

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    // listener expects service to have 'com.ayushrawat.auth.payload.event.UserRegisteredEvent'
    // better to create a separate payload library once we have non maintainable payloads, just one right now
    Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
    DefaultClassMapper classMapper = new DefaultClassMapper();
    classMapper.setTrustedPackages("*");
    Map<String, Class<?>> idClassMapping = Map.of(
      "com.ayushrawat.auth.payload.event.UserRegisteredEvent",
      UserRegisteredEvent.class
    );
    classMapper.setIdClassMapping(idClassMapping);
    converter.setClassMapper(classMapper);
    return converter;
  }

}
