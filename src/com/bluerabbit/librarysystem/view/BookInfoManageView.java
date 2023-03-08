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
 * ͼ��������
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
		super(mv,"ͼ����Ϣ����",true);
		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		bookView = new JPanel();
			buttonView = new JPanel();
				Badd = new JLabel("����");
				Bfix = new JLabel("�޸�");
				Bdel = new JLabel("ɾ��");
				Bclo = new JLabel("�ر�");
			InfoView = new JPanel();
				toolView = new JPanel();
					inqTipWay = new JLabel("ѡ��������ʽ");
					inqTipInfo = new JLabel("����ͼ����Ϣ����");
					chooseWay = new JComboBox<String>();
					chooseInfo = new JTextField();
					exeInquire = new JButton("����һ��");
					labPerch = new JLabel("       ");//ռλ��
					adSearch = new JButton("�߼�����");
				tableView = new JPanel();
					snpView = new JScrollPane();
						tableDataView = new MyTable();
	/**��ͼ�ṹ��
	 * bookView = new JPanel();
			buttonView = new JPanel();
				Badd = new JLabel("����");
				Bfix = new JLabel("�޸�");
				Bdel = new JLabel("ɾ��");
				Bclo = new JLabel("�ر�");
			InfoView = new JPanel();
				toolView = new JPanel();
					inqTipWay = new JLabel("ѡ��������ʽ");
					inqTipInfo = new JLabel("����ͼ����Ϣ����");
					chooseWay = new JComboBox();
					chooseInfo = new JTextField();
					exeInquire = new JLabel("����һ��");
					adSearch = new JButton("�߼�����");
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
		//�������û��������ڴ�С
		this.setResizable(false);
		//�߽粼��
		bookView.setLayout(new BorderLayout());
		//��ˮ����
		buttonView.setLayout(new FlowLayout(FlowLayout.LEFT));
		//�߽粼��
		InfoView.setLayout(new BorderLayout());
		//��ˮ����
		toolView.setLayout(new FlowLayout(FlowLayout.LEFT));
		//��񲼾�
		tableView.setLayout(new GridLayout(1,1));
		
		//��ť���Ĵ���
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
		
		//�������Ĵ���
		//��ʾtext
//		inqTipInfo.setBounds(0, 0, 180, 20);
//		//text
//		chooseInfo.setBounds(0, 30, 180, 20);
//		//��ʾcombobox
//		inqTipWay.setBounds(0, 60, 180, 20);
//		//combobox
//		chooseWay.setBounds(0, 90, 180, 20);
		chooseWay.addItem("�鿯���");
		chooseWay.addItem("�鿯����");
		chooseWay.addItem("�鿯����");
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
		//���ô�С
		labPerch.setPreferredSize(new Dimension(117,30));
		//�߿�
		//exeInquire.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		toolView.add(adSearch);
		
		//Ϊ������ť��Ӽ���
		exeInquire.addMouseListener(new BookInfoManageView_exeInquire_MouseListener(this));
		//setJLabelImage(exeInquire,"res/BookInfo/sousuo2.png",197,27);
		
		
		adSearch.addActionListener(new BookInfoManageView_adSearch_ActionListener(this));

		
		//���Ĵ���
		
		tableDataView.addMouseListener(new BookInfoManageView_tableView_MouseListener(this));
		
		//��ȡ����
		updateTableData();
		
		snpView.getViewport().add(tableDataView);
		tableView.add(snpView);
	
		bookView.add(buttonView, BorderLayout.NORTH);
		bookView.add(InfoView, BorderLayout.CENTER);
		
		InfoView.add(toolView, BorderLayout.NORTH);
		InfoView.add(snpView, BorderLayout.CENTER);
		
		//BookInfoManageView_Del_MouseListener bmv = new BookInfoManageView_Del_MouseListener(this);
		//�����¼�
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
		//1.���ñ���еı������ݼ���
		Vector<String> title = new Vector<String>();
		title.add("����");
		title.add("����");
		title.add("�鿯���");
		title.add("������");
		title.add("������");
		title.add("ʣ�����");
		title.add("��������");
		title.add("�������");
		title.add("ͼ����");
		
		//2.���ñ���е����ݼ���
		Vector<Vector> datas = new Vector<Vector>();
		Vector row = null;
		
		
		for(BookInfoBeans b : list) {
			row = new Vector();
			row.add(b.getBookName());//����
			row.add(b.getAuthor());//����
			row.add(b.getBookClassify());//����
			row.add(b.getPublisher());//������
			row.add(b.getPublishTime());//������
			row.add(b.getQuantity());//ʣ�����
			row.add(b.getStack());//��������
			row.add(b.getBookShelf());//�������
			row.add(b.getBookID());//ͼ����
			datas.add(row);
		}
		
 		//����tableModel
		this.dtmView = new DefaultTableModel(datas,title);
		//��tableModel����table��
		this.tableDataView.setModel(dtmView);
		
		//���ñ������Ӧ����
		tableDataView.FitTableColumns();
	}
	
	
	public void setJLabelImage(JLabel label,String imagePath,int w,int h){
		//ʵ����ImageIcon ����
		ImageIcon image = new ImageIcon(imagePath);
		//�õ�Image����
		Image img = image.getImage();
		//�������Ű汾
		img = img.getScaledInstance(w,h, Image.SCALE_DEFAULT);
		//�滻Ϊ���Ű汾
		image.setImage(img);
		//JLabel����ͼ��
		label.setIcon(image);
		//����Ĭ�ϱ߿�
		label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}
	
	
	//���������ͨ
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

	//���±�����
	public void updateTableData() {
		// TODO Auto-generated method stub
		//��ȡ����
		BookInfoDAO dao = new BookInfoDAO();
		List<BookInfoBeans> list = dao.getAllBookInfo();
		//��������
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


