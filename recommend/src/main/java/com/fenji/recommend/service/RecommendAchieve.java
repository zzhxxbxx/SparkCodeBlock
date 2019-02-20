package com.fenji.recommend.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.fenji.recommend.bean.FeedArticle;
import com.fenji.recommend.bean.User;
import com.fenji.recommend.dao.daoAchieve.Dao;
import com.fenji.recommend.dao.daoInf.DaoInf;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * @Auther: zzh
 * @Date: 2019/1/25 14:49
 * @Description:
 */
public class RecommendAchieve {
	@Resource
	private DaoInf Dao = new Dao();

	public int feedRefresh(String value) {
		// 获取用户id
		String userIdStr = value.substring(value.lastIndexOf("--") + 2);
		Integer userId = new Integer(userIdStr);
		boolean classFlag = false;

		// 获取用户的班级信息

		// 检查中间层推荐文章
		long middleLayerLen = this.Dao.getMiddleLayerLen(userId);
		if(middleLayerLen<10){
			// 补充中间层
		}
		// 检测redis缓存推荐列表长度
		long feedLen = this.Dao.getFeedlen(userId);
		if(feedLen>30){
			return 0;
		}else {

			 // 开始补充中间层

			// 从中间层去10条数据，存入feed层
			int reason1 = (int) (Math.random() * 10 + 1);
			int reason2 = (int) (Math.random() * 10 + 1);
			while (reason1-reason2>2 | reason1-reason2<-2){
				reason2 = (int) (Math.random() * 10 + 1);
			}
			ArrayList<Integer> reasonList = new ArrayList<>();
			reasonList.add(4);
			reasonList.add(6);
			reasonList.add(7);

			//
			// levelId 放入推荐列表
			// summaryId 记录推荐记录
			// categoryId 控制推荐列表category分散
			//
			ArrayList<Integer> summaryIdList = new ArrayList<>();
			ArrayList<Integer> categoryIdList = new ArrayList<>();
			int i;
			for(i=1;i<9;i++){
				String rKey = userId.toString();
				String rValue = null;
				Integer levelId;
				Integer summaryId;
				Integer categoryId;

				FeedArticle usableFeed = getUsableFeed(userId);
				levelId = usableFeed.getLevelId();
				summaryId = usableFeed.getSummaryId();
				categoryId = usableFeed.getCategory();

				try {
					// summary 去重
					while (summaryIdList.indexOf(summaryId) != -1){
						usableFeed = getUsableFeed(userId);
						levelId = usableFeed.getLevelId();
						summaryId = usableFeed.getSummaryId();
						categoryId = usableFeed.getCategory();
					}
					// category 去重
					int categoryIdCount = 0;
					int cycle = 0;
					while (categoryIdCount<4) {
						cycle ++;
						for (Integer categoryIdi : categoryIdList) {
							if (categoryIdCount > 3) {
								break;
							}
							if (categoryIdi.equals(categoryId)) {
								categoryIdCount++;
							}
						}
						if(categoryIdCount > 3){
							usableFeed = getUsableFeed(userId);
							levelId = usableFeed.getLevelId();
							summaryId = usableFeed.getSummaryId();
							categoryId = usableFeed.getCategory();
							// 重新计数
							categoryIdCount = 0;
						}
						if(cycle>5){
							break;
						}
					}

					if (levelId != null){
						int i1 = (int) (Math.random() * 10 + 1);
						if (i == reason1 | i == reason2){
							if (classFlag){
								if(i1==1){
									rValue = levelId+"_1_9";
								}else if(i1==2){
									rValue = levelId+"_1_1";
								}
							}else {
								if (i1==1){
									rValue = levelId+"_1_9";
								}else {
									rValue = levelId+"_1_"+reasonList.get((int) (Math.random() * reasonList.size()));
								}
							}
						}else {
							rValue = levelId+"_1_0";
						}
					}
					if(rValue!=null){
						Long date = new Date().getTime() * 1000;
						rValue+=date;
						this.Dao.insertFeed(rKey, rValue);
						summaryIdList.add(summaryId);
						categoryIdList.add(categoryId);
					}

				}catch (JSONException | IndexOutOfBoundsException e){
					e.printStackTrace();
				}
			}
			return i;
		}
	}
	private FeedArticle getUsableFeed(Integer userId) {
		Integer levelId = null;
		Integer summaryId = null;
		Integer categoryId = null;
		String feedArticleStr = this.Dao.getFeedArticle(userId);
		try {
			JSONArray feedArticle = JSONArray.parseArray(feedArticleStr);
			try {
				levelId = (Integer) feedArticle.get(0);
				summaryId = (Integer) feedArticle.get(1);
				categoryId = (Integer) feedArticle.get(2);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}

		}catch (IndexOutOfBoundsException e){
			e.printStackTrace();
		}
		FeedArticle feedArticle1 = new FeedArticle();
		feedArticle1.setLevelId(levelId);
		feedArticle1.setSummaryId(summaryId);
		feedArticle1.setCategory(categoryId);

		return feedArticle1;
	}

	public void supplementMiddleLayer(Integer userId){
		long middleLayerLen = this.Dao.getMiddleLayerLen(userId);
		if(middleLayerLen<=9){
			Integer userLevel = null;
			String categories = null;
			String label = null;
			String recommendation = null;
			// 获取user信息
			try {
				User user = this.Dao.getUserInfo(userId);
				ResultScanner summary_levels_topic  = this.Dao.supplementMiddleLayer(userId, userLevel, categories, label, recommendation);
				if (summary_levels_topic!=null){
					for (Result r: summary_levels_topic){
						String rowKey = new String(r.getRow());
						String level_id = new String(r.getValue(Bytes.toBytes("a"), Bytes.toBytes("a_level_id")));
						// 循环中直接计算最终得分
						// 计算方式 ： 写个方法吧


					}
					summary_levels_topic.close();

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public HashMap<String, String> getModel(String recommended){
		String[] split = recommended.split(",");


		return null;
	}

}
