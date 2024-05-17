package com.spring.chatapp.websocket;

import com.spring.chatapp.models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WebSocketController {

    @MessageMapping("/message")  // This will be prefixed with "/app" due to config, i.e., "/app/message"
    @SendTo("/topic/messages")   // Defines the topic on which the messages will be published.
    public OutputMessage sendMessage(Message message) {
        return new OutputMessage(
                message.getSender().getUsername(),
                message.getMessage(),
                message.getTimestamp()
        );
    }

    /*@MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public OutputMessage sendMessage(Message message) {
        return new OutputMessage(message.getSender(), message.getContent(), new Date());
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public OutputMessage addUser(Message message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return new OutputMessage(message.getSender(), "joined", new Date());
    }*/
}
