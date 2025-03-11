package com.reyga.dev.config;

import com.reyga.dev.config.properties.CustomMailConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@Import(CustomMailConfigProperties.class)
public class MailConfig {

    @Bean
    public JavaMailSender mailSender(CustomMailConfigProperties properties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getHost());
        mailSender.setPort(properties.getPort());

        return mailSender;
    }

    @Bean
    public SimpleMailMessage defaultSimpleMailMessage(CustomMailConfigProperties properties) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getDefaultSender());
        message.setSubject(properties.getDefaultSubject());
        message.setText(properties.getDefaultMessage());
        return message;
    }
}
