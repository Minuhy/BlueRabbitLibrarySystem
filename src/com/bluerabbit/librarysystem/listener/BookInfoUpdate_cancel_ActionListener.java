package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.view.BookInfoUpdateView;
/**
 * 书籍详情的取消按钮监听类
 * @author minuy
 *
 */
public class BookInfoUpdate_cancel_ActionListener implements ActionListener {

	BookInfoUpdateView biuv;
	
	public BookInfoUpdate_cancel_ActionListener(
			BookInfoUpdateView bookInfoUpdateView) {
		// TODO Auto-generated constructor stub
		this.biuv = bookInfoUpdateView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		biuv.dispose();
	}

}
