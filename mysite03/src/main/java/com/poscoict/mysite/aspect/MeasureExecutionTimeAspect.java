package com.poscoict.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	//*..*:com~ mysite
	//(..):parameter
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		//before
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = pjp.proceed();
		
		//after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		//class의 class하면 안나옴 그래서 getName써줘야함
		String className = pjp.getTarget().getClass().getName();
		String methodName =pjp.getSignature().getName();
		String taskName = className + "." + methodName; 
		System.out.println("[Execution Time]["+ taskName +"]"+ totalTime + "millis");
		
		return result;
	}
}
