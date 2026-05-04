package com._4.javasocketchat.component;

import org.springframework.context.event.EventListener;
// import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebSocketEventListener {

    // private final SimpMessageSendingOperations messagingTemplate;

    // public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
    //     this.messagingTemplate = messagingTemplate;
    // }

    @EventListener
    public void WsConnListener(SessionConnectedEvent event) {
        log.info("WebSocket 已连接: ");
        //连接处理
        
    }

    @EventListener
    public void WsDisconnListener(SessionDisconnectEvent event) {
        log.info("WebSocket 已断开: ");
        //断开处理
    }
}
