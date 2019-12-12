package com.bluerabbit.librarysystem.listener;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.loginView;
/**
 * 主视图退出登录按钮的监听类
 * @author minuy
 *
 */
public class MainView_exit_MouseListener implements MouseListener {

	MainView mv;
	JLabel label;

	public MainView_exit_MouseListener(MainView mv,JLabel la) {
		// TODO Auto-generated constructor stub
		this.mv = mv;
		this.label = la;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//点击了一下
		int option = JOptionPane.showConfirmDialog(null, "是否退出登录？", "退出登录", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			//用户选择退出登录
			new loginView();
			mv.dispose();
		}
		else {
			//用户选择不退出登录
		}
		System.out.println("点了一下");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标点着不放开
		//SetButtonUporDown.setButtonDown(label);
		setJLabelImage(170, 57, label, "res/exit2.png");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标点击放开了
		//SetButtonUporDown.SetButtonUp(label);
		System.out.println("鼠标点击放开了");
		setJLabelImage(170, 57, label, "res/exit0.png");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标到上面了
		//SetButtonUporDown.SetButtonUp(label);
		System.out.println("鼠标到上面了");
		setJLabelImage(170, 57, label, "res/exit0.png");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标离开了
		//SetButtonUporDown.reSetButton(label);
		System.out.println("鼠标离开了");
		setJLabelImage(170, 57, label, "res/exit1.png");
	}
	
	public void setJLabelImage(int width,int height,JLabel label,String imagePath){
		//实例化ImageIcon 对象
		ImageIcon image = new ImageIcon(imagePath);
		//得到Image对象
		Image img = image.getImage();
		//创建缩放版本
		img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		//替换为缩放版本
		image.setImage(img);
		//JLabel设置图像
		label.setIcon(image);
	}

}
