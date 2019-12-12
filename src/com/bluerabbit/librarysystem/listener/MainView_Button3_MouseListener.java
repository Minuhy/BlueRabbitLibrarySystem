package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.bluerabbit.librarysystem.view.BookInfoManageView;
import com.bluerabbit.librarysystem.view.MainView;
/**
 * 主界面图书管理按钮的监听类
 * @author minuy
 *
 */
public class MainView_Button3_MouseListener implements MouseListener {

	JLabel label;
	MainView mv;
	
	public MainView_Button3_MouseListener(MainView button3) {
		// TODO Auto-generated constructor stub
		this.label = button3.getButton3();
		this.mv = button3;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//点击了一下
		new BookInfoManageView(mv);
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标点着不放开
		mv.setJLabelImageAndSize(730, 120, 275, 210, label, "res/MainView/3/press.png");
		//"res/MainView/1/default.png"
		//SetButtonUporDown.setButtonDown(label);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标点击放开了
		mv.setJLabelImageAndSize(730, 120, 275, 210, label, "res/MainView/3/focus.png");
		///SetButtonUporDown.SetButtonUp(label);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标到上面了
		//SetButtonUporDown.SetButtonUp(label);
		mv.setJLabelImageAndSize(730, 120, 275, 210, label, "res/MainView/3/focus.png");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标离开了
		mv.setJLabelImageAndSize(730, 120, 275, 210, label, "res/MainView/3/default.png");
		//SetButtonUporDown.reSetButton(label);
	}


}
