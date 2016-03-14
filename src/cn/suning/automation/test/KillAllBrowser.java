package cn.suning.automation.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.suning.automation.config.Const;
import cn.suning.automation.keyword.BrowserKeyword;
import cn.suning.automation.keyword.DriverFactory;

public class KillAllBrowser {
	private static Logger log = Logger.getLogger(KillAllBrowser.class);
	@BeforeClass
	public void beforeMethod() {
		
	}

	@Test
	public void tc01_fireFox(){
		// 初始化火狐浏览器driver
		WebDriver fireFox = DriverFactory.initWebDriver(Const.BROWSER_TYPE.Firefox.toString());
		
	}

	@Test
	public void tc01_IE(){
		// 初始化火狐浏览器driver
		WebDriver IE = DriverFactory.initWebDriver(Const.BROWSER_TYPE.IE.toString());
		
	}

	@Test
	public void tc01_chrome(){
		// 初始化火狐浏览器driver
		WebDriver chrome = DriverFactory.initWebDriver(Const.BROWSER_TYPE.Chrome.toString());
		
	}
	@AfterClass
	public void afterMethod() {
//		BrowserKeyword.killAllBrowsers();
		BrowserKeyword.killBrowserByName("");
	}
}
