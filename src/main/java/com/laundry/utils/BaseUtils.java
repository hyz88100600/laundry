package com.laundry.utils;

import org.apache.commons.lang3.StringUtils;

public class BaseUtils {

	// 验证手机号是否符合规则
	public static boolean phoneCheck(String phone) {
		if (StringUtils.isBlank(phone)) {
			return false;
		}
		String regex = "1[358][0-9]{9,9}";
		return phone.matches(regex);
	}
}
