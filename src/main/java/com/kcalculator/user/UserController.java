package com.kcalculator.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/{loginId}")
public class UserController {
	
	@GetMapping("/profile")
	public String profile(HttpSession session) {
		return "user/profile";
	}
}
