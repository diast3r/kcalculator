package com.kcalculator.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcalculator.account.BO.AccountBO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/account")
@RestController
public class AccountRestController {
	
	private final AccountBO accountBO;
	
	// 아이디 중복확인
	@GetMapping("/is-duplicate-id")
	public Map<String, Object> isDuplicateId(@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();

		// 중복확인
		boolean isDuplicatedId = accountBO.isDuplicateId(loginId);
		
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
			@RequestParam("password-check") String passwordCheck,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam("email") String email
			
			) {
		Map<String, Object> result = new HashMap<>();
		
		result.put("message", "응답성공");
		
		
		return result;
	}
}
