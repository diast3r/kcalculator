package com.kcalculator.user.ingredient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RawIngredientDTO {
	private int id;
	private String foodCode;
	private String foodName;
	private int caloriePerServing;
	private Double carbohydratesPerServing;
	private double proteinPerServing;
	private Double fatPerServing;
}
