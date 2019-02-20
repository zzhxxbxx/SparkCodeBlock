package com.fenji.recommend.dao.daoInf;

import cn.fjread.bigdata.recommend.bean.User;
import com.fenji.recommend.bean.User;
import org.apache.hadoop.hbase.client.ResultScanner;

import java.sql.SQLException;

/**
 * @Auther: Administrator
 * @Date: 2019/1/25 15:22
 * @Description:
 */
public interface DaoInf {
	long getMiddleLayerLen(Integer userId);

	long getFeedlen(Integer userId);

	String getFeedArticle(Integer userId);

	long insertFeed(String rKey, String rValue);

	ResultScanner supplementMiddleLayer(Integer userId, Integer userLevel, String categories, String label, String recommendation);

	User getUserInfo(Integer userId) throws SQLException;
}
