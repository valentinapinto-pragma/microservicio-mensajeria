package com.pragma.mensajeria.application.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageRequestDtoTest {

    @Test
    @DisplayName("Should create MessageRequestDto using all-args constructor and getters")
    void shouldCreateWithAllArgsConstructor() {
        MessageRequestDto dto = new MessageRequestDto("+573001234567", "Test message");

        assertEquals("+573001234567", dto.getDestination());
        assertEquals("Test message", dto.getMessage());
    }

    @Test
    @DisplayName("Should create MessageRequestDto using no-args constructor and setters")
    void shouldCreateWithNoArgsConstructorAndSetters() {
        MessageRequestDto dto = new MessageRequestDto();
        dto.setDestination("+573009876543");
        dto.setMessage("Another message");

        assertEquals("+573009876543", dto.getDestination());
        assertEquals("Another message", dto.getMessage());
    }
}