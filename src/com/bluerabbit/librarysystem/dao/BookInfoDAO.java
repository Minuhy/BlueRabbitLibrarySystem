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
 * ͼ����Ϣ���ݿ⽻���࣬��ͼ����ص����ݿ����
 * @author minuy
 *
 */
public class BookInfoDAO {

	public BookInfoBeans getABookInfoByBookID(String key){
		List<BookInfoBeans> list = new ArrayList<BookInfoBeans>();
		BookInfoBeans book = null;
		//SQL�������
		String sql = "select * from Books_Info WHERE BookId=?";
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//ƴ�����
			pstm.setString(1,key);
			//����SQL����ȡ����
			list = getDataBySQL(pstm);
			//��ȡ
			book = list.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(pstm,conn);
		}
		return book;
	}

	public List<BookInfoBeans> getAllBookInfo(){
		List<BookInfoBeans> list = new ArrayList<BookInfoBeans>();
		//SQL�������
		String sql = "select * from Books_Info";
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//����SQL����ȡ����
			list = getDataBySQL(pstm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(pstm,conn);
		}
		return list;
	}

	public List<BookInfoBeans> getDataBySQL(PreparedStatement pstm){
		List<BookInfoBeans> list = new ArrayList<BookInfoBeans>();
		//������������
		ResultSet rs = null;
		//����һ��BookInfoBeans����
		BookInfoBeans bookInfo = null;
		try {
			//��ò�������
			rs = pstm.executeQuery();
			//ѭ�������б�rs.next()����falseʱ��ʾ���ݴ�����
			while(rs.next()) {
				//�½�һ��AdministratorBeans�����Դ�����
				bookInfo = new BookInfoBeans();
				//�����ֶ�����ȡ�������
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
				//������ѹ���б�
				list.add(bookInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(rs, pstm);
		}
		return list;
	}
}
