package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. handler 종류 확인(HandlerMethod-controller의 핸들러 정보확인, defaultServletHandler 이렇게 두종류있음)
		//true -> default handler method로 간다 
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. casting//여긴 handlerMethod라는 뜻
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Handler Method의 @Auth 받아오기
		//그핸들러메소드에 @Auth붙여있냐? 물어보는 거임 -> getMethodAnnotation
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. @Auth가 없다면...//그냥 핸들러 실행시키면 끝이고.. 라고 하심.뭔말임???
		if(auth == null) {
			return true;
		}
		//5. @Auth가 적용이 되어 있기 때문에 인증(Authentication)여부 확인
		
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath()+ "/user/login");
			return false;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+ "/user/login");
		}
		//여기까지 왔다는 것은 authUser도 있고 annotaion도 있다는 뜻
		//여기까지 왔다는 것은 인증이 확인됐다는 뜻이니까 다음 controller로 가게 해라->true
		//6. 인증확인 !!! -> controller의 handler(method)실행
		return true;
	}
}
