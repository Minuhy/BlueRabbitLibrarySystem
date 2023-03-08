package com.bluerabbit.librarysystem.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.view.AdminInfoManageView;
import com.bluerabbit.librarysystem.view.MainView;
/**
 * 主视图人员管理按钮的监听类
 * @author minuy
 *
 */
public class MainView_Button4_MouseListener implements MouseListener {

	JLabel label;
	MainView mv;
	
	public MainView_Button4_MouseListener(MainView button4) {
		// TODO Auto-generated constructor stub
		this.label = button4.getButton4();
		this.mv = button4;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//点击了一下
		System.out.println("点击了管理员管理按钮！");
		if(mv.isSu()){
			System.out.println("是超级管理员！");
			new AdminInfoManageView(mv);
			System.out.println("打开了超级管理员管理界面！");
		}else{
			System.out.println("没有超级管理员权限！");
			JOptionPane.showMessageDialog(null, "没有超级管理员权限！", "错误",JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标点着不放开
		mv.setJLabelImageAndSize(730, 350, 275, 200, label, "res/main/4/press.png");
		//"res/MainView/1/default.png"
		//SetButtonUporDown.setButtonDown(label);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标点击放开了
		mv.setJLabelImageAndSize(730, 350, 275, 200, label, "res/main/4/focus.png");
		///SetButtonUporDown.SetButtonUp(label);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标到上面了
		//SetButtonUporDown.SetButtonUp(label);
		mv.setJLabelImageAndSize(730, 350, 275, 200, label, "res/main/4/focus.png");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标离开了
		mv.setJLabelImageAndSize(730, 350, 275, 200, label, "res/main/4/default.png");
		//SetButtonUporDown.reSetButton(label);
	}

}
