///*
package com.bluerabbit.librarysystem.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.listener.PersonalInfoView_ActionListener;
/**
 * 个人信息页面
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class PersonalInfoView extends JDialog {
	private JPanel PersonalWin;
	private int WindowsHeight;
	private int windowsWidth;
	//private JLabel labBackground;//背景
	private JLabel labPhoto;
	//private JLabel labChangerPwd;//密码：
	private JButton btnChangerPwd;//点击更改密码
	private JButton btnSave;//保存
	private JButton btnClose;//关闭

	private JLabel labName;//昵称
	private JTextField txfName;//昵称输入框
	AdministratorBeans admin;
	MainView mv;
	public PersonalInfoView(MainView mv,AdministratorBeans admin){
		super(mv,"个人信息管理",true);
		this.admin = admin;
		this.mv = mv;
		PersonalWin = new JPanel(){
					//定义一张图片，新建一个ImageIcon对象并调用getImage方法获得一个Image对象
					private Image image = new ImageIcon("res\\loginBackground1.png"/*图片的路径*/).getImage();
					//这里系统要调用这个paintComponent方法来画这张图片，这里系统传入了一个Graphics对象（画笔），
					//我们需要用这个对象来画背景图片
					protected void paintComponent(Graphics g) {
						//调用画笔的drawImage方法，参数是要画的图片，初始坐标，结束坐标，和在哪里画，this代表是LoginWin这
						//个“画布”对象
						g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
					}  
					//这样就实现了背景图
				};

		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		btnClose = new JButton("关闭");

		//labBackground = new JLabel();
		labPhoto = new JLabel();
		btnChangerPwd = new JButton("修改密码");
		btnSave = new JButton("保存");
		labName = new JLabel("昵称：");
		txfName = new JTextField(admin.getAdminName());

		Init();
	}

	private void Init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-700, WindowsHeight-200);
		PersonalWin.setLayout(null);
		PersonalWin.setFocusable(true);
		//不允许用户调整窗口大小
		this.setResizable(false);
		CenterView.CenterByWindow(this);
//		//背景
//		ImageIcon image = new ImageIcon("res/loginBackground1.png");
//		//得到Image对象
//		Image img = image.getImage();
//		//创建缩放版本
//		img = img.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
//		//替换为缩放版本
//		image.setImage(img);
//		labBackground.setIcon(image);
//		labBackground.setBounds(0, 0, 400, 400);
		//头像
		labPhoto.setBounds(125, 25, 185, 185);
		//实例化ImageIcon 对象
		ImageIcon image = new ImageIcon("res/photo1.png");
		//得到Image对象
		Image img = image.getImage();
		//创建缩放版本
		img = img.getScaledInstance(165, 165, Image.SCALE_DEFAULT);
		//替换为缩放版本
		image.setImage(img);
		//JLabel设置图像
		labPhoto.setIcon(image);
		//设置图像居中
		labPhoto.setHorizontalAlignment(JLabel.CENTER);
		labPhoto.setBorder(BorderFactory.createTitledBorder(admin.getAdminName()));
		//按钮
		btnSave.setBounds(40, 350, 60, 30);
		btnClose.setBounds(300, 350, 60, 30);
		//密码
		btnChangerPwd.setBounds(160, 350, 80, 30);
		//txfChangerPwd.setEditable(false);
//		labChangerPwd.setBounds(70, 280, 60, 30);
//		labChangerPwd.setFont(new Font("微软雅黑", 1, 17));
		//名字
		labName.setFont(new Font("微软雅黑", 1, 17));
		labName.setBounds(70, 240, 60, 30);
		txfName.setBounds(130, 240, 200, 30);

		PersonalInfoView_ActionListener listen = new PersonalInfoView_ActionListener(this,mv,admin);

		btnChangerPwd.addActionListener(listen);
		
		btnSave.addActionListener(listen);

		btnClose.addActionListener(listen);

//		PersonalWin.add(txfChangerPwd);
		PersonalWin.add(labPhoto);
		PersonalWin.add(btnSave);
		PersonalWin.add(labName);
		PersonalWin.add(txfName);
		PersonalWin.add(btnClose);
		PersonalWin.add(btnChangerPwd);
		//PersonalWin.add(labBackground);

		this.add(PersonalWin);
		this.setVisible(true);



	}

	public JButton getTxfChangerPwd() {
		return btnChangerPwd;
	}

	public JTextField getTxfName() {
		return txfName;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public JButton getBtnSave() {
		return btnSave;
	}



}
//*/








/*
package com.bluerabbit.librarysystem.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;

@SuppressWarnings("serial")
public class PersonalInfoView extends JDialog {
	private int WindowsHeight;
	private int windowsWidth;

	private JPanel PersonalWin;
	private JLabel PersonalTitle;
	private JLabel title;
	private JButton PersonalButton;   //修改按钮
	private HexTextField PersonalUser;
	private HexPasswordField PersonalPWD1;

	public PersonalInfoView(MainView mv,AdministratorBeans admin){
		super(mv,"个人信息管理",true);
		//title = new JLabel("！");
		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		PersonalView = new JPanel();

		Init();
	}




	private void Init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-600, WindowsHeight-100);
		CenterView.CenterByWindow(this);
		PersonalView.setLayout(null);
		//不允许用户调整窗口大小
		this.setResizable(false);

		this.add(PersonalView);
		this.setVisible(true);
	}
}
 */