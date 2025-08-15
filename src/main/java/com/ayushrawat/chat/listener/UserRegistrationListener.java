package com.ayushrawat.chat.listener;

import com.ayushrawat.chat.entity.User;
import com.ayushrawat.chat.payload.event.UserRegisteredEvent;
import com.ayushrawat.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserRegistrationListener {

  private static final Logger logger = LoggerFactory.getLogger(UserRegistrationListener.class);

  private final UserRepository userRepository;

  @RabbitListener(queues = {"${auth.user.rmq.queue}"})
  public void registerUser(UserRegisteredEvent userEvent) {
    logger.info("Received user event: {}", userEvent.toString());
    User user = User.builder()
      .id(userEvent.getId())
      .username(userEvent.getUsername())
      .email(userEvent.getEmail())
      .role(userEvent.getUserRole())
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .deleted(false)
      .build();
    userRepository.save(user);
    // todo: implement Dead Letter Queue in case something went wrong
  }

}
