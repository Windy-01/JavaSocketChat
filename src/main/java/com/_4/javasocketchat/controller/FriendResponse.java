package com._4.javasocketchat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com._4.javasocketchat.service.FriendService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/friend")
public class FriendResponse {
    private final FriendService friendService;

    public FriendResponse(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/add")      // 目前没有设计用户同意的流程，后续需要完善
    public ResponseEntity<?> addFriendRequest(
            @RequestParam(value="uid", required = true) int uid
    ){
        try {
            friendService.addFriend(uid);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            log.error("Failed to add friend: " + e.getMessage());
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/remove")
    public ResponseEntity<?> removeFriendRequest(
            @RequestParam(value="uid", required = true) int uid
    ){
        try {
            friendService.deleteFriend(uid);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            log.error("Failed to remove friend: " + e.getMessage());
            return ResponseEntity.status(404).build();
        }
    }

}
