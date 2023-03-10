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
 * 
 * @author minuhy
 *
 */
public class PersonalInfoView extends JDialog {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -4561975399172426055L;
	private JPanel PersonalWin;
	private int WindowsHeight;
	private int windowsWidth;
	private JLabel labPhoto;
	private JButton btnChangerPwd;// �����������
	private JButton btnSave;// ����
	private JButton btnClose;// �ر�

	private JLabel labName;// �ǳ�
	private JTextField txfName;// �ǳ������
	AdministratorBeans admin;
	MainView mv;

	public PersonalInfoView(MainView mv, AdministratorBeans admin) {
		super(mv, "������Ϣ����", true);
		this.admin = admin;
		this.mv = mv;
		PersonalWin = new JPanel() {
			/**
			 * UID
			 */
			private static final long serialVersionUID = 6491237615713785318L;
			// ����һ��ͼƬ���½�һ��ImageIcon���󲢵���getImage�������һ��Image����
			private Image image = new ImageIcon("res\\loginBackground1.png"/* ͼƬ��·�� */).getImage();

			// ����ϵͳҪ�������paintComponent������������ͼƬ������ϵͳ������һ��Graphics���󣨻��ʣ���
			// ������Ҫ�����������������ͼƬ
			protected void paintComponent(Graphics g) {
				// ���û��ʵ�drawImage������������Ҫ����ͼƬ����ʼ���꣬�������꣬�������ﻭ��this������LoginWin��
				// ��������������
				g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
			}
			// ������ʵ���˱���ͼ
		};

		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		btnClose = new JButton("�ر�");

		labPhoto = new JLabel();
		btnChangerPwd = new JButton("�޸�����");
		btnSave = new JButton("����");
		labName = new JLabel("�ǳƣ�");
		txfName = new JTextField(admin.getAdminName());

		Init();
	}

	private void Init() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth - 700, WindowsHeight - 200);
		PersonalWin.setLayout(null);
		PersonalWin.setFocusable(true);
		// �������û��������ڴ�С
		this.setResizable(false);
		CenterView.CenterByWindow(this);

		// ͷ��
		labPhoto.setBounds(125, 25, 185, 185);
		// ʵ����ImageIcon ����
		ImageIcon image = new ImageIcon(MainView.getAvater());
		// �õ�Image����
		Image img = image.getImage();
		// �������Ű汾
		img = img.getScaledInstance(165, 165, Image.SCALE_DEFAULT);
		// �滻Ϊ���Ű汾
		image.setImage(img);
		// JLabel����ͼ��
		labPhoto.setIcon(image);
		// ����ͼ�����
		labPhoto.setHorizontalAlignment(JLabel.CENTER);
		labPhoto.setBorder(BorderFactory.createTitledBorder(admin.getAdminName()));
		// ��ť
		btnSave.setBounds(40, 350, 60, 30);
		btnClose.setBounds(300, 350, 60, 30);
		// ����
		btnChangerPwd.setBounds(160, 350, 80, 30);
		// ����
		labName.setFont(new Font("΢���ź�", 1, 17));
		labName.setBounds(70, 240, 60, 30);
		txfName.setBounds(130, 240, 200, 30);

		PersonalInfoView_ActionListener listen = new PersonalInfoView_ActionListener(this, mv, admin);

		btnChangerPwd.addActionListener(listen);

		btnSave.addActionListener(listen);

		btnClose.addActionListener(listen);

		PersonalWin.add(labPhoto);
		PersonalWin.add(btnSave);
		PersonalWin.add(labName);
		PersonalWin.add(txfName);
		PersonalWin.add(btnClose);
		PersonalWin.add(btnChangerPwd);

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