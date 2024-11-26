package com.kcalculator.account;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcalculator.account.BO.UserBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/account")
@RestController
public class AccountRestController {
	
	private final UserBO userBO;
	
	// 아이디 중복확인
	@GetMapping("/is-duplicate-id")
	public Map<String, Object> isDuplicateId(@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();

		// 중복확인
		boolean isDuplicatedId = userBO.isDuplicateId(loginId);
		
		if (isDuplicatedId) {
			result.put("result", true);
			return result;
		}
		
		
		result.put("result", false);
		
		return result;
	}
	
	
	// 회원가입
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("login-id") String loginId,
			@RequestParam("password") String password,
			@RequestParam(value = "nickname", required = true) String nickname,
			@RequestParam("email") String email) {
		Map<String, Object> result = new HashMap<>();
		
		// 암호화
		try {
			
			
		} catch(Exception e) {
			log.info("[### AcountRestConroller 비밀번호 암호화 실패] password:{}, e.class:{}, e.cause:{}", password, e.getClass(), e.getCause());
			result.put("message", "에러 확인 요망");
			result.put("code", 200);
			return result;
		}
		
		// DB insert
		if (!userBO.addUser(loginId, password, nickname, email)) {
			result.put("code", 200);
			result.put("message", "에러 확인 요망");
			return result;
		}
		
		result.put("message", "응답성공");
		return result;
		
	}
}
