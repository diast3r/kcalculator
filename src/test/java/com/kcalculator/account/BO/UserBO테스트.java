package com.kcalculator.account.BO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserBO테스트 {
	
	@Autowired
	private UserBO userBO;
	
	@Transactional
	@Test
	void signUp() {
		// fail("Not yet implemented");
		String loginId = "테스트 아이디";
		String password = "1234";
		String nickname = "테스트 닉네임";
		String email = "테스트 이메일@test.com";
		
		userBO.signUp(loginId, password, nickname, email);
		log.info("{}", userBO.logIn(loginId, password));

	}

}
