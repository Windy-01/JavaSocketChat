package com._4.javasocketchat.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

import com._4.javasocketchat.dto.ChatMessage;
import com._4.javasocketchat.service.WsMessageSender;

@Controller
public class WSChat {
    private final WsMessageSender sender;

    public WSChat(WsMessageSender sender) {
        this.sender = sender;
    }

    @MessageMapping("/private")
    public void Test(ChatMessage chatMessage, Principal principal) {
        //保存到数据库

        System.out.println("保存聊天记录: " + principal.getName() + ":" + chatMessage.getContent());
        System.out.println("内容: " + chatMessage.getSender()+":" + chatMessage.getContent() + "->" + chatMessage.getType() + "." + chatMessage.getReceiver());
        //转发给用户
        if(chatMessage.getType().equals("friend")) {
            sender.sendToUser(chatMessage);
        }
        else if(chatMessage.getType().equals("group")) {
            sender.sendToGroup(chatMessage);
        }
        //传回给发送者确认信息
        // messagingTemplate.convertAndSendToUser(
        //     chatMessage.getSender(),
        //     "/queue/chat",
        //     chatMessage
        // );
    }
}
