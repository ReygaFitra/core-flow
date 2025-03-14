package com.reyga.dev.config;

import com.reyga.dev.utils.CommonLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    @Bean
    public CommonLogger logger() {
        return new CommonLogger();
    }
}
