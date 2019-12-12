package com.bluerabbit.librarysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.database.DBUtil;

/**
 * 图书信息数据库交互类，与图书相关的数据库操作
 * @author minuy
 *
 */
public class BookInfoDAO {

	public BookInfoBeans getABookInfoByBookID(String key){
		List<BookInfoBeans> list = new ArrayList<BookInfoBeans>();
		BookInfoBeans book = null;
		//SQL操作语句
		String sql = "select * from Books_Info WHERE BookId=?";
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1,key);
			//根据SQL语句获取数据
			list = getDataBySQL(pstm);
			//提取
			book = list.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}
		return book;
	}

	public List<BookInfoBeans> getAllBookInfo(){
		List<BookInfoBeans> list = new ArrayList<BookInfoBeans>();
		//SQL操作语句
		String sql = "select * from Books_Info";
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//根据SQL语句获取数据
			list = getDataBySQL(pstm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}
		return list;
	}

	public List<BookInfoBeans> getDataBySQL(PreparedStatement pstm){
		List<BookInfoBeans> list = new ArrayList<BookInfoBeans>();
		//声明操作对象
		ResultSet rs = null;
		//声明一个BookInfoBeans对象
		BookInfoBeans bookInfo = null;
		try {
			//获得操作对象
			rs = pstm.executeQuery();
			//循环整个列表，rs.next()返回false时表示数据处理完
			while(rs.next()) {
				//新建一个AdministratorBeans对象以存数据
				bookInfo = new BookInfoBeans();
				//根据字段名获取相关数据
				bookInfo.setBookName(rs.getString("BookName"));
				bookInfo.setSumQuantity(rs.getInt("SumQuantity"));
				bookInfo.setQuantity(rs.getInt("Quantity"));
				bookInfo.setLendTime(rs.getInt("LendTime"));
				bookInfo.setBookID(rs.getString("BookID"));
				bookInfo.setBookBarcode(rs.getString("BookBarcode"));
				bookInfo.setBookClassify(rs.getString("BookClassify"));
				bookInfo.setBookThem(rs.getString("BookThem"));
				bookInfo.setAuthor(rs.getString("Author"));
				bookInfo.setPublisher(rs.getString("Publisher"));
				bookInfo.setPublishTime(rs.getString("PublishTime"));
				bookInfo.setPublishDate(rs.getString("PublishDate"));
				bookInfo.setBookType(rs.getString("BookType"));
				bookInfo.setStack(rs.getString("Stack"));
				bookInfo.setBookShelf(rs.getString("BookShelf"));
				bookInfo.setPrice(rs.getDouble("Price"));
				bookInfo.setContentText(rs.getString("ContentText"));
				bookInfo.setRemark(rs.getString("Remark"));
				bookInfo.setBookPage(rs.getInt("BookPage"));
				bookInfo.setWordsNumber(rs.getString("WordsNumber"));
				//把数据压入列表
				list.add(bookInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//一定要做的事，释放连接
			DBUtil.free(rs, pstm);
		}
		return list;
	}
}
