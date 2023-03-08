package com.bluerabbit.librarysystem.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bluerabbit.librarysystem.listener.LoginView_helpButton_ActionListener;
import com.bluerabbit.librarysystem.listener.LoginView_loginButton_ActionListener;
/**
 * 登录界面
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class loginView extends JFrame{
	//定义一个“画布”，相当于是画控件的白纸
	private JPanel loginWin;
	//定义一些标签，用于显示文字
	private JLabel loginTitle;//标题“图书管理系统”
	private JLabel loginName;//账号
	private JLabel loginPWD;//密码
	//定义了一些按钮，登录和帮助按钮
	private JButton loginButton;//登录
	private JButton helpButton;//帮助
	//一个下拉列表，用于选择历史登录账号
	private JComboBox<String> loginUserHistory;//历史登录账户
	//带暗示“hint”的输入框和密码框
	private HexTextField loginUser;//账号输入框
	private HexPasswordField loginUserPWD;//密码输入框

	//构造函数，一般界面分两个部分初始化，一部分是新建（new）（构造函数），一部分是定义位置和属性（init函数）
	public loginView(){
		//新建一个画布，并且带一个匿名内部类
		loginWin = new JPanel(){
			//定义一张图片，新建一个ImageIcon对象并调用getImage方法获得一个Image对象
			private Image image = new ImageIcon("res\\loginBackground.png"/*图片的路径*/).getImage();
			//这里系统要调用这个paintComponent方法来画这张图片，这里系统传入了一个Graphics对象（画笔），
			//我们需要用这个对象来画背景图片
			protected void paintComponent(Graphics g) {
				//调用画笔的drawImage方法，参数是要画的图片，初始坐标，结束坐标，和在哪里画，this代表是LoginWin这
				//个“画布”对象
				g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
			}  
			//这样就实现了背景图
		};
		//新建一个label，传入初始参数，暨要显示的内容，后面也可用setText方法来更新
		loginTitle = new JLabel("蓝兔图书管理系统");
		loginName = new JLabel("账号：");
		loginPWD = new JLabel("密码：");
		loginButton = new JButton("登录");
		helpButton = new JButton("帮助");
		//下面只是新建一下
		loginUserHistory = new JComboBox<String>();
		loginUser = new HexTextField();
		loginUserPWD = new HexPasswordField();
		
		System.out.println("新建窗口成功！");
		//调用初始化函数，这里包括设置坐标、大小、设置监听方法
		intiView();
	}
	
	//这里需要设置为私有的，以防止其他程序调用多次产生多个监听器而产生点一次按钮响应两次的结果
	private void intiView(){
		//设置标题，this表示LoginView这个界面，所以里的标题是界面的标题
		this.setTitle("蓝兔图书管理系统 - 管理员登录");
		//设置窗口可关闭，退出的方式有多种，exit和dispose
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口大小
		this.setSize(550,350);
		//禁用或启用此 frame 的装饰
		//this.setUndecorated(true);
		//设置窗口位置，根据分辨率自动调整
		CenterView.CenterByWindow(this);
		//不允许用户调整窗口大小
		this.setResizable(false);
		

		//设置容器布局方式为空布局
		loginWin.setLayout(null);
		//默认是界面获得焦点
		loginWin.setFocusable(true);

		//宽高，宽高
		//通过setFont方法来设置标签的字体，包括大小
		loginTitle.setFont(new Font("微软雅黑", 3, 27));
		//setBounds是null的灵魂,设置坐标和大小，宽高，宽高
		loginTitle.setBounds(100, 20, 250, 30);

		loginName.setFont(new Font("微软雅黑", 0, 17));
		loginName.setBounds(115, 80, 60, 20);

		loginPWD.setFont(new Font("微软雅黑", 0, 17));
		loginPWD.setBounds(115, 120, 60, 20);

		//登录按钮
		loginButton.setBounds(148, 170, 200, 40);
		
		//帮助按钮
		helpButton.setBounds(218, 240, 60, 20);

		loginUser.setBounds(175, 80, 180, 30);
		
		//这个方法是我自己写的HexTextFild的方法，用来设置没有输入的时候的占位符
		loginUser.setPlaceholder("请输入账号");//当输入框没有内容时,占位显示信息

		loginUserHistory.setBounds(175, 80, 200, 30);
		//定义一个串数组，给下拉框的内容
		//也可以通过.addItem("")的方法来设置
		String[] select = {"admin","10000","10003","10005"};
		loginUserHistory.setModel(new DefaultComboBoxModel<String>(select));
		loginUser.setText(select[0]);
		
		//和上面一样
		loginUserPWD.setBounds(175, 120, 200, 30); 
		loginUserPWD.setPlaceholder("请输入密码");



		//增加按钮监听，监听类自己设计
		helpButton.addActionListener(new LoginView_helpButton_ActionListener(this));
		loginButton.addActionListener(new LoginView_loginButton_ActionListener(this));

		//把设置好的控件全加到画布里，这里注意一点顺序，先加的在上面，所以
		//先加账号框再加下拉框
		loginWin.add(loginTitle);
		loginWin.add(loginName);
		loginWin.add(loginPWD);
		loginWin.add(loginButton);
		loginWin.add(helpButton);
		loginWin.add(loginUser);
		loginWin.add(loginUserHistory);
		loginWin.add(loginUserPWD);

		//把画布放到窗口里
		this.add(loginWin);
		//窗口使能
		this.setVisible(true);
		System.out.println("初始化窗口成功！");
		
		//下面两行是为方便的，自动输入账号密码
//		loginUser.setText("10000");
//		loginUserPWD.setText("10000");
		
		//下面的匿名类是为账号框和下拉框服务的，监听下拉框的事件，把选择的列表放到文本框里
		loginUserHistory.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                	//获取下拉框的值后放到文本框里
                	loginUser.setText(loginUserHistory.getSelectedItem().toString());
                    System.out.print(loginUser.getText());
                }
            }
        });
	}
	
	//下面两个是给登录监听类放出的端口
	public JPasswordField getLoginUserPWD() {
		return loginUserPWD;
	}
	
	public JTextField getLoginUser() {
		return loginUser;
	}
}
