package com.bluerabbit.librarysystem.service.borrow;

import com.bluerabbit.librarysystem.beans.BorrowBeans;
import com.bluerabbit.librarysystem.dao.BorrowDao;
import com.bluerabbit.librarysystem.view.borrow.BorrowBookView;

import java.util.List;

/**
 * @author minuhy
 * @date 2023/3/9 0:54
 */
public class BorrowMainServer {

    int totalCount; // ����
    int totalPage; // ��ҳ��
    int currentPage; // ��ǰҳ��

    BorrowBookView bbv;
    BorrowDao dao;
    public BorrowMainServer(BorrowBookView bbv) {
        this.bbv = bbv;
        dao = new BorrowDao();
    }

    public void Search(String way, String keyWord, int page) {
        System.out.println(way + ":" + keyWord + ":" + page);

        boolean isSearch = true;
        if(keyWord.equals("")){
            isSearch = false;
        }

        keyWord = "%" + keyWord + "%";

        String searchSql = " WHERE reader.ReaderID LIKE ? "; // ѧ��
        if ("���".equals(way)) {
            searchSql = " WHERE books_info.BookID LIKE ? ";
        } else if ("����".equals(way)) {
            searchSql = " WHERE reader.ReaderName LIKE ? ";
        } else if ("����".equals(way)) {
            searchSql = " WHERE books_info.BookName LIKE ? ";
        } else if ("�黹ʱ��".equals(way)) {
            searchSql = " WHERE IF(t_borrow.return_timestamp=0,'δ�黹',FROM_UNIXTIME(t_borrow.return_timestamp)) LIKE ? ";
        }

        //��ȡ����

        totalCount = dao.getAllCount(searchSql, keyWord,isSearch);
        System.out.println("�����¼�ܼƣ�" + totalCount);
        this.totalPage = (totalCount / BorrowDao.PAGE_SIZE) + ((totalCount % BorrowDao.PAGE_SIZE) != 0 ? 1 : 0);
        System.out.println("�����¼��ҳ����" + totalPage);
        if (page < 1) {
            page = 1;
        } else if (page > this.totalPage) {
            page = this.totalPage;
        }

        bbv.getTipPage().setText(page + "/" + this.totalPage);

        List<BorrowBeans> list = dao.getAllBorrow(searchSql, keyWord, page,isSearch);
        //��������
        bbv.setTableData(list);
        currentPage = page;

    }

    public int getTotalPage() {
        return totalPage;
    }


    public int getCurrentPage() {
        return currentPage;
    }

}
