package com.kcalculator.account;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@GetMapping("/log-in")
	public String logIn(HttpServletRequest request, HttpServletResponse response) {
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
