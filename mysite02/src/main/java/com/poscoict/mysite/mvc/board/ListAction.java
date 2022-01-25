package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		String kwd = request.getParameter("kwd");
		List<BoardVo> boardList=null;

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String strpage = request.getParameter("page");
		if(strpage==null||strpage.isEmpty()) strpage="1";
		int page = Integer.parseInt(strpage);
		kwd = request.getParameter("kwd");// 중복
		
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("pagecount", 10);
		map.put("currentpage", page);
		map.put("nextpage", 1);
		map.put("prepage", -1);
		
		if (kwd != null) {
			map.put("cnt", new BoardDao().getCount(kwd));
			
		} else {
			map.put("cnt", new BoardDao().getCount());
			
		}
		map.put("listcnt", (int) Math.ceil((double)map.get("cnt")/map.get("pagecount")));
		map.put("boardcnt", map.get("cnt")-(map.get("currentpage")-1)*map.get("pagecount"));
		
		System.out.println(map);
		
		session.setAttribute("authUser", authUser);
		request.setAttribute("boardList", new BoardDao().findAll((map.get("currentpage")-1)*map.get("pagecount"), kwd));
		request.setAttribute("page", map);
		
		
		MvcUtil.forward("board/list", request, response);

		
	}

}
