package polymorphism;

public class BeanFactory { //클라이언트에서 자동적으로 TV객체를 생성할 수 있도록 설정
	public Object getBean(String beanName) {
		if(beanName.equals("samsung")) {
			return new SamsungTV();
		} else if(beanName.equals("lg")) {
			return new LgTV();
		}
		return null;
	}
}
