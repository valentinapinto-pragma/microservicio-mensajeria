package com.pragma.mensajeria.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO representing the notification message request")
public class MessageRequestDto {

    @NotBlank(message = "Destination cannot be blank")
    @Schema(description = "Destination phone number or recipient identifier", example = "+573001234567")
    private String destination;

    @NotBlank(message = "Message text cannot be blank")
    @Schema(description = "Content of the message to be sent", example = "¡Hola! Tu pedido #1 está LISTO para reclamar. PIN: 123456")
    private String message;
}