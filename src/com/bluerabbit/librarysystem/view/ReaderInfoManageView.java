package com.bluerabbit.librarysystem.view;
/**
 * ������Ϣ������棬�����������
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
 * ������Ϣ�������
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
		super(mv,"������Ϣ����",true);
		WindowsHeight = mv.getHeight();
		windowsWidth = mv.getWidth();
		ReaderView = new JPanel();
		buttonView = new JPanel();
			Badd = new JLabel("����",JLabel.CENTER);
			Bfix = new JLabel("�޸�",JLabel.CENTER);
			Bdel = new JLabel("ɾ��",JLabel.CENTER);
			Bclo = new JLabel("�ر�",JLabel.CENTER);
		InfoView = new JPanel();
			toolView = new JPanel();
				inqTipWay = new JLabel("ѡ��������ʽ");
				inqTipInfo = new JLabel("����ؼ���");
				chooseWay = new JComboBox<String>();
				chooseInfo = new JTextField();
				exeInquire = new JLabel("����һ��",JLabel.CENTER);
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
		//�������û��������ڴ�С
		this.setResizable(false);

		//�߽粼��
		ReaderView.setLayout(new BorderLayout());
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
		
		//�������Ĵ���
		//��ʾtext
//		inqTipInfo.setBounds(0, 0, 180, 20);
//		//text
//		chooseInfo.setBounds(0, 30, 180, 20);
//		//��ʾcombobox
//		inqTipWay.setBounds(0, 60, 180, 20);
//		//combobox
//		chooseWay.setBounds(0, 90, 180, 20);
		chooseWay.addItem("ѧ��");
		chooseWay.addItem("����");
		chooseWay.addItem("�༶");
		chooseWay.addItem("ѧԺ");
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
		//���ñ߿�
		exeInquire.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
//		���ô�С
		exeInquire.setPreferredSize(new Dimension(80,30));
		
		//����������
		ReaderInfoManageView_MouseListener listener = new ReaderInfoManageView_MouseListener(this);
		
		//Ϊ������ť��Ӽ���
		exeInquire.addMouseListener(listener);

		
		//���Ĵ���
		
		tableDataView.addMouseListener(listener);
		
		//��ȡ����
		updateTableData();
		
		
		snpView.getViewport().add(tableDataView);
		tableView.add(snpView);
	
		ReaderView.add(buttonView, BorderLayout.NORTH);
		ReaderView.add(InfoView, BorderLayout.CENTER);
		
		InfoView.add(toolView, BorderLayout.NORTH);
		InfoView.add(snpView, BorderLayout.CENTER);
		
		//BookInfoManageView_Del_MouseListener bmv = new BookInfoManageView_Del_MouseListener(this);
		//�����¼�
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
			//1.���ñ���еı������ݼ���
			Vector<String> title = new Vector<String>();
			title.add("ѧ��");
			title.add("����");
			title.add("�Ա�");
			title.add("ѧԺ");
			title.add("�༶");
			title.add("��ϵ��ʽ");
			
			//2.���ñ���е����ݼ���
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
			
	 		//����tableModel
			this.dtmView = new DefaultTableModel(datas,title);
			//��tableModel����table��
			this.tableDataView.setModel(dtmView);
			
			//���ñ������Ӧ����
			//tableDataView.FitTableColumns();
		}
		
		
//		public void setJLabelImage(JLabel label,String imagePath,int w,int h){
//			//ʵ����ImageIcon ����
//			ImageIcon image = new ImageIcon(imagePath);
//			//�õ�Image����
//			Image img = image.getImage();
//			//�������Ű汾
//			img = img.getScaledInstance(w,h, Image.SCALE_DEFAULT);
//			//�滻Ϊ���Ű汾
//			image.setImage(img);
//			//JLabel����ͼ��
//			label.setIcon(image);
//			//����Ĭ�ϱ߿�
//			label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
//		}
		
		
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


		//���±�����
		public void updateTableData() {
			// TODO Auto-generated method stub
			//��ȡ����
			ReaderInfoBeansDAO dao = new ReaderInfoBeansDAO();
			List<ReaderInfoBeans> list = dao.getAllReader();
			//��������
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
