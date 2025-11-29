package com._4.javasocketchat.dto;

public class Profile {
    protected long id;
    protected String name;
    protected String lastMessage;

    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLastMessage(String lastMessage) { this.lastMessage = lastMessage; }
    public long getId() { return id; }
    public String getName() { return name; }
    public String getLastMessage() { return lastMessage; }
}
