package com.pragma.mensajeria.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelegramMessageRequestDto {

    @JsonProperty("chat_id")
    private String chatId;

    @JsonProperty("text")
    private String text;
}