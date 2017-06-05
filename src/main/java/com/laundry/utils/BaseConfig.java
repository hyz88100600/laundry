/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.laundry.utils;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 全局配置类
 * @author ThinkGem
 * @version 2013-03-23
 */
public class BaseConfig {
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");
	
	/**
	 * 获取配置
	 */
	public static String getCfgValue(String key) {
		String value = map.get(key);
		if (value == null){
			value =  propertiesLoader.getProperty(key);
			map.put(key, value);
		}
		return value;
	}
}
