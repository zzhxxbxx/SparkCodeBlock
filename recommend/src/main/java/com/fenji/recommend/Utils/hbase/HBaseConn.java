package com.fenji.recommend.Utils.hbase;


import com.fenji.recommend.Utils.properties.PropertiesUtil;
import com.fenji.recommend.constant.Constant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

/**
 * @Auther: Administrator
 * @Date: 2019/1/15 18:57
 * @Description:
 */
public class HBaseConn {
	private static final HBaseConn INSTANCE = new HBaseConn();
	private static Configuration configuration;
	private static Connection connection;

	private HBaseConn() {
		PropertiesUtil proUtil = PropertiesUtil.createPropertiesUtil(Constant.UPLOADPATH_FILE);
		if (configuration == null) {
			configuration = HBaseConfiguration.create();
			configuration.set("hbase.zookeeper.quorum", proUtil.getProperty(Constant.HBASE_ZOOKEEPER_QUORUM));
			configuration.set("hbase.zookeeper.property.clientPort", "2181");

		}
	}

	/**
	 * 创建数据库连接
	 *
	 * @return
	 */
	private Connection getConnection() {
		if (connection == null || connection.isClosed()) {
			try {
				connection = ConnectionFactory.createConnection(configuration);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	/**
	 * 获取数据库连接
	 *
	 * @return
	 */
	public static Connection getHBaseConn() {

		return INSTANCE.getConnection();
	}

	/**
	 * 获取表实例
	 *
	 * @param tableName
	 * @return
	 * @throws IOException
	 */
	public static Table getTable(String tableName) throws IOException {
		return INSTANCE.getConnection().getTable(TableName.valueOf(tableName));
	}

	/**
	 * 关闭连接
	 */
	public static void closeConn() {
		if (connection != null) {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

