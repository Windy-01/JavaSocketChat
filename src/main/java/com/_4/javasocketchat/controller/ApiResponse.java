package com._4.javasocketchat.controller;

import com._4.javasocketchat.dto.*;
import com._4.javasocketchat.service.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5500")
public class ApiResponse {
    private final UserService userService;

    public ApiResponse(UserService userService) {
        this.userService = userService;
    }
    //此处逻辑有误
    @PostMapping("/signin")
    public ResponseEntity<?> signinRequest(@RequestBody SigninRequest signinRequest){
        try{
            userService.signin(signinRequest);
            System.out.println("User signed in successfully.");
        } catch (Exception e){
            System.out.println("Signin failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //System.out.println("signinInfo:\n"+signinRequest.getUserAccount()+"\n"+signinRequest.getUserPassword());
        //这里应该是Token，但是Token还没写，暂时顶替一下
        return ResponseEntity.ok("token");
    }
    //此处逻辑有误
    @PostMapping("/signup")
    public ResponseEntity<?> signupRequest(@RequestBody SignupRequest signupRequest){
        try {
            System.out.println("\nsignupInfo:\n"+signupRequest.getUserName()+"\n"+signupRequest.getUserAccount()+"\n"+signupRequest.getUserPassword()+"\n");
            userService.signup(signupRequest);
        } catch (Exception e) {
            System.out.println("Signup failed: " + e.getMessage());
            //这里需要细化返回码处理
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        //这里应该是Token，但是Token还没写，暂时顶替一下
        return ResponseEntity.ok("token");
    }
}
