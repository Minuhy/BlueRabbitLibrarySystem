package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.bluerabbit.librarysystem.service.SourchBookInfo;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
/**
 * 搜索按钮监听
 * @author minuy
 *
 */
public class BookInfoManageView_exeInquire_MouseListener implements
		MouseListener {
	BookInfoManageView bookInfoManageView;
	public BookInfoManageView_exeInquire_MouseListener(
			BookInfoManageView bookInfoManageView) {
		// TODO Auto-generated constructor stub
		this.bookInfoManageView = bookInfoManageView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//搜索一下
		new SourchBookInfo(bookInfoManageView);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//按下去了
		//bookInfoManageView.setJLabelImage(bookInfoManageView.getExeInquire(),"res/book/sousuo2.png",197,27);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//放开了
		//bookInfoManageView.setJLabelImage(bookInfoManageView.getExeInquire(),"res/book/sousuo3.png",197,27);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//移入
		//bookInfoManageView.setJLabelImage(bookInfoManageView.getExeInquire(),"res/book/sousuo3.png",197,27);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//移出
		//bookInfoManageView.setJLabelImage(bookInfoManageView.getExeInquire(),"res/book/sousuo1.png",197,27);
	}
}
