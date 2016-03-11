package cn.suning.automation.test;

import org.apache.log4j.Logger;

import cn.suning.automation.util.prop.PropKit;

public class Thread2 implements Runnable {
	private static Logger log = Logger.getLogger(Thread2.class);
	@Override
	public void run() {
		while(true){
			PropKit.use("Mail153_map.properties");
			log.error("153===="+PropKit.getLocator("login.username").toString());
		}
	}

}
