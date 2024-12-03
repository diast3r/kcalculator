package com.kcalculator.user.ingredient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyIngredientDTO {
	private int id;
	private int ingredientId;
	private int userId;
	private String type;
}
