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

import jakarta.servlet.http.HttpSession;
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
	public String editProfile(HttpSession session, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		
		model.addAttribute("user", userBO.getUserProfileByLoginId(loginId));
		return "user/editProfile";
	}
	
	@AuthRequired
	@GetMapping("/{loginId}/ingredient/register")
	public String registerIngredient(HttpSession session, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		
		model.addAttribute("user", userBO.getUserProfileByLoginId(loginId));
		// TODO 기능 구현 - 내 재료 페이징
		return "user/registerIngredient";
	}
	
	@AuthRequired
	@GetMapping("/{loginId}/ingredient")
	public String ingredient(HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("id");
		
		model.addAttribute("myIngredient", ingredientBO.getMyIngredientList(userId));
		model.addAttribute("myCustomIngredients", ingredientBO.getMyCustomIngredientList(userId));
		
		return "user/ingredient";
	}
}
