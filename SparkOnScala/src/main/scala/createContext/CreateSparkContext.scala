package createContext

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Auther: Administrator
  * @Date: 2019/2/19 11:28
  * @Description:
  */
object CreateSparkContext {
	def main(args: Array[String]): Unit = {
		val sparkConf:SparkConf = new SparkConf()
		val sparkSession:SparkSession = SparkSession.builder
				.master("local[*]")
        		.appName("sparkSession")
        		.config(sparkConf)
        		.enableHiveSupport() //允许使用hive
        		.getOrCreate()
	}

}
