package cn.suning.automation.testSuite.mail163;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.suning.automation.keyword.BrowserKeyword;
import cn.suning.automation.page.mail163.HomePage;
import cn.suning.automation.page.mail163.LoginPage;
import cn.suning.automation.util.prop.Prop;
import cn.suning.automation.util.prop.PropKit;


/**
 * 
 * 功能描述:163测试套
 * @author jiaozhongbin
 */
public class TestSuite02 {

	private static Logger log = Logger.getLogger(TestSuite02.class);
	private Prop prop;
	private String browserType;
	@BeforeClass
	public void beforeMethod() {
		prop = PropKit.use("Mail163_map.properties");
		browserType = "Firefox";
	}

	@Test
	public void tc01_Login(){
		try {
			log.info("测试登陆用例start");
			LoginPage loginPage = new LoginPage(prop, browserType);
			loginPage.login();
			BrowserKeyword.browserSnapshot("loginSuccess",prop.get("snapshotPath"));
			log.info("测试登陆用例end");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void tc02_writeMail(){
		try {
			log.info("测试写信用例start");
			HomePage homePage = new HomePage(prop);
			homePage.writeMail();
			BrowserKeyword.browserSnapshot("writeMailSuccess",prop.get("snapshotPath"));
			log.info("测试写信用例end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterMethod() {
		BrowserKeyword.browserQuit();
	}
}
