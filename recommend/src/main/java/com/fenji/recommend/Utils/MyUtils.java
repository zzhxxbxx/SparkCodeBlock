package com.fenji.recommend.Utils;

import org.apache.hadoop.hbase.util.Bytes;

import java.util.*;

/**
 * @Auther: Administrator
 * @Date: 2019/1/16 10:31
 * @Description:
 */
public class MyUtils {
	public static int getRowKey(int i, int len){
		byte[] bytes = Bytes.toBytes(i);
		return 0;
	}
	public static int getRowKey(Integer i, int len){
		byte[] bytes = Bytes.toBytes(i);
		return 0;
	}
	/**
	 *
	 * @param map HashMap<String, Integer> 按照值进行排序
	 * @return：返回排序后的Map
	 */
	public static HashMap<String, Integer> hashMapSort(HashMap<String, Integer> map){
		//1、按顺序保存map中的元素，使用LinkedList类型
		List<Map.Entry<String, Integer>> keyList = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		//2、按照自定义的规则排序
		Collections.sort(keyList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1,
			                   Map.Entry<String, Integer> o2) {
				if(o2.getValue().compareTo(o1.getValue())>0){
					return 1;
				}else if(o2.getValue().compareTo(o1.getValue())<0){
					return -1;
				}  else {
					return 0;
				}
			}

		});
		//3、将LinkedList按照排序好的结果，存入到HashMap中
		HashMap<String,Integer> result=new LinkedHashMap<>();
		for(Map.Entry<String, Integer> entry:keyList){
			result.put(entry.getKey(),entry.getValue());
		}
		return result;
	}

	/**
	 *
	 * @param id    数字
	 * @param len   返回字符串的长度
	 * @return      八位整齐的字符串
	 */
	public static String toRowKey(Integer id,int len){
		String rowKey = id.toString();
		int n = rowKey.length();
		for(int i=0;i<len-n;i++){
			rowKey = "0"+rowKey;
		}
		return rowKey;
	}
}
