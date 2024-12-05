package com.kcalculator.user.ingredient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessedIngredientDTO {
	// TODO 유효성 검사 - ProcessedIngredientDTO
	private int id;
	private String foodCode;
	private String foodName;
	private String productCode;
	private String netWeight;
	private int caloriePer100;
	private Double carbohydratesPer100;
	private double proteinPer100;
	private Double fatPer100;
	private String servingSize;
	private String manufacturer;
}