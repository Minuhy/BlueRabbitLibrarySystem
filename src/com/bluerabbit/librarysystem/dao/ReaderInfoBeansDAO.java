package com.bluerabbit.librarysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
import com.bluerabbit.librarysystem.database.DBUtil;

/**
 * ������Ϣ��ص����ݿ����
 * @author minuy
 *
 * ����SQL���
 */
public class ReaderInfoBeansDAO {

	public List<ReaderInfoBeans> getAllReader() {
		// TODO Auto-generated method stub
		//�½�һ��List�����AdministratorBeans
		List<ReaderInfoBeans> list = new ArrayList<ReaderInfoBeans>();
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		//������������
		ResultSet rs = null;
		//SQL�������
		String sql = "select * from Reader";
		//����һ��AdministratorBeans����
		ReaderInfoBeans reader = null;
		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//��ò�������
			rs = pstm.executeQuery();
			//ѭ�������б�rs.next()����falseʱ��ʾ���ݴ�����
			while(rs.next()) {
				//�½�һ��AdministratorBeans�����Դ�����
				reader = new ReaderInfoBeans();
				//�����ֶ�����ȡ�������
				reader.setApart(rs.getString("Apart"));
				reader.setReaderID(rs.getString("ReaderID"));
				reader.setReaderName(rs.getString("ReaderName"));
				reader.setSex(rs.getString("Sex"));
				reader.setTheClass(rs.getString("Class"));
				reader.setTelNo(rs.getString("TelNo"));
				//������ѹ���б�
				list.add(reader);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(rs, pstm, conn);
		}
		System.out.println("GetData");
		//���ض�������
		return list;
	}

	public boolean SaveAReaderInfo(ReaderInfoBeans readerInfo){
		boolean rs = false;

		String sql = "INSERT INTO `Reader` (`ReaderID`, `ReaderName`, `Apart`, `Sex`, `Class`, `TelNo`) VALUES(?,?,?,?,?,?)";
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;

		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//ƴ�����
			pstm.setString(1,readerInfo.getReaderID());
			pstm.setString(2,readerInfo.getReaderName());
			pstm.setString(3,readerInfo.getApart());
			pstm.setString(4,readerInfo.getSex());
			pstm.setString(5,readerInfo.getTheClass());
			pstm.setString(6,readerInfo.getTelNo());
			//ִ�����
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("���ݲ������:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e);
			String ee = String.valueOf(e);
			if(ee.indexOf("Duplicate entry") != (-1)){
				JOptionPane.showMessageDialog(null, "ѧ�Ų�����ͬ��", "����", JOptionPane.ERROR_MESSAGE);
			}

		}finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(pstm,conn);
		}

		return rs;
	}


	public boolean UpReaderInfo(ReaderInfoBeans readerInfo,String oldId){
		boolean rs = false;

		String sql = "UPDATE `Reader` SET `ReaderID` = ?, `ReaderName` = ?, `Apart` = ?, `Sex` = ?, `Class` = ?, `TelNo` = ? WHERE (`ReaderID` = ?)";
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;

		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//ƴ�����
			pstm.setString(1,readerInfo.getReaderID());
			pstm.setString(2,readerInfo.getReaderName());
			pstm.setString(3,readerInfo.getApart());
			pstm.setString(4,readerInfo.getSex());
			pstm.setString(5,readerInfo.getTheClass());
			pstm.setString(6,readerInfo.getTelNo());
			pstm.setString(7,oldId);
			//ִ�����
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("���ݲ������:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e);
			//			String ee = String.valueOf(e);
			//			if(ee.indexOf("Duplicate entry") != (-1)){
			//				JOptionPane.showMessageDialog(null, "ѧ�Ų�����ͬ��", "����", JOptionPane.ERROR_MESSAGE);
			//			}

		}finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(pstm,conn);
		}

		return rs;
	}

	public ReaderInfoBeans getReaderInfoById(String readerId) {
		// TODO Auto-generated method stub
		ReaderInfoBeans readerInfo = new ReaderInfoBeans();
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		//������������
		ResultSet rs = null;
		//SQL�������
		String sql = "select * from Reader where ReaderID=?";
		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//ƴ�����
			pstm.setString(1, readerId);
			//��ò�������
			rs = pstm.executeQuery();
			//ѭ�������б�rs.next()����falseʱ��ʾ���ݴ�����
			if(rs.next()) {
				//�����ֶ�����ȡ�������
				readerInfo.setApart(rs.getString("Apart"));
				readerInfo.setReaderID(rs.getString("ReaderID"));
				readerInfo.setReaderName(rs.getString("ReaderName"));
				readerInfo.setSex(rs.getString("Sex"));
				System.out.println(rs.getString("Sex"));
				readerInfo.setTheClass(rs.getString("Class"));
				readerInfo.setTelNo(rs.getString("TelNo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(rs, pstm, conn);
		}

		return readerInfo;
	}

	public boolean DelReaderById(String id) {
		// TODO Auto-generated method stub
		boolean rs = false;
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		System.out.println(id);
		String sql = "DELETE FROM `Reader` WHERE (`ReaderID` = ?)";
		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//ƴ�����
			pstm.setString(1, id);
			//ִ��
			if(pstm.executeUpdate() == 1) {
				rs = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(pstm, conn);
		}
		return rs;
	}

	public List<ReaderInfoBeans> getDataBySql(String Sql,String Key) {
		// TODO Auto-generated method stub

		//�½�һ��List�����AdministratorBeans
		List<ReaderInfoBeans> list = new ArrayList<ReaderInfoBeans>();
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		//������������
		ResultSet rs = null;
		//����һ��AdministratorBeans����
		ReaderInfoBeans reader = null;
		try {
			//��ò������
			pstm = conn.prepareStatement(Sql);
			//ƴ�����
			pstm.setString(1, "%"+Key+"%");
			//��ò�������
			rs = pstm.executeQuery();
			//ѭ�������б�rs.next()����falseʱ��ʾ���ݴ�����
			while(rs.next()) {
				//�½�һ��AdministratorBeans�����Դ�����
				reader = new ReaderInfoBeans();
				//�����ֶ�����ȡ�������
				reader.setApart(rs.getString("Apart"));
				reader.setReaderID(rs.getString("ReaderID"));
				reader.setReaderName(rs.getString("ReaderName"));
				reader.setSex(rs.getString("Sex"));
				reader.setTheClass(rs.getString("Class"));
				reader.setTelNo(rs.getString("TelNo"));
				//������ѹ���б�
				list.add(reader);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(rs, pstm, conn);
		}
		System.out.println("GetData");
		//���ض�������
		return list;
	}
}

