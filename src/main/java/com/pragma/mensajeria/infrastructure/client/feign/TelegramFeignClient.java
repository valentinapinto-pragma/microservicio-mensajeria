package com.pragma.mensajeria.infrastructure.client.feign;

import com.pragma.mensajeria.infrastructure.client.dto.TelegramMessageRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegramFeignClient", url = "${telegram.api.url}")
public interface TelegramFeignClient {

    @PostMapping("/bot{botToken}/sendMessage")
    void sendMessage(
            @PathVariable("botToken") String botToken,
            @RequestBody TelegramMessageRequestDto requestDto
    );
}