package com.poscoict.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;

//이 컨트롤러에 있는 모든 핸들러(메소드)는 다 인증을 받아라라는 뜻
//@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SiteService siteService;
	
//	@Autowired
//	private ServletContext servletContext;

	@RequestMapping(value = "")
	public String main(Model model) {
		model.addAttribute("sitevo", siteService.selectAll());
		return "admin/main";
	}

//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(@RequestParam(value = "upload-file") MultipartFile multipartFile, SiteVo siteVo) {
//		System.out.println("========================" + siteVo);
//		siteService.update(siteVo);
//		return "redirect:/main/index";
//	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
