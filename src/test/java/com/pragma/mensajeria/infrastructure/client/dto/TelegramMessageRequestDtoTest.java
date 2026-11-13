package com.pragma.mensajeria.infrastructure.client.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelegramMessageRequestDtoTest {

    @Test
    @DisplayName("Should test all-args and no-args constructor and getters/setters")
    void shouldTestDtoProperties() {
        TelegramMessageRequestDto dto = new TelegramMessageRequestDto("12345", "Hello");
        assertEquals("12345", dto.getChatId());
        assertEquals("Hello", dto.getText());

        TelegramMessageRequestDto empty = new TelegramMessageRequestDto();
        empty.setChatId("67890");
        empty.setText("World");
        assertEquals("67890", empty.getChatId());
        assertEquals("World", empty.getText());
    }
}