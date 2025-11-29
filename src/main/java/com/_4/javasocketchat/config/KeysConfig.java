package com._4.javasocketchat.config;

import java.security.Key;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.jsonwebtoken.security.Keys;

@Configuration
public class KeysConfig{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public Key publicKey() throws Exception {
    //     String publicKey;
    //     try(InputStream is = new ClassPathResource("/keys/cert.pem").getInputStream()){
    //         publicKey = new String(is.readAllBytes(), StandardCharsets.UTF_8);
    //     }
    //     publicKey = publicKey.replace("-----BEGIN CERTIFICATE-----", "")
    //                          .replace("-----END CERTIFICATE-----", "")
    //                          .replaceAll("\\s", "")
    //                          .substring(1);
    //     byte[] keyBytes = Base64.getDecoder().decode(publicKey);
    //     PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    //     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    //     return keyFactory.generatePrivate(keySpec);
    // }
    
    // 私钥部分已完工，公钥部分还存在问题
    // @Bean
    // public Key privateKey() throws Exception {
    //     String privateKey;
    //     try(InputStream is = new ClassPathResource("/keys/private.key").getInputStream()){
    //         privateKey = new String(is.readAllBytes(), StandardCharsets.UTF_8);
    //     }
    //     privateKey = privateKey.replace("-----BEGIN RSA PRIVATE KEY-----", "")
    //                            .replace("-----END RSA PRIVATE KEY-----", "")
    //                            .replaceAll("\\s", "")
    //                            .substring(1);
    //     byte[] keyBytes = Base64.getDecoder().decode(privateKey);
    //     PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    //     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    //     return keyFactory.generatePrivate(keySpec);
    // }

    @Bean
    public Key secretKey(){
        String secret = "a-string-secret-at-least-256-bits-long";
        byte[] keyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
