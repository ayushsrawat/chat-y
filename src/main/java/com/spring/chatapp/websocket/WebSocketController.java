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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class WebSocketController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message sendMessage(@RequestBody Message message) {
        /*try{
            if (message != null
                    && message.getSender() != null
                    && !message.getSender().isEmpty()
                    && message.getMessage() != null
                    && !message.getMessage().isEmpty()) {

                Thread.sleep(500);
                User sender = userService.findUserByUsername(message.getSender());
                if(sender != null) {
                    System.out.println("Sender Username : " + sender.getUsername());
                    message.setSender(sender.getUsername());
                    message.setTimestamp(LocalDateTime.now());
                    messageService.saveMessage(message);
                }else {
                    System.out.println("Sender User not found: " + message.getSender());
                }
            }else{
                System.out.println("Message is null!");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        return message;
    }
}
