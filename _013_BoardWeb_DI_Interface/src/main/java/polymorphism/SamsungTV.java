package polymorphism;

public class SamsungTV implements TV {
	//소니스피커 객체 생성
	private Speaker speaker;
	private int price;
	
	//생성자 만들기
	public SamsungTV() {
		System.out.println("====> SamsungTV 객체(1) 생성");
	}
	
	public SamsungTV(Speaker speaker) {
		System.out.println("====> SamsungTV 객체(2) 생성");
		this.speaker = speaker; // 빈 객체에 추가해주었기때문에 자동으로 소니스피커를 넣어줌
	}
	
	public SamsungTV(Speaker speaker, int price){
		System.out.println("====> SamsungTV 객체(3) 생성");
		this.speaker = speaker; // 빈 객체에 추가해주었기때문에 자동으로 소니스피커를 넣어줌
		this.price = price; //소니스피커가 0번째, 가격이 1번째 인덱스가 됨
	}
	
	
	//initMethod() 초기화 작업 진행
	public void initMethod() {
		System.out.println("객체 초기화 작업 처리...");
	}
	
	//destroyMethod() 객체 삭제전 처리할 로직
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
//		speaker = new SonySpeaker();
		speaker.volumeUp();
//		System.out.println("SamsungTV---소리 올린다.");
	}
	
	public void volumeDown() {
//		speaker = new SonySpeaker();
		speaker.volumeDown();
//		System.out.println("SamsungTV---소리 내린다.");
	}
}
