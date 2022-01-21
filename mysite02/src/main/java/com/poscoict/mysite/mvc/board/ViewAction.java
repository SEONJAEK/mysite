package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nostring = request.getParameter("no");
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo boardVo= new BoardDao().findOne(no);
		request.setAttribute("board", boardVo);
		
		//쿠키 읽기
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(nostring.equals(cookie.getName())) {
					viewCookie = cookie;
					break;
				}
			}
		}
		
		//쿠키 초기화 세팅(굽기)
		if(viewCookie == null) {
			Cookie  cookie = new Cookie(nostring, String.valueOf(boardVo.getHit() + 1));
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(60);
			response.addCookie(cookie);
			//조회수 증가
			new BoardDao().views(boardVo);
		}
		
		MvcUtil.forward("board/view", request, response);

	}
}
