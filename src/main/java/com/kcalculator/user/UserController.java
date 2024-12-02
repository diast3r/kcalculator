package com.kcalculator.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kcalculator.account.bo.UserBO;
import com.kcalculator.account.dto.UserSimpleDTO;
import com.kcalculator.common.annotation.AuthRequired;
import com.kcalculator.user.ingredient.bo.IngredientBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserBO userBO;
	private final IngredientBO ingredientBO;
	
	
	@GetMapping("/{loginId}/profile")
	public String profile(@PathVariable("loginId") String loginId, Model model) {
		UserSimpleDTO user = userBO.getUserSimpleByLoginId(loginId);
		
		model.addAttribute("user", user);
		
		return "user/profile";
	}
	
	@AuthRequired
	@GetMapping("/{loginId}/profile/edit")
	public String editProfile(@PathVariable("loginId") String loginId, Model model) {
		model.addAttribute("user", userBO.getUserProfileByLoginId(loginId));
		return "user/editProfile";
	}
	
	@AuthRequired
	@GetMapping("/{loginId}/ingredient")
	public String ingredient(@PathVariable("loginId") String loginId, Model model) {
		model.addAttribute("user", userBO.getUserProfileByLoginId(loginId));
		return "user/ingredient";
	}
	
}
