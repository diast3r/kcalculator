package com.kcalculator.user.ingredient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyCustomIngredientDTO {
	// TODO 유효성 검사 - MyCustomIngredientDTO
	private int id;
	private int userId;
	private String foodName;
	private Integer netWeight;
	private Integer calorie;
	private Double carbohydrates;
	private Double protein;
	private Double fat;
}
