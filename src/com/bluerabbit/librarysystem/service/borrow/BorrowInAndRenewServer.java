package com.bluerabbit.librarysystem.service.borrow;

import com.bluerabbit.librarysystem.beans.BorrowBeans;
import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
import com.bluerabbit.librarysystem.dao.BorrowDao;
import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.borrow.BorrowInAndRenewView;

import javax.swing.*;
import java.util.List;

/**
 * @author minuhy
 * @date 2023/3/10 12:25
 */
public class BorrowInAndRenewServer {

    BorrowDao dao;
    BorrowInAndRenewView b3v;

    String readerId;

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


    public BorrowInAndRenewServer(BorrowInAndRenewView b3v) {
        this.b3v = b3v;
        this.dao = new BorrowDao();
        keywordBook = null;
        keywordReader = null;
    }

    /**
     * 上一页书
     */
    public void prevBook() {

        if (currentBookPage > 1) {
            currentBookPage -= 1;

            List<BorrowBeans> list = dao.getBookBySearchAndReaderId(keywordBook, readerId, currentBookPage);
            b3v.setBookInfo(currentBookPage, totalBookPage, list, keywordBook);
        }
    }

    /**
     * 下一页书
     */
    public void nextBook() {
        if (currentBookPage < totalBookPage) {
            currentBookPage += 1;

            List<BorrowBeans> list = dao.getBookBySearchAndReaderId(keywordBook, readerId, currentBookPage);
            b3v.setBookInfo(currentBookPage, totalBookPage, list, keywordBook);
        }
    }

    /**
     * 下一个读者
     */
    public void nextReader() {

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(b3v, "请输入读者关键词点搜索后再操作此按钮");
            return;
        }

