package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.bluerabbit.librarysystem.service.SourchBookInfo;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
/**
 * ������ť����
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
		//����һ��
		new SourchBookInfo(bookInfoManageView);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//����ȥ��
		//bookInfoManageView.setJLabelImage(bookInfoManageView.getExeInquire(),"res/book/sousuo2.png",197,27);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//�ſ���
		//bookInfoManageView.setJLabelImage(bookInfoManageView.getExeInquire(),"res/book/sousuo3.png",197,27);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//����
		//bookInfoManageView.setJLabelImage(bookInfoManageView.getExeInquire(),"res/book/sousuo3.png",197,27);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//�Ƴ�
		//bookInfoManageView.setJLabelImage(bookInfoManageView.getExeInquire(),"res/book/sousuo1.png",197,27);
	}
}
