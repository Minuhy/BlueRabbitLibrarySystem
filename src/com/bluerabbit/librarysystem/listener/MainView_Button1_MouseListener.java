package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.borrow.BorrowBookView;

/**
 * ����ͼ���Ĺ���ť�ļ�����
 * @author minuy
 *
 */
public class MainView_Button1_MouseListener implements MouseListener {

	JLabel label;
	MainView mv;
	
	public MainView_Button1_MouseListener(MainView button1) {
		this.label = button1.getButton1();
		this.mv = button1;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//�����һ��
		new BorrowBookView(mv);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//�����Ų��ſ�
		mv.setJLabelImageAndSize(90, 120, 350, 430, label, "res/main/1/press.png");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//������ſ���
		mv.setJLabelImageAndSize(90, 120, 350, 430, label, "res/main/1/focus.png");
		///SetButtonUporDown.SetButtonUp(label);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//��굽������
		//SetButtonUporDown.SetButtonUp(label);
		mv.setJLabelImageAndSize(90, 120, 350, 430, label, "res/main/1/focus.png");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//����뿪��
		mv.setJLabelImageAndSize(90, 120, 350, 430, label, "res/main/1/default.png");
		//SetButtonUporDown.reSetButton(label);
	}

}
