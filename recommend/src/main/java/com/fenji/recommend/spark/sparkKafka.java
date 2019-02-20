package com.fenji.recommend.spark;

import com.fenji.recommend.Utils.properties.PropertiesUtil;
import com.fenji.recommend.constant.Constant;
import com.fenji.recommend.service.RecommendAchieve;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2019/2/20 15:01
 * @Description:
 */
public class sparkKafka {
	public static void main(String[] args) {
		RecommendAchieve recommendAchieve = new RecommendAchieve();

		//获取常量
		PropertiesUtil pUtil = PropertiesUtil.createPropertiesUtil(Constant.UPLOADPATH_FILE);
		//接收数据的地址和端口
		String zkQuorum = pUtil.getProperty(Constant.KAFKA_ZOOKEEPER_QUORUM);
		//话题所在的组
		String group = pUtil.getProperty(Constant.GROUP);
		//话题名称以“，”分隔
		String topics = pUtil.getProperty(Constant.TOPICS);
		//每个话题的分片数
		int numThreads = 2;
		SparkConf sparkConf = new SparkConf()
				.setAppName(pUtil.getProperty(Constant.SPARK_APP_NAME))
				.setMaster(pUtil.getProperty(Constant.SPARK_MASTER));
		new JavaSparkContext();
		new SparkContext();
		JavaStreamingContext jssc = new JavaStreamingContext(JavaSparkContext.fromSparkContext(Context.getSparkSession().sparkContext()), new Duration(1000));
//		StreamingContext jssc = new StreamingContext(Context.getSparkSession().sparkContext(), new Duration(1000));
//		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(1000));
		jssc.checkpoint("checkpoint"); //设置检查点
		//存放话题跟分片的映射关系
		Map<String, Integer> topicmap = new HashMap<>();
		String[] topicsArr = topics.split(",");
		int n = topicsArr.length;
		for(int i=0;i<n;i++){
			topicmap.put(topicsArr[i], numThreads);
		}
		//从Kafka中获取数据转换成RDD
		JavaPairReceiverInputDStream<String, String> lines = KafkaUtils.createStream(jssc, zkQuorum, group, topicmap);
		JavaDStream<HashMap<String, String>> hash_title_data =  lines.map(new Function<Tuple2<String, String>, HashMap<String, String>>() {
			@Override
			public HashMap<String, String> call(Tuple2<String, String> stringStringTuple2) throws Exception {
				String spark_1 = "spark---";
				String spark_2 = "---";
				String log = stringStringTuple2._2;
				int spark_index = log.indexOf(spark_1);
				String log_all = log.substring(spark_index+spark_1.length());

				int spark_2_index = log_all.indexOf(spark_2);
				String title = log_all.substring(0, spark_2_index);

				String data = log_all.substring(spark_2_index + spark_2.length());
				//容错，取出行首的“-”
				while (data.substring(0,1).equals("-")){
					data = data.substring(1);
				}
				data = data.replace("\n", "");
				HashMap<String, String> title_data = new HashMap<>();
				title_data.put(title,data);
				return title_data;
			}
		});

		hash_title_data.foreachRDD(new VoidFunction2<JavaRDD<HashMap<String, String>>, Time>() {
			@Override
			public void call(JavaRDD<HashMap<String, String>> hashMapJavaRDD, Time time) throws Exception {
				hashMapJavaRDD.foreach(new VoidFunction<HashMap<String, String>>() {

					@Override
					public void call(HashMap<String, String> stringStringHashMap) throws Exception {
						Iterator<String> ite = stringStringHashMap.keySet().iterator();
						while (ite.hasNext()){
							String key = ite.next();
							String value = stringStringHashMap.get(key);
							System.out.println(value);
							switch (key){
								case "文章刷新feed推荐&有题列表" :
									recommendAchieve.feedRefresh(value);
									break;
								case "创建专题" :
//									controllerClass.createTopic(value);
									break;
								case "修改专题" :
									break;

							}


						}
					}
				});
			}
		});

		hash_title_data.print();
		jssc.start();
		try {
			jssc.awaitTermination();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
