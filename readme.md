# 蓝兔图书管理系统（Java+MySQL+Swing）
## 零、前言
- 本项目是2019年6月湖南人文科技学院和湖南虹猫信息科技有限公司的校企合作项目中我们小组（5人）的实训作品。我是通信工程专业的，2019年上期我刚上完学校的Java选修课程和SQLServer2008的选项课程，只对Java一知半解，参加这次实训本来是想选择另外一家公司的用Cocos2D做“天天酷跑”的项目的，说好跟她组队一起做天天酷跑的，然而她因为各种原因被学校分到这个公司的实训项目中。没办法，想跟她组队，然后就毅然跟学校领导协调，所幸最终可以跟她一起组队，并且收获也很大。这是我第一个Java比较完整项目（上课的那种小例子可以不算项目），学会了怎么用JDBC，学会了Java的一般程序模型（DAO层、JavaBean、监听层、服务层、视图层等），了解了一个软件的开发和维护。总之实训结束的时候我觉得我可以一个人独立开发软件了，这让我感觉收获非常大，这奠定了后来我独立开发软件的基础，但是在写程序的时候也出现了很多问题，比如监听层处理业务比较小的时候能不能放到视图层里（后面写安卓的时候经常这样，但是老师要求我们分开，不是很理解），服务层要操作视图层时是直接把视图传过去还是只传控件，以后要移植的话服务层怎么保证不用改很多地方等问题，以至于我自己独立写程序时很多地方不确定这样写是不是比较科学的。后来发现解决这一些问题的有专门的模式设计的课程，但还没有深入了解。感谢老师和同学们对我的帮助，实训过去5个月，现分享出来供大家学习交流。

- 下面简单介绍项目软件，文章末尾有相关资源链接。

- 项目中所有内容仅用于学习用途。

## 一、功能
1. 管理员登录、改密码、资料等
2. 有超级权限的账号能管理其他管理员
3. 图书信息的增删改查
4. 能对读者的信息进行管理
5. 借阅管理（暂停）
6. 退出登录

## 二、相关技术及开发环境
1. Eclipse 2019（4.12.0）
2. JDK8
3. mysql-connector-java-5.1.47
4. beautyeye_lnf
5. Java Swing
6. MySQL5.7

## 三、软件或项目截图及说明
![登录界面](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/loginScan.jpg)
<center>登录界面</center>

![帮助界面](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/helpScan.jpg)
<center>帮助界面：主要显示一些环境相关的帮助</center>

![主界面](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/main1Scan.jpg)
<center>主界面：主界面按钮获得焦点后会有动效（灵感来源于王者荣耀界面布局）</center>

![界面动效](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/main2Scan.jpg)
<center>界面动效：图书管理获得焦点</center>

![个人信息界面](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/personScan.jpg)
<center>个人信息查看和修改界面（灵感来源于QQ资料界面）</center>

![图书管理界面](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/BookManage1Scan.jpg)
<center>图书管理界面：双击条目能查看详情，实现搜索功能，增删改功能，使用DAO层和JavaBean处理</center>

![图书详情界面](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/BookLookAndSeach.jpg)
<center>图书详情查看及搜索功能展示</center>

![图书添加界面](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/BookAdd.jpg)
<center>图书修改或增加有数据合法性校验（有模式窗口，只能操作最前的窗口）</center>

![读者管理界面](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/ReaderManageScan.jpg)
<center>读者管理：同样实现了增删改查和搜索功能</center>

![管理员权限](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/AdminNoSerpu.jpg)
<center>管理员不能进行人员管理</center>

![管理员管理](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/AdminManageScan.jpg)
<center>管理员管理：admin不是超级管理员，换了个账号，可直接改其他管理员信息（密码是MD5加密后保存的）</center>

![借阅管理](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/BookOther.jpg)
<center>借阅管理（实训结束后没有继续完善）</center>
## 四、数据库设计
1. 创建数据库
```SQL
CREATE DATABASE library_system;
```

