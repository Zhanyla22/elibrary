package com.example.neolabs.util;

import com.example.neolabs.dto.websocket.AppStatusIncomingMessage;
import com.example.neolabs.dto.websocket.AppStatusOutgoingMessage;
import com.example.neolabs.service.impl.ApplicationServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocketUtil {

    final ApplicationServiceImpl applicationService;

    public AppStatusOutgoingMessage convertAppStatusIncomingMessage(AppStatusIncomingMessage appStatusIncomingMessage){
        applicationService.updateApplicationStatus(appStatusIncomingMessage.getApplicationId(),
                appStatusIncomingMessage.getNewStatus());
        return AppStatusOutgoingMessage.builder()
                .applicationId(appStatusIncomingMessage.getApplicationId())
                .newStatus(appStatusIncomingMessage.getNewStatus())
                .oldStatus(appStatusIncomingMessage.getOldStatus())
                .build();
    }
}
