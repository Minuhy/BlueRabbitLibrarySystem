package com.bluerabbit.librarysystem.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.dao.BookInfoDAO;
import com.bluerabbit.librarysystem.listener.BookInfoManageView_Add_MouseListener;
import com.bluerabbit.librarysystem.listener.BookInfoManageView_Clo_MouseListener;
import com.bluerabbit.librarysystem.listener.BookInfoManageView_Del_MouseListener;
import com.bluerabbit.librarysystem.listener.BookInfoManageView_Fix_MouseListener;
import com.bluerabbit.librarysystem.listener.BookInfoManageView_adSearch_ActionListener;
import com.bluerabbit.librarysystem.listener.BookInfoManageView_exeInquire_MouseListener;
import com.bluerabbit.librarysystem.listener.BookInfoManageView_tableView_MouseListener;
/**
 * 图书管理界面
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class BookInfoManageView extends JDialog {
	private int WindowsHeight;
	private int windowsWidth;
	private int buttonSize = 50;

	private JPanel bookView;
	private JPanel buttonView;
	private JPanel toolView;
	private JPanel tableView;
	private JPanel InfoView;

	private JLabel Badd;
	private JLabel Bfix;
	private JLabel Bdel;
	private JLabel Bclo;

	private JLabel inqTipWay;
	private JLabel inqTipInfo;
	private JComboBox<String> chooseWay;
	private JTextField chooseInfo;
	private JButton exeInquire;
	private JLabel labPerch;
	private JButton adSearch;

	private MyTable tableDataView;
	private DefaultTableModel dtmView;
	private JScrollPane snpView;


	public BookInfoManageView(MainView mv){
		super(mv,"图书信息管理",true);
		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		bookView = new JPanel();
			buttonView = new JPanel();
				Badd = new JLabel("增加");
				Bfix = new JLabel("修改");
				Bdel = new JLabel("删除");
				Bclo = new JLabel("关闭");
			InfoView = new JPanel();
				toolView = new JPanel();
					inqTipWay = new JLabel("选择搜索方式");
					inqTipInfo = new JLabel("输入图书信息搜索");
					chooseWay = new JComboBox<String>();
					chooseInfo = new JTextField();
					exeInquire = new JButton("搜索一下");
					labPerch = new JLabel("       ");//占位符
					adSearch = new JButton("高级搜索");
				tableView = new JPanel();
					snpView = new JScrollPane();
						tableDataView = new MyTable();
	/**视图结构表
	 * bookView = new JPanel();
			buttonView = new JPanel();
				Badd = new JLabel("增加");
				Bfix = new JLabel("修改");
				Bdel = new JLabel("删除");
				Bclo = new JLabel("关闭");
			InfoView = new JPanel();
				toolView = new JPanel();
					inqTipWay = new JLabel("选择搜索方式");
					inqTipInfo = new JLabel("输入图书信息搜索");
					chooseWay = new JComboBox();
					chooseInfo = new JTextField();
					exeInquire = new JLabel("搜索一下");
					adSearch = new JButton("高级搜索");
				tableView = new JPanel();
					snpView = new JScrollPane();
						tableDataView = new JTable();
	 	*/

		Init();
	}

	private void Init() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-100, WindowsHeight-100);
		CenterView.CenterByWindow(this);
		//不允许用户调整窗口大小
		this.setResizable(false);
		//边界布局
		bookView.setLayout(new BorderLayout());
		//流水布局
		buttonView.setLayout(new FlowLayout(FlowLayout.LEFT));
		//边界布局
		InfoView.setLayout(new BorderLayout());
		//流水布局
		toolView.setLayout(new FlowLayout(FlowLayout.LEFT));
		//表格布局
		tableView.setLayout(new GridLayout(1,1));
		
		//按钮栏的处理
//		Badd.setBounds(0, 0, 80, 80);
//		Bfix.setBounds(100, 0, 80, 80);
//		Bdel.setBounds(180, 0, 80, 80);
//		Bclo.setBounds(200, 0, 80, 80);
		
//		buttonView.setLayout(null);
		Badd.setPreferredSize(new Dimension(buttonSize,buttonSize));
		Bfix.setPreferredSize(new Dimension(buttonSize,buttonSize));
		Bdel.setPreferredSize(new Dimension(buttonSize,buttonSize));
		Bclo.setPreferredSize(new Dimension(buttonSize,buttonSize));
		
		buttonView.add(Badd);
		buttonView.add(Bfix);
		buttonView.add(Bdel);
		buttonView.add(Bclo);
		
		setJLabelImage(Badd,"res/book/Bookadd.png",buttonSize,buttonSize);
		setJLabelImage(Bfix,"res/book/Bookfix.png",buttonSize,buttonSize);
		setJLabelImage(Bdel,"res/book/Bookdel.png",buttonSize,buttonSize);
		setJLabelImage(Bclo,"res/book/Bookclo.png",buttonSize,buttonSize);
		
		//工具栏的处理
		//提示text
