package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.view.HelpView;
import com.bluerabbit.librarysystem.view.loginView;
/**
 * 登录界面帮助按钮的监听类
 * @author minuy
 *
 */
public class LoginView_helpButton_ActionListener implements ActionListener {
	private loginView lv;//对窗口的引用
	
	public LoginView_helpButton_ActionListener(loginView lv) {
		this.lv = lv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//lv.dispose();
		new HelpView(lv);
	}

}
