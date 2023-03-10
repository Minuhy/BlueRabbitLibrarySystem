package com.bluerabbit.librarysystem.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * DBUtils��java����е����ݿ����ʵ�ù��ߣ�С�ɼ�ʵ�ã�
 * 1.�������ݱ�Ķ������������԰ѽ��ת����List��Array��Set��java���ϣ����ڳ���Ա������
 * 2.�������ݱ��д������Ҳ��úܼ򵥣�ֻ��дsql��䣩
 *
 * @author minuhy
 */

public abstract class DBUtil {
    //�����������ݿ�����Ҫ�������Ϣ

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/br_library_system?useUnicode=true&characterEncoding=UTF-8";
    private static String userName = "bls";
    private static String passWord = "tsglxt2019";

    //������ģʽ
    private DBUtil() {

    }

    //��̬�ķ�ʽ����JDBC����
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����һ�����ݿ�����
     *
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException e) {
            System.out.println("���ݿ�����ʧ�ܣ�");
            JOptionPane.showMessageDialog(null, "����������ʧ��");
            e.printStackTrace();
        }

//		new Thread(){
//		    public void run(){
//		        try {
//		            Connection con = DriverManager.getConnection(connectString, sql_user, sql_pwd);
//		            con.close();
//		        } catch (SQLException e) { //����ʧ��
//		            e.printStackTrace();
//		        }
//		    }
//		}.start();

        return conn;
    }

    /**
     * �ͷ����ݿ�������Ϣ
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

    //�ͷ�����
    private static void FreeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //�ͷ���Դ
    private static void FreeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //�ͷŲ�������
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
