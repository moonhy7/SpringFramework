package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {
	//ProceedingJoinPoint : 지금 실행중인 포인트컷 메소드, JoinPoint 인터페이스를 상속받아서 구현한 객체
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("[BEFORE] : 비즈니스 로직 수행 전 처리할 내용...");
		Object returnObj = pjp.proceed();
		System.out.println("[AFTER] : 비즈니스 로직 수행 후 처리할 내용...");
		return returnObj;
	}
}
