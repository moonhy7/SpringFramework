package com.springbook.ioc.injection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		
//		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
//		List<String> addressList = bean.getAddressList();
//		for(String address : addressList) {
//			System.out.println(address.toString());
//		}
		
//		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
//		Set<String> addressList = bean.getAddressList2(); //List를 Set으로, address에 2붙이기
//		for(String address : addressList) {
//			System.out.println(address.toString());
//		}
		
//		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
//		Map<String, String> addressList = bean.getAddressList3();
//		System.out.println(addressList.get("고길동"));
//		System.out.println(addressList.get("마이콜"));
		
//		for(String key : addressList.keySet()) {
//			System.out.println(addressList.get(key));
//		}
		
		
		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
		Properties addressList = bean.getAddressList4();
		System.out.println(addressList.get("고길동"));
		System.out.println(addressList.get("마이콜"));
		
		
		factory.close();
	}
}
