package com.pragma.mensajeria.infrastructure.configuration;

import com.pragma.mensajeria.domain.repositoryPort.MessageSenderPort;
import com.pragma.mensajeria.domain.servicePort.MessageServicePort;
import com.pragma.mensajeria.domain.useCase.MessageUseCase;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public MessageServicePort messageServicePort(MessageSenderPort messageSenderPort) {
        return new MessageUseCase(messageSenderPort);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio Mensajería API")
                        .version("1.0.0")
                        .description("API REST para el envío de notificaciones y mensajes")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}