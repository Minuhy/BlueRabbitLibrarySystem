package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.dao.AdministratorBeansDAO;
import com.bluerabbit.librarysystem.database.MD5Utils;
import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.loginView;
/**
 * ��¼����ĵ�¼��ť������
 * @author minuy
 *
 */
public class LoginView_loginButton_ActionListener implements ActionListener {
	loginView view;
	JTextField user;
	JPasswordField passwd;

	public LoginView_loginButton_ActionListener(loginView loginView) {
		// TODO Auto-generated constructor stub
		this.view = loginView;
		this.user = view.getLoginUser();
		this.passwd = view.getLoginUserPWD();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//��ȡ�˺�
		String Account = user.getText();
		//���û�������˺�
		if(Account.equals("�������˺�")){
			System.out.println("�������˺ţ�");
			JOptionPane.showMessageDialog(null, "�������˺ţ�", "��ʾ",JOptionPane.WARNING_MESSAGE); 
			return;
		}
		//������벢�ҽ���MD5����
		String pwMD5 = MD5Utils.MD5Encode(new String(passwd.getPassword()),"utf8");
		//�½�һ������Ա��ѯ����
		AdministratorBeansDAO user = new AdministratorBeansDAO();
		//�ж��Ƿ���ȷ
		if(user.validateByAccountAndPassword(Account, pwMD5)){
			AdministratorBeans admin = user.getAdminInfo();
			System.out.println("������ȷ��");
			System.out.println("��ӭ�û���" + admin.getAdminName());
			JOptionPane.showMessageDialog(null, "��ӭ��" + admin.getAdminName());
			new MainView(admin);
			view.dispose();
		}else{
			System.out.println("�˺Ż��������");
			JOptionPane.showMessageDialog(null, "�˺Ż��������", "����",JOptionPane.ERROR_MESSAGE);
		}
	}

}
