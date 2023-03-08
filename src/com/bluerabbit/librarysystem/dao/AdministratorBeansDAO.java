package com.bluerabbit.librarysystem.dao;

import java.util.*;
/**
 * ע�����ﵼ���һ����java.util.*
 * ��java.awt.List�Ļ��ᱨ���
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.database.DBUtil;

/**
 * ����Ա���ݿ⽻���࣬�����Ա��ص����ݿ����
 * @author minuy
 * 
 * ����SQL���
 */
public class AdministratorBeansDAO {
	AdministratorBeans user;

	public List<AdministratorBeans> inquire(String sql,String sqlKey){
		List<AdministratorBeans> list = new ArrayList<AdministratorBeans>();
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		//������������
		ResultSet rs = null;
		//����һ��AdministratorBeans����
		AdministratorBeans admin = null;
		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//ƴ�����
			pstm.setString(1, sqlKey);
			//��ò�������
			rs = pstm.executeQuery();
			//ѭ�������б�rs.next()����falseʱ��ʾ���ݴ�����
			while(rs.next()) {
				//�½�һ��AdministratorBeans�����Դ�����
				admin = new AdministratorBeans();
				//�����ֶ�����ȡ�������
				admin.setAdminID(rs.getInt("AdminID"));
				admin.setAdminName(rs.getString("AdminName"));
				admin.setAccount(rs.getString("Account"));
				admin.setPassword(rs.getString("Password"));
				admin.setIfsuper(rs.getBoolean("ifsuper"));
				//������ѹ���б�
				list.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(rs, pstm, conn);
		}

		return list;
	}

	public boolean DelAdminByAccout(String ac){
		boolean rs = false;
		//DELETE FROM Books_Info WHERE BookId='00002';
		String sql = "DELETE FROM Administrator WHERE Account=?";
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//ƴ�����
			pstm.setString(1,ac);
			//ִ�����
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("���ݲ������:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(pstm,conn);
		}

		return rs;

	}

	/**
	 * ��ȡ���й���Ա����Ϣ
	 * @return ����һ��AdministratorBeans��List
	 */
	public List<AdministratorBeans> getAllAdministrator() {
		//�½�һ��List�����AdministratorBeans
		List<AdministratorBeans> list = new ArrayList<AdministratorBeans>();
		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;
		//������������
		ResultSet rs = null;
		//SQL�������
		String sql = "select * from Administrator";
		//����һ��AdministratorBeans����
		AdministratorBeans admin = null;
		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//��ò�������
			rs = pstm.executeQuery();
			//ѭ�������б�rs.next()����falseʱ��ʾ���ݴ�����
			while(rs.next()) {
				//�½�һ��AdministratorBeans�����Դ�����
				admin = new AdministratorBeans();
				//�����ֶ�����ȡ�������
				admin.setAdminID(rs.getInt("AdminID"));
				admin.setAdminName(rs.getString("AdminName"));
				admin.setAccount(rs.getString("Account"));
				admin.setPassword(rs.getString("Password"));
				admin.setIfsuper(rs.getBoolean("ifsuper"));
				//������ѹ���б�
				list.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(rs, pstm, conn);
		}
		//���ض�������
		return list;
	}

