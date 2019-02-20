package com.fenji.recommend.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

/**
 * @Auther: Administrator
 * @Date: 2019/2/20 14:54
 * @Description:
 */
public class Context {
	public static SparkSession getSparkSession(){
		SparkConf sparkConf = new SparkConf()
				.setMaster("local[2]")
				.setAppName("recommend_3.0");
		SparkSession sparkSession = SparkSession.builder()
				.config(sparkConf)
				.getOrCreate();
		return sparkSession;
	}
}
