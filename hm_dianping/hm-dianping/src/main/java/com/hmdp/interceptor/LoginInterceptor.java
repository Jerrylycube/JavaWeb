package com.hmdp.interceptor;

import com.hmdp.utils.UserHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//因为前置的拦截器已经将存在的用户保存在ThreadLocal中
		//若前面拦截的请求有token但token在redis中不存在，则说明token已经过期或者无效，需要拦截
		// 1.判断是否需要拦截（ThreadLocal中是否有用户）
		if (UserHolder.getUser() == null) {
			// 没有，需要拦截，设置状态码
			response.setStatus(401);
			// 拦截
			return false;
		}
		// 有用户，则放行
		return true;
	}
}