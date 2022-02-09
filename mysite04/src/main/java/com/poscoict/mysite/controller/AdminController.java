package com.poscoict.mysite.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.service.FileUploadService;
import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;

//이 컨트롤러에 있는 모든 핸들러(메소드)는 다 인증을 받아라라는 뜻
@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.getSite();
		model.addAttribute("site", siteVo);
		return "admin/main";
	}
	
	
	@RequestMapping("/main/update")
	public String main(SiteVo site, @RequestParam("file") MultipartFile file) {
		String profile = fileUploadService.restore(file);
		
		if(profile != null) {
			site.setProfile(profile);
		}
		siteService.update(site);
		servletContext.setAttribute("site", site);
		return "redirect:/admin";
	}
	

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
