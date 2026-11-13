package com.pragma.mensajeria.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.mensajeria.application.dto.request.MessageRequestDto;
import com.pragma.mensajeria.application.handler.MessageHandler;
import com.pragma.mensajeria.domain.exception.InvalidMessageException;
import com.pragma.mensajeria.infrastructure.exceptionhandler.ControllerAdvisor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {MessageController.class, ControllerAdvisor.class})
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MessageHandler messageHandler;

    @Test
    @DisplayName("Should return 200 OK when message request is valid")
    void shouldReturnOkWhenMessageRequestIsValid() throws Exception {
        MessageRequestDto requestDto = new MessageRequestDto("+573001234567", "¡Tu pedido está listo!");

        doNothing().when(messageHandler).sendMessage(any(MessageRequestDto.class));

        mockMvc.perform(post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        verify(messageHandler, times(1)).sendMessage(any(MessageRequestDto.class));
    }

    @Test
    @DisplayName("Should return 400 Bad Request when validation fails")
    void shouldReturnBadRequestWhenValidationFails() throws Exception {
        MessageRequestDto invalidDto = new MessageRequestDto("", "");

        mockMvc.perform(post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());

        verify(messageHandler, never()).sendMessage(any());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when handler throws InvalidMessageException")
    void shouldReturnBadRequestWhenInvalidMessageException() throws Exception {
        MessageRequestDto requestDto = new MessageRequestDto("+573001234567", "Hello");

        doThrow(new InvalidMessageException("Invalid message data"))
                .when(messageHandler).sendMessage(any(MessageRequestDto.class));

        mockMvc.perform(post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }
}