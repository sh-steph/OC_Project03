package com.openclassrooms.occhatop.services;

import com.openclassrooms.occhatop.models.message.Message;
import com.openclassrooms.occhatop.repositories.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void addNewMessage(Message message) {
        messageRepository.save(message);
    }

}
