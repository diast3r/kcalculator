package com.kcalculator.user.ingredient;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcalculator.user.ingredient.bo.IngredientBO;
import com.kcalculator.user.ingredient.dto.ProcessedIngredientDTO;
import com.kcalculator.user.ingredient.dto.RawIngredientDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/ingredient")
@RestController
public class IngredientRestController {
	
	private final IngredientBO ingredientBO;
	
	@GetMapping("/processed")
	public List<ProcessedIngredientDTO> getProcessedIngredientByKeyword(@RequestParam("keyword") String keyword) {
		
		return ingredientBO.getProcessedIngredientListByKeyword(keyword);
	}
	
	@GetMapping("/raw")
	public List<RawIngredientDTO> getRawIngredientByKeyword(@RequestParam("keyword") String keyword) {
		
		return ingredientBO.getRawIngredientListByKeyword(keyword);
	}
}
