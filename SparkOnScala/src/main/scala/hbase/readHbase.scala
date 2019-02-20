package hbase

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{Result, Scan}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.util.Base64
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * @Auther: zzh
  * @Date: 2019/2/19 14:33
  * @Description:
  */
object readHbase {
	def main(args: Array[String]): Unit = {
		val sparkConf = new SparkConf()
        		.setAppName("readHbase")
        		.setMaster("local[2]")
		val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
		val sparkContext = sparkSession.sparkContext

		val hbaseConfiguration = HBaseConfiguration.create()
		hbaseConfiguration.set("hbase.zookeeper.quorum","hadoop1:2181,hadoop2:2181,hadoop3:2181")
		hbaseConfiguration.set(TableInputFormat.INPUT_TABLE,"dev_topic")
		val scan = new Scan()
		val value = ProtobufUtil.toScan(scan)
		val str = Base64.encodeBytes(value.toByteArray)
		hbaseConfiguration.set(TableInputFormat.SCAN,str)

		val hbaseRDD = sparkContext.newAPIHadoopRDD(
			hbaseConfiguration,classOf[TableInputFormat],
			classOf[ImmutableBytesWritable],
			classOf[Result])
		val l = hbaseRDD.count()
		println(l)
	}
}
