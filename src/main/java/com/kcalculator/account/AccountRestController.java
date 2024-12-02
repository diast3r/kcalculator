package com.kcalculator.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcalculator.account.bo.UserBO;
import com.kcalculator.account.dto.UserSimpleDTO;
import com.kcalculator.common.Encrypter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/account")
@RestController
public class AccountRestController {
	
	private final Encrypter encrypter;
	private final UserBO userBO;
	
	// 아이디 중복확인
	@GetMapping("/is-duplicate-id")
	public Map<String, Object> isDuplicateId(@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();

		// 중복확인
		boolean isDuplicatedId = (userBO.getUserSimpleByLoginId(loginId) != null);
		
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
		
		// TODO 유효성 검사 - 회원 가입 시 유효성 검사
		
		
		// DB insert
		if (!userBO.addUser(loginId, password , nickname, email)) {
			result.put("code", 500);
			result.put("message", "에러 확인 요망");
			return result;
		}
		
		result.put("message", "회원가입 성공");
		result.put("code", 200);
		
		return result;
	}
	
	// 로그인
	@PostMapping("/log-in")
	public Map<String, Object> logIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletResponse response, 
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		UserSimpleDTO userSimple = userBO.logIn(loginId, password);
		
		if (userSimple == null) {
			result.put("code", 200);
			result.put("message", "회원정보 불일치");
			return result;
		}
		
		session.setAttribute("id", userSimple.getId());
		session.setAttribute("loginId", userSimple.getLoginId());
		session.setAttribute("nickname", userSimple.getNickname());
		
		result.put("code", 200);
		result.put("message", "로그인 성공");
		
		return result;
	}
	
	// 로그아웃
	@GetMapping("/log-out")
	public Map<String, Object> logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("message", "로그아웃 성공");
		
		response.sendRedirect("/diet/list");
		return result;
	}
	
	// TODO 기능 구현 - 아이디, 비번 찾기(RestController)
}
