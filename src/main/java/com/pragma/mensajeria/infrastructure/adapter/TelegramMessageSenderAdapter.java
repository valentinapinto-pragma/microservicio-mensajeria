package com.pragma.mensajeria.infrastructure.adapter;

import com.pragma.mensajeria.domain.model.Message;
import com.pragma.mensajeria.domain.repositoryPort.MessageSenderPort;
import com.pragma.mensajeria.infrastructure.client.dto.TelegramMessageRequestDto;
import com.pragma.mensajeria.infrastructure.client.feign.TelegramFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TelegramMessageSenderAdapter implements MessageSenderPort {

    private final TelegramFeignClient telegramFeignClient;
    private final String botToken;
    private final String defaultChatId;

    public TelegramMessageSenderAdapter(
            TelegramFeignClient telegramFeignClient,
            @Value("${telegram.bot.token:}") String botToken,
            @Value("${telegram.bot.chat-id:}") String defaultChatId
    ) {
        this.telegramFeignClient = telegramFeignClient;
        this.botToken = botToken;
        this.defaultChatId = defaultChatId;
    }

    @Override
    public void sendMessage(Message message) {
        if (botToken == null || botToken.isBlank()) {
            log.warn("Telegram bot token no configurado. Notificación simulada a {}: {}",
                    message.getDestination(), message.getText());
            return;
        }

        TelegramMessageRequestDto requestDto = new TelegramMessageRequestDto(defaultChatId, message.getText());

        try {
            telegramFeignClient.sendMessage(botToken, requestDto);
            log.info("Notificación enviada exitosamente por Telegram a chatId: {} (destinatario original: {})",
                    defaultChatId, message.getDestination());
        } catch (Exception e) {
            log.error("Error al enviar notificación por Telegram a chatId {}: {}", defaultChatId, e.getMessage());
        }
    }
}