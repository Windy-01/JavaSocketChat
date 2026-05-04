package com._4.javasocketchat.service;

import com._4.javasocketchat.dto.*;
import com._4.javasocketchat.entity.*;
import com._4.javasocketchat.mapper.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    //这里逻辑并不完善，很奇怪（）
    public void signin(SigninRequest signinRequest) throws Exception {
        String hashPassword = userMapper.getHashPassword(userMapper.getID(signinRequest.getUsername()));
        if(!passwordEncoder.matches(signinRequest.getPassword(), hashPassword)){
            throw new Exception("Wrong password");
        }
    }

    public void signup(SignupRequest signupRequest) throws Exception {        
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        UserInfoEntity userinfo = new UserInfoEntity();
        //signupRequest.getUserName(),signupRequest.getUserAccount(),encodedPassword
        userinfo.setUsername(signupRequest.getUsername());
        userinfo.setEmail(signupRequest.getEmail());        
        userMapper.addUserinfo(userinfo);
        UserCredentialEntity usercredential = new UserCredentialEntity();
        usercredential.setId(userinfo.getId());
        usercredential.setPassword_hash(encodedPassword);
        userMapper.addUsercredential(usercredential);
    }

    public boolean isExist(int id) throws Exception {
        return (userMapper.isExist(id) == 1);
    }

    // public void resetPassword() throws Exception 

    public int getUserIDByAccount(String userAccount) throws Exception {
        return userMapper.getID(userAccount);
    }

    public Map<?,?> getFriendsList(int userId) throws Exception {
        // 这里的逻辑需要完善，后续需要根据用户ID查询好友列表和群聊列表，同时还有相关权限检测没有做到位
        Map<String, List<?>> friendsList = new HashMap<>();
        friendsList.put("friends", userMapper.getFriendList(userId));
        friendsList.put("groups", userMapper.getGroupList(userId));
        return friendsList;
    }
}
