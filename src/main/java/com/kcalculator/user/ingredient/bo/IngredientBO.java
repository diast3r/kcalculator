package com.kcalculator.user.ingredient.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kcalculator.user.ingredient.dto.ProcessedIngredientDTO;
import com.kcalculator.user.ingredient.dto.RawIngredientDTO;
import com.kcalculator.user.ingredient.mapper.IngredientMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IngredientBO {
	
	private final IngredientMapper ingredientMapper;
	
	public List<ProcessedIngredientDTO> getProcessedIngredientListByKeyword(String keyword) {
		return ingredientMapper.selectProcessedIngredientListMatchFoodNameAndManufacturer(keyword);
	}
	
	public List<RawIngredientDTO> getRawIngredientListByKeyword(String keyword) {
		return ingredientMapper.selectRawIngredientListMatchFoodNameAndManufacturer(keyword);
	}
}
