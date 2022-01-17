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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//접근제어(Access Control)
		//연결한다는게 무슨의미인가?????? 세션을 준비한다는 것은 ?여기가 아니라 login했을 때 세션 연결해야할 것 같단 말이지?
		//세션객체 쓰려면 준비해줘야하는 코드가 httpseesion session = request.getsession()이건가?
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser==null) {
			MvcUtil.redirect(request.getContextPath()+"/user?a=loginform", request, response);
			return;
		}
		Long no = authUser.getNo();//각 사람 번호
		UserVo vo = new UserDao().findByNo(no);
		request.setAttribute("vo", vo);
		//forwarding user/updateform
		//request.setAttribute("UserVo", vo);
		MvcUtil.forward("user/updateform", request, response);
	}

}
