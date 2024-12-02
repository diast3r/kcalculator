package com.kcalculator.interceptor;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kcalculator.account.bo.UserBO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserInterceptor implements HandlerInterceptor {
	
	private final UserBO userBO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws IOException, ServletException {
		
		
		return true;
		
	}
	// TODO 인터셉터 - UserInterceptor가 먼저 동작하면 PermissionInterceptor는 동작하지 않게 하기.
	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler,
			ModelAndView mav) throws ServletException, IOException {
		
		// 없는 사용자를 찾으면 userNotFound.html 뿌리기
		Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		String pathLoginId = pathVariables.get("loginId");
		
		if (userBO.getUserSimpleByLoginId(pathLoginId) == null) {
			mav.setViewName("/user/userNotFound.html");
		}
		
		// view, model이 있다는건 html이 해석되기 전
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, 
			HttpServletResponse response, Object handler,
			Exception ex) {
		
		// html이 렌더링이 끝난 상태
//		log.info("[########## afterCompletion]");
	}
}
