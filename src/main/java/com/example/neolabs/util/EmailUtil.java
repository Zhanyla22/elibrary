package com.example.neolabs.util;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailUtil {

    private final JavaMailSender javaMailSender;

    private final Environment environment;

    public void send(String toEmail, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setFrom(Objects.requireNonNull(environment.getProperty("spring.mail.username")));
        javaMailSender.send(simpleMailMessage);
    }
}

