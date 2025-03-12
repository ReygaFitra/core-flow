package com.reyga.dev.config;

import com.reyga.dev.config.properties.CustomMailConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Import(CustomMailConfigProperties.class)
public class MailConfig {

    @Bean
    public JavaMailSender mailSender(CustomMailConfigProperties properties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getHost());
        mailSender.setPort(properties.getPort());
        mailSender.setUsername(properties.getUsername());
        mailSender.setPassword(properties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.connectiontimeout", properties.getConnectTimeout());
        props.put("mail.smtp.timeout", properties.getTimeout());
        props.put("mail.smtp.writetimeout", properties.getWriteTimeout());

        return mailSender;
    }

}