//		inqTipInfo.setBounds(0, 0, 180, 20);
//		//text
//		chooseInfo.setBounds(0, 30, 180, 20);
//		//提示combobox
//		inqTipWay.setBounds(0, 60, 180, 20);
//		//combobox
//		chooseWay.setBounds(0, 90, 180, 20);
		chooseWay.addItem("书刊编号");
		chooseWay.addItem("书刊名称");
		chooseWay.addItem("书刊作者");
//		//button
//		exeInquire.setBounds(0, 250, 180, 30);
//		//ADsouch
//		adSearch.setBounds(0, 300, 180, 30);
		
//		toolView.setLayout(null);
		toolView.add(inqTipWay);
		toolView.add(chooseWay);
		toolView.add(inqTipInfo);
		toolView.add(chooseInfo);
		chooseInfo.setPreferredSize(new Dimension(100,30));
		toolView.add(exeInquire);
		toolView.add(labPerch);
		//设置大小
		labPerch.setPreferredSize(new Dimension(117,30));
		//边框
		//exeInquire.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		toolView.add(adSearch);
		
		//为搜索按钮添加监听
		exeInquire.addMouseListener(new BookInfoManageView_exeInquire_MouseListener(this));
		//setJLabelImage(exeInquire,"res/BookInfo/sousuo2.png",197,27);
		
		
		adSearch.addActionListener(new BookInfoManageView_adSearch_ActionListener(this));

		
		//表格的处理
		
		tableDataView.addMouseListener(new BookInfoManageView_tableView_MouseListener(this));
		
		//获取数据
		updateTableData();
		
		snpView.getViewport().add(tableDataView);
		tableView.add(snpView);
	
		bookView.add(buttonView, BorderLayout.NORTH);
		bookView.add(InfoView, BorderLayout.CENTER);
		
		InfoView.add(toolView, BorderLayout.NORTH);
		InfoView.add(snpView, BorderLayout.CENTER);
		
		//BookInfoManageView_Del_MouseListener bmv = new BookInfoManageView_Del_MouseListener(this);
		//监听事件
		Badd.addMouseListener(new BookInfoManageView_Add_MouseListener(this));
		Bfix.addMouseListener(new BookInfoManageView_Fix_MouseListener(this));
		Bdel.addMouseListener(new BookInfoManageView_Del_MouseListener(this));
		Bclo.addMouseListener(new BookInfoManageView_Clo_MouseListener(this));
		
		
		this.add(bookView);
		this.setVisible(true);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setTableData(List<BookInfoBeans> list) {
		// TODO Auto-generated method stub
		//1.设置表格中的标题数据集合
		Vector<String> title = new Vector<String>();
		title.add("书名");
		title.add("作者");
		title.add("书刊类别");
		title.add("出版社");
		title.add("出版版次");
		title.add("剩余册数");
		title.add("所在书室");
		title.add("所在书架");
		title.add("图书编号");
		
		//2.设置表格中的数据集合
		Vector<Vector> datas = new Vector<Vector>();
		Vector row = null;
		
		
		for(BookInfoBeans b : list) {
			row = new Vector();
			row.add(b.getBookName());//书名
			row.add(b.getAuthor());//作者
			row.add(b.getBookClassify());//分类
			row.add(b.getPublisher());//出版社
			row.add(b.getPublishTime());//出版版次
			row.add(b.getQuantity());//剩余册数
			row.add(b.getStack());//所在书室
			row.add(b.getBookShelf());//所在书架
			row.add(b.getBookID());//图书编号
			datas.add(row);
		}
		
 		//设置tableModel
		this.dtmView = new DefaultTableModel(datas,title);
		//将tableModel绑定在table上
		this.tableDataView.setModel(dtmView);
		
		//设置表格自适应数据
		tableDataView.FitTableColumns();
	}
	
	
	public void setJLabelImage(JLabel label,String imagePath,int w,int h){
		//实例化ImageIcon 对象
		ImageIcon image = new ImageIcon(imagePath);
		//得到Image对象
		Image img = image.getImage();
		//创建缩放版本
		img = img.getScaledInstance(w,h, Image.SCALE_DEFAULT);
		//替换为缩放版本
		image.setImage(img);
		//JLabel设置图像
		label.setIcon(image);
		//设置默认边框
		label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}
	
	
	//给监听类放通
	public JLabel getBadd() {
		return Badd;
	}
	
	public JLabel getBclo() {
		return Bclo;
	}
	
	public JLabel getBdel() {
		return Bdel;
	}
	
	public JLabel getBfix() {
		return Bfix;
	}
	
	public JTable getTableDataView() {
		return tableDataView;
	}

	//更新表单数据
	public void updateTableData() {
		// TODO Auto-generated method stub
		//获取数据
		BookInfoDAO dao = new BookInfoDAO();
		List<BookInfoBeans> list = dao.getAllBookInfo();
		//设置数据
		setTableData(list);
	}

	public JButton getExeInquire() {
		// TODO Auto-generated method stub
		return this.exeInquire;
	}
	
	public JTextField getChooseInfo() {
		return this.chooseInfo;
	}
	
	public JComboBox<String> getChooseWay() {
		return this.chooseWay;
	}
	
}


