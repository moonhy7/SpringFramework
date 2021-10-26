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
