package com.fenji.recommend.Utils.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Auther: Administrator
 * @Date: 2019/1/7 17:08
 * @Description:
 */
public class DBUtil_BO {
	public Connection conn = null;
	public PreparedStatement st = null;
	public ResultSet rs = null;
	public DBUtil_BO() {
		super();
	}
}
