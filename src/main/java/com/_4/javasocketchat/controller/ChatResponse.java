package com._4.javasocketchat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatResponse {
    @GetMapping("/history")
    public void getChatHistory() {}

    // @GetMapping("/retract")
    // public String retractMessage() {
    //     return "撤回消息接口";
    // }

    

}
