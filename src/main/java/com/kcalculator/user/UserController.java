package com.kcalculator.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kcalculator.account.BO.UserBO;
import com.kcalculator.account.dto.UserSimpleDTO;
import com.kcalculator.common.annotation.AuthRequired;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserBO userBO;
	
	
	// TODO 클라이언트 요청 검증 - /user/{loginId}/** (없는 사용자일 경우 userNotFound로 forward처리)
	@GetMapping("/{loginId}/profile")
	public String profile(@PathVariable("loginId") String loginId, Model model) {
		UserSimpleDTO user = userBO.getUserSimpleByLoginId(loginId);
		
		model.addAttribute("user", user);
		
		return "user/profile";
	}
	
	// TODO 클라이언트 요청 검증 - 내정보 수정(세션정보 불일치하면 돌려보내기)
	@AuthRequired
	@GetMapping("/{loginId}/profile/edit")
	public String editProfile(@PathVariable("loginId") String loginId, HttpSession session, Model model) {
		model.addAttribute("user", userBO.getUserProfileByLoginId(loginId));
		return "user/editProfile";
	}
	
}
