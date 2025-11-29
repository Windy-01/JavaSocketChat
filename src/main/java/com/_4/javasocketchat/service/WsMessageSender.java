package com._4.javasocketchat.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com._4.javasocketchat.dto.ChatMessage;

@Service
public class WsMessageSender {
    private final SimpMessagingTemplate messagingTemplate;

    public WsMessageSender(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendToUser(ChatMessage chatMessage){
        messagingTemplate.convertAndSendToUser(
            chatMessage.getReceiver(),
            "/queue/chat",
            chatMessage
        );
    }

    public void sendToGroup(ChatMessage chatMessage){

    }
}
