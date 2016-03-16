
package cn.suning.automation.config;


/**
 * Global constants definition
 */
public interface Const {
	/**
	 * 默认编码类型UTF-8
	 */
	String DEFAULT_ENCODING = "UTF-8";
	/**
	 * 元素默认分隔符
	 */
	String DEFAULT_SPLIT = ">";
	/**
	 * 浏览器类型(IE、Chrome、Firefox)
	 */
	public enum BROWSER_TYPE {
		IE, Chrome, Firefox,Sougou
	}

	/**
	 * 控件元素类型(ID、NAME、CLASSNAME、XPATH)
	 */
	public enum ELEMENT_TYPE {
		ID, NAME, CLASSNAME, XPATH,LINKTEXT
	}
}

