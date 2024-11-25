package com.kcalculator.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kcalculator.test.domain.ProcessedIngredient;

@Mapper
public interface TestMapper {
	public List<ProcessedIngredient> selectIngredientList(int offset);
}
