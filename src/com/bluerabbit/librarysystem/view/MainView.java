package com.bluerabbit.librarysystem.view;
//ssm

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.listener.MainView_AdminPhoto_MouseListener;
import com.bluerabbit.librarysystem.listener.MainView_Button1_MouseListener;
import com.bluerabbit.librarysystem.listener.MainView_Button2_MouseListener;
import com.bluerabbit.librarysystem.listener.MainView_Button3_MouseListener;
import com.bluerabbit.librarysystem.listener.MainView_Button4_MouseListener;
import com.bluerabbit.librarysystem.listener.MainView_exit_MouseListener;
/**
 * ������
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class MainView extends JFrame{
	private AdministratorBeans admin;
	private JPanel mainWin;
	private JLabel status;
	private JLabel adminName;
	private JLabel adminPhoto;
	private JLabel adminBack;
	private JLabel button1;
	private JLabel button2;
	private JLabel button3;
	private JLabel button4;
	private JLabel exit;
	/**
	 * ������
	 * @param name ��¼�������û�����
	 * @param ifsuper �ǲ��ǳ�������Ա
	 */
	public MainView(AdministratorBeans admin){
		this.admin = admin;
		mainWin = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				Image img = new ImageIcon("res/background2.png").getImage();
				g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);
			}
		};

		adminName = new JLabel();
		adminPhoto = new JLabel();
		adminBack = new JLabel();
		button1 = new JLabel("���Ĺ���");
		button2 = new JLabel("���߹���");
		button3 = new JLabel("ͼ�����");
		button4 = new JLabel("��Ա����");
		status = new JLabel();

		exit = new JLabel();

		Init();
	}

	private void Init(){
		//���ñ���
		this.setTitle("����ͼ�����ϵͳ - ������");
		//���ô��ڴ�С
		this.setSize(1152,679);
		//ע��ر��¼�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ھ���
		CenterView.CenterByWindow(this);
		//�����������ַ�ʽΪ�ղ���
		mainWin.setLayout(null);
		
		//�������û��������ڴ�С
		this.setResizable(false);
		
		
		//�����û�������
		adminName.setBounds(70, 0, 600, 30);
		adminName.setFont(new Font("΢���ź�", 1, 27));
		
		status.setBounds(70, 40, 100, 30);
		status.setFont(new Font("΢���ź�", 3, 17));
		
		adminName.setText(admin.getAdminName());
		if(admin.isIfsuper()){
			status.setText("��������Ա");
		}else{
			status.setText("����Ա");
		}
		
		//����ͷ�����
		setJLabelImageAndSize(5, 5, 60, 60, adminPhoto,  "res/photo1.png");
		adminPhoto.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//������Ƭ��������
		setJLabelImageAndSize(0, 0, 1098, 80, adminBack, "res/titlebackground.png");
		//���Ĺ���
		setJLabelImageAndSize(90, 120, 350, 430, button1, "res/MainView/1/default.png");
		//button1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//���߹���
		setJLabelImageAndSize(460, 120, 250, 430, button2, "res/MainView/2/default.png");
		//button2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//ͼ�����
		setJLabelImageAndSize(730, 120, 275, 210, button3, "res/MainView/3/default.png");
		//button3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//��Ա����
		setJLabelImageAndSize(730, 350, 275, 200, button4, "res/MainView/4/default.png");
		//button4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//�˳���¼
		setJLabelImageAndSize(910, 10, 170, 57, exit, "res/exit1.png");
		//exit.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		
		adminPhoto.addMouseListener(new MainView_AdminPhoto_MouseListener(this,admin));
		button1.addMouseListener(new MainView_Button1_MouseListener(this));
		button2.addMouseListener(new MainView_Button2_MouseListener(this));
		button3.addMouseListener(new MainView_Button3_MouseListener(this));
		button4.addMouseListener(new MainView_Button4_MouseListener(this));
		exit.addMouseListener(new MainView_exit_MouseListener(this,exit));
		
		
		mainWin.add(status);
		mainWin.add(adminPhoto);
		mainWin.add(adminName);
		mainWin.add(button4);
		mainWin.add(button3);
		mainWin.add(button2);
		mainWin.add(button1);
		mainWin.add(exit);

		mainWin.add(adminBack);
		
		this.add(mainWin);
		//���ô��ڿɼ�
		this.setVisible(true);
	}
	
	public void setJLabelImageAndSize(int x,int y,int width,int height,JLabel label,String imagePath){
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
		//JLabel��������ʹ�С
		label.setBounds(x, y, width, height);
	}

	/**
	 * ��д���ڵ��¼���ת�����������Ǵ��������processWindowEvent���뵽���ڹر��¼���
	 */
	@Override
	protected void processWindowEvent(WindowEvent e)
	{
		//������Ҫ�Խ�����WindowEvent�����жϣ���Ϊ���������д��ڹرյ�WindowEvent��������������������WindowEvent����
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{
			int option = JOptionPane.showConfirmDialog(null, "�Ƿ�رճ���", "�����˳���ʾ", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION)
			{
				//�û�ѡ��رճ������ϴ�����ʾ��ȷ�ϴ������ദ��
				super.processWindowEvent(e);
			}
			else {
				//�û�ѡ���˳���������ѹر��¼���ȡ
				return;
			}
		}
		else {
			//����������¼����������ദ��
			super.processWindowEvent(e);
		}
	}
	
	public JLabel getButton1() {
		return button1;
	}
	
	public JLabel getButton2() {
		return button2;
	}
	
	public JLabel getButton3() {
		return button3;
	}
	
	public JLabel getButton4() {
		return button4;
	}
	
	public void updateAdmin(AdministratorBeans admin){
		this.admin = admin;
		adminName.setText(admin.getAdminName());
		if(admin.isIfsuper()){
			status.setText("��������Ա");
		}else{
			status.setText("����Ա");
		}
	}
	
	public boolean isSu(){
		return this.admin.isIfsuper();
	}
	
	public String getAdminAcc(){
		return this.admin.getAccount();
	}
	
	public String getPasawd(){
		return this.admin.getPassword();
	}
}

