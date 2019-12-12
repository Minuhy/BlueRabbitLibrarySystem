package com.bluerabbit.librarysystem.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.bluerabbit.librarysystem.listener.LibraryManagementSelectView_Back_ActionListener;
import com.bluerabbit.librarysystem.listener.LibraryManagementSelectView_Borrow_ActionListener;
/**
 * 借阅信息选择层，这里选择借书或者归还
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class LibraryManagementSelectView extends JDialog{
	private JPanel LMSV;
	private JButton borrow;
	private JButton back;
	MainView mv;
	public LibraryManagementSelectView(MainView mv){
		//设置标题
		super(mv,"借书管理",true);
		this.mv = mv;
		LMSV = new JPanel();
		borrow = new JButton("借书");
		back = new JButton("还书");
		Init();
	}
	
	public void Init(){
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(400, 200);
		CenterView.CenterByWindow(this);
		LMSV.setLayout(null);
		//不允许用户调整窗口大小
		this.setResizable(false);
		
		borrow.setBounds(20, 20, 140, 80);
		back.setBounds(185, 20, 140, 80);
		
		borrow.addActionListener(new LibraryManagementSelectView_Borrow_ActionListener(this,mv));
		back.addActionListener(new LibraryManagementSelectView_Back_ActionListener(this,mv));
		
		LMSV.add(borrow);
		LMSV.add(back);
		
		this.add(LMSV);
		this.setVisible(true);
	}
}
