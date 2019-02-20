package com.fenji.recommend.bean;

/**
 * @Auther: Administrator
 * @Date: 2019/1/30 16:45
 * @Description:
 */
public class User {
	private Integer userId;
	private String categories;
	private Integer userLevel;
	private String label;
	private String recommendation;

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", categories='" + categories + '\'' +
				", userLevel=" + userLevel +
				", label='" + label + '\'' +
				", recommendation='" + recommendation + '\'' +
				'}';
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
}
