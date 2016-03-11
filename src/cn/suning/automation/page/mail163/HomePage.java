package cn.suning.automation.page.mail163;

import java.awt.AWTException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import cn.suning.automation.keyword.BrowserKeyword;
import cn.suning.automation.keyword.CommonKeyword;
import cn.suning.automation.keyword.DriverFactory;
import cn.suning.automation.keyword.WebElementKeyword;
import cn.suning.automation.util.prop.Prop;

public class HomePage {
	private Prop prop;

	public HomePage(Prop prop) {
		this.prop = prop;
		PageFactory.initElements(DriverFactory.driver, this);
		BrowserKeyword.browserMaximize();
	}

	/**
	 * 
	 * 功能描述:写信操作
	 * 
	 * @author jiaozhongbin
	 * @throws InterruptedException 
	 * @throws AWTException 
	 */
	public void writeMail() throws InterruptedException, AWTException {
		//点击写信链接
		WebElement writeMailBtn = WebElementKeyword.findElement(prop.getLocator("home.writeMailLink"));
		WebElementKeyword.elementClick(writeMailBtn);
		CommonKeyword.sleep(5000);
		//开始写信
		WebElementKeyword.keyBoardCtrlV(prop.get("writeMail.receiver"));
		WebElementKeyword.keyBoardTab();
		CommonKeyword.sleep(2000);
		WebElementKeyword.keyBoardCtrlV(prop.get("writeMail.topic"));
		WebElementKeyword.keyBoardTab();
		CommonKeyword.sleep(2000);
		WebElementKeyword.keyBoardCtrlV(prop.get("writeMail.context"));
		CommonKeyword.sleep(2000);
		//提交信件内容
		WebElement sendMailBtn = WebElementKeyword.findElement(prop.getLocator("writeMail.sendMailBtn")); 
		WebElementKeyword.elementClick(sendMailBtn);
		CommonKeyword.sleep(2000);
	}

}
