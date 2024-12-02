package com.kcalculator.user.ingredient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessedIngredientDTO {
	private int id;
	private String food_code;
	private String food_name;
	private String product_code;
	private String net_weight;
	private int calorie_per_serving;
	private Double carbohydrates_per_serving;
	private double protein_per_serving;
	private Double fat_per_serving;
	private String serving_size;
	private String manufacturer;
}
