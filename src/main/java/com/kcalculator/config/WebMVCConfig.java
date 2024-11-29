package com.kcalculator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kcalculator.common.FileManagerService;
import com.kcalculator.interceptor.AccountInterceptor;
import com.kcalculator.interceptor.PermissionInterceptor;
import com.kcalculator.interceptor.UserInterceptor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
	
private final UserInterceptor userInterceptor;
private final PermissionInterceptor permissionInterceptor;
private final AccountInterceptor accountInterceptor;
	
	// 인터셉터 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 로그인한 사람이 /account 접속 시 /diet/list로 리다이렉트
		registry
		.addInterceptor(accountInterceptor)
		.addPathPatterns("/account/**")
		.excludePathPatterns("/account/log-out");
		
		// 사용자 없으면 userNotFound로 포워드
		registry
		.addInterceptor(userInterceptor)
		.order(0)
		.addPathPatterns("/user/{loginId}/**")
		.excludePathPatterns("/css/**", "/img/**", "imgaes/**", "account/log-out");
		
		// 로그인 검증 또는 사용자 인증 인터셉터
		registry
		.addInterceptor(permissionInterceptor)
		.order(1)
		.addPathPatterns("/**")
		.excludePathPatterns("/css/**", "/img/**", "images/**", "/account/log-out"); // 정적자원에 대한 요청은 인터셉터 동작 안 하도록 설정
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // path  http://localhost/images/asdf_1730889214464/test.png 로 들어온 요청을 잡아줌.
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // file:// => 맥 또는 리눅스 file:/// => 윈도우
	}
}
