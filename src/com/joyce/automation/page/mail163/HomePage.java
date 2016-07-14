package com.joyce.automation.page.mail163;

import java.awt.AWTException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.joyce.automation.keyword.BrowserKeyword;
import com.joyce.automation.keyword.CommonKeyword;
import com.joyce.automation.keyword.DriverFactory;
import com.joyce.automation.keyword.VerifyKeyword;
import com.joyce.automation.keyword.WebElementKeyword;
import com.joyce.automation.util.prop.Prop;

/**
 * 
 * 功能描述： 163主页
 * @author JIAOZHONGBIN
 * @date 创建时间：2016年7月14日 下午2:57:44 
 * @version 1.0 
 * @parameter 
 * @return
 */
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
		//验证发送是否成功
		VerifyKeyword.isElementVisible(prop.getLocator("writeMail.sendMailSuccess"), 5);
	}

}
