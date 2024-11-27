package com.kcalculator.encrypt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kcalculator.common.Encrypter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class 암호화테스트 {
	@Autowired
	Encrypter encrypter;
	
	@Test
	void 암호화() {
		log.info("[### 암호화 테스트] {}", encrypter.generateHash("aA1234!"));
		
		log.info("[### 암호화 테스트] {}", encrypter.matchWithEncryptedString("암호: aA1234!", "해시된: yXH487zSO6+1Ewl43HRXNT1zWPS7bQPVkWnhtaV0Kxs=", "소금간: bB5NjoQIywAze0S2KCUWww=="));
	}

}
