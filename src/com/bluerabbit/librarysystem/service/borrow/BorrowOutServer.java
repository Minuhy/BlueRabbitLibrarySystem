package com.bluerabbit.librarysystem.service.borrow;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
import com.bluerabbit.librarysystem.dao.BorrowDao;
import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.borrow.BorrowOutView;

import javax.swing.*;

/**
 * @author minuhy
 * @date 2023/3/9 1:50
 */
public class BorrowOutServer {

    BorrowDao dao;
    BorrowOutView bov;


    // 读者
    int totalReaderCount; // 总数
    int totalReaderPage; // 总页数
    int currentReaderPage; // 当前页面
    String keywordReader;

    // 书籍
    int totalBookCount; // 总数
    int totalBookPage; // 总页数
    int currentBookPage; // 当前页面
    String keywordBook;


    public BorrowOutServer(BorrowOutView bov) {
        this.bov = bov;
        this.dao = new BorrowDao();
        keywordBook = null;
        keywordReader = null;
    }

    /**
     * 借出
     */
    public void out() {
        String readerId = bov.getCltReaderId().getText(); // 读者ID
        String bookId = bov.getCltBookId().getText(); // 书ID

        String surplusNumber = bov.getCltBookNumber().getText(); // 剩余册数

        String duration = bov.getCltBorrowDuration().getText(); // 借出时长
        String number = bov.getCltBorrowNumber().getText(); // 借出册数

        try {
            int i = Integer.parseInt(duration);
            if (i < 1) {
                JOptionPane.showMessageDialog(bov, "借出时长至少一天");
                return;
            } else if (i > 180) {
                JOptionPane.showMessageDialog(bov, "借出时长至多180天");
                return;
            }
            long d = i;
            d = d * 24* 60 * 60 * 1000;
            duration = String.valueOf(d);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(bov, "借出时长格式错误，请只输入数字");
            return;
        }

        try {
            int i = Integer.parseInt(number);
            if (i < 1) {
                JOptionPane.showMessageDialog(bov, "借出至少一册");
                return;
            }
            try {
                int si = Integer.parseInt(surplusNumber);
                if (i > si) {
                    JOptionPane.showMessageDialog(bov, "剩余册数不足");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(bov, "系统数据格式错误");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(bov, "借出册数格式错误，请只输入数字");
            return;
        }

        String adminID = String.valueOf(MainView.getAdmin().getAdminID()); // 借出管理员
        String time = String.valueOf(System.currentTimeMillis());

        String result = dao.borrowOut(readerId, bookId, duration, number, adminID, time);

        if (result == null) {
            JOptionPane.showMessageDialog(bov, "借出成功");
            bov.resetData();
        } else {
            JOptionPane.showMessageDialog(bov, "借出时出错：" + result);
        }
    }

    /**
     * 上一本书
     */
    public void prevBook() {
        if (keywordBook == null || keywordBook.equals("")) {
            JOptionPane.showMessageDialog(bov, "请输入书籍关键词搜索");
            return;
        }

        if (currentBookPage > 1) {
            currentBookPage -= 1;
        }
        BookInfoBeans beans = dao.getBookBySearch(keywordBook, currentBookPage);
        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "无结果");
            return;
        }
        bov.setBookInfo(currentBookPage, totalBookPage, beans);
    }

    /**
     * 下一本书
     */
    public void nextBook() {
        if (keywordBook == null || keywordBook.equals("")) {
            JOptionPane.showMessageDialog(bov, "请输入书籍关键词搜索");
            return;
        }

        if (currentBookPage < totalBookPage) {
            currentBookPage += 1;
        }
        BookInfoBeans beans = dao.getBookBySearch(keywordBook, currentBookPage);
        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "无结果");
            return;
        }
        bov.setBookInfo(currentBookPage, totalBookPage, beans);
    }

    /**
     * 下一个读者
     */
    public void nextReader() {

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(bov, "请输入读者关键词搜索");
            return;
        }

        if (currentReaderPage < totalReaderPage) {
            currentReaderPage += 1;
        }
        ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);
        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "无结果");
            return;
        }
        bov.setReaderInfo(currentReaderPage, totalReaderPage, beans);
    }

    /**
     * 上一个读者
     */
    public void prevReader() {

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(bov, "请输入读者关键词搜索");
            return;
        }

        if (currentReaderPage > 1) {
            currentReaderPage -= 1;
        }
        ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);
        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "无结果");
            return;
        }
        bov.setReaderInfo(currentReaderPage, totalReaderPage, beans);
    }

    /**
     * 搜索书
     */
    public void searchBook() {
        // 拿到关键词
        keywordBook = bov.getJtfBookKeyword().getText();

        if (keywordBook == null || keywordBook.equals("")) {
            JOptionPane.showMessageDialog(bov, "请输入书籍关键词搜索");
            return;
        }

        keywordBook = '%' + keywordBook + '%';

        totalBookCount = dao.getBookCountBySearch(keywordBook);
        totalBookPage = totalBookCount;
        currentBookPage = 1;

        BookInfoBeans beans = dao.getBookBySearch(keywordBook, currentBookPage);

        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "无结果");
            return;
        }

        bov.setBookInfo(currentBookPage, totalBookPage, beans);
    }

    /**
     * 搜索人
     */
    public void searchReader() {
        // 拿到关键词
        keywordReader = bov.getJtfReaderKeyword().getText();

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(bov, "请输入读者关键词搜索");
            return;
        }

        keywordReader = '%' + keywordReader + '%';

        totalReaderCount = dao.getReaderCountBySearch(keywordReader);
        totalReaderPage = totalReaderCount;
        currentReaderPage = 1;

        ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);

        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "无结果");
            return;
        }

        bov.setReaderInfo(currentReaderPage, totalReaderPage, beans);
    }
}
