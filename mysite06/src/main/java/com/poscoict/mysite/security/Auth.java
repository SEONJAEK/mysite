package com.poscoict.mysite.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//method처럼 범위를 지정해준건데, 타입이라는 것 안에 3가지 정도의 타입이 있다. 
//그중 하나가 클래스다.
//auth의 범위와 role을 지정해주겠다는 뜻밖에 안됨
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Auth {
	//public String value() default "USER";
	public String role() default "USER";
	//public boolean test() default false;
}
