package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.view.AdminInfoManageView;
import com.bluerabbit.librarysystem.view.MainView;
/**
 * ����ͼ��Ա����ť�ļ�����
 * @author minuy
 *
 */
public class MainView_Button4_MouseListener implements MouseListener {

	JLabel label;
	MainView mv;
	
	public MainView_Button4_MouseListener(MainView button4) {
		// TODO Auto-generated constructor stub
		this.label = button4.getButton4();
		this.mv = button4;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//�����һ��
		System.out.println("����˹���Ա����ť��");
		if(mv.isSu()){
			System.out.println("�ǳ�������Ա��");
			new AdminInfoManageView(mv);
			System.out.println("���˳�������Ա������棡");
		}else{
			System.out.println("û�г�������ԱȨ�ޣ�");
			JOptionPane.showMessageDialog(null, "û�г�������ԱȨ�ޣ�", "����",JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//�����Ų��ſ�
		mv.setJLabelImageAndSize(730, 350, 275, 200, label, "res/main/4/press.png");
		//"res/MainView/1/default.png"
		//SetButtonUporDown.setButtonDown(label);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//������ſ���
		mv.setJLabelImageAndSize(730, 350, 275, 200, label, "res/main/4/focus.png");
		///SetButtonUporDown.SetButtonUp(label);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//��굽������
		//SetButtonUporDown.SetButtonUp(label);
		mv.setJLabelImageAndSize(730, 350, 275, 200, label, "res/main/4/focus.png");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//����뿪��
		mv.setJLabelImageAndSize(730, 350, 275, 200, label, "res/main/4/default.png");
		//SetButtonUporDown.reSetButton(label);
	}

}
