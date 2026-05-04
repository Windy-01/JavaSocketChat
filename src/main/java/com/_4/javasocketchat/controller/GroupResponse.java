package com._4.javasocketchat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com._4.javasocketchat.service.GroupService;

@RestController
@RequestMapping("/api/group")
public class GroupResponse {
    private final GroupService groupService;

    public GroupResponse(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/create")
    public ResponseEntity<?> createGroupRequest(
            @RequestParam(value="groupName", required = true) String groupName
    ){
        try {
            groupService.CreatGroup(groupName);
            return ResponseEntity.ok("Group created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create group: " + e.getMessage());
        }
    }

    @GetMapping("/join")
    public ResponseEntity<?> joinGroupRequest(
            @RequestParam(value="groupId", required = true) int groupId
    ){
        try {
            groupService.JoinGroup(groupId);
            return ResponseEntity.ok("Joined group successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to join group: " + e.getMessage());
        }
    }

    @GetMapping("/setAdmin")
    public ResponseEntity<?> setAdminRequest(
            @RequestParam(value="groupId", required = true) int groupId,
            @RequestParam(value="memberId", required = true) int memberId
    ){
        try {
            groupService.SetGroupAdmin(groupId, memberId);
            return ResponseEntity.ok("Set admin successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to set admin: " + e.getMessage());
        }
    }

    @GetMapping("/setMember")
    public ResponseEntity<?> setMemberRequest(
            @RequestParam(value="groupId", required = true) int groupId,
            @RequestParam(value="memberId", required = true) int memberId
    ){
        try {
            groupService.SetGroupMember(groupId, memberId);
            return ResponseEntity.ok("Set member successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to set member: " + e.getMessage());
        }
    }

    @GetMapping("/removeMember")
    public ResponseEntity<?> removeMemberRequest(
            @RequestParam(value="groupId", required = true) int groupId,
            @RequestParam(value="memberId", required = true) int memberId
    ){
        try {
            groupService.RemoveGroupMember(groupId, memberId);
            return ResponseEntity.ok("Removed member successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to remove member: " + e.getMessage());
        }
    }

    @GetMapping("/exitGroup")
    public ResponseEntity<?> exitGroupRequest(
            @RequestParam(value="groupId", required = true) int groupId
    ){
        try {
            groupService.RemoveGroupMember(groupId);
            return ResponseEntity.ok("Exited group successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to exit group: " + e.getMessage());
        }
    }

    @GetMapping("/info")
    public ResponseEntity<?> getGroupInfoRequest(
            @RequestParam(value="groupId", required = true) int groupId
    ){
        try {
            return ResponseEntity.ok(groupService.GetGroupInfo(groupId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to get group info: " + e.getMessage());
        }
    }

    @GetMapping("/disband")
    public ResponseEntity<?> disbandGroupRequest(
            @RequestParam(value="groupId", required = true) int groupId
    ){
        try {
            groupService.DisbandGroup(groupId);
            return ResponseEntity.ok("Disbanded group successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to disband group: " + e.getMessage());
        }
    }


}