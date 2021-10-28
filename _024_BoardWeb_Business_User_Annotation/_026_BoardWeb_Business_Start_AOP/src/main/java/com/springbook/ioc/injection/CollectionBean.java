package com.springbook.ioc.injection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionBean {
	private List<String> addressList;
	private Set<String> addressList2;
	private Map<String, String> addressList3;
	private Properties addressList4;

	public List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}

	public Set<String> getAddressList2() {
		return addressList2;
	}

	public void setAddressList2(Set<String> addressList2) {
		this.addressList2 = addressList2;
	}

	public Map<String, String> getAddressList3() {
		return addressList3;
	}

	public void setAddressList3(Map<String, String> addressList3) {
		this.addressList3 = addressList3;
	}

	public Properties getAddressList4() {
		return addressList4;
	}

	public void setAddressList4(Properties addressList4) {
		this.addressList4 = addressList4;
	}
}
