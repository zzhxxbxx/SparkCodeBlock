package com.fenji.recommend.Utils.redis;//package com.zzh.bigData.utils.redis;
//
//import redis.clients.jedis.Jedis;
//
///**
// * @Auther: Administrator
// * @Date: 2019/1/10 11:05
// * @Description:
// */
//public class RedisPoolUtil {
//
//	//设置key的有效期
//	public static Long expire(String key,int seconds){
//		Jedis jedis = null;
//		Long result = null;
//		try {
//			jedis = RedisPool.getJedis();//从自定义RedisPool连接池获取一个实例
//			result = jedis.expire(key,seconds);
//		} catch (Exception e) {
////			log.error("expire key:{} error",key ,e);
//			RedisPool.close(jedis);//发生错误回收
//			return result;
//		}
//		RedisPool.close(jedis);//正常回收
//		return result;
//	}
//
//	//设置key时间并且设置过期时间
//	public static String setEx(String key,String value,int seconds){
//		Jedis jedis = null;
//		String result = null;
//		try {
//			jedis = RedisPool.getJedis();
//			result = jedis.setex(key,seconds,value);
//		} catch (Exception e) {
////			log.error("expire key:{} value:{} error",key ,value,e);
//			RedisPool.close(jedis);
//			return result;
//		}
//		RedisPool.close(jedis);
//		return result;
//	}
//
//	//设置一个key
//	public static String set(String key,String value){
//		Jedis jedis = null;
//		String result = null;
//		try {
//			jedis = RedisPool.getJedis();
//			result = jedis.set(key,value);
//		} catch (Exception e) {
////			log.error("expire key:{} value:{} error",key ,value,e);
//			RedisPool.close(jedis);
//			return result;
//		}
//		RedisPool.close(jedis);
//		return result;
//	}
//
//	//获取一个key
//	public static String get(String key){
//		Jedis jedis = null;
//		String result = null;
//		try {
//			jedis = RedisPool.getJedis();
//			result = jedis.get(key);
//		} catch (Exception e) {
////			log.error("expire key:{} value:{} error",key ,e);
//			RedisPool.close(jedis);
//			return result;
//		}
//		RedisPool.close(jedis);
//		return result;
//	}
//
//	//删除一个key
//	public static Long del(String key){
//		Jedis jedis = null;
//		Long result = null;
//		try {
//			jedis = RedisPool.getJedis();
//			result = jedis.del(key);
//		} catch (Exception e) {
////			log.error("expire key:{} value:{} error",key ,e);
//			RedisPool.close(jedis);
//			return result;
//		}
//		RedisPool.close(jedis);
//		return result;
//	}
//
////	//调用测试
////	public static void main(String[] args) {
////		String result =  RedisPoolUtil.set("mark","25");
////		result = RedisPoolUtil.get("mark");
////		result = RedisPoolUtil.setEx("mark2","23",20);
////		Long resultL = RedisPoolUtil.expire("mark",20);
////		resultL = RedisPoolUtil.del("mark");
////		resultL = RedisPoolUtil.del("marks");
////		System.out.println("补充：使用redis缓存数据时候，最好加上过期时间，因为redis缓存需要占用内存空间");
////	}
//
//}