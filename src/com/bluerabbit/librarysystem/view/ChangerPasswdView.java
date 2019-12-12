package com.bluerabbit.librarysystem.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.listener.ChangerPwdView_Save_Button;

/**
 * ��������ҳ����޸��������
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class ChangerPasswdView extends JDialog {
	private JPanel ChangerPwdViewWin;
	JPanel mainView;
	JLabel label;
	private JLabel ChangePWD1;//ԭ����
	private JLabel ChangePWD2;//������
	private JLabel ChangePWD3;//ȷ������
	private JButton ChangePWDButtonSave;//����
	private HexPasswordField ChangPWD1;//ԭ���������
	private HexPasswordField ChangPWD2;//�����������
	private HexPasswordField ChangPWD3;//ȷ�����������
	AdministratorBeans admin;
	private JButton close;

	public JButton getClose() {
		return close;
	}

	//public ChangerPasswdView(PersonalInfoView pv, AdministratorBeans admin)
	boolean isOldPwd;
	
	public boolean isIsoldPwd(){
		return isOldPwd;
	}
	/**
	 * 
	 * @param pv
	 * @param admin
	 * @param isOldPwd�Ƿ�Ҫ����ԭ����
	 */
	public ChangerPasswdView(JDialog pv, AdministratorBeans admin,boolean isOldPwd){
		super(pv,true);
		ChangerPwdViewWin = new JPanel(){};
		this.admin = admin;
		this.isOldPwd = isOldPwd;
		//		ChangerPwdViewWin = new JPanel(){
		//			private Image image = new ImageIcon("res\\background2.png"/*ͼƬ��·��*/).getImage();
		//			protected void paintComponent(Graphics g) {
		//				g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
		//			}  
		//		};

		ChangePWD1 = new JLabel("ԭ���룺");
		ChangePWD2 = new JLabel("�����룺");
		ChangePWD3 = new JLabel("ȷ�����룺");
		ChangePWDButtonSave = new JButton("����");
		close = new JButton("ȡ��");
		ChangPWD1 = new HexPasswordField();
		ChangPWD2 = new HexPasswordField();
		ChangPWD3 = new HexPasswordField();
		this.setTitle("���޸�����");
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(400,300);

		ChangePWD1.setFont(new Font("΢���ź�", 0, 17));
		ChangePWD1.setBounds(40, 35, 100, 20);
		ChangePWD2.setFont(new Font("΢���ź�", 0, 17));
		ChangePWD2.setBounds(40, 35, 100, 20);
		ChangePWD3.setFont(new Font("΢���ź�", 0, 17));
		ChangePWD3.setBounds(40, 85, 100, 20);
		ChangPWD1.setBounds(130, 35, 180, 30); 
		ChangPWD1.setPlaceholder("       ������ԭ����");
		ChangPWD2.setBounds(130, 35, 180, 30); 
		ChangPWD2.setPlaceholder("       ������������");
		ChangPWD3.setBounds(130, 85, 180, 30); 
		ChangPWD3.setPlaceholder("       ��ȷ������");
		
		if(isOldPwd){
			//���ƶ�
			ChangePWD2.setFont(new Font("΢���ź�", 0, 17));
			ChangePWD2.setBounds(40, 85, 100, 20);
			ChangPWD2.setBounds(130, 85, 180, 30); 
			ChangPWD2.setPlaceholder("       ������������");
			ChangPWD3.setBounds(130, 135, 180, 30); 
			ChangPWD3.setPlaceholder("       ��ȷ������");
			ChangePWD3.setFont(new Font("΢���ź�", 0, 17));
			ChangePWD3.setBounds(40, 135, 100, 20);
		}

		ChangePWDButtonSave.setBounds(50, 180, 80, 25);
		close.setBounds(230, 180, 80, 25);
		ChangerPwdViewWin.setLayout(null);
		ChangerPwdViewWin.setFocusable(true);

		ChangerPwdView_Save_Button listen = new ChangerPwdView_Save_Button(this,admin);

		ChangePWDButtonSave.addActionListener(listen);
		close.addActionListener(listen);

		this.add(ChangerPwdViewWin);
		if(isOldPwd){
			ChangerPwdViewWin.add(ChangePWD1);
		}
		ChangerPwdViewWin.add(ChangePWD2);
		ChangerPwdViewWin.add(ChangePWD3);
		if(isOldPwd){
			ChangerPwdViewWin.add(ChangPWD1);
		}
		ChangerPwdViewWin.add(ChangPWD2);
		ChangerPwdViewWin.add(ChangPWD3);
		ChangerPwdViewWin.add(ChangePWDButtonSave);
		ChangerPwdViewWin.add(close);

		CenterView.CenterByWindow(this);
		this.setVisible(true);
	}

	public HexPasswordField getChangPWD1() {
		return ChangPWD1;
	}

	public HexPasswordField getChangPWD2() {
		return ChangPWD2;
	}

	public HexPasswordField getChangPWD3() {
		return ChangPWD3;
	}

	public JButton getChangePWDButtonSave() {
		return ChangePWDButtonSave;
	}

}
