package com.spring.chatapp.websocket;

import com.spring.chatapp.models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

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
}
