package com.talentica.feign.config;

import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.codec.ErrorDecoder;

public class ClientConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

}
