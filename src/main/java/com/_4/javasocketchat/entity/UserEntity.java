package com._4.javasocketchat.entity;

public class UserEntity {
    private String userName;
    private String userAccount;
    private String hashPassword;

    public UserEntity(){}
    public UserEntity(String userName, String userAccount, String hashPassword) {
        this.userName = userName;
        this.userAccount = userAccount;
        this.hashPassword = hashPassword;
    }
    public String getUserName() { return userName; }
    public String getUserAccount() { return userAccount; }
    public String getHashPassword() { return hashPassword; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setUserAccount(String userAccount) { this.userAccount = userAccount; }
    public void setHashPassword(String hashPassword) { this.hashPassword = hashPassword; }
}
