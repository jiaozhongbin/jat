package cn.suning.automation.keyword;

import org.apache.log4j.Logger;

/**
 * 
 * 功能描述: 通用关键字
 * 
 * @date 2016-3-7 15:22:48
 * @version 1.0.0
 * @author jiaozhongbin
 */
public class CommonKeyword {
	private static Logger log = Logger.getLogger(CommonKeyword.class);

	/**
	 * 
	 * 功能描述:等待时间设置
	 * @param milliseconds
	 *            毫秒
	 * @author jiaozhongbin
	 * @throws InterruptedException 
	 */
	public static void sleep(long milliseconds) throws InterruptedException {
		if (milliseconds >= 0) {
			Thread.sleep(milliseconds);
			log.info("等待"+milliseconds+"毫秒");
		} else {
			log.error("等待时间设置不正确");
		}

	}
}
