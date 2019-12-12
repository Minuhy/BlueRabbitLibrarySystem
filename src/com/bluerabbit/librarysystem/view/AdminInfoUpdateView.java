package com.bluerabbit.librarysystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.dao.AdministratorBeansDAO;
import com.bluerabbit.librarysystem.listener.AdminInfoUpdateView_ActionListener;
/**
 * 管理员信息详情页面，更改、增加、查看功能
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class AdminInfoUpdateView extends JDialog {
	private String oldPasswd;
	public ComboJLAndJT getLname() {
		return Lname;
	}

	public ComboJLAndJT getLaccout() {
		return Laccout;
	}

	public ComboJLAndJT getLid() {
		return Lid;
	}

	public JPasswordField getPwfPasswd() {
		return pwfPasswd;
	}
	
	public String getOldPasswd() {
		return oldPasswd;
	}

	private boolean mode;//t:add.f:fix
	private JPanel mianView;
	private JPanel contentView;
	private JPanel functionView;
	private int WindowsHeight;
	private int windowsWidth;

	//管理员信息
	private ComboJLAndJT Lname;//昵称
	private ComboJLAndJT Laccout;//账号
	private ComboJLAndJT Lid;//编号
	
	private JPanel pnlPasswd;
	private JLabel Lpasswd;//密码
	private JPasswordField pwfPasswd;

	private JCheckBox jcbIfsup;

	private JButton ok;
	private JButton cancel;

	private AdminInfoManageView wv;
	boolean editor;
	
	MainView mv;
	
	public AdminInfoUpdateView(AdminInfoManageView wv,String title,String Account,boolean editor,MainView mv){
		super(wv,title,true);
		this.wv = wv;
		this.mv = mv;
		mode = true;
		this.editor = editor;
		//获得父视图的大小
		WindowsHeight = wv.getHeight();
		windowsWidth = wv.getWidth();

		//界面部分
		mianView = new JPanel();
		functionView = new JPanel();
		contentView = new JPanel();
		pnlPasswd = new JPanel();

		//馆藏信息
		Lname = new ComboJLAndJT("昵称：",editor,30);//所在书室
		Laccout = new ComboJLAndJT("账号：",editor,30);//所在书架
		Lid = new ComboJLAndJT("编号：",editor,30);//书刊类别
		Lpasswd = new JLabel("新密码：");//馆藏类别
		pwfPasswd = new JPasswordField(30);
		jcbIfsup = new JCheckBox("超级管理员权限");

	

		ok = new JButton("确定");
		cancel = new JButton("取消");

		if(Account == null){
			Account = "";
		}
		if(!Account.equals("")){
			//不为空，是修改信息，在这里载入初始化信息
			System.out.println("加载账号信息！");
			loader(Account);
			mode = false;
			ok = new JButton("更新");
			Laccout.getGettxt().setEditable(false);
		}

		Init();
	}

	public AdminInfoUpdateView(AdminInfoManageView wv,String title,String Account,boolean editor){
		super(wv,title,true);
		this.wv = wv;
		mode = true;
		this.editor = editor;
		//获得父视图的大小
		WindowsHeight = wv.getHeight();
		windowsWidth = wv.getWidth();

		//界面部分
		mianView = new JPanel();
		functionView = new JPanel();
		contentView = new JPanel();
		pnlPasswd = new JPanel();

		//馆藏信息
		Lname = new ComboJLAndJT("昵称：",editor,30);//所在书室
		Laccout = new ComboJLAndJT("账号：",editor,30);//所在书架
		Lid = new ComboJLAndJT("编号：",editor,30);//书刊类别
		Lpasswd = new JLabel("新密码：");//馆藏类别
		pwfPasswd = new JPasswordField(30);
		jcbIfsup = new JCheckBox("超级管理员权限");

	

		ok = new JButton("确定");
		cancel = new JButton("取消");

		if(Account == null){
			Account = "";
		}
		if(!Account.equals("")){
			//不为空，是修改信息，在这里载入初始化信息
			System.out.println("加载账号信息！");
			loader(Account);
			mode = false;
			ok = new JButton("更新");
		}

		Init();
	}

	private void loader(String Account) {
		// TODO Auto-generated method stub
		AdministratorBeans adminInfo = null;
		//获取图书信息
		adminInfo = new AdministratorBeansDAO().getAdminInfoByAcc(Account);

		//设置图书信息
		//馆藏信息
		Lname.setIText(adminInfo.getAdminName());//名字
		Laccout.setIText(adminInfo.getAccount());//账号
		Lid.setIText(adminInfo.getAdminID() + "");//编号
		pwfPasswd.setText(adminInfo.getPassword());//密码
		jcbIfsup.setSelected(adminInfo.isIfsuper());
		//备份密码，用于判断是否改密
		oldPasswd = adminInfo.getPassword();

	}

	private void Init() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-650, WindowsHeight-300);
		CenterView.CenterByWindow(this);
		//不允许用户调整窗口大小
		this.setResizable(false);

		//设置布局
		mianView.setLayout(new BorderLayout());
		contentView.setLayout(new GridLayout(4,1));
		functionView.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//配置内容视图边框
		contentView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		
		

		functionView.add(jcbIfsup);
		jcbIfsup.setPreferredSize(new Dimension(200,20));
		//馆藏信息
		contentView.add(Lname);//名字
		contentView.add(Laccout);//账号
		contentView.add(Lid);//编号
		
		pnlPasswd.setLayout(new FlowLayout());
		
		pnlPasswd.add(Lpasswd);
		pnlPasswd.add(pwfPasswd);
		contentView.add(pnlPasswd);//密码
		if(!editor){
			pwfPasswd.setEnabled(false);
			jcbIfsup.setEnabled(false);
		}

		//添加监听事件
		AdminInfoUpdateView_ActionListener listener = new AdminInfoUpdateView_ActionListener(this,wv,mv);
		cancel.addActionListener(listener);
		ok.addActionListener(listener);

		//如果可以编辑
		if(editor){
			functionView.add(ok);
		}
		functionView.add(cancel);


		mianView.add(functionView, BorderLayout.SOUTH);
		mianView.add(contentView, BorderLayout.CENTER);
		this.add(mianView);
		this.setVisible(true);
	}

	public boolean isMode() {
		return mode;
	}
	
	public JButton getOk() {
		return ok;
	}
	
	public JButton getCancel() {
		return cancel;
	}
	
	public JCheckBox getJcbIfsup() {
		return jcbIfsup;
	}
}
