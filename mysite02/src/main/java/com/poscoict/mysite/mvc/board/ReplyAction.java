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

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글 고유번호(이전)
		Long pre_no = Long.parseLong(request.getParameter("preNo"));
		BoardVo pre_boardVo = new BoardDao().findOne(pre_no);
		
		//현재 넘어온 데이터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//boardVo
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContents(content);
		boardVo.setGroupNo(pre_boardVo.getGroupNo());
		boardVo.setOrderNo(pre_boardVo.getOrderNo());
		boardVo.setDepth(pre_boardVo.getDepth());
		
		HttpSession session = request.getSession(true);//이건 무조건 써줘야하는건가?
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		//답글 1차 작업: 답글 순서 reorder
		new BoardDao().replyUpdate(pre_boardVo);
		//답글 2차 작업: reply insert해주기
		new BoardDao().replyInsert(boardVo, userVo.getNo());
		
		MvcUtil.redirect("/board",request, response); 
	}

}
