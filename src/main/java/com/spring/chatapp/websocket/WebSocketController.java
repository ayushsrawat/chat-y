package com.spring.chatapp.websocket;

import com.spring.chatapp.models.Message;
import com.spring.chatapp.models.User;
import com.spring.chatapp.services.MessageService;
import com.spring.chatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WebSocketController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        System.out.println("Received raw message: " + message.getMessage());
        if (message != null && message.getSender() != null && !message.getSender().isEmpty() && message.getMessage() != null && !message.getMessage().isEmpty()) {
            System.out.println("Received message: " + message.getMessage() + " from " + message.getSender());
            User sender = userService.findUserByUsername(message.getSender());
            if (sender != null) {
                message.setSender(sender.getUsername());
                message.setTimestamp(LocalDateTime.now());
                messageService.saveMessage(message);
                System.out.println("Message saved and sent: " + message.getMessage());
            } else {
                System.out.println("Sender User not found: " + message.getSender());
            }
        } else {
            System.out.println("Message content or sender is null!");
            /*System.out.println(userService.findUserByUsername(message.getSender()).getPassword());*/
        }
        return message;
    }
}
