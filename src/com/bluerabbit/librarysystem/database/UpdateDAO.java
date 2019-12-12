package com.bluerabbit.librarysystem.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 数据库更新万能类，在本项目中没有用到
 * @author minuy
 *
 */
public abstract class UpdateDAO {
	private UpdateDAO() {
		
	}
	public static boolean update(String sql,Object... colValue) {
		boolean bool = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			for(int i = 1;i <= colValue.length;i++) {
				pstm.setObject(i, colValue[i-1]);
			}
			int len = pstm.executeUpdate();
			if(len > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.free(pstm, conn);
		}
		return bool;
	}
	
	
}

