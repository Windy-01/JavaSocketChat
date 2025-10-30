package com._4.javasocketchat;

import com._4.javasocketchat.dto.*;
import com._4.javasocketchat.service.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavasocketchatApplicationTests {
	@Autowired
	private UserService userService;

	@Test
	void contextLoads() throws Exception {
		System.out.println("\nStart Test");
		
		// SignupRequest signupRequest = new SignupRequest();
		// signupRequest.setUserName("TestName");
		// signupRequest.setUserAccount("TestUser");
		// signupRequest.setUserPassword("password123");
		// try {
		// 	userService.signup(signupRequest);
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }
		SigninRequest signinRequest = new SigninRequest();
		signinRequest.setUserAccount("TestUser");
		signinRequest.setUserPassword("password");
		try {
			userService.signin(signinRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("End Test");
	}

}
