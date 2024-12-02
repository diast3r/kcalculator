package com.kcalculator.interceptor;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.kcalculator.common.annotation.AuthRequired;
import com.kcalculator.common.annotation.LoginRequired;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		log.info("{}", request.getRequestURI());
		log.info("{}", session.getAttribute("loginId"));
		
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);
		AuthRequired authRequired = handlerMethod.getMethodAnnotation(AuthRequired.class);
		
		if (loginRequired != null) { // @LoginRequired붙은 메소드 검증
			if (loginId == null) {
				response.sendRedirect("/account/log-in");
				return false;
			}
		}
		
		if (authRequired != null) { // @AuthRequired붙은 메소드 검증
			Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			if (loginId == null) {
				response.sendRedirect("/account/log-in");
				return false;
			}
			if (!pathVariables.get("loginId").equals(loginId)) {
				response.sendRedirect("/diet/list");
				return false;
			}
		}
		
		
		return true;
	}
	
}
