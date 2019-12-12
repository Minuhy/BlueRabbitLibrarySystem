package com.bluerabbit.librarysystem.listener;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.loginView;
/**
 * ����ͼ�˳���¼��ť�ļ�����
 * @author minuy
 *
 */
public class MainView_exit_MouseListener implements MouseListener {

	MainView mv;
	JLabel label;

	public MainView_exit_MouseListener(MainView mv,JLabel la) {
		// TODO Auto-generated constructor stub
		this.mv = mv;
		this.label = la;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//�����һ��
		int option = JOptionPane.showConfirmDialog(null, "�Ƿ��˳���¼��", "�˳���¼", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			//�û�ѡ���˳���¼
			new loginView();
			mv.dispose();
		}
		else {
			//�û�ѡ���˳���¼
		}
		System.out.println("����һ��");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//�����Ų��ſ�
		//SetButtonUporDown.setButtonDown(label);
		setJLabelImage(170, 57, label, "res/exit2.png");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//������ſ���
		//SetButtonUporDown.SetButtonUp(label);
		System.out.println("������ſ���");
		setJLabelImage(170, 57, label, "res/exit0.png");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//��굽������
		//SetButtonUporDown.SetButtonUp(label);
		System.out.println("��굽������");
		setJLabelImage(170, 57, label, "res/exit0.png");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//����뿪��
		//SetButtonUporDown.reSetButton(label);
		System.out.println("����뿪��");
		setJLabelImage(170, 57, label, "res/exit1.png");
	}
	
	public void setJLabelImage(int width,int height,JLabel label,String imagePath){
		//ʵ����ImageIcon ����
		ImageIcon image = new ImageIcon(imagePath);
		//�õ�Image����
		Image img = image.getImage();
		//�������Ű汾
		img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		//�滻Ϊ���Ű汾
		image.setImage(img);
		//JLabel����ͼ��
		label.setIcon(image);
	}

}
