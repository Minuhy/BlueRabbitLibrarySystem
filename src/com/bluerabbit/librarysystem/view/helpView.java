package com.bluerabbit.librarysystem.view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * 帮助界面
 * @author minuhy
 *
 */
public class HelpView extends JDialog {
	private static final long serialVersionUID = 1L;
	private boolean reflshTime = true;
	private JPanel helpWin;
	private JLabel labExplain;
	private JTextField txfAbout;
	private JTextField txfThank;
	private JScrollPane slpSql;
	private JScrollPane slpmain;
	private JLabel labDate;
	private JTextArea sql;
	public HelpView(loginView lv){
		//设置父窗口标题并且禁止操作父视图
		super(lv,"帮助",true);
		helpWin = new JPanel();
		labExplain = new JLabel("admin\u521d\u59cb\u5bc6\u7801\u4e3a\u7a7a\u0021\u0020");
		txfAbout = new JTextField();
		txfThank = new JTextField();
		sql = new JTextArea();
		slpSql = new JScrollPane();
		slpmain = new JScrollPane();
		labDate = new JLabel();

		Init();
	}

	private void Init(){
		//启用窗口关闭
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//设置窗口大小
		this.setSize(400, 400);
		//设置窗口居中
		CenterView.CenterByWindow(this);
		//设置容器布局方式为空布局
		helpWin.setLayout(null);

		labExplain.setBounds(0, 0, 310, 50);
		labExplain.setBorder(BorderFactory.createTitledBorder("\u8bf4\u660e"));
		
		labDate.setBounds(0, 1650, 310, 50);
		labDate.setBorder(BorderFactory.createTitledBorder("Time"));
		txfThank.setBounds(0, 1550, 310, 50);
		txfThank.setEditable(false);
		txfThank.setBorder(BorderFactory.createTitledBorder("\u611f\u8c22"));
		txfAbout.setBounds(0, 1600, 310, 50);
		txfAbout.setEditable(false);
		txfAbout.setBorder(BorderFactory.createTitledBorder("\u5236\u4f5c\u7ec4"));
		slpSql.setBounds(0, 80, 310, 1000);
		slpSql.setBorder(BorderFactory.createTitledBorder("MySQL\u8bed\u53e5\uff1a\uff08\u670d\u52a1\u5668\uff1alocalhost\uff0c\u5bc6\u7801\uff1atsglxt2019\uff09"));

		txfThank.setText("\u611f\u8c22\u8679\u732b\u4fe1\u606f\u8001\u5e08\u7684\u6307\u5bfc\u4e0e\u5e2e\u52a9\u3002");
		txfAbout.setText("\u9879\u76ee\u7ec4\u7ec4\u5458\uff1a\u4e8e\u654f\u54f2\u3001\u66fe\u7433\u7433\u3001\u738b\u666f\u5ead\u3001\u90b9\u4e3d\u654f\u3001\u8096\u96e8\u6668");
		setSql(sql);

		
		helpWin.add(labDate);
		helpWin.add(labExplain);
		helpWin.add(slpSql);
		helpWin.add(txfThank);
		helpWin.add(txfAbout);
		slpSql.getViewport().add(sql);

		labExplain.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println(labExplain.getText());
				System.out.println(txfAbout.getText());
				System.out.println(sql.getText());
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		//设置主视图的大小
		helpWin.setPreferredSize(new Dimension(300,1700));


		//添加滑动条
		slpmain.setViewportView(helpWin);
		slpmain.setBounds(0, 0, 350, 400);
		
		this.getContentPane().add(slpmain);
		showTime();
		this.setVisible(true);
		
		reflshTime = false;
	}

