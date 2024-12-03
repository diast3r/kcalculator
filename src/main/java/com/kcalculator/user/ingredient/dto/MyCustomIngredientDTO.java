package com.kcalculator.user.ingredient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyCustomIngredientDTO {
	private int id;
	private int userId;
	private String foodName;
	private Integer netWeight;
	private Integer calorie;
	private Double carbohydrates;
	private Double protein;
	private Double fat;
}
