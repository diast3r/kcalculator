package com.kcalculator.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kcalculator.account.bo.UserBO;
import com.kcalculator.common.annotation.AuthRequired;
import com.kcalculator.user.ingredient.bo.IngredientBO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
	
	private final UserBO userBO;
	private final IngredientBO ingredientBO;
	
	// 민감하지 않은 정보 수정(닉네임, 프사, 이메일)
	@AuthRequired
	@PostMapping("/{loginId}/profile/edit")
	public Map<String, Object> editProfile(
			@PathVariable("loginId") String loginId, 
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("resetProfileImage") boolean resetProfileImage,
			@RequestParam(value = "nickname", required = false) String nickname, 
			@RequestParam("email") String email,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		String sessionLoginId = (String)session.getAttribute("loginId");
		
		if (sessionLoginId == null) {
			result.put("code", 401);
			result.put("message", "로그인 후 이용");
			return result;
		} 
			
		if (!loginId.equals(sessionLoginId)) {
			result.put("code", 403);
			result.put("message", "인증되지 않은 사용자");
			return result;
		}
		
		if (userBO.editProfile(loginId, file, resetProfileImage, nickname, email) == false) {
			result.put("code", 500);
			result.put("message", "정보 수정 실패");
			
			return result;
		}
		
		result.put("code", 200);
		result.put("message", "프로필 수정 완료");
		
		return result;
	}
	
	// 재료 등록(원재료/가공)
	@AuthRequired
	@PostMapping("/{loginId}/ingredient/register")
	public Map<String, Object> registerMyIngredient(
			@RequestParam("ingredientId") int ingredientId,
			@RequestParam("type") String type,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		int userId = (Integer) session.getAttribute("id");
		// TODO 기능 구현 - 재료가 이미 담겨있는지 확인
		
		int rowCount = ingredientBO.addMyIngredient(userId, ingredientId, type);
		
		if (rowCount < 1) {
			result.put("code", 500);
			result.put("message", "재료 추가 실패");
			return result;
		}
		
		result.put("code", 200);
		result.put("message", "재료 추가 성공");
		
		return result;
	}
	
}
