package com.kcalculator.user.ingredient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RawIngredientDTO {
	private int id;
	private String foodCode;
	private String foodName;
	private int caloriePer100;
	private Double carbohydratesPer100;
	private double proteinPer100;
	private Double fatPer100;
}
