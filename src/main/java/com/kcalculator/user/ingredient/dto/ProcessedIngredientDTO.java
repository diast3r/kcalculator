package com.kcalculator.user.ingredient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessedIngredientDTO {
	private int id;
	private String foodCode;
	private String foodName;
	private String productCode;
	private String netWeight;
	private int caloriePerServing;
	private Double carbohydratesPerServing;
	private double proteinPerServing;
	private Double fatPerServing;
	private String servingSize;
	private String manufacturer;
}
