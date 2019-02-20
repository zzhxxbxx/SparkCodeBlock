package com.fenji.recommend.Utils.redis;//package com.zzh.bigData.utils.redis;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
///**
// * @Auther: Administrator
// * @Date: 2019/1/10 11:00
// * @Description:
// */
//public class RedisPool {
//
//	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RedisPool.class);
//
//	private static JedisPool pool;
//	private static Integer MaxTotal = 20;//最大连接数
//	private static Integer MaxIdle = 10;//在jedispool中最大的idle状态(空闲的)的jedis实例的个数
//	private static Integer MinIdle = 2;//在jedispool中最小的idle状态(空闲的)的jedis实例的个数
//
//	private static Boolean testOnBorrow = true;//在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true。则得到的jedis实例肯定是可以用的。
//	private static Boolean testReturn = false;//在return一个jedis实例的时候，是否要进行验证操作，如果赋值true。则放回jedispool的jedis实例肯定是可以用的。
//
//	private static String redisIp = "127.0.0.1";//redis服务端的ip
//	private static Integer redisPort = 3679;//redis提供的接口
//
//
//	private static void initPool(){
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxTotal(MaxTotal);
//		config.setMaxIdle(MaxIdle);
//		config.setMinIdle(MinIdle);
//		config.setTestOnBorrow(testOnBorrow);
//		config.setTestOnReturn(testReturn);
//		config.setBlockWhenExhausted(true);//连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
//		pool = new JedisPool(config,redisIp,redisPort,1000*2);
//	}
//	static {
//		initPool();
//	}
//
//	//对外开放的接口
//	public static Jedis getJedis(){//获取一个Jedis实例
//		return  pool.getResource();
//	}
//
//	public static void close(Jedis jedis){
//		try {
//			if (jedis != null) {
//				jedis.close();
//			}
//		} catch (Exception e) {
//			log.error("return redis resource exception", e);
//		}
//	}
//
//
//
//	//测试
////	public static void main(String[] args) {
////		Jedis jedis = pool.getResource();
////		jedis.set("mark","mark");
////		returnResource(jedis);
////
////		pool.destroy();//临时调用，销毁连接池中的所有连接
////		System.out.println("program is end");
////
////	}
//
//}
