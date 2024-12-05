package com.kcalculator.user.ingredient.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyIngredientDTO {
	// TODO 유효성 검사 - MyIngredientDTO
	private int id;
	private int ingredientId;
	private int userId;
	private String type;
}
