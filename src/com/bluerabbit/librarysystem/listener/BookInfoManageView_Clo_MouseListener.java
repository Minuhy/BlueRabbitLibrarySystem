package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.bluerabbit.librarysystem.view.BookInfoManageView;
import com.bluerabbit.librarysystem.view.SetButtonUporDown;
/**
 * 图书管理类里关闭按钮的监听
 * @author minuy
 *
 */
public class BookInfoManageView_Clo_MouseListener implements MouseListener {

	BookInfoManageView wv;
	JLabel label;
	
	public BookInfoManageView_Clo_MouseListener(
			BookInfoManageView bookInfoManageView) {
		// TODO Auto-generated constructor stub
		this.wv = bookInfoManageView;
		this.label = wv.getBclo();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//关闭窗口
		wv.dispose();
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
