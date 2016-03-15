package cn.suning.automation.keyword;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.os.WindowsUtils;

/**
 * 
 * 功能描述: 页面关键字之浏览器操作关键字
 * 
 * @date 2016-3-7 15:22:48
 * @version 1.0.0
 * @author jiaozhongbin
 */
public class BrowserKeyword extends DriverFactory {

	private static Logger log = Logger.getLogger(BrowserKeyword.class);

	/**
	 * 
	 * 功能描述: 浏览器最大化
	 * 
	 * @param driver
	 *            浏览器驱动
	 * @author jiaozhongbin
	 */
	public static void browserMaximize() {
		driver.manage().window().maximize();
	}

	/**
	 * 
	 * 功能描述:模拟浏览器重新输入URL（用于浏览器已经打开时）
	 * 
	 * @param driver
	 *            浏览器驱动
	 * @param url
	 *            要跳转的URL
	 * @author jiaozhongbin
	 */
	public static void browserLocate(String url) {
		driver.navigate().to(url);
	}

	/**
	 * 
	 * 功能描述:浏览器前进
	 * 
	 * @param driver
	 *            浏览器驱动
	 * @author jiaozhongbin
	 */
	public static void browserForward() {
		driver.navigate().forward();
	}

	/**
	 * 
	 * 功能描述:浏览器后退
	 * 
	 * @param driver
	 *            浏览器驱动
	 * @author jiaozhongbin
	 */
	public static void browserBack() {
		driver.navigate().back();
	}

	/**
	 * 
	 * 功能描述:浏览器刷新
	 * 
	 * @param driver
	 *            浏览器驱动
	 * @author jiaozhongbin
	 */
	public static void browserRefresh() {
		driver.navigate().refresh();
	}

	/**
	 * 
	 * 功能描述:浏览器截屏
	 * 
	 * @param fileName
	 *            截屏文件名称
	 * @param screenPath
	 *            截屏文件存放地址
	 * @author jiaozhongbin
	 * @throws IOException
	 */
	public static void browserSnapshot(String fileName, String screenPath) throws IOException {
		StringBuffer sb = new StringBuffer();
		TakesScreenshot ts = (TakesScreenshot) driver;
		if (StringUtils.isNotBlank(fileName)) {
			sb.append(fileName);
		}
		sb.append(String.valueOf(new Date().getTime())).append(".jpg");// 截图文件名称
		File destFile = new File(screenPath);
		if (!destFile.exists()) {
			destFile.mkdirs();
		}
		String filePath = destFile.getAbsolutePath() + "/" + sb.toString();
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(filePath));
	}

	/**
	 * 
	 * 功能描述: 浏览器退出
	 * 
	 * @author jiaozhongbin
	 */
	public static void browserQuit() {
		driver.quit();
	}

	/**
	 * 
	 * 功能描述: 浏览器关闭
	 * 
	 * @author jiaozhongbin
	 */
	public static void browserClose() {
		driver.close();
	}

	/**
	 * 
	 * 功能描述:切换到新窗口
	 * 
	 * @author jiaozhongbin
	 */
	public static WebDriver switchWindow(String windowName) {
		return driver.switchTo().window(windowName);// 切换窗口
	}

	/**
	 * 
	 * 功能描述:获取当前窗口句柄
	 * 
	 * @author jiaozhongbin
	 */
	public static String getWindowHandler() {
		return driver.getWindowHandle();
	}

	/**
	 * 
	 * 功能描述:获取所有窗口句柄
	 * 
	 * @author jiaozhongbin
	 */
	public static Set<String> getWindowHandlers() {
		return driver.getWindowHandles();
	}

	/**
	 * 
	 * 功能描述:For an HTML element, this method returns a WebElement For a decimal,
	 * a Double is returned For a non-decimal number, a Long is returned For a
	 * boolean, a Boolean is returned For all other cases, a String is returned.
	 * For an array, return a List<Object> with each object following the rules
	 * above. We support nested lists. Unless the value is null or there is no
	 * return value, in which null is returned
	 * 
	 * @author jiaozhongbin
	 */
	public static Object executeJScript(String js) {
		return ((JavascriptExecutor) driver).executeScript(js);
	}

	/**
	 * 
	 * 功能描述:弹框点击操作
	 * 
	 * @param option
	 *            true or false
	 * @author jiaozhongbin
	 */
	public static boolean clickAlert(boolean option) {
		// 是否存在
		boolean flag = false;
		// 异常捕获
		try {
			Alert alert = driver.switchTo().alert();
			// 判断是否存在alert弹框
			if (null == alert) {
				throw new NoAlertPresentException();
			}
			// 异常捕获
			try {
				// 确认or取消
				if (option) {
					// 确认
					alert.accept();
					log.info("Accept the alert: " + alert.getText());
				} else {
					// 取消
					alert.dismiss();
					log.info("Dismiss the alert: " + alert.getText());
				}
				flag = true;
			} catch (WebDriverException e) {
				if (e.getMessage().startsWith("Could not find")) {
					log.info("没有发现 alert弹出框!");
				} else {
					throw e;
				}
			}
		} catch (NoAlertPresentException e) {
			log.info("没有发现 alert弹出框!");
		}
		return flag;
	}

	/**
	 * 
	 * 功能描述:清除当前所有的浏览器进程，包括IE、Firefox、Chrome。可作为容错步骤使用
	 * 
	 * @author jiaozhongbin
	 */
	public static void killAllBrowsers() {
		WindowsUtils.tryToKillByName("firefox.exe");
		WindowsUtils.tryToKillByName("iexplore.exe");
		WindowsUtils.tryToKillByName("chrome.exe");
	}

	/**
	 * 
	 * 功能描述:清除指定浏览器进程
	 * 
	 * @param browserName
	 *            浏览器进程名称
	 * @author jiaozhongbin
	 */
	public static void killBrowserByName(String browserName) {
		if (StringUtils.isBlank(browserName)) {
			throw new NullPointerException("浏览器进程名称为null");
		} else {
			WindowsUtils.tryToKillByName(browserName);
		}
	}

	/**
	 * 
	 * 功能描述:获取当前页面URL
	 * 
	 * @author jiaozhongbin
	 */
	public static String getBrowserUrl() {
		
		return driver.getCurrentUrl();
	}

	/**
	 * 
	 * 功能描述:根据输入的像素距离滚动条向下移动，缺省值为-1，表示移动至页尾。为0时，表示移动至页头。
	 * @param height
	 *         滚动距离
	 * @author jiaozhongbin
	 */
	public static void scrollVerticalBar(int height) {
		Long totalHeight = (Long) executeJScript("return document.body.scrollHeight");
		String js = "";
		if (height == 0) {
			js = "window.scrollTo(0, 0)";
		} else if (height == -1) {
			js = "window.scrollTo(0, " + totalHeight + ")";
		} else {
			js = "window.scrollTo(0, " + height + ")";
		}
		executeJScript(js);
	}
}
