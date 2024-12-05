package com.kcalculator.user.ingredient.bo;

import java.util.List;

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
	
	public int addMyCustomIngredient(int userId, String foodName, Integer netWeight, 
			Integer calorie, Double carbohydrates, Double protein,Double fat) {
		return ingredientMapper.insertMyCustomIngredient(userId, foodName, netWeight, calorie, carbohydrates, protein, fat);
	}
	
	public boolean isDuplicateIngredient(int userId, int ingredientId, String type) {
			// List<MyIngredientDTO> myIngredientList = ingredientMapper.selectMyIngredientList(userId);
			// myIngredientList가 userId, ingredientId, type을 가지고 있는지 검사
		return false;
	}
	
	public List<ProcessedIngredientDTO> getProcessedIngredientListByIdList(List<Integer> ingredientIdList) {
		// TODO type을 enum으로 만들기
		return ingredientMapper.selectProcessedIngredientListByIdList(ingredientIdList);
	}
	
	public List<RawIngredientDTO> getRawIngredientListByIdList(List<Integer> ingrdientIdList) {
		return ingredientMapper.selectRawIngredientListByIdList(ingrdientIdList);
	}
	
}
