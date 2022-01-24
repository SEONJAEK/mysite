package com.poscoict.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.poscoict.mysite.service.BoardService;

public class BoardController {
	@Autowired
	private BoardService boardService;
//	//글 수정 전//수정해라
//	//userNo와 session에 있는 user와 같다면 
//	@RequestMapping(value="/modify", method = RequestMethod.POST)
//	public String modify(
//			HttpSession session,
//			@RequestParam (value="email", required=true, defaultValue="") String email, 
//			@RequestParam (value="password", required=true, defaultValue="") String password, 
//			Model model) {
//		UserVo authUser = userService.getUser(email, password);
//		
//		if(authUser == null) {
//			model.addAttribute("result", "fail");
//			model.addAttribute("email", email);
//			return "user/login";
//		}
//		
//		/*인증처리*/
//		session.setAttribute("authUser", authUser);
//		return "redirect:/";
//	}
	
	//새글, 답글 달기
	
	
	//글 한개 보기
	
	//글 수정 전
}
