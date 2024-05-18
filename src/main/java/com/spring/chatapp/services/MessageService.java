package com.spring.chatapp.services;

import com.spring.chatapp.models.Message;
import com.spring.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        System.out.println(message);
        return messageRepository.save(message);
    }

    public List<Message> getMessageBySender(String sender) {
        return messageRepository.findBySender(sender);
    }
}
