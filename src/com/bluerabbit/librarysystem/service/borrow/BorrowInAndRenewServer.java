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


    public BorrowInAndRenewServer(BorrowInAndRenewView b3v) {
        this.b3v = b3v;
        this.dao = new BorrowDao();
        keywordBook = null;
        keywordReader = null;
    }

    /**
     * ��һҳ��
     */
    public void prevBook() {

        if (currentBookPage > 1) {
            currentBookPage -= 1;

            List<BorrowBeans> list = dao.getBookBySearchAndReaderId(keywordBook, readerId, currentBookPage);
            b3v.setBookInfo(currentBookPage, totalBookPage, list, keywordBook);
        }
    }

    /**
     * ��һҳ��
     */
    public void nextBook() {
        if (currentBookPage < totalBookPage) {
            currentBookPage += 1;

            List<BorrowBeans> list = dao.getBookBySearchAndReaderId(keywordBook, readerId, currentBookPage);
            b3v.setBookInfo(currentBookPage, totalBookPage, list, keywordBook);
        }
    }

    /**
     * ��һ������
     */
    public void nextReader() {

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(b3v, "��������߹ؼ��ʵ��������ٲ����˰�ť");
            return;
        }

        if (currentReaderPage < totalReaderPage) {
            currentReaderPage += 1;

            ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);
            if (beans == null) {
                JOptionPane.showMessageDialog(b3v, "�޽��");
                return;
            }
            b3v.setReaderInfo(currentReaderPage, totalReaderPage, beans);
        }
    }

    /**
     * ��һ������
     */
    public void prevReader() {

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(b3v, "��������߹ؼ��ʵ��������ٲ����˰�ť");
            return;
        }

        if (currentReaderPage > 1) {
            currentReaderPage -= 1;

            ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);
            if (beans == null) {
                JOptionPane.showMessageDialog(b3v, "�޽��");
                return;
            }
            b3v.setReaderInfo(currentReaderPage, totalReaderPage, beans);
        }
    }

    /**
     * ������
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
                JOptionPane.showMessageDialog(b3v, "���������鼮�ؼ����ٵ�����");
            }
            return;
        }

        readerId = b3v.getCltReaderId().getText();

        if (readerId == null || readerId.equals("")) {
            System.out.println("����IDΪ��");
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
            System.out.println("����IDΪ��");
            return;
        }

        totalBookCount = dao.getBookCountBySearchAndReaderId(readerId, null);
        System.out.println("�������������" + totalBookCount);
        totalBookPage = (totalBookCount / BorrowDao.PAGE_MINI_SIZE) + ((totalBookCount % BorrowDao.PAGE_MINI_SIZE) != 0 ? 1 : 0);
        System.out.println("�������ҳ����" + totalBookPage);
        currentBookPage = 1;

        List<BorrowBeans> list = dao.getBookBySearchAndReaderId(null, readerId, currentBookPage);

        b3v.setBookInfo(currentBookPage, totalBookPage, list, null);
    }

    /**
     * ������
     */
    public void searchReader() {
        // �õ��ؼ���
        keywordReader = b3v.getJtfReaderKeyword().getText();

        if (keywordReader == null || keywordReader.equals("")) {
            JOptionPane.showMessageDialog(b3v, "����������߹ؼ����ٵ�����");
            return;
        }

        keywordReader = '%' + keywordReader + '%';

        totalReaderCount = dao.getReaderCountBySearch(keywordReader);
        totalReaderPage = totalReaderCount;
        currentReaderPage = 1;

        ReaderInfoBeans beans = dao.getReaderBySearch(keywordReader, currentReaderPage);

        if (beans == null) {
            JOptionPane.showMessageDialog(b3v, "�޽��");
            return;
        }

        b3v.setReaderInfo(currentReaderPage, totalReaderPage, beans);
    }

    /**
     * ����
     */
    public void renew() {
        String duration = b3v.getCltBackPay().getText();
        int row = b3v.getTableDataView().getSelectedRow();//��ȡѡ�е���
        if (row < 0) {
            JOptionPane.showMessageDialog(b3v, "��ѡ����Ҫ�������鼮");
            return;
        }
        String borrowId = String.valueOf(b3v.getTableDataView().getValueAt(row, 0));
        String status = b3v.getJcbBookStatus().getSelectedItem().toString();

        try {
            int i = Integer.parseInt(duration);
            if (i < 1) {
                JOptionPane.showMessageDialog(b3v, "����ʱ������һ��");
                return;
            } else if (i > 180) {
                JOptionPane.showMessageDialog(b3v, "����ʱ������180��");
                return;
            }
            long d = i;
            d = d * 24 * 60 * 60 * 1000;
            duration = String.valueOf(d);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(b3v, "����ʱ����ʽ������ֻ��������");
            return;
        }

        // 0������1���飬2�������أ�3��ʧ
        if ("����".equals(status)) {
            status = "1";
        } else if ("��������".equals(status)) {
            status = "2";
        } else if ("��ʧ".equals(status)) {
            status = "3";
        } else {
            status = "0";
        }

        String adminID = String.valueOf(MainView.getAdmin().getAdminID()); // �������Ա
        String time = String.valueOf(System.currentTimeMillis());

        String result = dao.borrowRenew(borrowId, duration, status, adminID, time);

        if (result == null) {
            JOptionPane.showMessageDialog(b3v, "����ɹ�");
            searchBook(true);
        } else {
            JOptionPane.showMessageDialog(b3v, "����ʱ����" + result);
        }
    }

    /**
     * ����
     */
    public void back() {
        String pay = b3v.getCltBackPay().getText();
        int row = b3v.getTableDataView().getSelectedRow();//��ȡѡ�е���
        if (row < 0) {
            JOptionPane.showMessageDialog(b3v, "��ѡ����Ҫ�������鼮");
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
                JOptionPane.showMessageDialog(b3v, "֧������������Ԫ");
                return;
            } else if (i > 100000000) {
                JOptionPane.showMessageDialog(b3v, "֧����������һ��Ԫ");
                return;
            }
            pay = String.valueOf(i);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(b3v, "֧�����ø�ʽ������ֻ��������");
            return;
        }

        try {
            int i = Integer.parseInt(number);
            if (i < 1) {
                JOptionPane.showMessageDialog(b3v, "��������һ��");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(b3v, "���������ʽ��������ϵ����Ա");
            return;
        }


        // 0������1���飬2�������أ�3��ʧ
        if ("����".equals(status)) {
            status = "1";
        } else if ("��������".equals(status)) {
            status = "2";
        } else if ("��ʧ".equals(status)) {
            status = "3";
        } else {
            status = "0";
        }

        if(!"δ�黹".equals(isBack)){
            JOptionPane.showMessageDialog(b3v,"�鼮�ѻ��룡");
            return;
        }

        String adminID = String.valueOf(MainView.getAdmin().getAdminID()); // �������Ա
        String time = String.valueOf(System.currentTimeMillis());

        String result = dao.borrowBack(borrowId, pay, status, adminID, time,number,bookId);

        if (result == null) {
            JOptionPane.showMessageDialog(b3v, "����ɹ�");
            searchBook(true);
        } else {
            JOptionPane.showMessageDialog(b3v, "����ʱ����" + result);
        }
    }
}
