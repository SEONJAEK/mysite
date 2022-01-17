package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.UserDao;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo authUser = new UserDao().findByEmailAndPassword(email, password);
		if(authUser == null) {
			//이메일 또는 비밀번호가 틀림
			//프로토콜이 끝난거지 아직 남아있음 그래서 return 코드가 끝난게 아니야. return해서 끝내야 함
			request.setAttribute("result", "fail");
			request.setAttribute("email", email);
			MvcUtil.forward("user/loginform", request, response);
			return;
		}
		//인증 처리(Session처리)
		HttpSession session = request.getSession(true);//true:없으면 만들어주라.
		session.setAttribute("authUser", authUser);
		
		MvcUtil.redirect(request.getContextPath(), request, response);
	}

}
