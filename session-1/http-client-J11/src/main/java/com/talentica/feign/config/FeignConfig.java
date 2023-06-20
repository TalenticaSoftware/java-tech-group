package com.talentica.feign.config;

import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.codec.ErrorDecoder;

public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}