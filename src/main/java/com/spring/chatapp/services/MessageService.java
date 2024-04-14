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
        return messageRepository.save(message);
    }

    public List<Message> getMessageBySenderId(Long senderId) {
        return messageRepository.findBySenderId(senderId);
    }
}
