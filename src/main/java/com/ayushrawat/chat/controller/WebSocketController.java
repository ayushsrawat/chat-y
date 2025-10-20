package com.ayushrawat.chat.controller;

import com.ayushrawat.chat.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

  private final SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/chat.private")
  public void handlePrivateMessage(@Payload Message message, Principal principal) {
    messagingTemplate.convertAndSendToUser(message.getSender(), "/queue/messages", message);
  }

  @MessageMapping("/chat.group")
  public void handleGroupMessage(@Payload Message message, Principal principal) {
    messagingTemplate.convertAndSend("/topic/group/" + message.getId(), message);
  }
}
