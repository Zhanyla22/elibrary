package com.example.neolabs.controller.websocket;

import com.example.neolabs.dto.websocket.AppStatusIncomingMessage;
import com.example.neolabs.dto.websocket.AppStatusOutgoingMessage;
import com.example.neolabs.service.impl.ApplicationServiceImpl;
import com.example.neolabs.util.SocketUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ApplicationWebSocketController {

    private final SocketUtil socketUtil;

    @MessageMapping("/application-status")
    @SendTo("/topic/application-status")
    public AppStatusOutgoingMessage incomingMessageResponse(AppStatusIncomingMessage appStatusIncomingMessage){
        return socketUtil.convertAppStatusIncomingMessage(appStatusIncomingMessage);
    }


    // TODO: 05.03.2023 topic for every dragged application card?
}
