package com.openclassrooms.occhatop.controllers;

import com.openclassrooms.occhatop.models.message.Message;
import com.openclassrooms.occhatop.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public void create(@RequestBody Message message) {
        messageService.addNewMessage(message);
    }

}
