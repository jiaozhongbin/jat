package cn.suning.automation.keyword;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cn.suning.automation.config.Const;
import cn.suning.automation.util.prop.PropKit;

/**
 * 
 * 功能描述:驱动管理类
 * 
 * @date 2016-3-7 15:22:48
 * @version 1.0.0
 * @author jiaozhongbin
 */
public class DriverFactory {
	private static Logger log = Logger.getLogger(DriverFactory.class);
	public static WebDriver driver;

	/**
	 * 
	 * 功能描述: 浏览器打开,获取浏览器驱动对象
	 * 
	 * @param browserType
	 *            浏览器类型(如果参数为null则默认调用全局配置的浏览器类型)
	 * @author jiaozhongbin
	 * @return WebDriver
	 */
	public static WebDriver initWebDriver(String browserType) {
		if (StringUtils.isBlank(browserType)) {
			browserType = PropKit.use("globalConfig.properties").get("BrowserType");
		}
		if (browserType.equals(Const.BROWSER_TYPE.IE.toString())) {// 打开IE浏览器
			driver = new InternetExplorerDriver();
		} else if (browserType.equals(Const.BROWSER_TYPE.Chrome.toString())) {// 打开google浏览器
			driver = new ChromeDriver();
		} else if (browserType.equals(Const.BROWSER_TYPE.Firefox.toString())) {// 打开火狐浏览器
			System.setProperty("webdriver.firefox.bin", PropKit.use("globalConfig.properties").get("DriverExePath"));
			driver = new FirefoxDriver();
		} else {
			log.error("浏览器类型字段输入报错.");
			return null;
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

}