2. 管理员表
```SQL
use library_system;
create table Administrator(
AdminID int not null,
AdminName char(10) not null,
Account varchar(6) not null primary key,
Password varchar(32) not null,
ifsuper boolean not null
)default charset=utf8;
```
![管理员表](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/SQLAdmin.jpg)

3. 图书信息表
```SQL
use library_system;
create table Books_Info(
BookName varchar(30) not null comment '书名' ,
SumQuantity int not null comment '总册数' ,
Quantity int not null comment '剩余册数' ,
LendTime int not null comment '借出次数',
BookID varchar(30) not null primary key comment '书刊编号' ,
BookBarcode varchar(30) not null comment '书刊条码',
BookClassify varchar(20) comment '书刊分类',
BookThem varchar(30) comment '主题词',
Author varchar(40) comment '作者' ,
Publisher varchar(20) comment '出版社' ,
PublishTime varchar(10) comment '出版版次',
PublishDate date comment '出版日期' ,
BookType varchar(10) comment '馆藏分类',
Stack varchar(20) not null comment '所在书室' ,
BookShelf varchar(20) not null comment '所在书架' ,
Price float comment '价格' ,
ContentText varchar(300) comment '简介',
Remark varchar(100) comment '备注' ,
BookPage int comment '页数' ,
WordsNumber varchar(20) comment '字数'
)default charset=utf8 comment '图书信息表'; 
```
![图书信息表](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/SQLBook.jpg)

4. 读者信息表
```SQL
use library_system;
create table Reader(
ReaderID varchar(20) not null primary key comment '学号' ,
ReaderName char(30) not null comment '姓名' ,
Apart char(10) comment '学院' ,
Sex char(2) default '男' check (Sex in ('男','女')),
Class char(10) comment '班级' ,
TelNo char(20) comment '电话' 
)default charset=utf8;
```
![读者信息表](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/SQLReader.jpg)

5. 表关系
![表关系](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/SQL.jpg)

##五、项目结构
项目分有数据层、视图层、监听层、服务层
![项目](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/jiegou.jpg)

