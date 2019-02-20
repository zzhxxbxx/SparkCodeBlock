package com.fenji.recommend.bean;

/**
 * @Auther: Administrator
 * @Date: 2019/1/28 14:49
 * @Description:
 */
public class FeedArticle {
	private Integer levelId;
	private Integer summaryId;
	private Integer category;

	@Override
	public String toString() {
		return "FeedArticle{" +
				"levelId=" + levelId +
				", summaryId=" + summaryId +
				", category=" + category +
				'}';
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Integer getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(Integer summaryId) {
		this.summaryId = summaryId;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
}
