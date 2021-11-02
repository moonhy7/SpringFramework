package polymorphism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component//==> id나 name 미지정 시 lgTV 자동으로 이름이 지정됨
//<bean class="polymorphism.LgTV></bean>
@Component("tv")
//<bean id="tv" class="polymorphism.LgTV></bean>
public class LgTV implements TV {
	//@Autowired 어노테이션 사용 시 생성자, setter 메소드를 이용한 의존성 주입이 이뤄지지 않음
	@Autowired
	//@Qualifier : 동일한 타입의 객체가 두 개이상 생성되어 있을 때 명확하게 지정해서 의존성 주입할 때 사용
	//@Qualifier("apple")
	//@Resource : @Autowired와 @Qulifier의 기능병합한 어노테이션. 타입과 이름까지 자동으로 매핑하여 의존성 주입
	//@Resource(name="apple")
	private Speaker speaker;
	
	public void powerOn() {
		System.out.println("LgTV---전원 켠다.");
	}
	
	public void powerOff() {
		System.out.println("LgTV---전원 끈다.");
	}
	
	public void volumeUp() {
		speaker.volumeUp();
	}
	
	public void volumeDown() {
		speaker.volumeDown();
	}
}
