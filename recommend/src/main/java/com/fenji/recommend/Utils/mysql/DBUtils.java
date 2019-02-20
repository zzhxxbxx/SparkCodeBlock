package com.fenji.recommend.Utils.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @Auther: Administrator
 * @Date: 2019/1/7 17:09
 * @Description:
 */
public class DBUtils {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(DBUtils.class.getName());


	private static void realseSource( Connection _conn, PreparedStatement _st,ResultSet _rs){
		C3p0Utils.close(_conn,_st,_rs);
	}

	public static void realseSource(DBUtil_BO _vo){
		if(_vo!=null){
			realseSource(_vo.conn, _vo.st, _vo.rs);
		}
	}
	//注意：查询操作完成后，因为还需提取结果集中信息，所以仍保持连接，在结果集使用完后才通过DBUtils.realseSource()手动释放连接
	public static void executeQuery(DBUtil_BO vo)
	{
		try{
			vo.rs = vo.st.executeQuery();
		}catch (SQLException e){
			realseSource(vo);
			String uuid = UUID.randomUUID().toString();
			//String uuid=Uuid.create().toString();
			logger.error("UUID:"+uuid+", SQL语法有误: ",e);
			//throw new MyException("err.user.dao.jdbc",e,uuid);
		}
	}

	//而update操作完成后就可以直接释放连接了，所以在方法末尾直接调用了realseSourse()
	public static  void executeUpdate(DBUtil_BO vo)
	{

		Connection conn = vo.conn;
		PreparedStatement st = vo.st;
		try {
			st.executeUpdate();
		} catch (SQLException e) {
			realseSource(conn, st, null);
			String uuid = UUID.randomUUID().toString();
			//String uuid=Uuid.create().toString();
			logger.error("UUID:"+uuid+", SQL语法有误: ",e);
			//throw new MyException("err.user.dao.jdbc",e,uuid);
		}
		realseSource(conn, st,null );

	}
}