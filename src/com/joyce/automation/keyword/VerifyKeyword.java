package com.joyce.automation.keyword;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * 功能描述： 页面校验关键字
 * @author JIAOZHONGBIN
 * @date 创建时间：2016年7月14日 下午2:54:17 
 * @version 1.0 
 * @parameter 
 * @return
 */
public class VerifyKeyword extends DriverFactory {

	/**
	 * 
	 * 功能描述:判断页面某个元素是否显示
	 * 
	 * @param by
	 *            By
	 * @param timeOutInSeconds
	 *            等待时间
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static boolean isElementVisible(final By by, long timeOutInSeconds) {
		WebDriverWait waitToExit = new WebDriverWait(driver, timeOutInSeconds);
		waitToExit.until(ExpectedConditions.visibilityOfElementLocated(by));
		return true;
	}

	/**
	 * 
	 * 功能描述:判断页面某个元素是否显示
	 * 
	 * @param element
	 *            页面元素
	 * @param timeOutInSeconds
	 *            等待时间
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static boolean isElementVisible(final WebElement element, long timeOutInSeconds) {
		WebDriverWait waitToExit = new WebDriverWait(driver, timeOutInSeconds);
		waitToExit.until(ExpectedConditions.visibilityOf(element));
		return true;
	}

	/**
	 * 
	 * 功能描述:判断某控件是否存在
	 * 
	 * @param by
	 *            By
	 * @author jiaozhognbin
	 * @return boolean
	 */
	public static boolean isElementPresent(By by) {
		driver.findElement(by);
		return true;
	}
}