package com.bluerabbit.librarysystem.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * DBUtils是java编程中的数据库操作实用工具，小巧简单实用，
 * 1.对于数据表的读操作，他可以把结果转换成List，Array，Set等java集合，便于程序员操作；
 * 2.对于数据表的写操作，也变得很简单（只需写sql语句）
 *
 * @author minuhy
 */

public abstract class DBUtil {
    //声明连接数据库所需要的相关信息

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/br_library_system?useUnicode=true&characterEncoding=UTF-8";
    private static String userName = "bls";
    private static String passWord = "tsglxt2019";

    //单例化模式
    private DBUtil() {

    }

    //静态的方式加载JDBC驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回一个数据库链接
     *
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            JOptionPane.showMessageDialog(null, "服务器连接失败");
            e.printStackTrace();
        }

//		new Thread(){
//		    public void run(){
//		        try {
//		            Connection con = DriverManager.getConnection(connectString, sql_user, sql_pwd);
//		            con.close();
//		        } catch (SQLException e) { //连接失败
//		            e.printStackTrace();
//		        }
//		    }
//		}.start();

        return conn;
    }

    /**
     * 释放数据库连接信息
     *
     * @param rs
     * @param pstm
     * @param conn
     */
    public static void free(ResultSet rs, Statement pstm, Connection conn) {
        FreeConnection(conn);
        FreeStatement(pstm);
        FreeResultSet(rs);
    }

    public static void free(Connection conn) {
        FreeConnection(conn);
    }

    public static void free(ResultSet rs, Statement pstm) {
        FreeResultSet(rs);
        FreeStatement(pstm);
    }


    public static void free(Statement pstm, Connection conn) {
        FreeStatement(pstm);
        FreeConnection(conn);
    }

    //释放连接
    private static void FreeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //释放资源
    private static void FreeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //释放操作对象
    private static void FreeStatement(Statement pstm) {
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
