package cn.suning.automation.test;

import cn.suning.automation.util.prop.PropKit;

public class PropTest {

	public static void main(String[] args) {
//		Thread t1 = new Thread(new Thread1());
//		Thread t2 = new Thread(new Thread2());
//		t1.start();
//		t2.start();
		System.out.println(PropKit.use("Mail163_map.properties").getLocatorValue("login.username"));
	}
}
