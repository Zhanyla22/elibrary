package com.example.neolabs.util;

import com.example.neolabs.dto.websocket.IncomingMessage;
import com.example.neolabs.dto.websocket.OutgoingMessage;
import com.example.neolabs.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocketUtil {

    final UserServiceImpl userService;

    public OutgoingMessage convertIncomingMessage(IncomingMessage incomingMessage){
        return OutgoingMessage.builder()
                .applicationId(incomingMessage.getApplicationId())
                .oldStatus(incomingMessage.getOldStatus())
                .newStatus(incomingMessage.getNewStatus())
                .userId(userService.getCurrentUserId())
                .build();
    }
}
