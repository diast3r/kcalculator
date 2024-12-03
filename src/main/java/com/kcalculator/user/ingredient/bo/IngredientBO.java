package com.kcalculator.user.ingredient.bo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.kcalculator.user.ingredient.dto.MyCustomIngredientDTO;
import com.kcalculator.user.ingredient.dto.MyIngredientDTO;
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
	
	public List<RawIngredientDTO> getRawIngredientListMatchKeyword(String keyword) {
		return ingredientMapper.selectRawIngredientListMatchFoodName(keyword);
	}
	
	public List<MyCustomIngredientDTO> getMyCustomIngredientList(int userId) {
		return ingredientMapper.selectMyCustomIngredientList(userId);
	}
	
	public List<MyIngredientDTO> getMyIngredientList(int userId) {
		return ingredientMapper.selectMyIngredientList(userId);
	}
	
	public int addMyIngredient(int userId, int ingredientId, String type) {
		return ingredientMapper.insertMyIngredient(userId, ingredientId, type);
	}
	
}
