package com.openclassrooms.occhatop.controllers;

import com.openclassrooms.occhatop.models.message.Message;
import com.openclassrooms.occhatop.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Operation(summary = "Post message", description = "Post a new message")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public void create(@RequestBody Message message) {
        messageService.addNewMessage(message);
    }

}
