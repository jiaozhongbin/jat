package cn.suning.automation.page.mail163;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import cn.suning.automation.keyword.BrowserKeyword;
import cn.suning.automation.keyword.DriverFactory;
import cn.suning.automation.keyword.VerifyKeyword;
import cn.suning.automation.keyword.WebElementKeyword;
import cn.suning.automation.util.prop.Prop;

public class LoginPage extends LoadableComponent<LoginPage> {
	private Prop prop;
	/**
	 * 网易用户名
	 */
	@FindBy(id = "idInput")
	private WebElement username;
	/**
	 * 网易密码
	 */
	@FindBy(name = "password")
	private WebElement password;
	/**
	 * 登陆按钮
	 */
	@FindBy(id = "loginBtn")
	private WebElement loginBtn;
	/**
	 * 退出
	 */
	@FindBy(xpath = "//*[@id='_mail_component_37_37']/a")
	private WebElement loginout;
	
	public LoginPage(Prop prop,String browserType){
		this.prop = prop;
		DriverFactory.initWebDriver(browserType);
		PageFactory.initElements(DriverFactory.driver, this);
		BrowserKeyword.browserMaximize();		
	}
	/**
	 * 
	 * 功能描述:登陆操作
	 * @author jiaozhongbin
	 */
	public void login(){
		this.load();
		VerifyKeyword.isElementVisible(username, 5);
		WebElementKeyword.textInput(username, 1, prop.get("login.username.input"));
		WebElementKeyword.textInput(password, 1, prop.get("login.password.input"));
		loginBtn.click();
		Assert.assertTrue(VerifyKeyword.isElementVisible(loginout, 10));
	}
	@Override
	protected void load() {
		WebElementKeyword.get(prop.get("login.url"));
	}

	@Override
	protected void isLoaded() throws Error {
		String title = prop.get("login.title");
		Assert.assertTrue(WebElementKeyword.getTitle().contains(title));
	}
	
}