        if (currentReaderPage < totalReaderPage) {
            currentReaderPage += 1;

            ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);
            if (beans == null) {
                JOptionPane.showMessageDialog(b3v, "无结果");
                return;
            }
            b3v.setReaderInfo(currentReaderPage, totalReaderPage, beans);
        }
    }

    /**
     * 上一个读者
     */
    public void prevReader() {

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(b3v, "请输入读者关键词点搜索后再操作此按钮");
            return;
        }

        if (currentReaderPage > 1) {
            currentReaderPage -= 1;

            ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);
            if (beans == null) {
                JOptionPane.showMessageDialog(b3v, "无结果");
                return;
            }
            b3v.setReaderInfo(currentReaderPage, totalReaderPage, beans);
        }
    }

    /**
     * 搜索书
     */
    public void searchBook() {
        searchBook(false);
    }

    public void searchBook(boolean isAuto) {
        keywordBook = b3v.getJtfBookKeyword().getText();
        if (keywordBook == null || keywordBook.equals("")) {
            if (isAuto) {
                loadBookByReader();
            } else {
                JOptionPane.showMessageDialog(b3v, "请先输入书籍关键词再点搜索");
            }
            return;
        }

        readerId = b3v.getCltReaderId().getText();

        if (readerId == null || readerId.equals("")) {
            System.out.println("读者ID为空");
            return;
        }


        keywordBook = '%' + keywordBook + '%';

        totalBookCount = dao.getBookCountBySearchAndReaderId(readerId, keywordBook);
        totalBookPage = (totalBookCount / BorrowDao.PAGE_MINI_SIZE) + ((totalBookCount % BorrowDao.PAGE_MINI_SIZE) != 0 ? 1 : 0);
        currentBookPage = 1;

        List<BorrowBeans> list = dao.getBookBySearchAndReaderId(keywordBook, readerId, currentBookPage);

        b3v.setBookInfo(currentBookPage, totalBookPage, list, keywordBook);
    }

    public void loadBookByReader() {
        readerId = b3v.getCltReaderId().getText();

        keywordBook = null;

        if (readerId == null || readerId.equals("")) {
            System.out.println("读者ID为空");
            return;
        }

        totalBookCount = dao.getBookCountBySearchAndReaderId(readerId, null);
        System.out.println("借出数据总数：" + totalBookCount);
        totalBookPage = (totalBookCount / BorrowDao.PAGE_MINI_SIZE) + ((totalBookCount % BorrowDao.PAGE_MINI_SIZE) != 0 ? 1 : 0);
        System.out.println("借出数据页数：" + totalBookPage);
        currentBookPage = 1;

        List<BorrowBeans> list = dao.getBookBySearchAndReaderId(null, readerId, currentBookPage);

        b3v.setBookInfo(currentBookPage, totalBookPage, list, null);
    }

    /**
     * 搜索人
     */
    public void searchReader() {
        // 拿到关键词
        keywordReader = b3v.getJtfReaderKeyword().getText();

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(b3v, "请先输入读者关键词再点搜索");
            return;
        }

        keywordReader = '%' + keywordReader + '%';

        totalReaderCount = dao.getReaderCountBySearch(keywordReader);
        totalReaderPage = totalReaderCount;
        currentReaderPage = 1;

        ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);

        if (beans == null) {
            JOptionPane.showMessageDialog(b3v, "无结果");
            return;
        }

        b3v.setReaderInfo(currentReaderPage, totalReaderPage, beans);
    }

    /**
     * 续借
     */
    public void renew() {
        String duration = b3v.getCltBackPay().getText();
        int row = b3v.getTableDataView().getSelectedRow();//获取选中的行
        if (row < 0) {
            JOptionPane.showMessageDialog(b3v, "请选择需要操作的书籍");
            return;
        }
        String borrowId = String.valueOf(b3v.getTableDataView().getValueAt(row, 0));
        String status = b3v.getJcbBookStatus().getSelectedItem().toString();

        try {
            int i = Integer.parseInt(duration);
            if (i < 1) {
                JOptionPane.showMessageDialog(b3v, "续借时长至少一天");
                return;
            } else if (i > 180) {
                JOptionPane.showMessageDialog(b3v, "续借时长至多180天");
                return;
            }
            long d = i;
            d = d * 24 * 60 * 60 * 1000;
            duration = String.valueOf(d);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(b3v, "续借时长格式错误，请只输入数字");
            return;
        }

        // 0正常，1破碎，2破损严重，3丢失
        if ("破损".equals(status)) {
            status = "1";
        } else if ("破损严重".equals(status)) {
            status = "2";
        } else if ("丢失".equals(status)) {
            status = "3";
        } else {
            status = "0";
        }

        String adminID = String.valueOf(MainView.getAdmin().getAdminID()); // 还入管理员
        String time = String.valueOf(System.currentTimeMillis());

        String result = dao.borrowRenew(borrowId, duration, status, adminID, time);

        if (result == null) {
            JOptionPane.showMessageDialog(b3v, "续借成功");
            searchBook(true);
        } else {
            JOptionPane.showMessageDialog(b3v, "续借时出错：" + result);
        }
    }

    /**
     * 还入
     */
    public void back() {
        String pay = b3v.getCltBackPay().getText();
        int row = b3v.getTableDataView().getSelectedRow();//获取选中的行
        if (row < 0) {
            JOptionPane.showMessageDialog(b3v, "请选择需要操作的书籍");
            return;
        }
        String borrowId = String.valueOf(b3v.getTableDataView().getValueAt(row, 0));
        String isBack = String.valueOf(b3v.getTableDataView().getValueAt(row, 12));
        String number = String.valueOf(b3v.getTableDataView().getValueAt(row, 10));
        String bookId = String.valueOf(b3v.getTableDataView().getValueAt(row, 4));
        String status = b3v.getJcbBookStatus().getSelectedItem().toString();

        try {
            long i = Long.parseLong(pay);
            if (i < 0) {
                JOptionPane.showMessageDialog(b3v, "支付费用至少零元");
                return;
            } else if (i > 100000000) {
                JOptionPane.showMessageDialog(b3v, "支付费用至多一亿元");
                return;
            }
            pay = String.valueOf(i);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(b3v, "支付费用格式错误，请只输入数字");
            return;
        }

        try {
            int i = Integer.parseInt(number);
            if (i < 1) {
                JOptionPane.showMessageDialog(b3v, "还入至少一册");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(b3v, "还入册数格式错误，请联系技术员");
            return;
        }


        // 0正常，1破碎，2破损严重，3丢失
        if ("破损".equals(status)) {
            status = "1";
        } else if ("破损严重".equals(status)) {
            status = "2";
        } else if ("丢失".equals(status)) {
            status = "3";
        } else {
            status = "0";
        }

        if(!"未归还".equals(isBack)){
            JOptionPane.showMessageDialog(b3v,"书籍已还入！");
            return;
        }

        String adminID = String.valueOf(MainView.getAdmin().getAdminID()); // 还入管理员
        String time = String.valueOf(System.currentTimeMillis());

        String result = dao.borrowBack(borrowId, pay, status, adminID, time,number,bookId);

        if (result == null) {
            JOptionPane.showMessageDialog(b3v, "还入成功");
            searchBook(true);
        } else {
            JOptionPane.showMessageDialog(b3v, "还入时出错：" + result);
        }
    }
}
