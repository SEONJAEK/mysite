package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String kwd = request.getParameter("kwd");
		List<BoardVo> boardList=null;
		if(kwd==null || kwd.equals("")) {
			boardList = new BoardDao().findAll();
		}else {
			boardList = new BoardDao().findKwd(kwd);
		}
		request.setAttribute("boardList", boardList);
		MvcUtil.forward("board/list", request, response);
//		int pageCount = 10;
//		int currentPage = 2;
//		int nextPage = -1;//nextpage가 없다. 
//		int startPage = 3;
//		int prePage = 2; //-1생기게끔 하고 링크를 준다??
//		
//		Map m;
//		m.put();
//		
//		private Long no;
//		private String title;
//		private String contents;
//		private int hit;
//		private int groupNo;
//		private int orderNo;
//		private int depth;
//		private String regDate;
//		private Long userNo;
//		private String userName;
		
		
		
	}

}
