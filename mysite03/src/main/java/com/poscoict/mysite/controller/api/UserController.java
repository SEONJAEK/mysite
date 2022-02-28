package com.poscoict.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poscoict.mysite.dto.JsonResult;
import com.poscoict.mysite.service.UserService;
import com.poscoict.mysite.vo.UserVo;

@RestController("userApicontroller")
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/checkemail")
	public Object checkEmail(
			@RequestParam(value="email", required=true, defaultValue="")String email) {
		UserVo userVo = userService.getUser(email);
		return JsonResult.success(userVo != null);
	}
}
//사용자가 좋아할지 말지 사용자에게 편해야해 