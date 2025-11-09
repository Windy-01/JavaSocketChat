package com._4.javasocketchat.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class JwtTokenService {
    private final Key secretKey;
    //private final Key publicKey;
    private final Key privateKey;

    public JwtTokenService(Key privateKey, Key secretKey){
        this.secretKey = secretKey;
        this.privateKey = privateKey;
        //this.publicKey = publicKey;
    }

    public String createToken(String userAccount){
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + 1000*60*60*20);
        String jwt =  Jwts.builder()
                        .setHeaderParam("typ", "JWT")
                        .setIssuer("Server")
                        .setAudience("ChatServer")
                        .setSubject(userAccount)
                        .setIssuedAt(iat)
                        .setExpiration(exp)
                        //.setId("zxcvb")
                        .claim("role","user")
                        .signWith(secretKey)
                        .compact();
        return jwt;
    }

    public Boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
            return true;
        }catch(Exception e){
            //e.printStackTrace();
            return false;
        }
    }
}
