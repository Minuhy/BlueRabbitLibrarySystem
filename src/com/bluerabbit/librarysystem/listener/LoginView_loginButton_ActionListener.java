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
 * 登录界面的登录按钮监听类
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
		//获取账号
		String Account = user.getText();
		//如果没有输入账号
		if(Account.equals("请输入账号")){
			System.out.println("请输入账号！");
			JOptionPane.showMessageDialog(null, "请输入账号！", "提示",JOptionPane.WARNING_MESSAGE); 
			return;
		}
		//获得密码并且进行MD5加密
		String pwMD5 = MD5Utils.MD5Encode(new String(passwd.getPassword()),"utf8");
		//新建一个管理员查询对象
		AdministratorBeansDAO user = new AdministratorBeansDAO();
		//判断是否正确
		if(user.validateByAccountAndPassword(Account, pwMD5)){
			AdministratorBeans admin = user.getAdminInfo();
			System.out.println("密码正确！");
			System.out.println("欢迎用户：" + admin.getAdminName());
			JOptionPane.showMessageDialog(null, "欢迎：" + admin.getAdminName());
			new MainView(admin);
			view.dispose();
		}else{
			System.out.println("账号或密码错误！");
			JOptionPane.showMessageDialog(null, "账号或密码错误！", "错误",JOptionPane.ERROR_MESSAGE);
		}
	}

}
