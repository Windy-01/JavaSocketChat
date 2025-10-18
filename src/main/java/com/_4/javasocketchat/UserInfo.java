package com._4.javasocketchat;

public class UserInfo {
    private String userid;
    private String password;
    //此处需要数据库依赖

    public String getUserid() { return userid; }
    public void setUserid(String userid) { this.userid = userid; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserInfo() { this.userid = "" ; this.password = ""; }

}