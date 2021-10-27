package com.springbook.biz.common;

public class BeforeAdvice {
	public void beforeAdvice() {
		System.out.println("[사전 처리] 비즈니스 로직 수행 전 동작");
	}
}