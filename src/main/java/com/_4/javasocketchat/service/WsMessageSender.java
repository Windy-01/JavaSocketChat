package com._4.javasocketchat.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com._4.javasocketchat.dto.*;

@Service
public class WsMessageSender {
    private final SimpMessagingTemplate messagingTemplate;

    public WsMessageSender(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendToUser(WsMessage message){
        /* 获取目标群组之后需要获取群组内的所有用户，然后发送消息
        String receiver = chatMessage.getReceiver();
        Arrays.stream(groupService.getGroupMembers(receiver)).forEach(member -> {
            messagingTemplate.convertAndSendToUser(
                member,
                "/queue/chat",
                chatMessage
            );
        });
        下面为暂时测试用的内容，只适用于私聊场景
        */
        messagingTemplate.convertAndSendToUser(
            String.valueOf(message.getReceiver_id()),
            "/queue/chat",
            message
        );
    }
}
