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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//인증 처리(Session처리)//나 세션 쓸거임? 이런뜻 맞지?
				HttpSession session = request.getSession(true);
				UserVo userVo = (UserVo)session.getAttribute("authUser");
				Long no = Long.parseLong(request.getParameter("no"));
						
				if (userVo != null) {
					//이때는 redirect처리 해야하는 지 forward처리 해야하는지?
					//authUser가지고 써야하니까 내가 생각했을 때는 forward임
					//근데 세션은 사실 항상 살아있잖아? 그럼 redirect해도 상관없는거 아님?
					//언제 redirect, forward하는지 개념 정리 바람
					
				
					BoardVo board = new BoardDao().findOne(no);
					request.setAttribute("board", board);
					MvcUtil.forward("board/modify", request, response);
				}else {//null이면 로그인 페이지로 가기
					MvcUtil.forward("user/loginform", request, response);
					//return해줘야할 경우랑 안해줘야할 경우랑 차이가 뭐임//내가 봤을 때는 forward+return => redirect각인데 맞음?
					return;
				}
	}

}
