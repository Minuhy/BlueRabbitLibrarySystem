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
 * 读者信息相关的数据库操作
 * @author minuy
 *
 * 更新SQL语句
 */
public class ReaderInfoBeansDAO {

	public List<ReaderInfoBeans> getAllReader() {
		// TODO Auto-generated method stub
		//新建一个List对象存AdministratorBeans
		List<ReaderInfoBeans> list = new ArrayList<ReaderInfoBeans>();
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		//声明操作对象
		ResultSet rs = null;
		//SQL操作语句
		String sql = "select * from Reader";
		//声明一个AdministratorBeans对象
		ReaderInfoBeans reader = null;
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//获得操作对象
			rs = pstm.executeQuery();
			//循环整个列表，rs.next()返回false时表示数据处理完
			while(rs.next()) {
				//新建一个AdministratorBeans对象以存数据
				reader = new ReaderInfoBeans();
				//根据字段名获取相关数据
				reader.setApart(rs.getString("Apart"));
				reader.setReaderID(rs.getString("ReaderID"));
				reader.setReaderName(rs.getString("ReaderName"));
				reader.setSex(rs.getString("Sex"));
				reader.setTheClass(rs.getString("Class"));
				reader.setTelNo(rs.getString("TelNo"));
				//把数据压入列表
				list.add(reader);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//一定要做的事，释放连接
			DBUtil.free(rs, pstm, conn);
		}
		System.out.println("GetData");
		//返回对象数组
		return list;
	}

	public boolean SaveAReaderInfo(ReaderInfoBeans readerInfo){
		boolean rs = false;

		String sql = "INSERT INTO `Reader` (`ReaderID`, `ReaderName`, `Apart`, `Sex`, `Class`, `TelNo`) VALUES(?,?,?,?,?,?)";
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;

		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1,readerInfo.getReaderID());
			pstm.setString(2,readerInfo.getReaderName());
			pstm.setString(3,readerInfo.getApart());
			pstm.setString(4,readerInfo.getSex());
			pstm.setString(5,readerInfo.getTheClass());
			pstm.setString(6,readerInfo.getTelNo());
			//执行语句
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("数据操作结果:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e);
			String ee = String.valueOf(e);
			if(ee.indexOf("Duplicate entry") != (-1)){
				JOptionPane.showMessageDialog(null, "学号不能相同！", "错误", JOptionPane.ERROR_MESSAGE);
			}

		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}

		return rs;
	}


	public boolean UpReaderInfo(ReaderInfoBeans readerInfo,String oldId){
		boolean rs = false;

		String sql = "UPDATE `Reader` SET `ReaderID` = ?, `ReaderName` = ?, `Apart` = ?, `Sex` = ?, `Class` = ?, `TelNo` = ? WHERE (`ReaderID` = ?)";
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;

		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1,readerInfo.getReaderID());
			pstm.setString(2,readerInfo.getReaderName());
			pstm.setString(3,readerInfo.getApart());
			pstm.setString(4,readerInfo.getSex());
			pstm.setString(5,readerInfo.getTheClass());
			pstm.setString(6,readerInfo.getTelNo());
			pstm.setString(7,oldId);
			//执行语句
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("数据操作结果:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e);
			//			String ee = String.valueOf(e);
			//			if(ee.indexOf("Duplicate entry") != (-1)){
			//				JOptionPane.showMessageDialog(null, "学号不能相同！", "错误", JOptionPane.ERROR_MESSAGE);
			//			}

		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}

		return rs;
	}

	public ReaderInfoBeans getReaderInfoById(String readerId) {
		// TODO Auto-generated method stub
		ReaderInfoBeans readerInfo = new ReaderInfoBeans();
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		//声明操作对象
		ResultSet rs = null;
		//SQL操作语句
		String sql = "select * from Reader where ReaderID=?";
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1, readerId);
			//获得操作对象
			rs = pstm.executeQuery();
			//循环整个列表，rs.next()返回false时表示数据处理完
			if(rs.next()) {
				//根据字段名获取相关数据
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
			//一定要做的事，释放连接
			DBUtil.free(rs, pstm, conn);
		}

		return readerInfo;
	}

	public boolean DelReaderById(String id) {
		// TODO Auto-generated method stub
		boolean rs = false;
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		System.out.println(id);
		String sql = "DELETE FROM `Reader` WHERE (`ReaderID` = ?)";
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1, id);
			//执行
			if(pstm.executeUpdate() == 1) {
				rs = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm, conn);
		}
		return rs;
	}

	public List<ReaderInfoBeans> getDataBySql(String Sql,String Key) {
		// TODO Auto-generated method stub

		//新建一个List对象存AdministratorBeans
		List<ReaderInfoBeans> list = new ArrayList<ReaderInfoBeans>();
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		//声明操作对象
		ResultSet rs = null;
		//声明一个AdministratorBeans对象
		ReaderInfoBeans reader = null;
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(Sql);
			//拼接语句
			pstm.setString(1, "%"+Key+"%");
			//获得操作对象
			rs = pstm.executeQuery();
			//循环整个列表，rs.next()返回false时表示数据处理完
			while(rs.next()) {
				//新建一个AdministratorBeans对象以存数据
				reader = new ReaderInfoBeans();
				//根据字段名获取相关数据
				reader.setApart(rs.getString("Apart"));
				reader.setReaderID(rs.getString("ReaderID"));
				reader.setReaderName(rs.getString("ReaderName"));
				reader.setSex(rs.getString("Sex"));
				reader.setTheClass(rs.getString("Class"));
				reader.setTelNo(rs.getString("TelNo"));
				//把数据压入列表
				list.add(reader);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//一定要做的事，释放连接
			DBUtil.free(rs, pstm, conn);
		}
		System.out.println("GetData");
		//返回对象数组
		return list;
	}
}

