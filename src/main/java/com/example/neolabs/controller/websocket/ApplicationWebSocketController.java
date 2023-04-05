package com.example.neolabs.controller.websocket;

import com.example.neolabs.dto.websocket.AppStatusIncomingMessage;
import com.example.neolabs.dto.websocket.AppStatusOutgoingMessage;
import com.example.neolabs.service.impl.ApplicationServiceImpl;
import com.example.neolabs.util.SocketUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class ApplicationWebSocketController {

    private final SocketUtil socketUtil;

    @MessageMapping("/application-status")
    @SendTo("/topic/application-status")
    public AppStatusOutgoingMessage incomingMessageResponse(AppStatusIncomingMessage appStatusIncomingMessage){
        return socketUtil.convertAppStatusIncomingMessage(appStatusIncomingMessage);
    }
}