	private void setSql(JTextArea txa){
		txa.setText("CREATE DATABASE library_system;\r\nuse library_system;\r\ncreate table Administrator(\r\nAdminID int not null,\r\nAdminName char(10) not null,\r\nAccount varchar(6) not null primary key,\r\nPassword varchar(32) not null,\r\nifsuper boolean not null\r\n)default charset=utf8;\r\nALTER TABLE library_system.Administrator ADD UNIQUE (Account);  \r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(0,'\u7ba1\u7406\u5458','admin','d41d8cd98f00b204e9800998ecf8427e',true);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(1,'\u5c0f\u732b','10000','b7a782741f667201b54880c925faec4b',true);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(2,'\u5c0f\u72d7','10001','d89f3a35931c386956c1a402a8e09941',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(3,'\u5c0f\u5154','10002','9103c8c82514f39d8360c7430c4ee557',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(4,'\u5c0f\u9f20','10003','f5dffc111454b227fbcdf36178dfe6ac',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(5,'\u5c0f\u53ef','10004','d783823cc6284b929c2cd8df2167d212',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(6,'\u7b11\u7b11','10005','6eb887126d24e8f1cd8ad5033482c781',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(17,'\u7433\u7433','10017','6eb887126d24e8f1cd8ad5033482c781',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(27,'\u5c0f\u9c7c','10027','6eb887126d24e8f1cd8ad5033482c781',false);\r\n\r\nuse library_system;\r\ncreate table Books_Info(\r\nBookName varchar(30) not null comment '\u4e66\u540d' ,\r\nSumQuantity int not null comment '\u603b\u518c\u6570' ,\r\nQuantity int not null comment '\u5269\u4f59\u518c\u6570' ,\r\nLendTime int not null comment '\u501f\u51fa\u6b21\u6570',\r\nBookID varchar(30) not null primary key comment '\u4e66\u520a\u7f16\u53f7' ,\r\nBookBarcode varchar(30) not null comment '\u4e66\u520a\u6761\u7801',\r\nBookClassify varchar(20) comment '\u4e66\u520a\u5206\u7c7b',\r\nBookThem varchar(30) comment '\u4e3b\u9898\u8bcd',\r\nAuthor varchar(40) comment '\u4f5c\u8005' ,\r\nPublisher varchar(20) comment '\u51fa\u7248\u793e' ,\r\nPublishTime varchar(10) comment '\u51fa\u7248\u7248\u6b21',\r\nPublishDate date comment '\u51fa\u7248\u65e5\u671f' ,\r\nBookType varchar(10) comment '\u9986\u85cf\u5206\u7c7b',\r\nStack varchar(20) not null comment '\u6240\u5728\u4e66\u5ba4' ,\r\nBookShelf varchar(20) not null comment '\u6240\u5728\u4e66\u67b6' ,\r\nPrice float comment '\u4ef7\u683c' ,\r\nContentText varchar(300) comment '\u7b80\u4ecb',\r\nRemark varchar(100) comment '\u5907\u6ce8' ,\r\nBookPage int comment '\u9875\u6570' ,\r\nWordsNumber varchar(20) comment '\u5b57\u6570'\r\n)default charset=utf8 comment '\u56fe\u4e66\u4fe1\u606f\u8868'; \r\n\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u6bdb\u6cfd\u4e1c\u601d\u60f3\u548c\u4e2d\u56fd\u7279\u8272\u793e\u4f1a\u4e3b\u4e49\u7406\u8bba\u4f53\u7cfb\u6982\u8bba', '5', '2', '5', '00001', '9787040494815', '\u601d\u60f3\u3001\u7406\u8bba\u3001\u5386\u53f2', '\u6bdb\u6cfd\u4e1c\u3001\u4e2d\u56fd\u7279\u8272\u793e\u4f1a\u4e3b\u4e49', '\u9ad8\u82f1', '\u9ad8\u7b49\u6559\u80b2\u51fa\u7248\u793e', '\u7b2c6\u7248', '2018-09-01', '\u56fe\u4e66', '\u7b2c\u4e00\u4e66\u5ba4', '\u7b2c\u4e00\u4e66\u67b6', '25.00', '\u4e2d\u56fd\u7279\u8272\u793e\u4f1a\u4e3b\u4e49\u7684\u5386\u53f2', '\u5927\u4e8c\u6559\u79d1\u4e66',313,'260\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u610f\u67972018\u5e74\u5408\u8ba2\u672c', '90', '54', '66', '00002', '9771007384158', '\u6742\u5fd7\u3001\u4f5c\u6587\u3001\u9752\u6625\u6587\u5b66', '\u4f5c\u6587\uff0c\u5c0f\u5c0f\u59d0', '\u9ad8\u82f1', '\u610f\u6797\u6742\u5fd7\u793e', '\u7b2c1\u7248', '2019-01-01', '\u671f\u520a', '\u7b2c\u4e09\u4e66\u5ba4', '\u7b2c\u4e8c\u4e66\u67b6', '29.8', '\u6709\u52a9\u4e8e\u4e2d\u5b66\u751f\u4f5c\u6587\u63d0\u9ad8\uff0c\u89e3\u51b3\u4e2d\u5b66\u751f\u70e6\u607c\u7684\u671f\u520a', '\u671f\u520a\u5408\u8ba2\u672c',744,'540\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u6bdb\u6cfd\u4e1c\u601d\u60f3\u548c\u4e2d\u56fd\u7279\u8272\u793e\u4f1a\u4e3b\u4e49\u7406\u8bba\u4f53\u7cfb\u6982\u8bba', '5', '2', '5', '00071', '9787040494815', '\u601d\u60f3\u3001\u7406\u8bba\u3001\u5386\u53f2', '\u6bdb\u6cfd\u4e1c\u3001\u4e2d\u56fd\u7279\u8272\u793e\u4f1a\u4e3b\u4e49', '\u9ad8\u82f1', '\u9ad8\u7b49\u6559\u80b2\u51fa\u7248\u793e', '\u7b2c6\u7248', '2018-09-01', '\u56fe\u4e66', '\u7b2c\u4e00\u4e66\u5ba4', '\u7b2c\u4e00\u4e66\u67b6', '25.00', '\u4e2d\u56fd\u7279\u8272\u793e\u4f1a\u4e3b\u4e49\u7684\u5386\u53f2', '\u5927\u4e8c\u6559\u79d1\u4e66',313,'260\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u610f\u67972018\u5e74\u5408\u8ba2\u672c', '90', '54', '66', '00702', '9771007384158', '\u6742\u5fd7\u3001\u4f5c\u6587\u3001\u9752\u6625\u6587\u5b66', '\u4f5c\u6587\uff0c\u5c0f\u5c0f\u59d0', '\u9ad8\u82f1', '\u610f\u6797\u6742\u5fd7\u793e', '\u7b2c1\u7248', '2019-01-01', '\u671f\u520a', '\u7b2c\u4e09\u4e66\u5ba4', '\u7b2c\u4e8c\u4e66\u67b6', '29.80', '\u6709\u52a9\u4e8e\u4e2d\u5b66\u751f\u4f5c\u6587\u63d0\u9ad8\uff0c\u89e3\u51b3\u4e2d\u5b66\u751f\u70e6\u607c\u7684\u671f\u520a', '\u671f\u520a\u5408\u8ba2\u672c',744,'540\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u65b0\u7f16\u5211\u6cd5\u7f6a\u540d\u9002\u7528\u6307\u5357', '8', '7', '12', '00003', '9771557384236', '\u6cd5\u5f8b', '\u5211\u6cd5 \u7f6a\u540d \u6cd5\u5f8b', '\u718a\u9009\u56fd', '\u4eba\u6c11\u6cd5\u9662\u51fa\u7248', '\u7b2c1\u7248', '2017-05-01', '\u671f\u520a', '\u7b2c\u4e09\u4e66\u5ba4', '\u7b2c\u4e8c\u4e66\u67b6', '9.80', '\u6cd5\u5f8b\u5211\u6cd5\u7684\u4e86\u89e3', '\u671f\u520a\u5408\u8ba2\u672c',264,'54\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('c++\u7a0b\u5e8f\u8bbe\u8ba1(\u7279\u522b\u7248)', '4', '3', '12', '00004', '9559338384236', '\u81ea\u52a8\u5316\u6280\u672f\u3001\u8ba1\u7b97\u673a\u6280\u672f', ' c++ \u7a0b\u5e8f\u8bbe\u8ba1 \u8f6f\u4ef6\u5de5\u7a0b', 'Special', '\u4eba\u6c11\u4ea4\u901a\u51fa\u7248\u793e', '\u7b2c2\u7248', '2018-09-03', '\u56fe\u4e66', '\u7b2c\u56db\u4e66\u5ba4', '\u7b2c\u4e00\u4e66\u67b6', '34.60', 'C++\u8bed\u8a00\u7684\u4e86\u89e3\u4e0e\u638c\u63e1', '\u5927\u4e00\u6559\u79d1\u4e66',504,'344\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u82f1\u8bed\u4e13\u4e1a\u56db\u7ea7\u5907\u8003', '15', '3', '26', '00005', '9534457984236', '\u7f8e\u6d32\u8bf8\u8bed\u8a00', '\u56db\u7ea7 \u82f1\u8bed', '\u76db\u8427', '\u5730\u8d28\u51fa\u7248\u793e', '\u7b2c5\u7248', '2019-02-23', '\u56fe\u4e66', '\u7b2c\u56db\u4e66\u5ba4', '\u7b2c\u4e09\u4e66\u67b6', '26.00', '\u5907\u8003\u56db\u7ea7', '\u5927\u5b66\u82f1\u8bed\u56db\u7ea7\u8003\u8bd5\u63d0\u70bc',198,'194\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u5916\u56fd\u6cd5\u5f8b\u53f2\u7814\u7a76', '10', '7', '9', '00006', '7715534569036', '\u6cd5\u5f8b', '\u5211\u6cd5 \u7f6a\u540d \u6cd5\u5f8b', '\u4f55\u52e4\u534e', '\u4e2d\u56fd\u653f\u6cd5\u5927\u5b66\u51fa\u7248\u793e', '\u7b2c3\u7248', '2018-09-01', '\u56fe\u4e66', '\u7b2c\u4e09\u4e66\u5ba4', '\u7b2c\u4e8c\u4e66\u67b6', '30.80', '\u6cd5\u5f8b\u5211\u6cd5\u7684\u4e86\u89e3', '\u6cd5\u5f8b\u5211\u6cd5\u7684\u4e86\u89e3',334,'670\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u7f16\u7a0b\u9ed1\u9a6c\u771f\u8a00', '13', '9', '8', '00007', '4563552169036', '\u81ea\u52a8\u5316\u6280\u672f\u3001\u8ba1\u7b97\u673a\u6280\u672f', '\u8f6f\u4ef6\u5de5\u5177\u3001\u5de5\u5177\u8f6f\u4ef6', '\u738b\u8f76\u7537', '\u4eba\u6c11\u4ea4\u901a\u51fa\u7248\u793e', '\u7b2c1\u7248', '2018-04-24', '\u56fe\u4e66', '\u7b2c\u56db\u4e66\u5ba4', '\u7b2c\u4e8c\u4e66\u67b6', '35.00', '\u8ba1\u7b97\u673a\u8bed\u8a00\u7684\u53d1\u5c55\u5386\u7a0b', '\u7f16\u7a0b\u8bed\u8a00\u7684\u62d3\u5c55',264,'145\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u9ed8\u8bfb', '13', '0', '25', '00008', '2453466799036', '\u5c0f\u8bf4', '\u5211\u4fa6 \u72af\u7f6a', 'priest', '\u4eba\u6c11\u4ea4\u901a\u51fa\u7248\u793e', '\u7b2c1\u7248', '2018-5-16', '\u56fe\u4e66', '\u7b2c\u4e94\u4e66\u5ba4', '\u7b2c\u4e09\u4e66\u67b6', '36.80', '\u6551\u8d4e\uff0c\u70e7\u8111\uff0c\u7834\u6848', '\u63d0\u5347\u63a8\u7406\u80fd\u529b',564,'445\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u6bdb\u6cfd\u4e1c\u7406\u8bba\u6982\u8ff0', '9', '2', '7', '00009', '4652377494815', '\u601d\u60f3\u3001\u7406\u8bba\u3001\u5386\u53f2', '\u6bdb\u6cfd\u4e1c\u3001\u6bdb\u6cfd\u4e1c\u7406\u8bba\u3001\u4e2d\u56fd\u7279\u8272\u793e\u4f1a\u4e3b\u4e49', '\u6559\u80b2\u90e8\u793e\u4f1a\u79d1\u5b66\u7814\u7a76\u6240', '\u9ad8\u7b49\u6559\u80b2\u51fa\u7248\u793e', '\u7b2c7\u7248', '2019-04-11', '\u56fe\u4e66', '\u7b2c\u4e00\u4e66\u5ba4', '\u7b2c\u4e00\u4e66\u67b6', '32.00', '\u4e2d\u56fd\u7279\u8272\u793e\u4f1a\u4e3b\u4e49\u7684\u5386\u53f2\u3001\u6bdb\u6cfd\u4e1c\u7406\u8bba\u7684\u53d1\u5c55\u5386\u7a0b', '\u6bdb\u6cfd\u4e1c\u7406\u8bba',453,'540\u5343\u5b57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u6d6e\u751f\u7269\u8bed', '17', '3', '27', '000010', '9367566799036', '\u5c0f\u8bf4', '\u67b6\u7a7a\u3001\u7384\u5e7b', '\u88df\u6924\u53cc\u6811', '\u4e0a\u6d77\u4ea4\u901a\u51fa\u7248\u793e', '\u7b2c5\u7248', '2018-8-16', '\u56fe\u4e66', '\u7b2c\u4e94\u4e66\u5ba4', '\u7b2c\u56db\u4e66\u67b6', '29.99', '\u8f7b\u5feb\u5e7d\u9ed8\u7684\u5996\u602a\u5386\u9669\u8bb0', '\u611f\u52a8\u3001\u5e7b\u60f3\u3001\u63d0\u5347\u751f\u6d3b\u4e50\u8da3',487,'393\u5343\u5b57');\r\n\r\ncreate table Reader(\r\nReaderID varchar(20) not null primary key comment '\u5b66\u53f7' ,\r\nReaderName char(30) not null comment '\u59d3\u540d' ,\r\nApart char(10) comment '\u5b66\u9662' ,\r\nSex char(2) default '\u7537' check (Sex in ('\u7537','\u5973')),\r\nClass char(10) comment '\u73ed\u7ea7' ,\r\nTelNo char(20) comment '\u7535\u8bdd' \r\n)default charset=utf8;\r\ninsert into Reader values(5056,'\u7530\u7530','\u4fe1\u606f\u5b66\u9662','\u7537','6233','00000011');\r\ninsert into Reader values(5156,'\u591a\u591a','\u4f53\u80b2\u5b66\u9662','\u7537','6345','00000012');\r\ninsert into Reader values(5256,'\u7cd6\u7cd6','\u5916\u56fd\u8bed\u5b66\u9662','\u5973','6453','00000013');\r\ninsert into Reader values(5356,'\u60a0\u60a0','\u97f3\u4e50\u821e\u8e48\u5b66\u9662','\u7537','6571','00000014');\r\ninsert into Reader values(5456,'\u4e1c\u4e1c','\u7f8e\u672f\u4e0e\u8bbe\u8ba1\u5b66\u9662','\u5973','6655','00000015');\r\ninsert into Reader values(5556,'\u65fa\u65fa','\u4fe1\u606f\u5b66\u9662','\u5973','6259','00000016');\r\ninsert into Reader values(5656,'\u9752\u9752','\u6cd5\u5b66\u9662','\u7537','6782','00000017');\r\ninsert into Reader values(5756,'\u83f2\u83f2','\u6559\u80b2\u5b66\u9662','\u5973','6890','00000018');\r\ninsert into Reader values(5856,'\u5409\u5409','\u6587\u5b66\u9662','\u5973','6934','00000019');\r\ninsert into Reader values(5956,'\u6668\u6668','\u5546\u5b66\u9662','\u7537','6075','00000020');\r\ninsert into Reader values(5966,'\u8d1d\u8d1d','\u6570\u5b66\u4e0e\u91d1\u878d\u9662','\u7537','6137','00000021');\r\ninsert into Reader values(5976,'\u4e50\u4e50','\u9a6c\u514b\u601d\u4e3b\u4e49\u5b66\u9662','\u5973','6953','00000022');\r\ninsert into Reader values(5986,'\u76fc\u76fc','\u4f53\u80b2\u5b66\u9662','\u7537','6370','00000023');");
	}
	
	private String getDatePoor(Date endDate, Date nowDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟" + sec +"秒";
	}
	
	private void showTime(){
		new Thread() {
			@Override
			public void run() {
				while(reflshTime){
				try {
					System.out.println("7");
					sleep(1000);
					Date now = new Date();
					Date old = new Date(1559265465000L);
					labDate.setText("2019-05-31 09.17.45：距今已过"+ getDatePoor(now,old) );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				}
		}.start();
		
	}

}
