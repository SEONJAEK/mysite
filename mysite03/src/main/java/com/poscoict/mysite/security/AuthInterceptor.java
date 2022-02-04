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
		
		//4.Handler Method에  @Auth가 없다면 Type(클래스)에 있는 지 확인(과제)...//그냥 핸들러 실행시키면 끝이고.. 라고 하심.뭔말임???
		if(auth == null) {
			auth = handlerMethod
					.getMethod()
					.getDeclaringClass() //클래스 범위 불러오는것 
					.getAnnotation(Auth.class); //클래스에 어노테이션이 붙어있는지 안 붙어있는 지 확인
		}
		
		//5. type과 method에 @Auth가 적용이 안되어있는 경우
		if(auth == null) {
			return true;
		}
		
		//6. @Auth가 적용이 되어 있기 때문에 인증(Authentication)여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath()+ "/user/login");
			return false;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+ "/user/login");
			return false;
		}
		//여기까지 왔다는 것은 authUser도 있고 annotaion도 있다는 뜻
		//여기까지 왔다는 것은 인증이 확인됐다는 뜻이니까 다음 controller로 가게 해라->true
		//6. 인증확인 !!! -> controller의 handler(method)실행
		
		
		// 7. 권한(Authorization)체크를 위해서 @Auth의 role가져오기
		String role = auth.role();//auth.role == USER
		// 8. @Auth의 role이 "USER'인 경우, authUser의 role은 상관이 없다. 
		if("USER".equals(role)){
			return true;
		}
		//9. @Auth의 role이 "ADMIN"인경우, authUser은 "ADMIN"이어야 한다. 
		if("ADMIN".equals(authUser.getRole()) == false){
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		//10. 옳은 관리자
		//@Auth의 role:ADMIN
		//authUser의 role ADMIN
		return true;
		
		
		//원래 선재 코드
//		if("ADMIN".equals(authUser.getRole())) {
//			return true;
//		}
//		System.out.println("real role:" + auth.role());
//		if(!auth.role().equals(authUser.getRole())) {
//			response.sendRedirect(request.getContextPath());
//			return false;
//		}
//		
//		return true;
	}
}
