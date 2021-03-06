package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardVo boardVo  = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContents(content);
		boardVo.setUserNo(userVo.getNo());
		
		new BoardDao().insert(boardVo);
		
		//잠시 보류하겠음... ㅎㅎㅎ
//		//그 사람의 no값 가져오셈
//		
//		String password = request.getParameter("password");
//		
//		GuestbookVo vo = new GuestbookVo();
//		vo.setName(name);
//		vo.setPassword(password);
//		vo.setMessage(content);
//		
//		GuestbookDao dao = new GuestbookDao();
//		dao.insert(vo);
//		
//		//왜 이거 안돼?
//		//MvcUtil.redirect("main/index",request, response); 
		MvcUtil.redirect("/board",request, response); 
	}

}
