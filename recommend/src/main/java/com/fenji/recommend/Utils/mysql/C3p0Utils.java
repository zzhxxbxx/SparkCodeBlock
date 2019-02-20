package com.fenji.recommend.Utils.mysql;

import com.fenji.recommend.Utils.error.MyError;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Auther: Administrator
 * @Date: 2019/1/7 17:05
 * @Description:
 */
public class C3p0Utils {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(C3p0Utils.class.getName());

	//通过标识名来创建相应连接池
	static ComboPooledDataSource dataSourceData=new ComboPooledDataSource("data");
	static ComboPooledDataSource dataSourceMain=new ComboPooledDataSource("main");
	static ComboPooledDataSource dataSourceStats=new ComboPooledDataSource("stats");
	static ComboPooledDataSource dataSourceApply=new ComboPooledDataSource("apply");
	//从连接池中取用一个连接
	public static Connection getConnection(String DataName){
		switch (DataName){
			case "data":
				try {
					return dataSourceData.getConnection();
				} catch (Exception e) {
					logger.error("Exception in C3p0Utils!", e);
					throw new MyError("数据库连接出错!", e);
				}
			case "main":
				try {
					return dataSourceMain.getConnection();
				} catch (Exception e) {
					logger.error("Exception in C3p0Utils!", e);
					throw new MyError("数据库连接出错!", e);
				}
			case "stats":
				try {
					return dataSourceStats.getConnection();
				} catch (Exception e) {
					logger.error("Exception in C3p0Utils!", e);
					throw new MyError("数据库连接出错!", e);
				}
			case "apply":
				try {
					return dataSourceApply.getConnection();
				} catch (Exception e) {
					logger.error("Exception in C3p0Utils!", e);
					throw new MyError("数据库连接出错!", e);
				}

		}
		return null;
	}


	//释放连接回连接池
	public static void close(Connection conn, PreparedStatement pst, ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("Exception in C3p0Utils!", e);
				throw new MyError("数据库连接关闭出错!", e);
			}
		}
		if(pst!=null){
			try {
				pst.close();
			} catch (SQLException e) {
				logger.error("Exception in C3p0Utils!", e);
				throw new MyError("数据库连接关闭出错!", e);
			}
		}

		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("Exception in C3p0Utils!", e);
				throw new MyError("数据库连接关闭出错!", e);
			}
		}
	}
}
