package com.fenji.recommend.dao.daoAchieve;


import com.fenji.recommend.Utils.mysql.C3p0Utils;
import com.fenji.recommend.Utils.mysql.DBUtil_BO;
import com.fenji.recommend.Utils.mysql.DBUtils;
import com.fenji.recommend.Utils.redis.JedisUtil;
import com.fenji.recommend.bean.User;
import com.fenji.recommend.dao.daoInf.DaoInf;
import org.apache.hadoop.hbase.client.ResultScanner;

import java.sql.SQLException;

/**
 * @Auther: Administrator
 * @Date: 2019/1/25 15:23
 * @Description:
 */
public class Dao implements DaoInf {

	/**
	 * 获取用户中间层长度  userId + "_read"
	 * @param userId
	 * @return 长度
	 */
	@Override
	public long getMiddleLayerLen(Integer userId) {
		JedisUtil jedisUtil = JedisUtil.getInstance();
		JedisUtil.Lists lists = jedisUtil.new Lists();
		long llen = lists.llen(userId + "_read", 0);
		return llen;
	}

	/**
	 * 获取推荐缓存长度 key:userId
	 * @param userId
	 * @return 长度
	 */
	@Override
	public long getFeedlen(Integer userId) {
		JedisUtil jedisUtil = JedisUtil.getInstance();
		JedisUtil.Lists lists = jedisUtil.new Lists();
		long llen = lists.llen(userId.toString(), 0);
		return llen;
	}

	@Override
	public String getFeedArticle(Integer userId) {
		JedisUtil jedisUtil = JedisUtil.getInstance();
		JedisUtil.Keys keys = jedisUtil.new Keys();
		boolean exists = keys.exists(userId.toString(), 0);
		String lpop = null;
		if (exists) {
			JedisUtil.Lists lists = jedisUtil.new Lists();
			lpop = lists.lpop(userId.toString(), 0);
		}
		return lpop;
	}

	@Override
	public long insertFeed(String rKey, String rValue) {
		JedisUtil jedisUtil = JedisUtil.getInstance();
		JedisUtil.Lists lists = jedisUtil.new Lists();
		long lpush = lists.lpush(rKey, rValue, 0);
		return lpush;
	}

	@Override
	public ResultScanner supplementMiddleLayer(Integer userId, Integer userLevel, String categories, String label, String recommendation) {
		// userLevel 适当调整
		if (userLevel != null){
			if (userLevel>14){
				userLevel = 14;
			}else if (userLevel <3){
				userLevel = 2;
			}
			// 过滤器：符合用户等级的文章  -1·-·+3
			for(int i=userLevel-1;i<=userLevel+2;i++){
				// 过滤器过滤一个等级的文章
			}
		}
		if (null == categories){
			categories = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16";
		}
		String[] categoryList = categories.split(",");
		// 过滤器：过滤用户感兴趣的category
		for (String category : categoryList) {
			if ("0".equals(category) | "12".equals(category) | "13".equals(category) | "14".equals(category) | "15".equals(category) | "16".equals(category)){
				continue;
			}
			if("2".equals(category)){
				// 过滤器：只过滤category=2下两天内的文章
			}else {
				// 过滤器：过滤一个category
			}
		}
		// 过滤器：summary状态和level状态都是100， a_level_status,a_summary_status

		// 过滤器：是否读过，hadread_userId


		// filterList add 所有过滤器


		return null;
	}

	@Override
	public User getUserInfo(Integer userId) throws SQLException {
		User user = new User();
		DBUtil_BO dbUtil_bo = new DBUtil_BO();
		dbUtil_bo.conn = C3p0Utils.getConnection("mysql");
		String sql = "select * from user_like where user_id=?";
		dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
		dbUtil_bo.st.setInt(1,userId);
		DBUtils.executeQuery(dbUtil_bo);
		while (dbUtil_bo.rs.next()){
			userId = dbUtil_bo.rs.getInt("user_id");
			user.setUserId(userId);
			int user_level = dbUtil_bo.rs.getInt("user_level");
			user.setUserLevel(user_level);
			String category = dbUtil_bo.rs.getString("category");
			user.setCategories(category);
			String recommendation = dbUtil_bo.rs.getString("recommendation");
			user.setRecommendation(recommendation);
		}

		return user;
	}
}
