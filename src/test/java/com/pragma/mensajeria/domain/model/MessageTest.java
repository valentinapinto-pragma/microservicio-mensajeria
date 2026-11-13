package com.pragma.mensajeria.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    @DisplayName("Should test Message constructors, getters and setters")
    void shouldTestMessageProperties() {
        Message message = new Message("+573001234567", "Hello");
        assertEquals("+573001234567", message.getDestination());
        assertEquals("Hello", message.getText());

        Message empty = new Message();
        empty.setDestination("+573009999999");
        empty.setText("New text");
        assertEquals("+573009999999", empty.getDestination());
        assertEquals("New text", empty.getText());
    }
}