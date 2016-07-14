/**
 * Copyright (c) 2011-2016, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.joyce.automation.util.prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.joyce.automation.config.Const;
import com.joyce.automation.util.log.LogKit;

/**
 * Prop. Prop can load properties file from CLASSPATH or File object.
 */
public class Prop {
	private static Logger log = Logger.getLogger(Prop.class);
	private Properties properties = null;

	/**
	 * Prop constructor.
	 * 
	 * @see #Prop(String, String)
	 */
	public Prop(String fileName) {
		this(fileName, Const.DEFAULT_ENCODING);
	}

	/**
	 * Prop constructor
	 * <p>
	 * Example:<br>
	 * Prop prop = new Prop("my_config.txt", "UTF-8");<br>
	 * String userName = prop.get("userName");<br>
	 * <br>
	 * 
	 * prop = new Prop("com/jfinal/file_in_sub_path_of_classpath.txt", "UTF-8");
	 * <br>
	 * String value = prop.get("key");
	 * 
	 * @param fileName
	 *            the properties file's name in classpath or the sub directory
	 *            of classpath
	 * @param encoding
	 *            the encoding
	 */
	public Prop(String fileName, String encoding) {
		InputStream inputStream = null;
		try {
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName); // properties.load(Prop.class.getResourceAsStream(fileName));
			if (inputStream == null)
				throw new IllegalArgumentException("Properties file not found in classpath: " + fileName);
			properties = new Properties();
			properties.load(new InputStreamReader(inputStream, encoding));
		} catch (IOException e) {
			throw new RuntimeException("Error loading properties file.", e);
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					LogKit.error(e.getMessage(), e);
				}
		}
	}

	/**
	 * Prop constructor.
	 * 
	 * @see #Prop(File, String)
	 */
	public Prop(File file) {
		this(file, Const.DEFAULT_ENCODING);
	}

	/**
	 * Prop constructor
	 * <p>
	 * Example:<br>
	 * Prop prop = new Prop(new File("/var/config/my_config.txt"), "UTF-8");<br>
	 * String userName = prop.get("userName");
	 * 
	 * @param file
	 *            the properties File object
	 * @param encoding
	 *            the encoding
	 */
	public Prop(File file, String encoding) {
		if (file == null)
			throw new IllegalArgumentException("File can not be null.");
		if (file.isFile() == false)
			throw new IllegalArgumentException("File not found : " + file.getName());

		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(new InputStreamReader(inputStream, encoding));
		} catch (IOException e) {
			throw new RuntimeException("Error loading properties file.", e);
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					LogKit.error(e.getMessage(), e);
				}
		}
	}

	public String get(String key) {
		return properties.getProperty(key);
	}

	public String get(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public Integer getInt(String key) {
		return getInt(key, null);
	}

	public Integer getInt(String key, Integer defaultValue) {
		String value = properties.getProperty(key);
		if (value != null)
			return Integer.parseInt(value.trim());
		return defaultValue;
	}

	public Long getLong(String key) {
		return getLong(key, null);
	}

	public Long getLong(String key, Long defaultValue) {
		String value = properties.getProperty(key);
		if (value != null)
			return Long.parseLong(value.trim());
		return defaultValue;
	}

	public Boolean getBoolean(String key) {
		return getBoolean(key, null);
	}

	public Boolean getBoolean(String key, Boolean defaultValue) {
		String value = properties.getProperty(key);
		if (value != null) {
			value = value.toLowerCase().trim();
			if ("true".equals(value))
				return true;
			else if ("false".equals(value))
				return false;
			throw new RuntimeException("The value can not parse to Boolean : " + value);
		}
		return defaultValue;
	}

	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * 
	 * 功能描述:根据配置文件的配置读取元素对象
	 * 
	 * @param elementNameInProp
	 *            元素配置文件中的key(eg:login.username=id>value )
	 * @author jiaozhongbin
	 * @throws Exception
	 */
	public By getLocator(String elementNameInProp) {
		String locator = properties.getProperty(elementNameInProp);
		String locatorType = locator.split(Const.DEFAULT_SPLIT)[0];
		String locatorValue = locator.split(Const.DEFAULT_SPLIT)[1];
		try {
//			locatorValue = new String(locatorValue.getBytes("ISO-8859-1"), "UTF-8");
			if (locatorType.toLowerCase().equals("id")) {
				return By.id(locatorValue);
			} else if (locatorType.toLowerCase().equals("xpath")) {
				return By.xpath(locatorValue);
			} else if (locatorType.toLowerCase().equals("name")) {
				return By.name(locatorValue);
			} else if (locatorType.toLowerCase().equals("classname") || locatorType.toLowerCase().equals("class")) {
				return By.className(locatorValue);
			} else if (locatorType.toLowerCase().equals("tagname") || locatorType.toLowerCase().equals("tag")) {
				return By.tagName(locatorValue);
			} else if (locatorType.toLowerCase().equals("linktext") || locatorType.toLowerCase().equals("link")) {
				return By.linkText(locatorValue);
			} else if (locatorType.toLowerCase().equals("partiallinktext")) {
				return By.partialLinkText(locatorValue);
			} else if (locatorType.toLowerCase().equals("cssselector") || locatorType.toLowerCase().equals("css")) {
				return By.cssSelector(locatorValue);
			} else
				log.error("输入的locatorType未在程序中定义:" + locatorType);
			return null;
		}catch (IllegalArgumentException e1) {
			log.error("查找元素{" + locatorType + "=" + locatorValue + "}失败！", e1);
		}
		return null;
	}
	/**
	 * 
	 * 功能描述:根据配置文件的配置读取元素对象类型
	 * 
	 * @param elementNameInProp
	 *            元素配置文件中的key(eg:login.username=id>value )
	 * @author jiaozhongbin
	 * return locatorType
	 */
	public String getLocatorType(String elementNameInProp) {
		String locator = properties.getProperty(elementNameInProp);
		String locatorType = locator.split(Const.DEFAULT_SPLIT)[0];
		return locatorType;
	}
	/**
	 * 
	 * 功能描述:根据配置文件的配置读取元素对象值
	 * 
	 * @param elementNameInProp
	 *            元素配置文件中的key(eg:login.username=id>value )
	 * @author jiaozhongbin
	 * @reture locatorValue
	 */
	public String getLocatorValue(String elementNameInProp) {
		String locator = properties.getProperty(elementNameInProp);
		String locatorValue = locator.split(Const.DEFAULT_SPLIT)[1];
		return locatorValue;
	}
}