项目分包
![项目包](https://github.com/Minuy/BlueRabbitLibrarySystem/blob/master/images/ssss.jpg)

视图层主要是JavaSwing类，给用户提供交互界面；

监听层主要是按钮点击事件的监听，主要接受用户的请求；

服务层主要是处理各种业务请求，把的得到的结果显示在视图层上；

数据层主要负责数据的查询修改等，提供给服务层使用；

## 六、主要代码分析和使用
- 找到

```java
package com.bluerabbit.librarysystem.database;
```

下面的*DBUtil*类

```java
package com.bluerabbit.librarysystem.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * DBUtils是java编程中的数据库操作实用工具，小巧简单实用，
 * 1.对于数据表的读操作，他可以把结果转换成List，Array，Set等java集合，便于程序员操作；
 * 2.对于数据表的写操作，也变得很简单（只需写sql语句）
 * @author minuy
 *
 */

public abstract class DBUtil {
	//声明连接数据库所需要的相关信息
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/library_system?useUnicode=true&characterEncoding=UTF-8";
	private static String userName = "root";
	private static String passWord = "YourPassword";
	
	//单例化模式
	private DBUtil() {
		
	}
	
	//静态的方式加载JDBC驱动
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回一个数据库链接
	 * @return
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, userName,passWord);
		} catch (SQLException e) {
			System.out.println("数据库连接失败！");
			JOptionPane.showMessageDialog(null, "服务器连接失败");
			e.printStackTrace();
		}

//		new Thread(){
//		    public void run(){
//		        try {
//		            Connection con = DriverManager.getConnection(connectString, sql_user, sql_pwd);
//		            con.close();
//		        } catch (SQLException e) { //连接失败
//		            e.printStackTrace();
//		        }
//		    }
//		}.start();
		
		return conn;
	}
	
	/**
	 * 释放数据库连接信息
	 * @param rs
	 * @param pstm
	 * @param conn
	 */
	public static void free(ResultSet rs,Statement pstm,Connection conn) {
		FreeConnection(conn);
		FreeStatement(pstm);
		FreeResultSet(rs);
	}
	
	public static void free(Connection conn){
		FreeConnection(conn);
	}
	
	public static void free(ResultSet rs,Statement pstm) {
		FreeResultSet(rs);
		FreeStatement(pstm);
	}
	
	
	public static void free(Statement pstm,Connection conn) {
		FreeStatement(pstm);
		FreeConnection(conn);
	}
	
	//释放连接
	private static void FreeConnection(Connection conn){
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//释放资源
	private static void FreeResultSet(ResultSet rs){
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//释放操作对象
	private static void FreeStatement(Statement pstm){
		if(pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

```

只需要改SQL的地址、账号和密码就可以开始使用！**但在此之前要设计好数据库！**

```java
	private static String url = "jdbc:mysql://localhost:3306/library_system?useUnicode=true&characterEncoding=UTF-8";
	private static String userName = "root";
	private static String passWord = "YourPassword";
```



- 主函数：主要设置Swing皮肤库，开启登录视图，后面的交给视图处理
```java
package com.bluerabbit.librarysystem;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import com.bluerabbit.librarysystem.view.loginView;

/**
 * 主函数，设置皮肤，启动窗口
 * @author minuy
 *
 */
public class BlueRabbitLibrarySystem {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {

		try
		{
			BeautyEyeLNFHelper.frameBorderStyle = FrameBorderStyle.translucencyAppleLike;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}


		new loginView();
		

	}
}

```

- 登录界面：主要负责视图的初始化和监听层的启动

*重点内容 ：

    将图片标签放在窗体底层面板，然后将窗体转化为JPan的容器，将JPan设为透明，背景图片就设置好了，之后就可以直接在该JPan中添加组件*
```java
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
		this.setTitle("蓝兔图书管理系统 - 用户登录");
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

```

监听层：主要负责监听点击事件和启动服务层处理业务（不过这里好像处理了服务层的内容）
```java
package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.dao.AdministratorBeansDAO;
import com.bluerabbit.librarysystem.database.MD5Utils;
import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.loginView;
/**
 * 登录界面的登录按钮监听类
 * @author minuy
 *
 */
public class LoginView_loginButton_ActionListener implements ActionListener {
	loginView view;
	JTextField user;
	JPasswordField passwd;

	public LoginView_loginButton_ActionListener(loginView loginView) {
		// TODO Auto-generated constructor stub
		this.view = loginView;
		this.user = view.getLoginUser();
		this.passwd = view.getLoginUserPWD();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//获取账号
		String Account = user.getText();
		//如果没有输入账号
		if(Account.equals("请输入账号")){
			System.out.println("请输入账号！");
			JOptionPane.showMessageDialog(null, "请输入账号！", "提示",JOptionPane.WARNING_MESSAGE); 
			return;
		}
		//获得密码并且进行MD5加密
		String pwMD5 = MD5Utils.MD5Encode(new String(passwd.getPassword()),"utf8");
		//新建一个管理员查询对象
		AdministratorBeansDAO user = new AdministratorBeansDAO();
		//判断是否正确
		if(user.validateByAccountAndPassword(Account, pwMD5)){
			AdministratorBeans admin = user.getAdminInfo();
			System.out.println("密码正确！");
			System.out.println("欢迎用户：" + admin.getAdminName());
			JOptionPane.showMessageDialog(null, "欢迎：" + admin.getAdminName());
			new MainView(admin);
			view.dispose();
		}else{
			System.out.println("账号或密码错误！");
			JOptionPane.showMessageDialog(null, "账号或密码错误！", "错误",JOptionPane.ERROR_MESSAGE);
		}
	}

}

```
## 七、项目资源及参考文档
GitHub地址：[https://github.com/Minuy/BlueRabbitLibrarySystem](https://github.com/Minuy/BlueRabbitLibrarySystem)

参考文档：[JavaSwing皮肤](https://blog.csdn.net/starcrm/article/details/52576379)
