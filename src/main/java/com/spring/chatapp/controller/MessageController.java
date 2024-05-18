package com.spring.chatapp.controller;

import com.spring.chatapp.models.Message;
import com.spring.chatapp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public Message postMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @GetMapping("/sender/{sender}")
    public List<Message> getMessageBySenderId(@PathVariable String sender) {
        return messageService.getMessageBySender(sender);
    }
}
