package com.example.neolabs.controller.websocket;

import com.example.neolabs.dto.websocket.AppStatusMessage;
import com.example.neolabs.dto.websocket.AppStatusMessageResponse;
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
    public AppStatusMessageResponse messageResponse(AppStatusMessage appStatusMessage){
        return socketUtil.convertAppStatusIncomingMessage(appStatusMessage);
    }
}
