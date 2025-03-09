package com.reyga.dev.config;

import com.reyga.dev.config.properties.CustomWebClientConfigProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@Import(CustomWebClientConfigProperties.class)
public class WebClientConfig {

    @Bean
    public WebClient webClient(CustomWebClientConfigProperties properties) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
                .responseTimeout(Duration.ofSeconds(properties.getResponseTimeout()))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout(), TimeUnit.SECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(properties.getWriteTimeout(), TimeUnit.SECONDS))
                );

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
