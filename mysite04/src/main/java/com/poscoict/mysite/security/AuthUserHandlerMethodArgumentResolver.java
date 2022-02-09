package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.poscoict.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		//@AuthUser가 안붙어있음
		if(authUser == null) {
			return false;
		}
		//여기까지 왔다는 것은 @AuthUser가 붙어있다는 뜻
		//파라미터 타입이 UserVo가 아님
		//UserVo클래스인지 확인하는 작업임
		if(parameter.getParameterType().equals(UserVo.class) == false) {
			return false;
		}
		//다 통과했다면
		return true;
	}

	@Override
	//webRequest: 서블릿 request임
	public Object resolveArgument(MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		
		if(!supportsParameter(parameter)) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		return session.getAttribute("authUser");
	}

}
