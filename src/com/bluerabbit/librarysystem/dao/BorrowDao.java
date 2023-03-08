package com.bluerabbit.librarysystem.dao;

import com.bluerabbit.librarysystem.beans.BorrowBeans;
import com.bluerabbit.librarysystem.database.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author minuhy
 * @date 2023/3/8 16:59
 */
public class BorrowDao {
    public final static int PAGE_SIZE = 10;

    public List<BorrowBeans> getAllBorrow(String searchSql, String keyWord, int page, boolean isSearch) {

        // 从1开始转换成从0开始
        if (page > 0) {
            page--;
        }

        String sql = "SELECT " +
                " t_borrow.id AS id,  " +
                " reader.ReaderID AS reader_id,  " +
                " reader.ReaderName AS reader_name,  " +
                " books_info.BookID AS book_id,  " +
                " books_info.BookName AS book_name,  " +
                " FROM_UNIXTIME(t_borrow.create_timestamp) AS create_timestamp,  " +
                " FROM_UNIXTIME(t_borrow.duration+t_borrow.create_timestamp) AS will_back_timestamp,  " +
                " IF(t_borrow.return_timestamp=0,'未归还',FROM_UNIXTIME(t_borrow.return_timestamp)) AS is_return " +
                "FROM " +
                " t_borrow " +
                " INNER JOIN " +
                " books_info " +
                " ON  " +
                "  t_borrow.book_id = books_info.BookID " +
                " INNER JOIN " +
                " reader " +
                " ON  " +
                "  t_borrow.reader_id = reader.ReaderID ";

        if(isSearch){
            sql = sql + " "+searchSql+" " +
                    "ORDER BY t_borrow.create_timestamp DESC "+
                    "LIMIT " + (PAGE_SIZE * page) + "," + PAGE_SIZE;
        }else{
            sql = sql + "ORDER BY t_borrow.create_timestamp DESC "+
                    "LIMIT " + (PAGE_SIZE * page) + "," + PAGE_SIZE;
        }

        System.out.println(sql);

        List<BorrowBeans> list = new ArrayList<>();
        //获得数据库连接
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            //获得操作句柄
            statement = conn.prepareStatement(sql);
            if(isSearch){
                statement.setString(1,keyWord);
            }
            //根据SQL语句获取数据
            rs = statement.executeQuery();

            while (rs.next()) {
                //新建一个AdministratorBeans对象以存数据
                BorrowBeans beans = new BorrowBeans();
                //根据字段名获取相关数据
                beans.setId(rs.getString("id"));
                beans.setReaderId(rs.getString("reader_id"));
                beans.setReaderName(rs.getString("reader_name"));
                beans.setBookId(rs.getString("book_id"));
                beans.setBookName(rs.getString("book_name"));
                beans.setCreateTimestamp(rs.getString("create_timestamp"));
                beans.setWillBackTimestamp(rs.getString("will_back_timestamp"));
                beans.setIsReturn(rs.getString("is_return"));

                //把数据压入列表
                list.add(beans);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //一定要做的事，释放连接
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            DBUtil.free(statement, conn);
        }
        return list;
    }

