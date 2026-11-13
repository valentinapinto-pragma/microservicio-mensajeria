package com.pragma.mensajeria.application.handler;

import com.pragma.mensajeria.application.dto.request.MessageRequestDto;
import com.pragma.mensajeria.application.mapper.MessageDtoMapper;
import com.pragma.mensajeria.domain.model.Message;
import com.pragma.mensajeria.domain.servicePort.MessageServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageHandler {

    private final MessageServicePort messageServicePort;
    private final MessageDtoMapper messageDtoMapper;

    public void sendMessage(MessageRequestDto messageRequestDto) {
        Message message = messageDtoMapper.toDomain(messageRequestDto);
        messageServicePort.sendMessage(message);
    }
}