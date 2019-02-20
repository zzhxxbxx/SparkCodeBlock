package com.fenji.recommend.Utils;

/**
 * @Auther: Administrator
 * @Date: 2019/1/22 14:10
 * @Description:
 */

import org.apache.commons.lang.StringUtils;

/**
 * 字符串工具类
 */
public final class StringUtil {

	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String str) {
		if (str != null) {
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}

	/**
	 * 判断字符串是否非空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
