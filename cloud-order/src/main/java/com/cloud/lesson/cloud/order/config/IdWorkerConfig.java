package com.cloud.lesson.cloud.order.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class IdWorkerConfig {
    @Bean
    public Long idWorker(){
        return Long.parseLong("123");
    }
}
