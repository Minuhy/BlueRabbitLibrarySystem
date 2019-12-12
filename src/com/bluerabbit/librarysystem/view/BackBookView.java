package com.bluerabbit.librarysystem.view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 还书页面，停用中
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class BackBookView extends JDialog {
	private JPanel borrowBookMian;
	private int WindowsHeight;
	private int windowsWidth;
	JLabel test;
	public BackBookView(MainView mv){
		super(mv,"还书",true);
		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		borrowBookMian = new JPanel();
		test = new JLabel("期待......");
		
		Init();
	}
	
	private void Init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-100, WindowsHeight-100);
		CenterView.CenterByWindow(this);
		borrowBookMian.setLayout(null);
		//不允许用户调整窗口大小
		this.setResizable(false);
		test.setBounds(0, 0, 100, 100);
		borrowBookMian.add(test);
		
		this.add(borrowBookMian);
		this.setVisible(true);
	}
}
