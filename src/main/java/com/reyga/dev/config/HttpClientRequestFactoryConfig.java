package com.reyga.dev.config;

import com.reyga.dev.config.properties.CustomRestClientTimeoutConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
@Import(CustomRestClientTimeoutConfigProperties.class)
public class HttpClientRequestFactoryConfig {

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(CustomRestClientTimeoutConfigProperties properties) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(properties.getConnectTimeout());
        factory.setReadTimeout(properties.getConnectReadTimeout());
        return factory;
    }
}
