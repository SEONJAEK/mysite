package com.poscoict.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poscoict.mysite.security.Auth;

//이 컨트롤러에 있는 모든 핸들러(메소드)는 다 인증을 받아라라는 뜻
@Auth
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@ResponseBody
	@RequestMapping("/main")
	public String main() {
		return "AdminController/main()";
	}
	
	@ResponseBody
	@RequestMapping("/board")
	public String board() {
		return "AdminController.board()";
	}

	@ResponseBody
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "AdminController.guestbook()";
	}
}
