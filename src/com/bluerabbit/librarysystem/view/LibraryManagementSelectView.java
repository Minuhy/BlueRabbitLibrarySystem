package com.bluerabbit.librarysystem.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.bluerabbit.librarysystem.listener.LibraryManagementSelectView_Back_ActionListener;
import com.bluerabbit.librarysystem.listener.LibraryManagementSelectView_Borrow_ActionListener;
/**
 * ������Ϣѡ��㣬����ѡ�������߹黹
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
		//���ñ���
		super(mv,"�������",true);
		this.mv = mv;
		LMSV = new JPanel();
		borrow = new JButton("����");
		back = new JButton("����");
		Init();
	}
	
	public void Init(){
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(400, 200);
		CenterView.CenterByWindow(this);
		LMSV.setLayout(null);
		//�������û��������ڴ�С
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
