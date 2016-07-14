package cn.suning.automation.keyword;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * 功能描述： 页面元素关键字
 * @author JIAOZHONGBIN
 * @date 创建时间：2016年7月14日 下午2:54:26 
 * @version 1.0 
 * @parameter 
 * @return
 */
public class WebElementKeyword extends DriverFactory {
	private static Logger log = Logger.getLogger(WebElementKeyword.class);

	/**
	 * 
	 * 功能描述:查找元素
	 * 
	 * @param by
	 *            web元素定位对象
	 * @author jiaozhongbin
	 * @return WebElement
	 */
	public static WebElement findElement(By by) {
		return driver.findElement(by);
	}

	/**
	 * 
	 * 功能描述:文本框文本输入
	 * 
	 * @param webElement
	 *            页面元素对象
	 * @param isClear
	 *            是否清楚原内容(1=是,2=否)
	 * @param text
	 *            待输入的内容
	 * @author jiaozhongbin
	 */
	public static void textInput(WebElement webElement, int isClear, String text) {
		if (isClear == 1) {
			webElement.clear();
		}
		webElement.sendKeys(text);
	}

	/**
	 * 
	 * 功能描述:控件单击
	 * 
	 * @param driver
	 *            浏览器驱动
	 * @param webElement
	 *            页面元素对象
	 * @author jiaozhongbin
	 */
	public static void elementClick(WebElement webElement) {
		webElement.click();
	}

	/**
	 * 
	 * 功能描述:等待某控件可见/不可见,等待最多为配置的最大等待时间，超时与预期判断不符则抛异常
	 * 
	 * @param timeOutInSeconds
	 *            等待时间
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static boolean waitForElementDisplayed(final By by, long timeOutInSeconds) {
		boolean waitToExit = new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				return findElement(by).isDisplayed();
			}
		});
		return waitToExit;
	}

	/**
	 * 
	 * 功能描述:等待某控件可见/不可见,等待最多为配置的最大等待时间，超时与预期判断不符则抛异常
	 * 
	 * @param timeOutInSeconds
	 *            等待时间
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static boolean waitForElementDisplayed(final WebElement element, long timeOutInSeconds) {
		boolean waitToExit = new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				return element.isDisplayed();
			}
		});
		return waitToExit;
	}

	/**
	 * 
	 * 功能描述:获取页面title
	 * 
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 
	 * 功能描述:获取页面源代码
	 * 
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static String getPageSource() {
		return driver.getPageSource();
	}

	/**
	 * 
	 * 功能描述:获取当前页面句柄
	 * 
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static String getWindowHandle() {
		return driver.getWindowHandle();
	}

	/**
	 * 
	 * 功能描述:加载页面
	 * 
	 * @author jiaozhognbin
	 * @return void
	 */
	public static void get(String url) {
		driver.get(url);
	}

	/**
	 * 
	 * 功能描述:键盘操作-粘贴
	 * 
	 * @param value
	 *            待粘贴的值
	 * @author jiaozhongbin
	 * @throws AWTException
	 */
	public static void keyBoardCtrlV(String value) throws AWTException {
		StringSelection stringSelection = new StringSelection(value);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		// 模拟Ctrl+V，进行粘贴
		Robot robot = null;
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		log.info("粘贴{" + value + "}成功");
	}

	/**
	 * 
	 * 功能描述:键盘操作-Tab
	 * 
	 * @author jiaozhongbin
	 * @throws AWTException
	 */
	public static void keyBoardTab() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}
}
