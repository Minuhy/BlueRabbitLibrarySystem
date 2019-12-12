package com.bluerabbit.librarysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.database.DBUtil;

/**
 * 书的数据库查询类，这个可以合并的
 * @author minuy
 *
 */
public class BookInfoInquire {

	public List<BookInfoBeans> InquireBookInfoByAKey(String sql,String key){

		List<BookInfoBeans> list = new ArrayList<BookInfoBeans>();
		//新建一个查询对象
		BookInfoDAO dao = new BookInfoDAO();
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1,"%"+key+"%");
			//根据SQL语句获取数据
			list = dao.getDataBySQL(pstm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}
		return list;
	}

	public List<BookInfoBeans> InquireBookInfoByLotKey(String sql,ArrayList<String> key){

		List<BookInfoBeans> list = new ArrayList<BookInfoBeans>();
		//新建一个查询对象
		BookInfoDAO dao = new BookInfoDAO();
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			for (int i = 0; i < key.size(); i++) {
				pstm.setString(i+1,"%"+key.get(i)+"%");
				System.out.println(key.get(i));
			}
			//pstm.setString(1,"%"+"001"+"%");
			//根据SQL语句获取数据
			list = dao.getDataBySQL(pstm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}
		return list;
	}
}
