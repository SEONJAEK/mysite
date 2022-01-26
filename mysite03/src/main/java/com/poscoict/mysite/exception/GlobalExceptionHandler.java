package com.poscoict.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//advice가 붙으면 what에 해당 aop라는 것을 알 수 있다. 
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public String ExceptionHandler(Model model, Exception e) {
		//1. 로깅
		//String errors = e.toString();
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		//화면에 남기지 않고 로그를 사용해서 뿌리겠다
		//System.out.println(errors.toString());
		LOGGER.error(errors.toString());
		
		
		//2. 사과페이지(HTML응답, 정상 종료)
		model.addAttribute("exception", errors.toString());
		return "error/exception";
		
		
	}
}
