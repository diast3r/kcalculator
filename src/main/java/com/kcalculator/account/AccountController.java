package com.kcalculator.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
	
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
