package cn.suning.automation.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.suning.automation.config.Const;
import cn.suning.automation.keyword.BrowserKeyword;
import cn.suning.automation.keyword.CommonKeyword;
import cn.suning.automation.keyword.DriverFactory;

public class clickAlert {
	private static Logger log = Logger.getLogger(clickAlert.class);
	@BeforeClass
	public void beforeMethod() {
		// 初始化浏览器driver
		WebDriver driver = DriverFactory.initWebDriver(Const.BROWSER_TYPE.Chrome.toString());
	}

	@Test
	public void tc01_chrome() throws InterruptedException{
		BrowserKeyword.executeJScript("if(confirm('这是一个alert弹出框')){alert('true')}else{ alert('false')}");
		CommonKeyword.sleep(2000);
		BrowserKeyword.clickAlert(false);
		
	}
	@AfterClass
	public void afterMethod() throws InterruptedException {
		CommonKeyword.sleep(2000);
		BrowserKeyword.browserQuit();
	}
}
