package com.kcalculator.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kcalculator.common.annotation.AuthRequired;
import com.kcalculator.common.annotation.LoginRequired;

@Controller
@RequestMapping("/account")
public class AccountController {
	
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
