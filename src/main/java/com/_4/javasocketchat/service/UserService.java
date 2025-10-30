package com._4.javasocketchat.service;

import com._4.javasocketchat.dto.*;
import com._4.javasocketchat.entity.UserEntity;
import com._4.javasocketchat.repository.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; 

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //这里逻辑并不完善，很奇怪（）
    public void signin(SigninRequest signinRequest) throws Exception {
        String hashPassword = userRepository.getHashPassword(signinRequest.getUserAccount());
        if(!passwordEncoder.matches(signinRequest.getUserPassword(), hashPassword)){
            throw new Exception("Wrong password");
        }
    }

    public void signup(SignupRequest signupRequest) throws Exception {
        String encodedPassword = passwordEncoder.encode(signupRequest.getUserPassword());
        UserEntity userEntity = new UserEntity(signupRequest.getUserName(),signupRequest.getUserAccount(),encodedPassword);
        userRepository.add(userEntity);
    }

    public boolean isExist(String username) throws Exception {
        return userRepository.isExist(username);
    }
}
