package com._4.javasocketchat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignupRequest{
    private String userName;
    @JsonProperty("username")
    private String userAccount;
    @JsonProperty("password")
    private String userPassword;

    public SignupRequest() {
        this.userName = "";
        this.userAccount = "";
        this.userPassword = "";
    }
    public String getUserName() { return userName; }
    public String getUserAccount() { return userAccount; }
    public String getUserPassword() { return userPassword; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setUserAccount(String userAccount) { this.userAccount = userAccount; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
}
