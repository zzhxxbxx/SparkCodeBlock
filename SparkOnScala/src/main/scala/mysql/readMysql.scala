package mysql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @Auther: Administrator
  * @Date: 2019/2/19 14:33
  * @Description:
  */
object readMysql {
	def main(args: Array[String]): Unit = {
		val sparkConf:SparkConf = new SparkConf()
        		.setMaster("local[2]")
        		.setAppName("readMysql")
		val sparkSession:SparkSession = SparkSession.builder()
				.config(sparkConf)
				.getOrCreate()
		val dataFrame:DataFrame = sparkSession.read.format("jdbc")
				.options(
					Map("url"->"jdbc:mysql://bj-cdb-4v0fwff9.sql.tencentcdb.com:63530/fj_dev_data?characterEncoding=utf-8",
						"dbtable"->"(select * from category where a_category_id>2) tmp",
						"user"->"root",
						"password"->"fenji@sql123",
						"driver"->"com.mysql.jdbc.Driver")
				).load()
//		val value = dataFrame.where("a_category_id<5")
		dataFrame.show(10)
	}
}
