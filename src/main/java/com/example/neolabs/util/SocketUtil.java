package com.example.neolabs.util;

import com.example.neolabs.dto.websocket.AppStatusIncomingMessage;
import com.example.neolabs.dto.websocket.AppStatusOutgoingMessage;
import com.example.neolabs.service.impl.ApplicationServiceImpl;
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
    final ApplicationServiceImpl applicationService;

    public AppStatusOutgoingMessage convertAppStatusIncomingMessage(AppStatusIncomingMessage appStatusIncomingMessage){
        applicationService.updateApplicationStatus(appStatusIncomingMessage.getApplicationId(),
                appStatusIncomingMessage.getNewStatus());
        return AppStatusOutgoingMessage.builder()
                .applicationId(appStatusIncomingMessage.getApplicationId())
                .newStatus(appStatusIncomingMessage.getNewStatus())
                .userId(null) // FIXME: 25.03.2023 
                .build();
    }
}
