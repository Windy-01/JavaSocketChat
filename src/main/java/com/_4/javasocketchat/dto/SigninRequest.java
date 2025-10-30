package com._4.javasocketchat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SigninRequest{
    @JsonProperty("username")
    private String userAccount;
    @JsonProperty("password")
    private String userPassword;

    public SigninRequest() {
        this.userAccount = "";
        this.userPassword = "";
    }
    public String getUserAccount() { return userAccount; }
    public String getUserPassword() { return userPassword; }
    public void setUserAccount(String userAccount) { this.userAccount = userAccount; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
}