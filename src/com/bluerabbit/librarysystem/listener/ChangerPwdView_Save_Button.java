package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.service.AdminInfoSave;
import com.bluerabbit.librarysystem.view.ChangerPasswdView;
/**
 * 个人页面更改密码页面的保存密码监听
 * @author minuy
 *
 */
public class ChangerPwdView_Save_Button implements ActionListener {

	ChangerPasswdView changerPasswdView;
	AdministratorBeans admin;

	public ChangerPwdView_Save_Button(ChangerPasswdView changerPasswdView,
			AdministratorBeans admin) {
		// TODO Auto-generated constructor stub
		this.admin = admin;
		this.changerPasswdView = changerPasswdView;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object clic = e.getSource();
		if(clic == changerPasswdView.getChangePWDButtonSave()){
			new AdminInfoSave().AdminPwdSave(changerPasswdView,admin);
		}else{
			if(clic == changerPasswdView.getClose()){
				changerPasswdView.dispose();
			}
		}
	}

}
