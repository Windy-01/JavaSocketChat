package com._4.javasocketchat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com._4.javasocketchat.service.JwtTokenService;

@SpringBootTest
class JavasocketchatApplicationTests {
	// @Autowired
	// private String privateKey;
	@Autowired
	private JwtTokenService jwtTokenService;

	@Test
	void contextLoads() throws Exception {
		System.out.println("\nStart Test");
		
		System.out.println(jwtTokenService.createToken("666"));
		// System.out.println("Privatekey is:\n"+privateKey.substring(1));
		// System.out.println("\n");
		// System.out.println("Publickey is:\n"+publicKey.substring(1));

		System.out.println("End Test");
	}

}
