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


    // ����
    int totalReaderCount; // ����
    int totalReaderPage; // ��ҳ��
    int currentReaderPage; // ��ǰҳ��
    String keywordReader;

    // �鼮
    int totalBookCount; // ����
    int totalBookPage; // ��ҳ��
    int currentBookPage; // ��ǰҳ��
    String keywordBook;


    public BorrowOutServer(BorrowOutView bov) {
        this.bov = bov;
        this.dao = new BorrowDao();
        keywordBook = null;
        keywordReader = null;
    }

    /**
     * ���
     */
    public void out() {
        String readerId = bov.getCltReaderId().getText(); // ����ID
        String bookId = bov.getCltBookId().getText(); // ��ID

        String surplusNumber = bov.getCltBookNumber().getText(); // ʣ�����

        String duration = bov.getCltBorrowDuration().getText(); // ���ʱ��
        String number = bov.getCltBorrowNumber().getText(); // �������

        try {
            int i = Integer.parseInt(duration);
            if (i < 1) {
                JOptionPane.showMessageDialog(bov, "���ʱ������һ��");
                return;
            } else if (i > 180) {
                JOptionPane.showMessageDialog(bov, "���ʱ������180��");
                return;
            }
            long d = i;
            d = d * 24* 60 * 60 * 1000;
            duration = String.valueOf(d);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(bov, "���ʱ����ʽ������ֻ��������");
            return;
        }

        try {
            int i = Integer.parseInt(number);
            if (i < 1) {
                JOptionPane.showMessageDialog(bov, "�������һ��");
                return;
            }
            try {
                int si = Integer.parseInt(surplusNumber);
                if (i > si) {
                    JOptionPane.showMessageDialog(bov, "ʣ���������");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(bov, "ϵͳ���ݸ�ʽ����");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(bov, "���������ʽ������ֻ��������");
            return;
        }

        String adminID = String.valueOf(MainView.getAdmin().getAdminID()); // �������Ա
        String time = String.valueOf(System.currentTimeMillis());

        String result = dao.borrowOut(readerId, bookId, duration, number, adminID, time);

        if (result == null) {
            JOptionPane.showMessageDialog(bov, "����ɹ�");
            bov.resetData();
        } else {
            JOptionPane.showMessageDialog(bov, "���ʱ����" + result);
        }
    }

    /**
     * ��һ����
     */
    public void prevBook() {
        if (keywordBook == null || keywordBook.equals("")) {
            JOptionPane.showMessageDialog(bov, "�������鼮�ؼ�������");
            return;
        }

        if (currentBookPage > 1) {
            currentBookPage -= 1;
        }
        BookInfoBeans beans = dao.getBookBySearch(keywordBook, currentBookPage);
        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "�޽��");
            return;
        }
        bov.setBookInfo(currentBookPage, totalBookPage, beans);
    }

    /**
     * ��һ����
     */
    public void nextBook() {
        if (keywordBook == null || keywordBook.equals("")) {
            JOptionPane.showMessageDialog(bov, "�������鼮�ؼ�������");
            return;
        }

        if (currentBookPage < totalBookPage) {
            currentBookPage += 1;
        }
        BookInfoBeans beans = dao.getBookBySearch(keywordBook, currentBookPage);
        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "�޽��");
            return;
        }
        bov.setBookInfo(currentBookPage, totalBookPage, beans);
    }

    /**
     * ��һ������
     */
    public void nextReader() {

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(bov, "��������߹ؼ�������");
            return;
        }

        if (currentReaderPage < totalReaderPage) {
            currentReaderPage += 1;
        }
        ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);
        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "�޽��");
            return;
        }
        bov.setReaderInfo(currentReaderPage, totalReaderPage, beans);
    }

    /**
     * ��һ������
     */
    public void prevReader() {

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(bov, "��������߹ؼ�������");
            return;
        }

        if (currentReaderPage > 1) {
            currentReaderPage -= 1;
        }
        ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);
        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "�޽��");
            return;
        }
        bov.setReaderInfo(currentReaderPage, totalReaderPage, beans);
    }

    /**
     * ������
     */
    public void searchBook() {
        // �õ��ؼ���
        keywordBook = bov.getJtfBookKeyword().getText();

        if (keywordBook == null || keywordBook.equals("")) {
            JOptionPane.showMessageDialog(bov, "�������鼮�ؼ�������");
            return;
        }

        keywordBook = '%' + keywordBook + '%';

        totalBookCount = dao.getBookCountBySearch(keywordBook);
        totalBookPage = totalBookCount;
        currentBookPage = 1;

        BookInfoBeans beans = dao.getBookBySearch(keywordBook, currentBookPage);

        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "�޽��");
            return;
        }

        bov.setBookInfo(currentBookPage, totalBookPage, beans);
    }

    /**
     * ������
     */
    public void searchReader() {
        // �õ��ؼ���
        keywordReader = bov.getJtfReaderKeyword().getText();

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(bov, "��������߹ؼ�������");
            return;
        }

        keywordReader = '%' + keywordReader + '%';

        totalReaderCount = dao.getReaderCountBySearch(keywordReader);
        totalReaderPage = totalReaderCount;
        currentReaderPage = 1;

        ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);

        if (beans == null) {
            JOptionPane.showMessageDialog(bov, "�޽��");
            return;
        }

        bov.setReaderInfo(currentReaderPage, totalReaderPage, beans);
    }
}
