package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import com.bluerabbit.librarysystem.view.BookInfoUpdateView;
import com.bluerabbit.librarysystem.view.DateChooseView;
/**
 * 书籍详情的日期选择输入框监听
 * @author minuy
 *
 */
public class BookInfoUpdateView_DateChoose_MouseListener implements
MouseListener {
	BookInfoUpdateView bookInfoUpdateView;
	JTextField gettxt;
	public BookInfoUpdateView_DateChoose_MouseListener(
			BookInfoUpdateView bookInfoUpdateView, JTextField gettxt) {
		// TODO Auto-generated constructor stub
		this.bookInfoUpdateView = bookInfoUpdateView;
		this.gettxt = gettxt;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//点击了一下
		if(bookInfoUpdateView.isEditor()){
			new DateChooseView(bookInfoUpdateView,gettxt);
			System.out.println("选择日期");
		}else{
			System.out.println("禁止编辑");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标点着不放开

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标点击放开了

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标到上面了

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标离开了

	}


}
