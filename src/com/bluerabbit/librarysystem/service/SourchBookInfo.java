package com.bluerabbit.librarysystem.service;

import java.util.List;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.dao.BookInfoInquire;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
/**
 * 图书的普通搜索逻辑服务类
 * @author minuy
 *
 */
public class SourchBookInfo {

	//setTableData

	public SourchBookInfo(BookInfoManageView bookInfoManageView) {
		// TODO Auto-generated constructor stub
		//获取两个框的数据
		String tpye = (String)bookInfoManageView.getChooseWay().getSelectedItem();

		String info = bookInfoManageView.getChooseInfo().getText();

		System.out.println(tpye + ":" + info);
		//根据两个框的数据生成SQL语句

		String sql = "SELECT * from Books_Info WHERE ";
		if(tpye.equals("书刊编号")){
			sql = sql + "BookID like ?";
		}else{
			if(tpye.equals("书刊名称")){
				sql = sql + "BookName like ?";
			}else{
				if(tpye.equals("书刊作者")){
					sql = sql + "Author like ?";
				}
			}
		}

		//DAO执行，获取数据
		BookInfoInquire data = new BookInfoInquire();
		//获取数据
		List<BookInfoBeans> list = data.InquireBookInfoByAKey(sql,info); 

		//根据数据更新表
		bookInfoManageView.setTableData(list);
	}

}
