package com.example.neolabs.util;

import com.example.neolabs.dto.websocket.AppStatusMessage;
import com.example.neolabs.dto.websocket.AppStatusMessageResponse;
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

    public AppStatusMessageResponse convertAppStatusIncomingMessage(AppStatusMessage appStatusMessage){
        applicationService.updateApplicationStatus(appStatusMessage.getApplicationId(),
                appStatusMessage.getNewStatus());
        return AppStatusMessageResponse.builder()
                .applicationId(appStatusMessage.getApplicationId())
                .newStatus(appStatusMessage.getNewStatus())
                .oldStatus(appStatusMessage.getOldStatus())
                .build();
    }
}
