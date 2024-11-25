package com.kcalculator.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcalculator.test.domain.ProcessedIngredient;
import com.kcalculator.test.mapper.TestMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TestController {
	
	private final TestMapper testMapper;
	
	@ResponseBody
	@GetMapping("/test1")
	public String test1() {
		return "<h1>테스트</h1>";
	}
	
	
	@ResponseBody
	@GetMapping("/test2")
	public Map<String, Object> test2() {
		Map<String, Object> result = new HashMap<>();
		
		result.put("테스트", "결과");
		
		return result;
	}
	
	
	@GetMapping("/test3")
	public String test3() {
		
		return "user/login";
	}
	
	@ResponseBody
	@GetMapping("/test4")
	public List<ProcessedIngredient> test4(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
		int offset = page * 1000;
		return testMapper.selectIngredientList(offset);
	}
}
