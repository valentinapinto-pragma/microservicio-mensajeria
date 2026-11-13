package com.pragma.mensajeria.domain.servicePort;

import com.pragma.mensajeria.domain.model.Message;

public interface MessageServicePort {
    void sendMessage(Message message);
}