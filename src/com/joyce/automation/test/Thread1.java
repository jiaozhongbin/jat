package com.joyce.automation.test;

import org.apache.log4j.Logger;

import com.joyce.automation.util.prop.PropKit;

public class Thread1 implements Runnable {
	private static Logger log = Logger.getLogger(Thread1.class);
	@Override
	public void run() {
		while(true){
			PropKit.use("Mail163_map.properties");
			log.error("163===="+PropKit.getLocator("login.username").toString());
		}
	}

}
