package com.bluerabbit.librarysystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
import com.bluerabbit.librarysystem.dao.ReaderInfoBeansDAO;
import com.bluerabbit.librarysystem.listener.ReaderInfoUpdateView_ActionListener;
/**
 * 读者信息查看，修改，新增界面
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class ReaderInfoUpdateView extends JDialog {
	private boolean mode;//t:add.f:fix
	private JPanel mianView;
	private JPanel contentView;
	private JPanel functionView;
	private int WindowsHeight;
	private int windowsWidth;

	//读者信息
	private ComboJLAndJT cltReaderID;//学号
	private ComboJLAndJT cltReaderName;//姓名
	private ComboJLAndJT cltApart;//学院
	private ComboJLAndJT cltTheClass;//班级
	private ComboJLAndJT cltTelNo;//班级

	private JPanel pnlSex;
	private JComboBox<String> jcbSex;
	private JLabel LabSex;//性别


	private JButton ok;
	private JButton cancel;

	private ReaderInfoManageView rv;
	boolean editor;
	private String readerId = null;

	String[] select = {"男","女"};//性别框
	/**
	 * 读者信息详细页面
	 * @param rv 读者信息管理器，父视图
	 * @param title 当前的详情页面标题
	 * @param readerId 读者的学号，有学号根据学号加载个人信息，没有为null的话就不加载
	 * @param editor 是否能被编辑
	 */
	public ReaderInfoUpdateView(ReaderInfoManageView rv,String title,String readerId,boolean editor){
		super(rv,title,true);
		this.rv = rv;
		mode = true;
		this.editor = editor;

		//如果学号为空，模式设置为false,不加载信息
		if(readerId == null){
			readerId = "";
		}
		if(!readerId.equals("")){
			mode = false;
			this.readerId = readerId;
		}

		//获得父视图的大小
		WindowsHeight = rv.getHeight();
		windowsWidth = rv.getWidth();

		//界面部分
		mianView = new JPanel();
		functionView = new JPanel();
		contentView = new JPanel();
		pnlSex = new JPanel();

		/**
		 * 解决添加和查看详情的大小适配问题
		 */
		int w;
		if(!editor){
			//查看模式
			w = 130;
		}else{
			//增加模式
			w = 20;
		}

		//馆藏信息
		cltReaderID = new ComboJLAndJT("    学号：",editor,w);//所在书室
		cltReaderName = new ComboJLAndJT("    姓名：",editor,w);//所在书架
		cltApart = new ComboJLAndJT("    学院：",editor,w);//书刊类别
		cltTheClass = new ComboJLAndJT("    班级：",editor,w);//书刊类别
		cltTelNo = new ComboJLAndJT("联系方式：",editor,w);//书刊类别
		jcbSex = new JComboBox<String>();
		LabSex = new JLabel("    性别：");



		ok = new JButton("确定");
		cancel = new JButton("取消");

		if(!mode){
			//不为空，是修改信息，在这里载入初始化信息
			System.out.println("加载账号信息！");
			loader(readerId);
			mode = false;
			ok = new JButton("更新");
			//禁止更改学号
			//cltReaderID.getGettxt().setEditable(false);
		}
		if(!editor){
			//禁止更改性别
			jcbSex.setEnabled(false);
		}

		Init();
	}

	/**
	 * 加载读者信息函数，设置打开时的初始值
	 * @param readerId 打开的读者信息
	 */
	private void loader(String readerId) {
		// TODO Auto-generated method stub
		ReaderInfoBeans readerInfo = null;
		//获取图书信息
		readerInfo = new ReaderInfoBeansDAO().getReaderInfoById(readerId);

		//设置读者信息
		cltReaderID.setIText(readerInfo.getReaderID());//学号

		cltReaderName.setIText(readerInfo.getReaderName());//姓名

		cltApart.setIText(readerInfo.getApart());//学院

		cltTheClass.setIText(readerInfo.getTheClass ());//班级

		cltTelNo.setIText(readerInfo.getTelNo());//联系方式

		if(readerInfo.getSex().equals("女")){
			select[0] = "女";
			select[1] = "男";
		}
	}

	/**
	 * 界面初始化函数，内部调用，设置布局什么的
	 */
	private void Init() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-550, WindowsHeight-350);
		CenterView.CenterByWindow(this);
		//不允许用户调整窗口大小

		//设置布局
		mianView.setLayout(new BorderLayout());
		contentView.setLayout(new GridLayout(3,2));
		functionView.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//配置内容视图边框
		contentView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

		//馆藏信息
		contentView.add(cltReaderID);//学号
		contentView.add(cltReaderName);//名字
		contentView.add(cltApart);//学院

		pnlSex.setLayout(new FlowLayout());

		pnlSex.add(LabSex);
		pnlSex.add(jcbSex);
		contentView.add(pnlSex);//性别
		jcbSex.setModel(new DefaultComboBoxModel<String>(select));
		jcbSex.setPreferredSize(new Dimension(130,27));


		contentView.add(cltTheClass);//班级
		contentView.add(cltTelNo);//联系方式



		//添加监听事件
		ReaderInfoUpdateView_ActionListener listener = new ReaderInfoUpdateView_ActionListener(this,rv);
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

	/**
	 * 返回增加模式或者修改模式，false是修改模式，true是增加模式
	 * @return 模式
	 */
	public boolean isMode() {
		return mode;
	}

	/**
	 * 提供的控件接口函数
	 * @return 确定或更新按钮
	 */
	public JButton getOk() {
		return this.ok;
	}

	/**
	 * 提供的控件接口函数
	 * @return 取消按钮
	 */
	public JButton getCancel() {
		return this.cancel;
	}

	/**
	 * 提供的控件接口函数
	 * @return 读者学号组合类
	 */
	public ComboJLAndJT getCltReaderID() {
		return cltReaderID;
	}	

	/**
	 * 提供的控件接口函数
	 * @return 读者名字组合类
	 */
	public ComboJLAndJT getCltReaderName() {
		return cltReaderName;
	}

	/**
	 * 提供的控件接口函数
	 * @return 读者学院组合类
	 */
	public ComboJLAndJT getCltApart() {
		return cltApart;
	}

	/**
	 * 提供的控件接口函数
	 * @return 读者班级组合类
	 */
	public ComboJLAndJT getCltTheClass() {
		return cltTheClass;
	}

	/**
	 * 提供的控件接口函数
	 * @return 读者联系方式组合类
	 */
	public ComboJLAndJT getCltTelNo() {
		return cltTelNo;
	}

	/**
	 * 提供的控件接口函数
	 * @return 读者性别下拉框
	 */
	public JComboBox<String> getJcbSex(){
		return jcbSex;
	}
	/**
	 * 获取读者载入ID
	 * @return 刚刚新建的时候的读者ID，没有的话是null
	 */
	public String getReaderId() {
		return readerId;
	}
}
