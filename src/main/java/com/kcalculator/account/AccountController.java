package com.kcalculator.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	// TODO #7 interceptor 활용해서 이미 로그인한 사람은 다른 페이지(이전 페이지 또는 게시판)로 리다이렉트
	// TODO 기능 구현 - 아이디, 비번 찾기(Controller)
	@GetMapping("/log-in")
	public String logIn() {
		return "account/logIn";
	}
	
	@GetMapping("/sign-up")
	public String signUp() {
		return "account/signUp";
	}
	
	@GetMapping("/find-account")
	public String findAccount() {
		return "account/findAccount";
	}
}