    /**
     * 获取总数据量
     *
     * @return 总数据量，获取失败返回 0
     * @param searchSql
     * @param keyWord
     * @param isSearch
     */
    public int getAllCount(String searchSql, String keyWord, boolean isSearch) {
        String sql = "SELECT " +
                " count(`t_borrow`.`id`) AS 'count' " +
                "FROM " +
                " t_borrow " +
                " INNER JOIN " +
                " books_info " +
                " ON  " +
                "  t_borrow.book_id = books_info.BookID " +
                " INNER JOIN " +
                " reader " +
                " ON  " +
                "  t_borrow.reader_id = reader.ReaderID";

        if(isSearch){
            sql = sql + " "+searchSql;
        }

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //获得操作句柄
            statement = conn.prepareStatement(sql);
            if(isSearch){
                statement.setString(1,keyWord);
            }
            //根据SQL语句获取数据
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //一定要做的事，释放连接
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            DBUtil.free(statement, conn);
        }
        return 0;
    }

    /**
     * 获取借出的具体情况
     * @param borrowId 借出ID
     * @return BorrowBeans
     */
    public BorrowBeans getBorrowInfoByBorrowID(String borrowId) {
        String sql = "SELECT " +
                " borrow_info.id AS id,  " +
                " FROM_UNIXTIME(borrow_info.create_timestamp) AS create_timestamp,  " +
                " FROM_UNIXTIME(borrow_info.duration+borrow_info.create_timestamp) AS will_back_timestamp,  " +
                " IF(borrow_info.return_timestamp=0,'未归还',FROM_UNIXTIME(borrow_info.return_timestamp)) AS is_return, " +
                " borrow_info.update_timestamp AS update_timestamp,  " +
                " borrow_info.penalty AS penalty,  " +
                " borrow_info.`status` AS `status`,  " +
                " borrow_info.book_number AS book_number,  " +
                " book_info.BookName AS book_name,  " +
                " book_info.Author AS book_author,  " +
                " book_info.Publisher AS book_publisher,  " +
                " book_info.PublishDate AS book_publish_date,  " +
                " book_info.BookID AS book_id,  " +
                " book_info.Stack AS book_stack,  " +
                " book_info.BookShelf AS book_shelf,  " +
                " book_info.BookBarcode AS book_barcode,  " +
                " reader_info.ReaderID AS reader_id,  " +
                " reader_info.ReaderName AS reader_name,  " +
                " reader_info.Apart AS reader_apart,  " +
                " reader_info.Class AS reader_class,  " +
                " reader_info.TelNo AS reader_tel,  " +
                " return_admin.AdminName AS return_admin_name,  " +
                " return_admin.AdminID AS return_admin_id,  " +
                " borrow_admin.AdminID AS borrow_admin_id,  " +
                " borrow_admin.AdminName AS borrow_admin_name, " +
                " reader_info.Sex AS reader_sex " +
                "FROM " +
                " t_borrow AS borrow_info " +
                " INNER JOIN " +
                " reader AS reader_info " +
                " ON  " +
                "  borrow_info.reader_id = reader_info.ReaderID " +
                " INNER JOIN " +
                " books_info AS book_info " +
                " ON  " +
                "  borrow_info.book_id = book_info.BookID " +
                " INNER JOIN " +
                " administrator AS return_admin " +
                " ON  " +
                "  borrow_info.return_admin_id = return_admin.AdminID " +
                " INNER JOIN " +
                " administrator AS borrow_admin " +
                " ON  " +
                "  borrow_info.borrow_admin_id = borrow_admin.AdminID " +
                " WHERE " +
                " borrow_info.id = ? " +
                " LIMIT 0, 1";

        BorrowBeans beans = null;

        System.out.println(sql);

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //获得操作句柄
            statement = conn.prepareStatement(sql);

            // 传入参数
            statement.setString(1,borrowId);

            //根据SQL语句获取数据
            rs = statement.executeQuery();
            if (rs.next()) {
                beans = new BorrowBeans();
                String id = rs.getString("id");
                String createTimestamp = rs.getString("create_timestamp");
                String willBackTimestamp = rs.getString("will_back_timestamp");
                String isReturn = rs.getString("is_return");
                String borrowAdminId = rs.getString("borrow_admin_id");
                String returnAdminId = rs.getString("return_admin_id");
                String borrowAdminName = rs.getString("borrow_admin_name");
                String returnAdminName = rs.getString("return_admin_name");
                String updateTimestamp = rs.getString("update_timestamp");
                String penalty = rs.getString("penalty");
                String status = rs.getString("status");
                String bookNumber = rs.getString("book_number");
                String bookName = rs.getString("book_name");
                String bookAuthor = rs.getString("book_author");
                String bookPublisher = rs.getString("book_publisher");
                String bookPublishDate = rs.getString("book_publish_date");
                String bookId = rs.getString("book_id");
                String bookBarcode = rs.getString("book_barcode");
                String bookStack = rs.getString("book_stack");
                String bookShelf = rs.getString("book_shelf");
                String readerId = rs.getString("reader_id");
                String readerName = rs.getString("reader_name");
                String readerApart = rs.getString("reader_apart");
                String readerClass = rs.getString("reader_class");
                String readerTel = rs.getString("reader_tel");
                String readerSex = rs.getString("reader_sex");

                beans.setId(id);
                beans.setCreateTimestamp(createTimestamp);
                beans.setWillBackTimestamp(willBackTimestamp);
                beans.setIsReturn(isReturn);
                beans.setBorrowAdminId(borrowAdminId);
                beans.setReturnAdminId(returnAdminId);
                beans.setBorrowAdminName(borrowAdminName);
                beans.setReturnAdminName(returnAdminName);
                beans.setUpdateTimestamp(updateTimestamp);
                beans.setPenalty(penalty);
                beans.setStatus(status);
                beans.setBookNumber(bookNumber);
                beans.setBookName(bookName);
                beans.setBookAuthor(bookAuthor);
                beans.setBookPublisher(bookPublisher);
                beans.setBookPublishDate(bookPublishDate);
                beans.setBookId(bookId);
                beans.setBookBarcode(bookBarcode);
                beans.setBookStack(bookStack);
                beans.setBookShelf(bookShelf);
                beans.setReaderId(readerId);
                beans.setReaderName(readerName);
                beans.setReaderApart(readerApart);
                beans.setReaderClass(readerClass);
                beans.setReaderTel(readerTel);
                beans.setReaderSex(readerSex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //一定要做的事，释放连接
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtil.free(statement, conn);
        }
        return beans;
    }
}
