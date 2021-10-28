package polymorphism;

public class SamsungTV implements TV {
	private Speaker speaker;
	private int price;	

	public SamsungTV() {
		System.out.println("====> SamsungTV 객체(1) 생성");
	}

	public void setSpeaker(Speaker speaker) {
		System.out.println("====> setSpeaker() 호출");
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		System.out.println("====> setPrice() 호출");
		this.price = price;
	}

	public void initMethod() {
		System.out.println("객체 초기화 작업 처리...");
	}
	
	public void destroyMethod() {
		System.out.println("객체 삭제전 처리할 로직...");
	}
	
	public void powerOn() {
		System.out.println("SamsungTV---전원 켠다. (가격 : " + price + ")");
	}
	
	public void powerOff() {
		System.out.println("SamsungTV---전원 끈다.");
	}
	
	public void volumeUp() {
		/* speaker = new SonySpeaker(); */
		speaker.volumeUp();
//		System.out.println("SamsungTV---소리 올린다.");
	}
	
	public void volumeDown() {
		/* speaker = new SonySpeaker(); */
		/* speaker = new AppleSpeaker(); */
		speaker.volumeDown();
//		System.out.println("SamsungTV---소리 내린다.");
	}
}
