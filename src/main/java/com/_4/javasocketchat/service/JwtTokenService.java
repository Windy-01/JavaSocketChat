package com._4.javasocketchat.service;

import java.security.Key;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com._4.javasocketchat.mapper.UserMapper;

import io.jsonwebtoken.Jwts;

@Service
public class JwtTokenService {
    private final UserMapper userMapper;
    private final Key secretKey;
    //private final Key publicKey;
    //private final Key privateKey;

    public JwtTokenService(UserMapper userMapper, Key secretKey) {
        this.userMapper = userMapper;
        this.secretKey = secretKey;
        //this.privateKey = privateKey;
        //this.publicKey = publicKey;
    }

    public String createTokenByUsername(String userAccount){
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + 1000*60*60*20);
        int userId = userMapper.getID(userAccount);
        String jwt =  Jwts.builder()
                        .setHeaderParam("typ", "JWT")
                        .setIssuer("Server")
                        .setAudience("ChatServer")
                        .setSubject(String.valueOf(userId))
                        .setIssuedAt(iat)
                        .setExpiration(exp)
                        //.setId("zxcvb")
                        .claim("roles","user")
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

    private String getSubject(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private String getRoles(String token){
        return (String) Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles");
    }

    public Authentication authentication(String token){
        UserDetails userDetails = User.builder()
                                    .username( getSubject(token) )
                                    .password("")
                                    .roles( getRoles(token) )
                                    .build();
        return new UsernamePasswordAuthenticationToken(userDetails, token);
    }

    public String getToken(String authorization){
        if(authorization != null && authorization.startsWith("Bearer ")){
            return authorization.substring(7);
        }
        return null;
    }
}
