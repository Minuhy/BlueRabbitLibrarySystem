package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.bluerabbit.librarysystem.service.FixBookInfo;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
/**
 * ͼ�������ļ�����
 * @author minuy
 *
 */
public class BookInfoManageView_tableView_MouseListener implements
MouseListener {
	BookInfoManageView mv;
	public BookInfoManageView_tableView_MouseListener(
			BookInfoManageView bookInfoManageView) {
		// TODO Auto-generated constructor stub
		this.mv = bookInfoManageView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//��õ������
		System.out.println(e);
		int ckCount = e.getClickCount();
		System.out.println(ckCount);
		if(ckCount == 2) {
			System.out.println("��������");
			new FixBookInfo(mv,false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
