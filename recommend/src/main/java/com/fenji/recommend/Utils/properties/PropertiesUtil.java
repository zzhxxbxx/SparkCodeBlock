package com.fenji.recommend.Utils.properties;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * 类名称：PropertiesUtil
 * 类描述： 文件读取类
 * 创建人：Jxufe HeHaiYang
 * 创建时间：2015-1-20 下午03:14:02
 * 修改备注：
 * @version
 */
public class PropertiesUtil {

	private static Properties properties=new Properties();

	private static PropertiesUtil propertiesUtil;

	private PropertiesUtil(){
	}

	private static void loadFile(String filename){
		try {
			properties.load(PropertiesUtil.class.getResourceAsStream("/"+filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized PropertiesUtil createPropertiesUtil(String filename){
		if (propertiesUtil==null) {
			propertiesUtil=new PropertiesUtil();
		}
		loadFile(filename);
		return propertiesUtil;
	}

	public String getProperty(String key){
		return properties.getProperty(key);
	}

}

