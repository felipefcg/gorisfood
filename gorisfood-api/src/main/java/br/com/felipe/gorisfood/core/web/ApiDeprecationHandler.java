package br.com.felipe.gorisfood.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiDeprecationHandler implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getRequestURI().startsWith("/v1")) {
			response.addHeader("X-GoriFood-Deprecated", 
					"Essa versão da API está depreciada e deixará de existir a partir de 01/01/2024. Use a versão mais atual da API.");
		}
		
		return true;
	}
}
