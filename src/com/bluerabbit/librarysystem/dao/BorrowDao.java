package com.bluerabbit.librarysystem.dao;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.beans.BorrowBeans;
import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
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
    public final static int PAGE_MINI_SIZE = 5;
    public final static int PAGE_SIZE = 12;

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
                " FROM_UNIXTIME(`t_borrow`.`create_timestamp`/1000) AS create_timestamp,  " +
                " FROM_UNIXTIME((`t_borrow`.`duration`+`t_borrow`.`create_timestamp`)/1000) AS will_back_timestamp,  " +
                " IF(t_borrow.return_timestamp=0,'未归还',FROM_UNIXTIME(`t_borrow`.`return_timestamp`/1000)) AS is_return " +
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

        if (isSearch) {
            sql = sql + " " + searchSql + " " +
                    "ORDER BY t_borrow.create_timestamp DESC " +
                    "LIMIT " + (PAGE_SIZE * page) + "," + PAGE_SIZE;
        } else {
            sql = sql + "ORDER BY t_borrow.create_timestamp DESC " +
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
            if (isSearch) {
                statement.setString(1, keyWord);
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
                } catch (SQLException t) {
                    t.printStackTrace();
                }
            }
            DBUtil.free(statement, conn);
        }
        return list;
    }

    /**
     * 获取总数据量
     *
     * @param searchSql 搜索的SQL部分
     * @param keyWord   关键词
     * @param isSearch  是否是搜索
     * @return 总数据量，获取失败返回 0
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

        if (isSearch) {
            sql = sql + " " + searchSql;
        }

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //获得操作句柄
            statement = conn.prepareStatement(sql);
            if (isSearch) {
                statement.setString(1, keyWord);
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
                } catch (SQLException t) {
                    t.printStackTrace();
                }
            }
            DBUtil.free(statement, conn);
        }
        return 0;
    }

    /**
     * 获取借出的具体情况
     *
     * @param borrowId 借出ID
     * @return BorrowBeans
     */
    public BorrowBeans getBorrowInfoByBorrowID(String borrowId) {
        String sql = "SELECT " +
                " borrow_info.id AS id,  " +
                " FROM_UNIXTIME(`borrow_info`.`create_timestamp`/1000) AS create_timestamp,  " +
                " FROM_UNIXTIME((`borrow_info`.`duration`+`borrow_info`.`create_timestamp`)/1000) AS will_back_timestamp,  " +
                " IF(borrow_info.return_timestamp=0,'未归还',FROM_UNIXTIME(`borrow_info`.`return_timestamp`/1000)) AS is_return, " +
                " borrow_info.update_timestamp AS update_timestamp,  " +
                " borrow_info.penalty AS penalty,  " +
                " borrow_info.`status` AS `status`,  " +
                " borrow_info.`return_admin_id` AS `return_admin_id`,  " +
                " borrow_info.book_number AS book_number,  " +
                " book_info.BookName AS book_name,  " +
                " book_info.Author AS book_author,  " +
                " book_info.Publisher AS book_publisher,  " +
                " book_info.PublishDate AS book_publish_date,  " +
                " book_info.BookID AS book_id,  " +
                " book_info.Stack AS book_stack,  " +
                " book_info.BookShelf AS book_shelf,  " +
                " book_info.BookBarcode AS book_barcode,  " +

                " book_info.SumQuantity AS book_total_number,  " +
                " book_info.Quantity AS book_surplus_number,  " +
                " book_info.LendTime AS book_lend_time,  " +
                " book_info.Price AS book_price,  " +

                " reader_info.ReaderID AS reader_id,  " +
                " reader_info.ReaderName AS reader_name,  " +
                " reader_info.Apart AS reader_apart,  " +
                " reader_info.Class AS reader_class,  " +
                " reader_info.TelNo AS reader_tel,  " +
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
            statement.setString(1, borrowId);

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
                String returnAdminName = "";
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


                String bookTotalNumber = rs.getString("book_total_number");//总册数
                String bookSurplusNumber = rs.getString("book_surplus_number");//剩余册数
                String bookPrice = rs.getString("book_price");//价格
                String bookLendTime = rs.getString("book_lend_time");//借出次数


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

                beans.setBookTotalNumber(bookTotalNumber);
                beans.setBookSurplusNumber(bookSurplusNumber);
                beans.setBookLendTime(bookLendTime);
                beans.setBookPrice(bookPrice);

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
            } else {
                System.out.println("没有查到数据");
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
        System.out.println(beans);
        return beans;
    }


    /**
     * 获取借出的具体情况
     *
     * @param borrowId 借出ID
     * @return BorrowBeans
     */
    public BorrowBeans getBorrowInfoInByBorrowID(String borrowId) {
        String sql = " " +
                "SELECT " +
                " return_admin.AdminName AS return_admin_name,  " +
                " return_admin.AdminID AS return_admin_id " +
                "FROM " +
                " t_borrow AS borrow_info " +
                " INNER JOIN " +
                " administrator AS return_admin " +
                " ON  " +
                "  borrow_info.return_admin_id = return_admin.AdminID " +
                " WHERE " +
                " borrow_info.id = ? " +
                " LIMIT 0, 1 ";

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
            statement.setString(1, borrowId);

            //根据SQL语句获取数据
            rs = statement.executeQuery();
            if (rs.next()) {
                beans = new BorrowBeans();
                String returnAdminId = rs.getString("return_admin_id");
                String returnAdminName = rs.getString("return_admin_name");

                beans.setReturnAdminId(returnAdminId);
                beans.setReturnAdminName(returnAdminName);
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


    public BookInfoBeans getBookBySearch(String keyword, int page) {

        // 从1开始转换成从0开始
        if (page > 0) {
            page--;
        }

        String sql = "SELECT " +
                " BookName AS `name`,  " +
                " Author AS author,  " +
                " Publisher AS publisher,  " +
                " PublishDate AS publish_date,  " +
                " BookID AS id,  " +
                " BookBarcode AS isbn,  " +
                " Stack AS stack,  " +
                " BookShelf AS shelf,  " +
                " Price AS price,  " +
                " Quantity AS quantity " +
                "FROM " +
                " books_info " +
                "WHERE " +
                " BookName LIKE ? OR " +
                " BookBarcode LIKE ? OR " +
                " BookID LIKE ? OR " +
                " Author LIKE ? OR " +
                " Publisher LIKE ? OR " +
                " DATE_FORMAT(PublishDate,'%Y-%m-%d') LIKE ? OR " +
                " Stack LIKE ? OR " +
                " BookShelf LIKE ? " +
                "LIMIT " + page + ", 1";

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //获得操作句柄
            statement = conn.prepareStatement(sql);
            // 插入数据
            for (int i = 0; i < 8; i++) {
                statement.setString(i + 1, keyword);
            }
            //根据SQL语句获取数据
            rs = statement.executeQuery();
            if (rs.next()) {
                BookInfoBeans beans = new BookInfoBeans();
                beans.setBookName(rs.getString("name"));
                beans.setAuthor(rs.getString("author"));
                beans.setPublisher(rs.getString("publisher"));
                beans.setPublishDate(rs.getString("publish_date"));
                beans.setBookID(rs.getString("id"));
                beans.setBookBarcode(rs.getString("isbn"));
                beans.setStack(rs.getString("stack"));
                beans.setBookShelf(rs.getString("shelf"));
                beans.setPrice(rs.getDouble("price"));
                beans.setQuantity(rs.getInt("quantity"));
                return beans;
            } else {
                return null;
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
        return null;
    }

    /**
     * 查询总数
     *
     * @param keyword 关键词
     * @return 总数
     */
    public int getBookCountBySearch(String keyword) {
        String sql = "SELECT " +
                " count(*) AS 'count' " +
                "FROM " +
                " books_info " +
                "WHERE " +
                " BookName LIKE ? OR " +
                " BookBarcode LIKE ? OR " +
                " BookID LIKE ? OR " +
                " Author LIKE ? OR " +
                " Publisher LIKE ? OR " +
                " DATE_FORMAT(PublishDate,'%Y-%m-%d') LIKE ? OR " +
                " Stack LIKE ? OR " +
                " BookShelf LIKE ?";

        System.out.println(sql);

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //获得操作句柄
            statement = conn.prepareStatement(sql);
            // 插入数据
            for (int i = 0; i < 8; i++) {
                statement.setString(i + 1, keyword);
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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtil.free(statement, conn);
        }
        return 0;
    }


    public ReaderInfoBeans getReaderBySearch(String keyword, int page) {

        // 从1开始转换成从0开始
        if (page > 0) {
            page--;
        }

        String sql = "SELECT " +
                " reader.ReaderID AS id,  " +
                " reader.ReaderName AS `name`,  " +
                " reader.Apart AS apart,  " +
                " reader.Class AS class,  " +
                " reader.Sex AS sex,  " +
                " reader.TelNo AS tel " +
                "FROM " +
                " reader " +
                "WHERE " +
                " reader.ReaderID LIKE ? OR " +
                " reader.ReaderName LIKE ? OR " +
                " reader.Apart LIKE ? OR " +
                " reader.Sex LIKE ? OR " +
                " reader.Class LIKE ? OR " +
                " reader.TelNo LIKE ? " +
                "LIMIT " + page + ", 1";

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //获得操作句柄
            statement = conn.prepareStatement(sql);
            // 插入数据
            for (int i = 0; i < 6; i++) {
                statement.setString(i + 1, keyword);
            }
            //根据SQL语句获取数据
            rs = statement.executeQuery();
            if (rs.next()) {
                ReaderInfoBeans beans = new ReaderInfoBeans();
                beans.setTelNo(rs.getString("tel"));
                beans.setSex(rs.getString("sex"));
                beans.setTheClass(rs.getString("class"));
                beans.setApart(rs.getString("apart"));
                beans.setReaderName(rs.getString("name"));
                beans.setReaderID(rs.getString("id"));
                return beans;
            } else {
                return null;
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
        return null;
    }

    /**
     * 查询总数
     *
     * @param keyword 关键词
     * @return 总数
     */
    public int getReaderCountBySearch(String keyword) {
        String sql = "SELECT " +
                " count(*) AS 'count' " +
                "FROM " +
                " reader " +
                "WHERE " +
                " reader.ReaderID LIKE ? OR " +
                " reader.ReaderName LIKE ? OR " +
                " reader.Apart LIKE ? OR " +
                " reader.Sex LIKE ? OR " +
                " reader.Class LIKE ? OR " +
                " reader.TelNo LIKE ? ";

        System.out.println(sql);

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //获得操作句柄
            statement = conn.prepareStatement(sql);
            // 插入数据
            for (int i = 0; i < 6; i++) {
                statement.setString(i + 1, keyword);
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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtil.free(statement, conn);
        }
        return 0;
    }

    public String borrowOut(String readerId, String bookId, String duration, String number, String adminID, String time) {
        String sql = "INSERT INTO  " +
                " `t_borrow` " +
                " (`reader_id`, `book_id`, `borrow_admin_id`, " +
                "`book_number`, `duration`, `create_timestamp`)  " +
                " VALUES (?, ?, ?," +
                " ?, ?, ?) ";

        System.out.println("借出数据库操作");
        System.out.println("sql" + sql);
        System.out.println("readerId" + readerId);
        System.out.println("bookId" + bookId);
        System.out.println("adminID" + adminID);
        System.out.println("number" + number);
        System.out.println("duration" + duration);
        System.out.println("time" + time);


        //获得数据库连接
        Connection conn = DBUtil.getConn();
        //声明句柄对象
        PreparedStatement statement = null;
        try {
            // 开启事务
            conn.setAutoCommit(false);

            //获得操作句柄
            statement = conn.prepareStatement(sql);
            //拼接语句
            statement.setString(1, readerId);
            statement.setString(2, bookId);
            statement.setString(3, adminID);
            statement.setString(4, number);
            statement.setString(5, duration);
            statement.setString(6, time);

            //执行语句
            if (statement.executeUpdate() < 1) {
                conn.rollback();
                return "写入数据失败";
            }
            statement.close();

            //--------------------------更新图书数据-----------------------------------

            sql = "UPDATE " +
                    "`books_info` " +
                    "SET `Quantity` = `Quantity` - ?, `LendTime` = `LendTime` + ? " +
                    "WHERE `BookID` = ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, number);
            statement.setString(2, number);
            statement.setString(3, bookId);

            //执行语句
            if (statement.executeUpdate() < 1) {
                conn.rollback();
                return "更新数据失败";
            }

            // 提交数据
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException t) {
                t.printStackTrace();
                return "数据库错误：" + t.getMessage();
            }
            return "数据库错误：" + e.getMessage();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException t) {
                t.printStackTrace();
            }
            //一定要做的事，释放连接
            DBUtil.free(statement, conn);
        }
        return null;
    }

    /**
     * 查询一个读者所借的书籍
     *
     * @param readerId 读者ID
     * @param keyword  书籍关键词，null为所有书籍
     * @return 书籍数量
     */
    public int getBookCountBySearchAndReaderId(String readerId, String keyword) {
        String sql = "SELECT " +
                " count(*) AS 'count' " +
                "FROM " +
                " t_borrow AS borrow " +
                " INNER JOIN " +
                " books_info AS book " +
                " ON  " +
                "  borrow.book_id = book.BookID " +
                " INNER JOIN " +
                " administrator AS borrow_admin " +
                " ON  " +
                "  borrow.borrow_admin_id = borrow_admin.AdminID " +
                "WHERE " +
                " borrow.reader_id = ? ";

        if (keyword != null) {
            sql += " AND " +
                    " ( " +
                    "  book.BookName LIKE ? OR " +
                    "  book.BookBarcode LIKE ? OR " +
                    "  book.BookID LIKE ? OR " +
                    "  book.Author LIKE ? OR " +
                    "  book.Publisher LIKE ? OR " +
                    "  CONVERT(DATE_FORMAT(book.PublishDate,'%Y-%m-%d'), CHAR) LIKE ? OR " +
                    "  book.Stack LIKE ? OR " +
                    "  book.BookShelf LIKE ? OR " +
                    "  CONVERT(borrow_admin.AdminID, CHAR) LIKE ? OR " +
                    "  borrow_admin.AdminName LIKE ? OR " +
                    "  CONVERT(borrow.id, CHAR) LIKE ? OR " +
                    "  CONVERT(FROM_UNIXTIME(borrow.create_timestamp/1000), CHAR) LIKE ? " +
                    " ) ";
        }

        System.out.println(sql);

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //获得操作句柄
            statement = conn.prepareStatement(sql);

            // 插入数据
            statement.setString(1, readerId);
            if (keyword != null) {
                for (int i = 0; i < 12; i++) {
                    statement.setString(i + 2, keyword);
                }
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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtil.free(statement, conn);
        }
        return 0;
    }

    /**
     * 分页获取一个读者下的借的书，支持搜索
     *
     * @param keyword  搜索关键词
     * @param readerId 读者ID
     * @param page     页数，从1开始
     * @return 一组借出数据
     */
    public List<BorrowBeans> getBookBySearchAndReaderId(String keyword, String readerId, int page) {

        // 从1开始转换成从0开始
        if (page > 0) {
            page--;
        }

        String sql = "SELECT " +
                " borrow.id AS borrow_id,  " + // 借出编号
                " borrow.book_number AS borrow_number,  " + // 借出的数量
                " FROM_UNIXTIME((`borrow`.`duration`+`borrow`.`create_timestamp`)/1000) AS will_back_timestamp,  " + // 预计归还时间
                " FROM_UNIXTIME(`borrow`.`create_timestamp`/1000) AS borrow_time,  " + // 借出时间
                " IF(borrow.return_timestamp=0,'未归还',FROM_UNIXTIME(borrow.return_timestamp/1000)) AS is_return,  " + // 归还时间
                " borrow.borrow_admin_id AS borrow_admin,  " + // 借出管理员
                " borrow.return_admin_id AS return_admin,  " + // 还入管理员
                " book.BookName AS book_name,  " + // 借出书名
                " book.BookBarcode AS book_isbn,  " + // 借出书的ISBN
                " book.Author AS book_author,  " + // 借出书的作者
                " book.Publisher AS book_publisher,  " + // 借出书的出版社
                " book.Stack AS book_stack,  " + // 借出书的书室
                " book.BookShelf AS book_shelf,  " + // 借出书的书架
                " book.Price AS book_price,  " + // 借出书的价格
                " book.BookID AS book_id,  " + // 借出书的编号
                " borrow_admin.AdminName AS borrow_admin_name " + // 借出书的管理员昵称
                "FROM " +
                " t_borrow AS borrow " +
                " INNER JOIN " +
                " books_info AS book " +
                " ON  " +
                "  borrow.book_id = book.BookID " +
                " INNER JOIN " +
                " administrator AS borrow_admin " +
                " ON  " +
                "  borrow.borrow_admin_id = borrow_admin.AdminID " +
                "WHERE " +
                " borrow.reader_id = ? ";

        if (keyword != null) {
            sql += " AND " +
                    " ( " +
                    "  book.BookName LIKE ? OR " +
                    "  book.BookBarcode LIKE ? OR " +
                    "  book.BookID LIKE ? OR " +
                    "  book.Author LIKE ? OR " +
                    "  book.Publisher LIKE ? OR " +
                    "  CONVERT(DATE_FORMAT(book.PublishDate,'%Y-%m-%d'), CHAR) LIKE ? OR " +
                    "  book.Stack LIKE ? OR " +
                    "  book.BookShelf LIKE ? OR " +
                    "  CONVERT(borrow_admin.AdminID, CHAR) LIKE ? OR " +
                    "  borrow_admin.AdminName LIKE ? OR " +
                    "  CONVERT(borrow.id, CHAR) LIKE ? OR " +
                    "  CONVERT(FROM_UNIXTIME(borrow.create_timestamp/1000), CHAR) LIKE ? " +
                    " ) ";
        }

        sql += " ORDER BY " +
                " is_return DESC,  " +
                " borrow_time DESC " +
                "LIMIT " + (page * PAGE_MINI_SIZE) + ", " + PAGE_MINI_SIZE;

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        PreparedStatement statement = null;
        ResultSet rs = null;

        List<BorrowBeans> list = new ArrayList<>();
        try {
            //获得操作句柄
            statement = conn.prepareStatement(sql);

            // 插入数据
            statement.setString(1, readerId);
            if (keyword != null) {
                for (int i = 0; i < 12; i++) {
                    statement.setString(i + 2, keyword);
                }
            }

            //根据SQL语句获取数据
            rs = statement.executeQuery();
            while (rs.next()) {
                BorrowBeans beans = new BorrowBeans();
                String id = rs.getString("borrow_id");
                String createTimestamp = rs.getString("borrow_time");
                String willBackTimestamp = rs.getString("will_back_timestamp");
                String isReturn = rs.getString("is_return");
                String borrowAdminId = rs.getString("borrow_admin");
                String returnAdminId = rs.getString("return_admin");
                String borrowAdminName = rs.getString("borrow_admin_name");
                String bookName = rs.getString("book_name");
                String bookBarcode = rs.getString("book_isbn");
                String bookAuthor = rs.getString("book_author");
                String bookPublisher = rs.getString("book_publisher");
                String bookStack = rs.getString("book_stack");
                String bookShelf = rs.getString("book_shelf");
                String bookPrice = rs.getString("book_price");//价格
                String bookId = rs.getString("book_id");
                String bookNumber = rs.getString("borrow_number"); // 借出数量


                beans.setId(id);
                beans.setBookNumber(bookNumber);
                beans.setCreateTimestamp(createTimestamp);
                beans.setWillBackTimestamp(willBackTimestamp);
                beans.setIsReturn(isReturn);
                beans.setBorrowAdminId(borrowAdminId);
                beans.setReturnAdminId(returnAdminId);
                beans.setBorrowAdminName(borrowAdminName);

                beans.setBookPrice(bookPrice);

                beans.setBookName(bookName);
                beans.setBookAuthor(bookAuthor);
                beans.setBookPublisher(bookPublisher);
                beans.setBookId(bookId);
                beans.setBookBarcode(bookBarcode);
                beans.setBookStack(bookStack);
                beans.setBookShelf(bookShelf);
                beans.setReaderId(readerId);
                list.add(beans);
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
        return list;
    }

    /**
     * 续借
     * @param borrowId 借出编号
     * @param duration 续借时长
     * @param status 书籍状态
     * @param adminID 操作员
     * @param time 时间
     * @return 成功为null，否则为错误信息
     */
    public String borrowRenew(String borrowId, String duration, String status, String adminID, String time) {
        String sql = "UPDATE `t_borrow`  " +
                "SET  " +
                " `return_admin_id` = ?,  " +
                " `duration` = `duration` + ?,  " +
                " `status` = ?, " +
                " `update_timestamp` = ?  " +
                "WHERE `id` = ?";

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        //声明句柄对象
        PreparedStatement statement = null;
        try {

            //获得操作句柄
            statement = conn.prepareStatement(sql);
            //拼接语句
            statement.setString(1, adminID);
            statement.setString(2, duration);
            statement.setString(3, status);
            statement.setString(4, time);
            statement.setString(5, borrowId);

            //执行语句
            if (statement.executeUpdate() < 1) {
                conn.rollback();
                return "写入数据失败";
            }

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException t) {
                t.printStackTrace();
                return "数据库错误：" + t.getMessage();
            }
            return "数据库错误：" + e.getMessage();
        } finally {
            //一定要做的事，释放连接
            DBUtil.free(statement, conn);
        }
        return null;
    }

    /**
     * 还入图书
     * @param borrowId 借出编号
     * @param pay 支付费用
     * @param status 状态，如果是3则不添加到库存中
     * @param adminID 管理员ID
     * @param time 时间
     * @param number 还入数量
     * @param bookId 书ID
     * @return 成功为null，否则为错误信息
     */
    public String borrowBack(String borrowId, String pay, String status, String adminID, String time, String number, String bookId) {
        String sql = "UPDATE `t_borrow`  " +
                "SET  " +
                "`return_admin_id` = ?, " +
                "`status` = ?,  " +
                "`penalty` = ?,  " +
                "`update_timestamp` = ?,  " +
                "`return_timestamp` = ?  " +
                "WHERE `id` = ? ";

        //获得数据库连接
        Connection conn = DBUtil.getConn();
        //声明句柄对象
        PreparedStatement statement = null;
        try {
            // 开启事务
            conn.setAutoCommit(false);

            //获得操作句柄
            statement = conn.prepareStatement(sql);
            //拼接语句
            statement.setString(1, adminID);
            statement.setString(2, status);
            statement.setString(3, pay);
            statement.setString(4, time);
            statement.setString(5, time);
            statement.setString(6, borrowId);

            //执行语句
            if (statement.executeUpdate() < 1) {
                conn.rollback();
                return "写入数据失败";
            }
            statement.close();

            //--------------------------更新图书数据-----------------------------------

            if(!"3".equals(status)) {
                sql = "UPDATE " +
                        "`books_info` " +
                        "SET `Quantity` = `Quantity` + ? " +
                        "WHERE `BookID` = ?";

                statement = conn.prepareStatement(sql);
                statement.setString(1, number);
                statement.setString(2, bookId);

                //执行语句
                if (statement.executeUpdate() < 1) {
                    conn.rollback();
                    return "更新数据失败";
                }
            }else{
                System.out.println("书籍已丢失");
            }

            // 提交数据
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException t) {
                t.printStackTrace();
                return "数据库错误：" + t.getMessage();
            }
            return "数据库错误：" + e.getMessage();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException t) {
                t.printStackTrace();
            }
            //一定要做的事，释放连接
            DBUtil.free(statement, conn);
        }
        return null;
    }
}
