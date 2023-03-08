package com.bluerabbit.librarysystem.dao;

import java.util.*;
/**
 * 注意这里导入的一定是java.util.*
 * 是java.awt.List的话会报错的
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.database.DBUtil;

/**
 * 管理员数据库交互类，与管理员相关的数据库操作
 * @author minuy
 * 
 * 更新SQL语句
 */
public class AdministratorBeansDAO {
	AdministratorBeans user;

	public List<AdministratorBeans> inquire(String sql,String sqlKey){
		List<AdministratorBeans> list = new ArrayList<AdministratorBeans>();
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		//声明操作对象
		ResultSet rs = null;
		//声明一个AdministratorBeans对象
		AdministratorBeans admin = null;
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1, sqlKey);
			//获得操作对象
			rs = pstm.executeQuery();
			//循环整个列表，rs.next()返回false时表示数据处理完
			while(rs.next()) {
				//新建一个AdministratorBeans对象以存数据
				admin = new AdministratorBeans();
				//根据字段名获取相关数据
				admin.setAdminID(rs.getInt("AdminID"));
				admin.setAdminName(rs.getString("AdminName"));
				admin.setAccount(rs.getString("Account"));
				admin.setPassword(rs.getString("Password"));
				admin.setIfsuper(rs.getBoolean("ifsuper"));
				//把数据压入列表
				list.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//一定要做的事，释放连接
			DBUtil.free(rs, pstm, conn);
		}

		return list;
	}

	public boolean DelAdminByAccout(String ac){
		boolean rs = false;
		//DELETE FROM Books_Info WHERE BookId='00002';
		String sql = "DELETE FROM Administrator WHERE Account=?";
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1,ac);
			//执行语句
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("数据操作结果:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}

		return rs;

	}

	/**
	 * 获取所有管理员的信息
	 * @return 返回一个AdministratorBeans的List
	 */
	public List<AdministratorBeans> getAllAdministrator() {
		//新建一个List对象存AdministratorBeans
		List<AdministratorBeans> list = new ArrayList<AdministratorBeans>();
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		//声明操作对象
		ResultSet rs = null;
		//SQL操作语句
		String sql = "select * from Administrator";
		//声明一个AdministratorBeans对象
		AdministratorBeans admin = null;
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//获得操作对象
			rs = pstm.executeQuery();
			//循环整个列表，rs.next()返回false时表示数据处理完
			while(rs.next()) {
				//新建一个AdministratorBeans对象以存数据
				admin = new AdministratorBeans();
				//根据字段名获取相关数据
				admin.setAdminID(rs.getInt("AdminID"));
				admin.setAdminName(rs.getString("AdminName"));
				admin.setAccount(rs.getString("Account"));
				admin.setPassword(rs.getString("Password"));
				admin.setIfsuper(rs.getBoolean("ifsuper"));
				//把数据压入列表
				list.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//一定要做的事，释放连接
			DBUtil.free(rs, pstm, conn);
		}
		//返回对象数组
		return list;
	}

	/**
	 * 按照用户名和密码验证用户名或密码是否正确
	 * @param Account 用户账号
	 * @param Password 用户密码
	 * @return 如果正确返回true，否则返回""
	 */
	public boolean validateByAccountAndPassword(String Account,String Password) {
		boolean ifyes = false;
		//获得连接
		Connection conn = DBUtil.getConn();
		//声明句柄
		PreparedStatement pstm = null;
		//声明操作对象
		ResultSet rs = null;
		//sql语句
		String sql = "select * from Administrator where Account=? and Password=?";
		try {
			//获得句柄
			pstm = conn.prepareStatement(sql);
			//设置句柄拼接？
			pstm.setString(1, Account);
			pstm.setString(2, Password);
			//获得数据（操作对象）
			/**
			 * 数据在这里获得，根据查询语句把整个结果表传到虚拟机里
			 */
			rs = pstm.executeQuery();
			//根据存不存在这个数据
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
			//一定释放资源
			DBUtil.free(rs, pstm, conn);
		}
		return ifyes;
	}

	public AdministratorBeans getAdminInfoByAcc(String Account){
		AdministratorBeans admin = null;
		//获得连接
		Connection conn = DBUtil.getConn();
		//声明句柄
		PreparedStatement pstm = null;
		//声明操作对象
		ResultSet rs = null;
		//sql语句
		String sql = "select * from Administrator where Account=?";
		try {
			//获得句柄
			pstm = conn.prepareStatement(sql);
			//设置句柄拼接？
			pstm.setString(1, Account);
			//获得数据（操作对象）
			/**
			 * 数据在这里获得，根据查询语句把整个结果表传到虚拟机里
			 */
			rs = pstm.executeQuery();
			//根据存不存在这个数据
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
			//一定释放资源
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
		//获得连接
		Connection conn = DBUtil.getConn();
		//声明句柄
		PreparedStatement pstm = null;

		//sql语句	
		String sql = "UPDATE `Administrator` SET `AdminID` = ?, `AdminName` = ?, `Password` = ?, `ifsuper` = ? WHERE (`Account` = ?)";
		try {
			//获得句柄
			pstm = conn.prepareStatement(sql);
			//设置句柄拼接？
			pstm.setString(1, admin.getAdminID()+"");
			pstm.setString(2, admin.getAdminName());
			pstm.setString(3, admin.getPassword());
			if(admin.isIfsuper()){
				pstm.setString(4, "1");
			}else{
				pstm.setString(4, "0");
			}
			pstm.setString(5, admin.getAccount());
			//获得数据（操作对象）
			/**
			 * 执行语句
			 */
			if(pstm.executeUpdate() == 1){
				yes = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//一定释放资源
			DBUtil.free(pstm, conn);
		}
		return yes;
	}
	//	
	//	public boolean AdminFix(AdministratorBeans admin){
	//		boolean rs = false;
	//		String sql = "UPDATE `library_system`.`Administrator` SET `AdminID` = ?, `AdminName` = ?, `Account` = ?, `Password` = ?, `ifsuper` = ? WHERE (`Account` = ?)";
	//
	//		//获得数据库连接
	//		Connection conn = DBUtil.getConn();
	//		//声明句柄对象
	//		PreparedStatement pstm = null;
	//
	//		try {
	//			//获得操作句柄
	//			pstm = conn.prepareStatement(sql);
	//			//拼接语句
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
	//			//执行语句
	//			if(pstm.executeUpdate() == 1){
	//				rs = true;
	//			}
	//			System.out.println("数据更新结果:"+rs);
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}finally {
	//			//一定要做的事，释放连接
	//			DBUtil.free(pstm,conn);
	//		}
	//
	//		return rs;
	//	}

	public boolean AdminSave(AdministratorBeans admin){
		boolean rs = false;
		String sql = "insert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(?,?,?,?,?)";

		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;

		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1,admin.getAdminID()+"");
			pstm.setString(2,admin.getAdminName());
			pstm.setString(3,admin.getAccount());
			pstm.setString(4,admin.getPassword());
			if(admin.isIfsuper()){
				pstm.setString(5,"1");
			}else{
				pstm.setString(5,"0");
			}

			//执行语句
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("数据更新结果:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}

		return rs;
	}

	public AdministratorBeans getAdminInfo(){
		return user;
	}
}
