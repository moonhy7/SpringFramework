package com.springbook.biz.common;

//@Service
//@Aspect
public class LogAdvice {
//	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}
	
//	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
//	//advice 어노테이션 : advice의 동작시점 어노테이션을 사용한다.
//	@Before("allPointcut()")
	public void printLog() {
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
}
