package com.fenji.recommend.constant;

/**
 * @Auther: Administrator
 * @Date: 2019/1/9 10:50
 * @Description:
 */
public class Constant {
	public static final String UPLOADPATH_FILE = "my.properties";
	public static final String PATH = "path";
	//hbase.zookeeper.quorum
	public static final String HBASE_ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";
	//hbase.zookeeper.property.clientPort
	public static final String HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT = "hbase.zookeeper.property.clientPort";

	//spark
	public static final String SPARK_APP_NAME = "sparkAppName";
	public static final String SPARK_MASTER = "sparkMaster";

	//kafka
	public static final String KAFKA_ZOOKEEPER_QUORUM = "zkQuorum";
	public static final String TOPICS = "topics";
	public static final String GROUP = "group";

	// redis
	public static final String Redis_Ip = "redis.ip";
	public static final String Redis_Port = "redis.port";
	public static final String Redis_Password = "redis.password";
	public static final String Redis_Timeout = "redis.timeout";
	public static final String Redis_Pool_MaxActive = "jedis.pool.maxActive";
	public static final String Redis_Pool_MaxIdle = "jedis.pool.maxIdle";
	public static final String Redis_Pool_MaxWait = "jedis.pool.maxWait";
	public static final String Redis_Pool_TestOnBorrow = "jedis.pool.testOnBorrow";
	public static final String Redis_Pool_TestOnReturn = "jedis.pool.testOnReturn";



}
