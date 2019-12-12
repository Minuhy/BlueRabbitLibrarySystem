package com.bluerabbit.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.dao.BookInfoInquire;
import com.bluerabbit.librarysystem.view.AdvancedSearchView;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
/**
 * ͼ��������ĸ߼����������߼�
 * @author minuy
 *
 */
public class AdvancedSearchServer {
	AdvancedSearchView advancedSearchView;
	BookInfoManageView wv;
	boolean where;//����ȷ���Ƿ����where
	public AdvancedSearchServer(AdvancedSearchView advancedSearchView,
			BookInfoManageView wv) {
		// TODO Auto-generated constructor stub
		this.advancedSearchView = advancedSearchView;
		this.wv = wv;
		where = false;
		
		List<BookInfoBeans> list;//���������

		//����Ӧ����stringbuff��
		String sql = "select *  from Books_Info";
		ArrayList<String> sqlkey = new ArrayList<String>(); 

		String buff = null;
		

		//�ݲ���Ϣ
		buff = advancedSearchView.getStackL().getText();//��������
		if(!buff.equals("")){
			sqlkey.add(buff);
			//ƴ��
			sql = ifwhere(sql);
			sql = sql + "Stack like ?";
		}

		buff = advancedSearchView.getBookShelfL().getText();//�������
		if(!buff.equals("")){
			sqlkey.add(buff);
			//ƴ��
			sql = ifwhere(sql);
			sql = sql + "BookShelf like ?";
		}

		buff = advancedSearchView.getBookClassifyL().getText();//�鿯���
		if(!buff.equals("")){
			sqlkey.add(buff);
			//ƴ��
			sql = ifwhere(sql);
			sql = sql + "BookClassify like ?";
		}

		buff = advancedSearchView.getBookTypeL().getText();//�ݲ����
		if(!buff.equals("")){
			sqlkey.add(buff);
			//ƴ��
			sql = ifwhere(sql);
			sql = sql + "BookType like ?";
		}

		//�鼮��Ϣ
		buff = advancedSearchView.getBookNameL().getText();//����
		if(!buff.equals("")){
			sqlkey.add(buff);
			//ƴ��
			sql = ifwhere(sql);
			sql = sql + "BookName like ?";
		}

		buff = advancedSearchView.getAuthorL().getText();//����
		if(!buff.equals("")){
			sqlkey.add(buff);
			//ƴ��
			sql = ifwhere(sql);
			sql = sql + "Author like ?";
		}

		buff = advancedSearchView.getPublisherL().getText();//������
		if(!buff.equals("")){
			sqlkey.add(buff);
			//ƴ��
			sql = ifwhere(sql);
			sql = sql + "Publisher like ?";
		}

		buff = advancedSearchView.getBookThemL().getText();//�����
		if(!buff.equals("")){
			sqlkey.add(buff);
			//ƴ��
			sql = ifwhere(sql);
			sql = sql + "BookThem like ?";
		}

		buff = advancedSearchView.getBookIDL().getText();//�鿯���
		if(!buff.equals("")){
			sqlkey.add(buff);
			//ƴ��
			sql = ifwhere(sql);
			sql = sql + "BookID like ?";
		}

		buff = advancedSearchView.getBookBarcodeL().getText();//�鿯����
		if(!buff.equals("")){
			sqlkey.add(buff);
			//ƴ��
			sql = ifwhere(sql);
			sql = sql + "BookBarcode like ?";
		}
		
		System.out.println(sql);
		
		//��������ȡ����
		list = new BookInfoInquire().InquireBookInfoByLotKey(sql, sqlkey);
		
		//��������
		wv.setTableData(list);

	}

	private String ifwhere(String sql){
		if(where){
			return sql + " and ";
		}else{
			where = true;
			return sql + " where ";
		}
	}
}
