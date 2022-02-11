package com.poscoict.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc //얘가 의미하는 바가 뭐지? Annotation기반의 Spring MVC를 간편하게 설정할 수 있도록 도와주는 어노테이션
//어노테이션 기반의 SpringMvc를 구성할 때 필요한 Bean설정들을 자동으로 해주는 어노테이션 
//<mvc:annoation-driven>대신에 써주는 거임 
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	//View Resolver(.jsp설정)
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	//Message Converter
	
	
	//서블릿 컨테이너(tomcat)의 DefaultServlet 위임
	//controller찾아보고 없으면 다보내는 곳
	//DefaultServlet Handler
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	
}
