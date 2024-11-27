package com.kcalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@SpringBootTest
class KcalculatorApplicationTests {

	// unit test
	// 확인하고 싶은 부분 단위로 쪼개서 테스트하는 것.
	@Test
	void contextLoads() {
		int a = 5;
		int b = 10;
		assertEquals(15, a + b);
		log.info("첫 번째 메소드");
		
	}
	
	// 각 unit 은 별도의 스레드로 동작함.
	@Test
	void 테스트() {
		log.info("두 번째 메소드");
		// given - 준비
		
		// when - 실행
		
		// then - 검증
		
	}

}
