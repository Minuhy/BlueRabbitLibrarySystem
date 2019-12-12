package com.bluerabbit.librarysystem.view;
/**
 * 读者信息管理界面，从主界面进入
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
import com.bluerabbit.librarysystem.dao.ReaderInfoBeansDAO;
import com.bluerabbit.librarysystem.listener.ReaderInfoManageView_MouseListener;
/**
 * 读者信息管理界面
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class ReaderInfoManageView extends JDialog {
	private JPanel ReaderView;
	private int WindowsHeight;
	private int windowsWidth;
	
	private int buttonSize = 50;

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
	private JLabel exeInquire;

	private MyTable tableDataView;
	private DefaultTableModel dtmView;
	private JScrollPane snpView;
	

	public ReaderInfoManageView(MainView mv){
		super(mv,"读者信息管理",true);
		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		ReaderView = new JPanel();
		buttonView = new JPanel();
			Badd = new JLabel("增加",JLabel.CENTER);
			Bfix = new JLabel("修改",JLabel.CENTER);
			Bdel = new JLabel("删除",JLabel.CENTER);
			Bclo = new JLabel("关闭",JLabel.CENTER);
		InfoView = new JPanel();
			toolView = new JPanel();
				inqTipWay = new JLabel("选择搜索方式");
				inqTipInfo = new JLabel("输入关键词");
				chooseWay = new JComboBox<String>();
				chooseInfo = new JTextField();
				exeInquire = new JLabel("搜索一下",JLabel.CENTER);
			tableView = new JPanel();
				snpView = new JScrollPane();
					tableDataView = new MyTable();
	
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
		ReaderView.setLayout(new BorderLayout());
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
		

		Badd.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		Bfix.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		Bdel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		Bclo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		buttonView.add(Badd,BorderLayout.CENTER);
		buttonView.add(Bfix,BorderLayout.CENTER);
		buttonView.add(Bdel,BorderLayout.CENTER);
		buttonView.add(Bclo,BorderLayout.CENTER);
		
//		setJLabelImage(Badd,"res/BookInfo/Bookadd.png",buttonSize,buttonSize);
//		setJLabelImage(Bfix,"res/BookInfo/Bookfix.png",buttonSize,buttonSize);
//		setJLabelImage(Bdel,"res/BookInfo/Bookdel.png",buttonSize,buttonSize);
//		setJLabelImage(labResetPwd,"res/BookInfo/Bookrst.png",buttonSize,buttonSize);
//		setJLabelImage(Bclo,"res/BookInfo/Bookclo.png",buttonSize,buttonSize);
		
		//工具栏的处理
		//提示text
//		inqTipInfo.setBounds(0, 0, 180, 20);
//		//text
//		chooseInfo.setBounds(0, 30, 180, 20);
//		//提示combobox
//		inqTipWay.setBounds(0, 60, 180, 20);
//		//combobox
//		chooseWay.setBounds(0, 90, 180, 20);
		chooseWay.addItem("学号");
		chooseWay.addItem("姓名");
		chooseWay.addItem("班级");
		chooseWay.addItem("学院");
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
		toolView.add(exeInquire,BorderLayout.CENTER);
		//设置边框
		exeInquire.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
//		设置大小
		exeInquire.setPreferredSize(new Dimension(80,30));
		
		//创建监听器
		ReaderInfoManageView_MouseListener listener = new ReaderInfoManageView_MouseListener(this);
		
		//为搜索按钮添加监听
		exeInquire.addMouseListener(listener);

		
		//表格的处理
		
		tableDataView.addMouseListener(listener);
		
		//获取数据
		updateTableData();
		
		
		snpView.getViewport().add(tableDataView);
		tableView.add(snpView);
	
		ReaderView.add(buttonView, BorderLayout.NORTH);
		ReaderView.add(InfoView, BorderLayout.CENTER);
		
		InfoView.add(toolView, BorderLayout.NORTH);
		InfoView.add(snpView, BorderLayout.CENTER);
		
		//BookInfoManageView_Del_MouseListener bmv = new BookInfoManageView_Del_MouseListener(this);
		//监听事件
		Badd.addMouseListener(listener);
		Bfix.addMouseListener(listener);
		Bdel.addMouseListener(listener);
		Bclo.addMouseListener(listener);
				
		this.add(ReaderView);		
		this.setVisible(true);
	}
	

		
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void setTableData(List<ReaderInfoBeans> list) {
			// TODO Auto-generated method stub
			//1.设置表格中的标题数据集合
			Vector<String> title = new Vector<String>();
			title.add("学号");
			title.add("姓名");
			title.add("性别");
			title.add("学院");
			title.add("班级");
			title.add("联系方式");
			
			//2.设置表格中的数据集合
			Vector<Vector> datas = new Vector<Vector>();
			Vector row = null;
			
			
			for(ReaderInfoBeans b : list) {
				row = new Vector();
			
				row.add(b.getReaderID());
				row.add(b.getReaderName());
				row.add(b.getSex());
				row.add(b.getApart());
				row.add(b.getTheClass());
				row.add(b.getTelNo());
				
				datas.add(row);
			}
			
	 		//设置tableModel
			this.dtmView = new DefaultTableModel(datas,title);
			//将tableModel绑定在table上
			this.tableDataView.setModel(dtmView);
			
			//设置表格自适应数据
			//tableDataView.FitTableColumns();
		}
		
		
//		public void setJLabelImage(JLabel label,String imagePath,int w,int h){
//			//实例化ImageIcon 对象
//			ImageIcon image = new ImageIcon(imagePath);
//			//得到Image对象
//			Image img = image.getImage();
//			//创建缩放版本
//			img = img.getScaledInstance(w,h, Image.SCALE_DEFAULT);
//			//替换为缩放版本
//			image.setImage(img);
//			//JLabel设置图像
//			label.setIcon(image);
//			//设置默认边框
//			label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
//		}
		
		
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


		//更新表单数据
		public void updateTableData() {
			// TODO Auto-generated method stub
			//获取数据
			ReaderInfoBeansDAO dao = new ReaderInfoBeansDAO();
			List<ReaderInfoBeans> list = dao.getAllReader();
			//设置数据
			setTableData(list);
		}

		public JLabel getExeInquire() {
			// TODO Auto-generated method stub
			return this.exeInquire;
		}
		
		public JTextField getChooseInfo() {
			return this.chooseInfo;
		}
		
		public JComboBox<String> getChooseWay() {
			return this.chooseWay;
		}
		
		public MyTable getTableDataView(){
			return tableDataView;
		}
	
}
