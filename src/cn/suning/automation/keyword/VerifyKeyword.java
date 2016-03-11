package cn.suning.automation.keyword;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * 功能描述:页面校验关键字
 * @date 2016年3月10日15:51:37
 * @version 1.0.0
 * @author jiaozhongbin
 */
public class VerifyKeyword extends DriverFactory{
	private static Logger log = Logger.getLogger(VerifyKeyword.class);
	/**
	 * 
	 * 功能描述:判断页面某个元素是否显示
	 * @param by
	 *           By
	 * @param timeOutInSeconds
	 *            等待时间
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static boolean isElementVisible(final By by, long timeOutInSeconds) {
		WebDriverWait waitToExit = new WebDriverWait(driver, timeOutInSeconds);
		try {
			waitToExit.until(ExpectedConditions.visibilityOfElementLocated(by));
			log.info("元素可见");
		} catch (Exception e) {
			log.warn("元素在页面没有显示！");
			return false;
		}
		return true;
	}
	/**
	 * 
	 * 功能描述:判断页面某个元素是否显示
	 * 
	 * @param timeOutInSeconds
	 *            等待时间
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static boolean isElementVisible(final WebElement element, long timeOutInSeconds) {
		WebDriverWait waitToExit = new WebDriverWait(driver, timeOutInSeconds);
		try {
			waitToExit.until(ExpectedConditions.visibilityOf(element));
			log.info("元素可见");
		} catch (Exception e) {
			log.warn("元素在页面没有显示！");
			return false;
		}
		
		return true;
	}

	/**
	 * 
	 * 功能描述:判断某控件是否存在
	 * 
	 * @param timeOutInSeconds
	 *            等待时间
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			log.warn("当前元素不存在！");
			return false;
		}
	}
}
