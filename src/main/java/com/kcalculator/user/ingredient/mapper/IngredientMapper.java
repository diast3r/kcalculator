package com.kcalculator.user.ingredient.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kcalculator.user.ingredient.dto.ProcessedIngredientDTO;
import com.kcalculator.user.ingredient.dto.RawIngredientDTO;

@Mapper
public interface IngredientMapper {
	public List<ProcessedIngredientDTO> selectProcessedIngredientListMatchFoodNameAndManufacturer(String keyword);
	
	public List<RawIngredientDTO> selectRawIngredientListMatchFoodNameAndManufacturer(String keyword);
}
