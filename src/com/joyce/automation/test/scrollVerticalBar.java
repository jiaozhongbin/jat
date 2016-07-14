package com.joyce.automation.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.joyce.automation.config.Const;
import com.joyce.automation.keyword.BrowserKeyword;
import com.joyce.automation.keyword.CommonKeyword;
import com.joyce.automation.keyword.DriverFactory;

public class scrollVerticalBar {
	private static Logger log = Logger.getLogger(scrollVerticalBar.class);
	@BeforeClass
	public void beforeMethod() {
		// 初始化浏览器driver
		WebDriver driver = DriverFactory.initWebDriver(Const.BROWSER_TYPE.Chrome.toString());
	}

	@Test
	public void tc01_chrome() throws InterruptedException{
		BrowserKeyword.browserLocate("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=sd&rsv_pq=e64827bc0000b8c4&rsv_t=4599ssddI6kO7SjrWvddTdm%2FcDhKrOXRzp1ORIYb7Yul69Bn7xzxmrJRoaM&rsv_enter=0&rsv_sug3=2&rsv_sug1=2&rsv_sug7=100&inputT=1041&rsv_sug4=1041");
		BrowserKeyword.scrollVerticalBar(-1);
		CommonKeyword.sleep(2000);
	}
	@AfterClass
	public void afterMethod() throws InterruptedException {
		CommonKeyword.sleep(5000);
		BrowserKeyword.browserQuit();
	}
}
