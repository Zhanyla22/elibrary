package com.example.neolabs.config.websocket;

import com.example.neolabs.security.jwt.JWTService;
import com.example.neolabs.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketAuthenticationInterceptor implements ChannelInterceptor {
    private final JWTService jwtService;
    private final UserServiceImpl userService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final var accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        final var cmd = accessor.getCommand();
        String jwt = null;
        if (StompCommand.CONNECT == cmd || StompCommand.SEND == cmd) {
            final var requestTokenHeader = accessor.getFirstNativeHeader("Authorization");
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
                jwt = requestTokenHeader.substring(7);
            }
            if (!jwtService.isTokenValid(jwt, userService.loadUserByUsername(jwtService.extractUsername(jwt)))){
                return null;
            }
        }
        return message;
    }

}