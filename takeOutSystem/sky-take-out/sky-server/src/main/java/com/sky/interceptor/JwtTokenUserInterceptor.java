package com.sky.interceptor;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtTokenUserInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtProperties jwtProperties;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader(jwtProperties.getUserTokenName());
		
		try {
			Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
			Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
			BaseContext.setCurrentId(userId);
			return true;
		} catch (Exception e) {
			response.setStatus(401);
			return false;
		}
	}
	
}
