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
//		TV tv = (TV)factory.getBean("tv");
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
		//3. Spring 컨테이너를 종료한다.
		factory.close(); //컨테이너와 함께 객체가 삭제됨
	}
}
