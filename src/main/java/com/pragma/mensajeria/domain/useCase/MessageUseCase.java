package com.pragma.mensajeria.domain.useCase;

import com.pragma.mensajeria.domain.exception.InvalidMessageException;
import com.pragma.mensajeria.domain.model.Message;
import com.pragma.mensajeria.domain.repositoryPort.MessageSenderPort;
import com.pragma.mensajeria.domain.servicePort.MessageServicePort;

public class MessageUseCase implements MessageServicePort {

    private static final String DESTINATION_REQUIRED_MESSAGE = "Destination cannot be null or empty";
    private static final String TEXT_REQUIRED_MESSAGE = "Message text cannot be null or empty";

    private final MessageSenderPort messageSenderPort;

    public MessageUseCase(MessageSenderPort messageSenderPort) {
        this.messageSenderPort = messageSenderPort;
    }

    @Override
    public void sendMessage(Message message) {
        validateMessageNotNull(message);
        validateDestination(message.getDestination());
        validateText(message.getText());

        messageSenderPort.sendMessage(message);
    }

    private void validateMessageNotNull(Message message) {
        if (message == null) {
            throw new InvalidMessageException("Message cannot be null");
        }
    }

    private void validateDestination(String destination) {
        if (destination == null || destination.isBlank()) {
            throw new InvalidMessageException(DESTINATION_REQUIRED_MESSAGE);
        }
    }

    private void validateText(String text) {
        if (text == null || text.isBlank()) {
            throw new InvalidMessageException(TEXT_REQUIRED_MESSAGE);
        }
    }
}