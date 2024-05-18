package com.spring.chatapp.websocket;

import com.spring.chatapp.models.Message;
import com.spring.chatapp.models.User;
import com.spring.chatapp.services.MessageService;
import com.spring.chatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class WebSocketController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        try{
            Thread.sleep(500);
            User sender = userService.findUserByUsername(message.getSender());
            message.setSender(sender.getUsername());
            message.setTimestamp(LocalDateTime.now());
            messageService.saveMessage(message);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return message;
    }
}
