package com.bluerabbit.librarysystem.view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * ����ҳ�棬ͣ����
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
		super(mv,"����",true);
		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		borrowBookMian = new JPanel();
		test = new JLabel("�ڴ�......");
		
		Init();
	}
	
	private void Init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-100, WindowsHeight-100);
		CenterView.CenterByWindow(this);
		borrowBookMian.setLayout(null);
		//�������û��������ڴ�С
		this.setResizable(false);
		test.setBounds(0, 0, 100, 100);
		borrowBookMian.add(test);
		
		this.add(borrowBookMian);
		this.setVisible(true);
	}
}
