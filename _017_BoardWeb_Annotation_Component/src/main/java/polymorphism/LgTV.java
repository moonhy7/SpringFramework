package polymorphism;

import org.springframework.stereotype.Component;

@Component//==> id나 name 미지정 시 lgTV 자동으로 이름이 지정됨
//<bean class="polymorphism.LgTV></bean>
//@Component("tv")
//<bean id="tv" class="polymorphism.LgTV></bean>
public class LgTV implements TV {
	public void powerOn() {
		System.out.println("LgTV---전원 켠다.");
	}
	
	public void powerOff() {
		System.out.println("LgTV---전원 끈다.");
	}
	
	public void volumeUp() {
		System.out.println("LgTV---소리 올린다.");
	}
	
	public void volumeDown() {
		System.out.println("LgTV---소리 내린다.");
	}
}
