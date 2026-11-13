package com.pragma.mensajeria.infrastructure.adapter;

import com.pragma.mensajeria.domain.model.Message;
import com.pragma.mensajeria.infrastructure.client.dto.TelegramMessageRequestDto;
import com.pragma.mensajeria.infrastructure.client.feign.TelegramFeignClient;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TelegramMessageSenderAdapterTest {

    @Mock
    private TelegramFeignClient telegramFeignClient;

    private TelegramMessageSenderAdapter adapter;

    private final String botToken = "test-token";
    private final String defaultChatId = "1833923415";

    @BeforeEach
    void setUp() {
        adapter = new TelegramMessageSenderAdapter(telegramFeignClient, botToken, defaultChatId);
    }

    @Test
    @DisplayName("Should send message using defaultChatId and message text")
    void shouldSendMessageUsingDefaultChatId() {
        Message message = new Message("+573001234567", "Tu pedido está listo. PIN: 123456");

        doNothing().when(telegramFeignClient).sendMessage(eq(botToken), any(TelegramMessageRequestDto.class));

        adapter.sendMessage(message);

        ArgumentCaptor<TelegramMessageRequestDto> captor = ArgumentCaptor.forClass(TelegramMessageRequestDto.class);
        verify(telegramFeignClient, times(1)).sendMessage(eq(botToken), captor.capture());

        TelegramMessageRequestDto captured = captor.getValue();
        assertNotNull(captured);
        assertEquals(defaultChatId, captured.getChatId());
        assertEquals(message.getText(), captured.getText());
    }

    @Test
    @DisplayName("Should simulate notification when botToken is null or blank")
    void shouldSimulateWhenBotTokenIsMissing() {
        TelegramMessageSenderAdapter unconfiguredAdapter = new TelegramMessageSenderAdapter(telegramFeignClient, null, defaultChatId);
        assertDoesNotThrow(() -> unconfiguredAdapter.sendMessage(new Message("+573001234567", "Test")));
        verifyNoInteractions(telegramFeignClient);

        TelegramMessageSenderAdapter blankTokenAdapter = new TelegramMessageSenderAdapter(telegramFeignClient, "   ", defaultChatId);
        assertDoesNotThrow(() -> blankTokenAdapter.sendMessage(new Message("+573001234567", "Test")));
        verifyNoInteractions(telegramFeignClient);
    }

    @Test
    @DisplayName("Should handle FeignException gracefully without throwing")
    void shouldHandleExceptionGracefully() {
        Request request = Request.create(Request.HttpMethod.POST, "/sendMessage", Collections.emptyMap(), null, new RequestTemplate());
        doThrow(new FeignException.InternalServerError("Telegram API error", request, null, null))
                .when(telegramFeignClient).sendMessage(eq(botToken), any(TelegramMessageRequestDto.class));

        assertDoesNotThrow(() -> adapter.sendMessage(new Message("+573001234567", "Test error")));
        verify(telegramFeignClient, times(1)).sendMessage(eq(botToken), any(TelegramMessageRequestDto.class));
    }
}