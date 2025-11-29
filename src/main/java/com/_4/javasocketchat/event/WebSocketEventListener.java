package com._4.javasocketchat.event;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void WsConnListener(SessionConnectedEvent event) {
        System.out.println("WebSocket 已连接: ");
        //连接处理
        
    }

    @EventListener
    public void WsDisconnListener(SessionDisconnectEvent event) {
        System.out.println("WebSocket 已断开: ");
        //断开处理
    }
}
