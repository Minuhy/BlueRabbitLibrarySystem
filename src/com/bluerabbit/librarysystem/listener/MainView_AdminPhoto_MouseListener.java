package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.PersonalInfoView;
/**
 * 主视图个人信息详情页面的头像点击按钮监听类
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class MainView_AdminPhoto_MouseListener extends JDialog implements
		MouseListener {

	MainView mv;
	AdministratorBeans admin;
	public MainView_AdminPhoto_MouseListener(MainView mainView, AdministratorBeans admin) {
		// TODO Auto-generated constructor stub
		this.mv = mainView;
		this.admin = admin;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		new PersonalInfoView(mv,admin);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
