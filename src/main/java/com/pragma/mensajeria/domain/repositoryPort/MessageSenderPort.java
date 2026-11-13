package com.pragma.mensajeria.domain.repositoryPort;

import com.pragma.mensajeria.domain.model.Message;

public interface MessageSenderPort {
    void sendMessage(Message message);
}