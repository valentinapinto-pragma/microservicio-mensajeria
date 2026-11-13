package com.pragma.mensajeria.infrastructure.exceptionhandler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseDtoTest {

    @Test
    @DisplayName("Should test ErrorResponseDto properties and constructors")
    void shouldTestErrorResponseDto() {
        ErrorResponseDto dto = new ErrorResponseDto("Error message");
        assertEquals("Error message", dto.getMessage());

        ErrorResponseDto empty = new ErrorResponseDto();
        empty.setMessage("Updated error");
        assertEquals("Updated error", empty.getMessage());
    }
}