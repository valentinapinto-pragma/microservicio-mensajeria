package com.pragma.mensajeria.domain.useCase;

import com.pragma.mensajeria.domain.exception.InvalidMessageException;
import com.pragma.mensajeria.domain.model.Message;
import com.pragma.mensajeria.domain.repositoryPort.MessageSenderPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageUseCaseTest {

    @Mock
    private MessageSenderPort messageSenderPort;

    @InjectMocks
    private MessageUseCase messageUseCase;

    @Test
    @DisplayName("Should send message successfully when message is valid")
    void shouldSendMessageSuccessfully() {
        Message message = new Message("+573001234567", "Tu pedido está listo");

        assertDoesNotThrow(() -> messageUseCase.sendMessage(message));

        verify(messageSenderPort, times(1)).sendMessage(message);
    }

    @Test
    @DisplayName("Should throw InvalidMessageException when message object is null")
    void shouldThrowWhenMessageIsNull() {
        InvalidMessageException ex = assertThrows(
                InvalidMessageException.class,
                () -> messageUseCase.sendMessage(null)
        );

        assertEquals("Message cannot be null", ex.getMessage());
        verifyNoInteractions(messageSenderPort);
    }

    @Test
    @DisplayName("Should throw InvalidMessageException when destination is null or blank")
    void shouldThrowWhenDestinationIsNullOrEmpty() {
        Message nullDestination = new Message(null, "Hola");
        Message blankDestination = new Message("   ", "Hola");

        assertThrows(InvalidMessageException.class, () -> messageUseCase.sendMessage(nullDestination));
        assertThrows(InvalidMessageException.class, () -> messageUseCase.sendMessage(blankDestination));

        verifyNoInteractions(messageSenderPort);
    }

    @Test
    @DisplayName("Should throw InvalidMessageException when text is null or blank")
    void shouldThrowWhenTextIsNullOrEmpty() {
        Message nullText = new Message("+573001234567", null);
        Message blankText = new Message("+573001234567", "   ");

        assertThrows(InvalidMessageException.class, () -> messageUseCase.sendMessage(nullText));
        assertThrows(InvalidMessageException.class, () -> messageUseCase.sendMessage(blankText));

        verifyNoInteractions(messageSenderPort);
    }
}