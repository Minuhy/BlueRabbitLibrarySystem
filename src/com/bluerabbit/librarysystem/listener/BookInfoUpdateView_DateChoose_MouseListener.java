package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import com.bluerabbit.librarysystem.view.BookInfoUpdateView;
import com.bluerabbit.librarysystem.view.DateChooseView;
/**
 * �鼮���������ѡ����������
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
		//�����һ��
		if(bookInfoUpdateView.isEditor()){
			new DateChooseView(bookInfoUpdateView,gettxt);
			System.out.println("ѡ������");
		}else{
			System.out.println("��ֹ�༭");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//�����Ų��ſ�

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//������ſ���

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//��굽������

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//����뿪��

	}


}
