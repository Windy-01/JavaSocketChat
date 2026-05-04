package com._4.javasocketchat.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

import com._4.javasocketchat.service.WsMessageSender;

import lombok.extern.slf4j.Slf4j;

import com._4.javasocketchat.dto.WsMessage;

@Slf4j
@Controller
public class WSChat {
    private final WsMessageSender sender;

    public WSChat(WsMessageSender sender) {
        this.sender = sender;
    }

    @MessageMapping("/private")
    public void WsPrivate(WsMessage message, Principal principal) {
        // 保存到数据库
        log.info("内容: " + message.getSender_id() + ":" + message.getContents() + "->" + message.getType() + "." + message.getReceiver_id());
        // 转发给用户
        sender.sendToUser(message);
    }

    // 以下的ChatMessage被重命名为OldMessage
    // @MessageMapping("/private")
    // public void Test(ChatMessage chatMessage, Principal principal) {
    //     //保存到数据库
    //     log.info("保存聊天记录: " + principal.getName() + ":" + chatMessage.getContent() + "\n" +
    //              "内容: " + chatMessage.getSender()+":" + chatMessage.getContent() + "->" + chatMessage.getType() + "." + chatMessage.getReceiver());
    //     //转发给用户
    //     sender.sendToUser(chatMessage);
    //     /* 传回给发送者确认信息，只需要传回消息序列号即可
    //     传回消息序列号
    //     */
    // }
}