	/**
	 * �����û�����������֤�û����������Ƿ���ȷ
	 * @param Account �û��˺�
	 * @param Password �û�����
	 * @return �����ȷ����true�����򷵻�""
	 */
	public boolean validateByAccountAndPassword(String Account,String Password) {
		boolean ifyes = false;
		//�������
		Connection conn = DBUtil.getConn();
		//�������
		PreparedStatement pstm = null;
		//������������
		ResultSet rs = null;
		//sql���
		String sql = "select * from Administrator where Account=? and Password=?";
		try {
			//��þ��
			pstm = conn.prepareStatement(sql);
			//���þ��ƴ�ӣ�
			pstm.setString(1, Account);
			pstm.setString(2, Password);
			//������ݣ���������
			/**
			 * �����������ã����ݲ�ѯ����������������������
			 */
			rs = pstm.executeQuery();
			//���ݴ治�����������
			if(rs.next()){
				user = new AdministratorBeans();

				user.setAdminID(rs.getInt("AdminID"));
				user.setAdminName(rs.getString("AdminName"));
				user.setAccount(rs.getString("Account"));
				user.setPassword(rs.getString("Password"));
				user.setIfsuper(rs.getBoolean("ifsuper"));

				ifyes = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//һ���ͷ���Դ
			DBUtil.free(rs, pstm, conn);
		}
		return ifyes;
	}

	public AdministratorBeans getAdminInfoByAcc(String Account){
		AdministratorBeans admin = null;
		//�������
		Connection conn = DBUtil.getConn();
		//�������
		PreparedStatement pstm = null;
		//������������
		ResultSet rs = null;
		//sql���
		String sql = "select * from Administrator where Account=?";
		try {
			//��þ��
			pstm = conn.prepareStatement(sql);
			//���þ��ƴ�ӣ�
			pstm.setString(1, Account);
			//������ݣ���������
			/**
			 * �����������ã����ݲ�ѯ����������������������
			 */
			rs = pstm.executeQuery();
			//���ݴ治�����������
			if(rs.next()){
				admin = new AdministratorBeans();

				admin.setAdminID(rs.getInt("AdminID"));
				admin.setAdminName(rs.getString("AdminName"));
				admin.setAccount(rs.getString("Account"));
				admin.setPassword(rs.getString("Password"));
				admin.setIfsuper(rs.getBoolean("ifsuper"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//һ���ͷ���Դ
			DBUtil.free(rs, pstm, conn);
		}
		return admin;
	}
	public boolean SaveAdminInfoName(AdministratorBeans admin,String newName){
		admin.setAdminName(newName);
		return SaveAdminInfo(admin);
	}

	public boolean SaveAdminInfoPwd(AdministratorBeans admin,String newPwd){
		admin.setPassword(newPwd);
		return SaveAdminInfo(admin);
	}

	public boolean SaveAdminInfo(AdministratorBeans admin){
		boolean yes = false;
		//�������
		Connection conn = DBUtil.getConn();
		//�������
		PreparedStatement pstm = null;

		//sql���	
		String sql = "UPDATE `Administrator` SET `AdminID` = ?, `AdminName` = ?, `Password` = ?, `ifsuper` = ? WHERE (`Account` = ?)";
		try {
			//��þ��
			pstm = conn.prepareStatement(sql);
			//���þ��ƴ�ӣ�
			pstm.setString(1, admin.getAdminID()+"");
			pstm.setString(2, admin.getAdminName());
			pstm.setString(3, admin.getPassword());
			if(admin.isIfsuper()){
				pstm.setString(4, "1");
			}else{
				pstm.setString(4, "0");
			}
			pstm.setString(5, admin.getAccount());
			//������ݣ���������
			/**
			 * ִ�����
			 */
			if(pstm.executeUpdate() == 1){
				yes = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//һ���ͷ���Դ
			DBUtil.free(pstm, conn);
		}
		return yes;
	}
	//	
	//	public boolean AdminFix(AdministratorBeans admin){
	//		boolean rs = false;
	//		String sql = "UPDATE `library_system`.`Administrator` SET `AdminID` = ?, `AdminName` = ?, `Account` = ?, `Password` = ?, `ifsuper` = ? WHERE (`Account` = ?)";
	//
	//		//������ݿ�����
	//		Connection conn = DBUtil.getConn();
	//		//�����������
	//		PreparedStatement pstm = null;
	//
	//		try {
	//			//��ò������
	//			pstm = conn.prepareStatement(sql);
	//			//ƴ�����
	//			pstm.setString(1,admin.getAdminID()+"");
	//			pstm.setString(2,admin.getAdminName());
	//			pstm.setString(3,admin.getAccount());
	//			pstm.setString(4,admin.getPassword());
	//			if(admin.isIfsuper()){
	//				pstm.setString(5,"1");
	//			}else{
	//				pstm.setString(5,"0");
	//			}
	//
	//			//ִ�����
	//			if(pstm.executeUpdate() == 1){
	//				rs = true;
	//			}
	//			System.out.println("���ݸ��½��:"+rs);
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}finally {
	//			//һ��Ҫ�����£��ͷ�����
	//			DBUtil.free(pstm,conn);
	//		}
	//
	//		return rs;
	//	}

	public boolean AdminSave(AdministratorBeans admin){
		boolean rs = false;
		String sql = "insert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(?,?,?,?,?)";

		//������ݿ�����
		Connection conn = DBUtil.getConn();
		//�����������
		PreparedStatement pstm = null;

		try {
			//��ò������
			pstm = conn.prepareStatement(sql);
			//ƴ�����
			pstm.setString(1,admin.getAdminID()+"");
			pstm.setString(2,admin.getAdminName());
			pstm.setString(3,admin.getAccount());
			pstm.setString(4,admin.getPassword());
			if(admin.isIfsuper()){
				pstm.setString(5,"1");
			}else{
				pstm.setString(5,"0");
			}

			//ִ�����
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("���ݸ��½��:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//һ��Ҫ�����£��ͷ�����
			DBUtil.free(pstm,conn);
		}

		return rs;
	}

	public AdministratorBeans getAdminInfo(){
		return user;
	}
}
