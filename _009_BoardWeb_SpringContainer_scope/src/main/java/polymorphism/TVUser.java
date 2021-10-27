package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		//0. applicationCon에다가 bin객체 생성
		
		//1. Spring 컨테이너를 구동한다.
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
	
		//2. Spring 컨테이너로부터 필요한 객체를 요청(Look Up)한다.
		// lookup하는 순간 매핑이되서 우리가 만든 객체로 담아주게됨 (객체는 룩업하기전에 생김)
		
		//lazy-init 특징 알아보기
		
		//1번 경우
		//TV tv = (TV)factory.getBean("tv"); // 주석처리 된 경우 실행 시 아무것도 안뜸
		// 사용자의 요청이 없기 때문임
		
		//2번 경우
//		TV tv = (TV)factory.getBean("tv");
		//룩업부분 주석 해제 후 실행하면 객체 생성된 것을 볼 수 있음 
		//사용자의 요청이 있었기 때문에 객체가 잘 생성된 것임
		
		//scope을 singleton으로 설정 시 하나의 객체만 생성
		//prototype으로 설정 시 요청받은 개수 만큼 전부 생성됨
		TV tv1 = (TV)factory.getBean("tv");
		TV tv2 = (TV)factory.getBean("tv");
		TV tv3 = (TV)factory.getBean("tv");
		
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
		//3. Spring 컨테이너를 종료한다.
		factory.close(); //컨테이너와 함께 객체가 삭제됨
	}
}
