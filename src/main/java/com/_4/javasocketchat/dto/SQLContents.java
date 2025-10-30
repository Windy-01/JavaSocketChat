package com._4.javasocketchat.dto;

public class SQLContents {
    public static String getHashPassword(){
        return "SELECT password_hash FROM userinfo WHERE useraccount = ?";
    }
    public static String addUser(){
        return "INSERT INTO userinfo(username,useraccount,password_hash) VALUES(?,?,?)";
    }
    public static String isExist(){
        return "SELECT COUNT(*) FROM userinfo WHERE useraccount = ?";
    }
}
