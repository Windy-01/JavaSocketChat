package com._4.javasocketchat.controller;

import com._4.javasocketchat.dto.*;
import com._4.javasocketchat.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5500")
public class ApiResponse {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    public ApiResponse(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }
    //此处逻辑有误(已更新)
    @PostMapping("/signin")
    public ResponseEntity<?> signinRequest(@RequestBody SigninRequest signinRequest){
        try{
            userService.signin(signinRequest);
            //System.out.println("User signed in successfully.");
        } catch (Exception e){
            //System.out.println("Signin failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtTokenService.createToken(signinRequest.getUserAccount());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
    //此处逻辑有误(已更新)
    @PostMapping("/signup")
    public ResponseEntity<?> signupRequest(@RequestBody SignupRequest signupRequest){
        try {
            userService.signup(signupRequest);
            //System.out.println("User signed up successfully.");
        } catch (Exception e) {
            //System.out.println("Signup failed: " + e.getMessage());
            //这里需要细化返回码处理
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        String token = jwtTokenService.createToken(signupRequest.getUserAccount());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profile/set")
    public ResponseEntity<?> setProfile(@RequestBody Profile profile){
        return ResponseEntity.ok(null);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> homeRequest(){
        return ResponseEntity.ok(null);
    }

    @GetMapping("/profile/get/{uid}")
    public ResponseEntity<?> visRequest(){
        //return ResponseEntity.ok(null);
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/friendlists")
    public ResponseEntity<?> listsRequest(){
        Map<String, List<?>> Lists = new HashMap<>();
        List<UserProfile> friends = new ArrayList<>();
        List<GroupProfile> groups = new ArrayList<>();
        Lists.put("friends", friends);
        Lists.put("groups", groups);

        UserProfile user1 = new UserProfile();
        user1.setId(114514);
        user1.setName("114514");
        user1.setLastMessage("Hello there!");
        friends.add(user1);
        UserProfile user2 = new UserProfile();
        user2.setId(798456);
        user2.setName("798456");
        user2.setLastMessage("Hello there!");
        friends.add(user2);
        GroupProfile group1 = new GroupProfile();
        group1.setId(123456);
        group1.setName("TestGroup");
        group1.setLastMessage("Welcome to Group A!");
        groups.add(group1);

        return ResponseEntity.ok(Lists);
    }
}
