package com.example.neolabs.controller.websocket;

import com.example.neolabs.dto.websocket.IncomingMessage;
import com.example.neolabs.dto.websocket.OutgoingMessage;
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
    public OutgoingMessage incomingMessageResponse(IncomingMessage incomingMessage){
        return socketUtil.convertIncomingMessage(incomingMessage);
    }
    // TODO: 05.03.2023 topic for every dragged application card?
}
