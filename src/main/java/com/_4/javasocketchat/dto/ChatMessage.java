package com._4.javasocketchat.dto;

import java.util.Date;

public class ChatMessage {
    private String sender;
    private String receiver;
    private long timestamp;
    private String content;
    private String type;

    public ChatMessage() {
        Date date = new Date();
        timestamp = date.getTime();
    }

    public static class builder {
        private String sender;
        private String receiver;
        private String content;
        private String type;

        public builder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public builder setReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public builder setContent(String content) {
            this.content = content;
            return this;
        }

        public builder setType(String type) {
            this.type = type;
            return this;
        }

        public ChatMessage build() {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.sender = this.sender;
            chatMessage.receiver = this.receiver;
            chatMessage.content = this.content;
            chatMessage.type = this.type;
            return chatMessage;
        }
    }

    public void setSender(String sender) { this.sender = sender; }
    public void setReceiver(String receiver) { this.receiver = receiver; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public void setContent(String content) { this.content = content; }
    public void setType(String type) { this.type = type; }

    public String getSender() { return sender; }
    public String getReceiver() { return receiver; }
    public long getTimestamp() { return timestamp; }
    public String getContent() { return content; }
    public String getType() { return type; }
}
