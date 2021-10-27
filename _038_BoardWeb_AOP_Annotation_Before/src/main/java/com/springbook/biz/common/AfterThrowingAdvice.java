package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice {
	public void afterThrowingAdvice(JoinPoint jp, Exception exceptionObj) {
		String method = jp.getSignature().getName();
		
		System.out.println("[예외 처리] " + method + "() 메소드 수행 중 발생한 예외 메시지" + exceptionObj.getMessage());
	}
}
