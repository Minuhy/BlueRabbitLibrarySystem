package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.service.AddBookInfo;
import com.bluerabbit.librarysystem.service.FixBookInfo;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
import com.bluerabbit.librarysystem.view.BookInfoUpdateView;
/**
 * 书籍详情的确认按钮监听类
 * @author minuy
 *
 */
public class BookInfoUpdate_ok_ActionListener implements ActionListener {
	BookInfoUpdateView biuv;
	BookInfoManageView bimv;
	public BookInfoUpdate_ok_ActionListener(
			BookInfoUpdateView bookInfoUpdateView,BookInfoManageView bimv) {
		// TODO Auto-generated constructor stub
		this.biuv = bookInfoUpdateView;
		this.bimv = bimv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(biuv.isMode()){
			//增加模式
			new AddBookInfo(bimv).add(biuv);
			System.out.println("添加数据");
		}else{
			//修改模式
			//这里判断表格有没有被选中也是服务层的，所以放了两个构造方法
			new FixBookInfo().fix(biuv,bimv);
			System.out.println("更改数据");
		}
	}

}
