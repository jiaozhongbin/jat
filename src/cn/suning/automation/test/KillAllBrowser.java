package cn.suning.automation.test;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.suning.automation.config.Const;

public class KillAllBrowser {
	private static Logger log = Logger.getLogger(KillAllBrowser.class);
	private String browserType;
	@BeforeClass
	public void beforeMethod() {
		browserType = Const.BROWSER_TYPE.IE.toString();
	}

	@Test
	public void tc01_Login(){
		
	}
	@AfterClass
	public void afterMethod() {
//		BrowserKeyword.browserQuit();
	}
}
