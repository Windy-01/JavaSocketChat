package com._4.javasocketchat.controller;

import com._4.javasocketchat.dto.*;
import com._4.javasocketchat.service.*;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiResponse {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    public ApiResponse(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/signin")         // 此处逻辑有误(已更新)
    public ResponseEntity<?> signinRequest(@RequestBody SigninRequest signinRequest){
        try{
            userService.signin(signinRequest);
            //log.info("User signed in successfully.");
        } catch (Exception e){
            //log.error("Signin failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtTokenService.createTokenByUsername(signinRequest.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/signup")         // 此处逻辑有误(已更新)
    public ResponseEntity<?> signupRequest(@RequestBody SignupRequest signupRequest){
        try {
            userService.signup(signupRequest);
            //log.info("User signed up successfully.");
        } catch (Exception e) {
            log.error("Signup failed: " + e.getMessage());
            //这里需要细化返回码处理
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        String token = jwtTokenService.createTokenByUsername(signupRequest.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profile/set")
    public ResponseEntity<?> profileSetter(@RequestBody Profile profile){
        return ResponseEntity.ok(null);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profileGetter(
            @RequestParam(value="uid", required = false, defaultValue = "-1") int uid
    ){
        return ResponseEntity.ok(null);
    }

    // @GetMapping("/profile/get/{uid}")
    // public ResponseEntity<?> visRequest(){
    //     //return ResponseEntity.ok(null);
    //     return ResponseEntity.status(404).build();
    // }

    @GetMapping("/friendslist")
    public ResponseEntity<?> listsRequest(
            @RequestParam(value="uid", required = true, defaultValue = "-1") int uid
    ){
        try {
            return ResponseEntity.ok(userService.getFriendsList(uid));
        } catch (Exception e) {
            log.error("Failed to get friends list: " + e.getMessage());
            return ResponseEntity.status(404).build();
        }
    }
}
