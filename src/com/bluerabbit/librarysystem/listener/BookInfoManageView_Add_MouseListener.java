package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.bluerabbit.librarysystem.view.BookInfoManageView;
import com.bluerabbit.librarysystem.view.BookInfoUpdateView;
import com.bluerabbit.librarysystem.view.SetButtonUporDown;
/**
 * 图书管理界面增加按钮的监听类
 * @author minuy
 *
 */
public class BookInfoManageView_Add_MouseListener implements MouseListener {

	BookInfoManageView wv;
	JLabel label;
	
	public BookInfoManageView_Add_MouseListener(
			BookInfoManageView bookInfoManageView) {
		// TODO Auto-generated constructor stub
		this.wv = bookInfoManageView;
		this.label = wv.getBadd();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		new BookInfoUpdateView(wv,"添加图书信息",null,true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		SetButtonUporDown.setButtonDown(label);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		SetButtonUporDown.SetButtonUp(label);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		SetButtonUporDown.SetButtonUp(label);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		SetButtonUporDown.reSetButton(label);
	}

}
