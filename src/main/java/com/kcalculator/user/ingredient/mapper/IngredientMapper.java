package com.kcalculator.user.ingredient.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kcalculator.user.ingredient.dto.MyCustomIngredientDTO;
import com.kcalculator.user.ingredient.dto.MyIngredientDTO;
import com.kcalculator.user.ingredient.dto.ProcessedIngredientDTO;
import com.kcalculator.user.ingredient.dto.RawIngredientDTO;

@Mapper
public interface IngredientMapper {
	/**
	 * 상품명, 제조사명으로 가공식품 검색
	 * @param keyword 상품명과 제조사명을 포함한 키워드(공백, 쉼표, 온점 등으로 구분)
	 * @return 상품정보, 영양정보 포함한 List
	 */
	public List<ProcessedIngredientDTO> selectProcessedIngredientListMatchFoodNameAndManufacturer(String keyword);
	
	/**
	 * 식품명으로 원재료 검색
	 * @param keyword 상품명 키워드
	 * @return
	 */
	public List<RawIngredientDTO> selectRawIngredientListMatchFoodName(String keyword);
	
	/**
	 * 내 재료(커스텀) 재료 가져오기
	 * @param loginId
	 * @return 재료명, 중량, 영양정보 등
	 */
	public List<MyCustomIngredientDTO> selectMyCustomIngredientList(int userId);
	
	/**
	 * 내 재료(원재료/가공식품) 정보 가져오기
	 * @param loginId
	 * @return
	 */
	public List<MyIngredientDTO> selectMyIngredientList(int userId);
	
	
	// TODO selectMyIngredientListMatchFoodName 내 재료(원재료/가공) 검색하기
	
	
	// id List로 재료(가공) List 가져오기
	public List<ProcessedIngredientDTO> selectProcessedIngredientListByIdList(List<Integer> idList);
	
	// id List로 재료(원재료) List 가져오기
	public List<RawIngredientDTO> selectRawIngredientListByIdList(List<Integer> idList);
	
	
	// 내 재료(원재료/가공식품) 등록
	public int insertMyIngredient(
			@Param("userId") int userId,
			@Param("ingredientId") int ingredientId, 
			@Param("type") String type); // TODO 리팩토링 - type은 enum으로 만들기
	
	public int insertMyCustomIngredient(
			@Param("userId") int userId,
			@Param("foodName") String foodName,
			@Param("netWeight") Integer netWeight, 
			@Param("calorie") Integer calorie,
			@Param("carbohydrates") Double carbohydrates,
			@Param("protein") Double protein,
			@Param("fat") Double fat);
		
}
