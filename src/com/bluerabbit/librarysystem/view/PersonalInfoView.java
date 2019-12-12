///*
package com.bluerabbit.librarysystem.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.listener.PersonalInfoView_ActionListener;
/**
 * ������Ϣҳ��
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class PersonalInfoView extends JDialog {
	private JPanel PersonalWin;
	private int WindowsHeight;
	private int windowsWidth;
	//private JLabel labBackground;//����
	private JLabel labPhoto;
	//private JLabel labChangerPwd;//���룺
	private JButton btnChangerPwd;//�����������
	private JButton btnSave;//����
	private JButton btnClose;//�ر�

	private JLabel labName;//�ǳ�
	private JTextField txfName;//�ǳ������
	AdministratorBeans admin;
	MainView mv;
	public PersonalInfoView(MainView mv,AdministratorBeans admin){
		super(mv,"������Ϣ����",true);
		this.admin = admin;
		this.mv = mv;
		PersonalWin = new JPanel(){
					//����һ��ͼƬ���½�һ��ImageIcon���󲢵���getImage�������һ��Image����
					private Image image = new ImageIcon("res\\loginBackground1.png"/*ͼƬ��·��*/).getImage();
					//����ϵͳҪ�������paintComponent������������ͼƬ������ϵͳ������һ��Graphics���󣨻��ʣ���
					//������Ҫ�����������������ͼƬ
					protected void paintComponent(Graphics g) {
						//���û��ʵ�drawImage������������Ҫ����ͼƬ����ʼ���꣬�������꣬�������ﻭ��this������LoginWin��
						//��������������
						g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
					}  
					//������ʵ���˱���ͼ
				};

		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		btnClose = new JButton("�ر�");

		//labBackground = new JLabel();
		labPhoto = new JLabel();
		btnChangerPwd = new JButton("�޸�����");
		btnSave = new JButton("����");
		labName = new JLabel("�ǳƣ�");
		txfName = new JTextField(admin.getAdminName());

		Init();
	}

	private void Init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-700, WindowsHeight-200);
		PersonalWin.setLayout(null);
		PersonalWin.setFocusable(true);
		//�������û��������ڴ�С
		this.setResizable(false);
		CenterView.CenterByWindow(this);
//		//����
//		ImageIcon image = new ImageIcon("res/loginBackground1.png");
//		//�õ�Image����
//		Image img = image.getImage();
//		//�������Ű汾
//		img = img.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
//		//�滻Ϊ���Ű汾
//		image.setImage(img);
//		labBackground.setIcon(image);
//		labBackground.setBounds(0, 0, 400, 400);
		//ͷ��
		labPhoto.setBounds(125, 25, 185, 185);
		//ʵ����ImageIcon ����
		ImageIcon image = new ImageIcon("res/photo1.png");
		//�õ�Image����
		Image img = image.getImage();
		//�������Ű汾
		img = img.getScaledInstance(165, 165, Image.SCALE_DEFAULT);
		//�滻Ϊ���Ű汾
		image.setImage(img);
		//JLabel����ͼ��
		labPhoto.setIcon(image);
		//����ͼ�����
		labPhoto.setHorizontalAlignment(JLabel.CENTER);
		labPhoto.setBorder(BorderFactory.createTitledBorder(admin.getAdminName()));
		//��ť
		btnSave.setBounds(40, 350, 60, 30);
		btnClose.setBounds(300, 350, 60, 30);
		//����
		btnChangerPwd.setBounds(160, 350, 80, 30);
		//txfChangerPwd.setEditable(false);
//		labChangerPwd.setBounds(70, 280, 60, 30);
//		labChangerPwd.setFont(new Font("΢���ź�", 1, 17));
		//����
		labName.setFont(new Font("΢���ź�", 1, 17));
		labName.setBounds(70, 240, 60, 30);
		txfName.setBounds(130, 240, 200, 30);

		PersonalInfoView_ActionListener listen = new PersonalInfoView_ActionListener(this,mv,admin);

		btnChangerPwd.addActionListener(listen);
		
		btnSave.addActionListener(listen);

		btnClose.addActionListener(listen);

//		PersonalWin.add(txfChangerPwd);
		PersonalWin.add(labPhoto);
		PersonalWin.add(btnSave);
		PersonalWin.add(labName);
		PersonalWin.add(txfName);
		PersonalWin.add(btnClose);
		PersonalWin.add(btnChangerPwd);
		//PersonalWin.add(labBackground);

		this.add(PersonalWin);
		this.setVisible(true);



	}

	public JButton getTxfChangerPwd() {
		return btnChangerPwd;
	}

	public JTextField getTxfName() {
		return txfName;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public JButton getBtnSave() {
		return btnSave;
	}



}
//*/








/*
package com.bluerabbit.librarysystem.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;

@SuppressWarnings("serial")
public class PersonalInfoView extends JDialog {
	private int WindowsHeight;
	private int windowsWidth;

	private JPanel PersonalWin;
	private JLabel PersonalTitle;
	private JLabel title;
	private JButton PersonalButton;   //�޸İ�ť
	private HexTextField PersonalUser;
	private HexPasswordField PersonalPWD1;

	public PersonalInfoView(MainView mv,AdministratorBeans admin){
		super(mv,"������Ϣ����",true);
		//title = new JLabel("��");
		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		PersonalView = new JPanel();

		Init();
	}




	private void Init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-600, WindowsHeight-100);
		CenterView.CenterByWindow(this);
		PersonalView.setLayout(null);
		//�������û��������ڴ�С
		this.setResizable(false);

		this.add(PersonalView);
		this.setVisible(true);
	}
}
 */