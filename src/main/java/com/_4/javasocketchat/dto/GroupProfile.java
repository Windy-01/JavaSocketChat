package com._4.javasocketchat.dto;

public class GroupProfile extends Profile {
    protected int memberCount;
    protected String lastMessageSender;

    public void setMemberCount(int memberCount) { this.memberCount = memberCount; }
    public void setLastMessageSender(String lastMessageSender) { this.lastMessageSender = lastMessageSender; }
    public int getMemberCount() { return memberCount; }
    public String getLastMessageSender() { return lastMessageSender; }
}
