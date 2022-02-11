package com.poscoict.mysite.Initializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.poscoict.mysite.config.AppConfig;
import com.poscoict.mysite.config.WebConfig;

public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	//rootApplicationContext설정
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class} ;
	}
	
	//Map
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	//ServletApplicationContext설정
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("UTF-8", false)};
	}

	//과제 web.xml을 없애보기
	//Default Handler Sevlet때문에 안됨
//	//not found도 exception처리하라는 이야기
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		
	}
	
	
}
