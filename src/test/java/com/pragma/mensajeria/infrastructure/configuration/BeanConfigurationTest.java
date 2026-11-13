package com.pragma.mensajeria.infrastructure.configuration;

import com.pragma.mensajeria.domain.repositoryPort.MessageSenderPort;
import com.pragma.mensajeria.domain.servicePort.MessageServicePort;
import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BeanConfigurationTest {

    @Test
    @DisplayName("Should instantiate MessageServicePort bean")
    void shouldCreateMessageServicePortBean() {
        BeanConfiguration config = new BeanConfiguration();
        MessageSenderPort senderPort = mock(MessageSenderPort.class);

        MessageServicePort servicePort = config.messageServicePort(senderPort);

        assertNotNull(servicePort);
    }

    @Test
    @DisplayName("Should instantiate OpenAPI bean")
    void shouldCreateOpenApiBean() {
        BeanConfiguration config = new BeanConfiguration();
        OpenAPI openAPI = config.customOpenAPI();

        assertNotNull(openAPI);
        assertEquals("Microservicio Mensajería API", openAPI.getInfo().getTitle());
    }
}