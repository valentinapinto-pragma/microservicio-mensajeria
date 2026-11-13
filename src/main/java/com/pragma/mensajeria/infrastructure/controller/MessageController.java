package com.pragma.mensajeria.infrastructure.controller;

import com.pragma.mensajeria.application.dto.request.MessageRequestDto;
import com.pragma.mensajeria.application.handler.MessageHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
@Tag(name = "Messages", description = "Endpoints for sending notification messages")
public class MessageController {

    private final MessageHandler messageHandler;

    @Operation(summary = "Send a notification message", description = "Dispatches a message to a destination phone or recipient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message dispatched successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload or empty fields", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody MessageRequestDto messageRequestDto) {
        messageHandler.sendMessage(messageRequestDto);
        return ResponseEntity.ok().build();
    }
}