package com.bluerabbit.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.dao.BookInfoInquire;
import com.bluerabbit.librarysystem.view.AdvancedSearchView;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
/**
 * 图书管理界面的高级搜索服务逻辑
 * @author minuy
 *
 */
public class AdvancedSearchServer {
	AdvancedSearchView advancedSearchView;
	BookInfoManageView wv;
	boolean where;//用来确认是否加了where
	public AdvancedSearchServer(AdvancedSearchView advancedSearchView,
			BookInfoManageView wv) {
		// TODO Auto-generated constructor stub
		this.advancedSearchView = advancedSearchView;
		this.wv = wv;
		where = false;
		
		List<BookInfoBeans> list;//结果的数据

		//这里应该用stringbuff的
		String sql = "select *  from Books_Info";
		ArrayList<String> sqlkey = new ArrayList<String>(); 

		String buff = null;
		

		//馆藏信息
		buff = advancedSearchView.getStackL().getText();//所在书室
		if(!buff.equals("")){
			sqlkey.add(buff);
			//拼接
			sql = ifwhere(sql);
			sql = sql + "Stack like ?";
		}

		buff = advancedSearchView.getBookShelfL().getText();//所在书架
		if(!buff.equals("")){
			sqlkey.add(buff);
			//拼接
			sql = ifwhere(sql);
			sql = sql + "BookShelf like ?";
		}

		buff = advancedSearchView.getBookClassifyL().getText();//书刊类别
		if(!buff.equals("")){
			sqlkey.add(buff);
			//拼接
			sql = ifwhere(sql);
			sql = sql + "BookClassify like ?";
		}

		buff = advancedSearchView.getBookTypeL().getText();//馆藏类别
		if(!buff.equals("")){
			sqlkey.add(buff);
			//拼接
			sql = ifwhere(sql);
			sql = sql + "BookType like ?";
		}

		//书籍信息
		buff = advancedSearchView.getBookNameL().getText();//书名
		if(!buff.equals("")){
			sqlkey.add(buff);
			//拼接
			sql = ifwhere(sql);
			sql = sql + "BookName like ?";
		}

		buff = advancedSearchView.getAuthorL().getText();//作者
		if(!buff.equals("")){
			sqlkey.add(buff);
			//拼接
			sql = ifwhere(sql);
			sql = sql + "Author like ?";
		}

		buff = advancedSearchView.getPublisherL().getText();//出版社
		if(!buff.equals("")){
			sqlkey.add(buff);
			//拼接
			sql = ifwhere(sql);
			sql = sql + "Publisher like ?";
		}

		buff = advancedSearchView.getBookThemL().getText();//主题词
		if(!buff.equals("")){
			sqlkey.add(buff);
			//拼接
			sql = ifwhere(sql);
			sql = sql + "BookThem like ?";
		}

		buff = advancedSearchView.getBookIDL().getText();//书刊编号
		if(!buff.equals("")){
			sqlkey.add(buff);
			//拼接
			sql = ifwhere(sql);
			sql = sql + "BookID like ?";
		}

		buff = advancedSearchView.getBookBarcodeL().getText();//书刊条码
		if(!buff.equals("")){
			sqlkey.add(buff);
			//拼接
			sql = ifwhere(sql);
			sql = sql + "BookBarcode like ?";
		}
		
		System.out.println(sql);
		
		//根据语句获取数据
		list = new BookInfoInquire().InquireBookInfoByLotKey(sql, sqlkey);
		
		//更新数据
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
