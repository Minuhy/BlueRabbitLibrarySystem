package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.ReaderInfoManageView;
/**
 * ����ͼ���߹��� �İ�ť������
 * @author minuy
 *
 */
public class MainView_Button2_MouseListener implements MouseListener {

	JLabel label;
	MainView mv;
	
	public MainView_Button2_MouseListener(MainView button2) {
		// TODO Auto-generated constructor stub
		this.label = button2.getButton2();
		this.mv = button2;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//�����һ��
		//JOptionPane.showMessageDialog(null, "ͣ��");
		new ReaderInfoManageView(mv);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//�����Ų��ſ�
		mv.setJLabelImageAndSize(460, 120, 250, 430, label, "res/main/2/press.png");
		//"res/MainView/1/default.png"
		//SetButtonUporDown.setButtonDown(label);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//������ſ���
		mv.setJLabelImageAndSize(460, 120, 250, 430, label, "res/main/2/focus.png");
		///SetButtonUporDown.SetButtonUp(label);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//��굽������
		//SetButtonUporDown.SetButtonUp(label);
		mv.setJLabelImageAndSize(460, 120, 250, 430, label, "res/main/2/focus.png");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//����뿪��
		mv.setJLabelImageAndSize(460, 120, 250, 430, label, "res/main/2/default.png");
		//SetButtonUporDown.reSetButton(label);
	}


}
