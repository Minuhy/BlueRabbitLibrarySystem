package com.bluerabbit.librarysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.database.DBUtil;

/**
 * ������ݿ��ѯ�࣬������Ժϲ���
 * @author minuy
 *
 */
public class BookInfoInquire {

	public List<BookInfoBeans> InquireBookInfoByAKey(String sql,String key){

		List<BookInfoBeans> list = new ArrayList<BookInfoBeans>();
		//�½�һ����ѯ����
		BookInfoDAO dao = new BookInfoDAO();
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//ƴ�����
			pstm.setString(1,"%"+key+"%");
			//����SQL����ȡ����
			list = dao.getDataBySQL(pstm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(pstm,conn);
		}
		return list;
	}

	public List<BookInfoBeans> InquireBookInfoByLotKey(String sql,ArrayList<String> key){

		List<BookInfoBeans> list = new ArrayList<BookInfoBeans>();
		//�½�һ����ѯ����
		BookInfoDAO dao = new BookInfoDAO();
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//ƴ�����
			for (int i = 0; i < key.size(); i++) {
				pstm.setString(i+1,"%"+key.get(i)+"%");
				System.out.println(key.get(i));
			}
			//pstm.setString(1,"%"+"001"+"%");
			//����SQL����ȡ����
			list = dao.getDataBySQL(pstm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(pstm,conn);
		}
		return list;
	}
}
