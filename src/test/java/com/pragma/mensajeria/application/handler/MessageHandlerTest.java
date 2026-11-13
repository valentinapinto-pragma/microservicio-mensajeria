package com.pragma.mensajeria.application.handler;

import com.pragma.mensajeria.application.dto.request.MessageRequestDto;
import com.pragma.mensajeria.application.mapper.MessageDtoMapper;
import com.pragma.mensajeria.domain.model.Message;
import com.pragma.mensajeria.domain.servicePort.MessageServicePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageHandlerTest {

    @Mock
    private MessageServicePort messageServicePort;

    @Mock
    private MessageDtoMapper messageDtoMapper;

    @InjectMocks
    private MessageHandler messageHandler;

    @Test
    @DisplayName("Should map request DTO to domain and call message service port")
    void shouldSendMessageSuccessfully() {
        MessageRequestDto requestDto = new MessageRequestDto("+573001234567", "Hello");
        Message domainMessage = new Message("+573001234567", "Hello");

        when(messageDtoMapper.toDomain(requestDto)).thenReturn(domainMessage);

        messageHandler.sendMessage(requestDto);

        verify(messageDtoMapper, times(1)).toDomain(requestDto);
        verify(messageServicePort, times(1)).sendMessage(domainMessage);
    }
}