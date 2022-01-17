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

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//접근제어
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		Long no = userVo.getNo();
		
		UserVo updateVo = new UserVo();
		updateVo.setName(name);
		updateVo.setPassword(password);
		updateVo.setGender(gender);
		updateVo.setNo(no);
//		UserVo updateVo =(UserVo)session.getAttribute("updateVo");
		boolean result = new UserDao().update(updateVo);
		//forwarding user/updateform
		//request.setAttribute("UserVo", vo);
		MvcUtil.forward("/main/index", request, response);
	}

}
