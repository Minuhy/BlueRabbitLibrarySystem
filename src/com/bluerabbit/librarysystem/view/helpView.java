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
 * @author minuy
 *
 */
public class helpView extends JDialog {
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
	public helpView(loginView lv){
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
		txa.setText("CREATE DATABASE library_system;\r\nuse library_system;\r\ncreate table Administrator(\r\nAdminID int not null,\r\nAdminName char(10) not null,\r\nAccount varchar(6) not null primary key,\r\nPassword varchar(32) not null,\r\nifsuper boolean not null\r\n)default charset=utf8;\r\nALTER TABLE library_system.Administrator ADD UNIQUE (Account);  \r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(0,'\u7BA1\u7406\u5458','admin','d41d8cd98f00b204e9800998ecf8427e',true);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(1,'\u5C0F\u732B','10000','b7a782741f667201b54880c925faec4b',true);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(2,'\u5C0F\u72D7','10001','d89f3a35931c386956c1a402a8e09941',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(3,'\u5C0F\u5154','10002','9103c8c82514f39d8360c7430c4ee557',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(4,'\u5C0F\u9F20','10003','f5dffc111454b227fbcdf36178dfe6ac',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(5,'\u5C0F\u53EF','10004','d783823cc6284b929c2cd8df2167d212',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(6,'\u7B11\u7B11','10005','6eb887126d24e8f1cd8ad5033482c781',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(17,'\u7433\u7433','10017','6eb887126d24e8f1cd8ad5033482c781',false);\r\ninsert into Administrator (AdminID,AdminName,Account, Password,ifsuper) values(27,'\u5C0F\u9C7C','10027','6eb887126d24e8f1cd8ad5033482c781',false);\r\n\r\nuse library_system;\r\ncreate table Books_Info(\r\nBookName varchar(30) not null comment '\u4E66\u540D' ,\r\nSumQuantity int not null comment '\u603B\u518C\u6570' ,\r\nQuantity int not null comment '\u5269\u4F59\u518C\u6570' ,\r\nLendTime int not null comment '\u501F\u51FA\u6B21\u6570',\r\nBookID varchar(30) not null primary key comment '\u4E66\u520A\u7F16\u53F7' ,\r\nBookBarcode varchar(30) not null comment '\u4E66\u520A\u6761\u7801',\r\nBookClassify varchar(20) comment '\u4E66\u520A\u5206\u7C7B',\r\nBookThem varchar(30) comment '\u4E3B\u9898\u8BCD',\r\nAuthor varchar(40) comment '\u4F5C\u8005' ,\r\nPublisher varchar(20) comment '\u51FA\u7248\u793E' ,\r\nPublishTime varchar(10) comment '\u51FA\u7248\u7248\u6B21',\r\nPublishDate date comment '\u51FA\u7248\u65E5\u671F' ,\r\nBookType varchar(10) comment '\u9986\u85CF\u5206\u7C7B',\r\nStack varchar(20) not null comment '\u6240\u5728\u4E66\u5BA4' ,\r\nBookShelf varchar(20) not null comment '\u6240\u5728\u4E66\u67B6' ,\r\nPrice float comment '\u4EF7\u683C' ,\r\nContentText varchar(300) comment '\u7B80\u4ECB',\r\nRemark varchar(100) comment '\u5907\u6CE8' ,\r\nBookPage int comment '\u9875\u6570' ,\r\nWordsNumber varchar(20) comment '\u5B57\u6570'\r\n)default charset=utf8 comment '\u56FE\u4E66\u4FE1\u606F\u8868'; \r\n\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('*\u5C4F\u853D\u7684\u5173\u952E\u5B57*\u601D\u60F3\u548C\u4E2D\u56FD\u7279\u8272\u793E\u4F1A\u4E3B\u4E49\u7406\u8BBA\u4F53\u7CFB\u6982\u8BBA', '5', '2', '5', '00001', '9787040494815', '\u601D\u60F3\u3001\u7406\u8BBA\u3001\u5386\u53F2', '*\u5C4F\u853D\u7684\u5173\u952E\u5B57*\u3001\u4E2D\u56FD\u7279\u8272\u793E\u4F1A\u4E3B\u4E49', '\u9AD8\u82F1', '\u9AD8\u7B49\u6559\u80B2\u51FA\u7248\u793E', '\u7B2C6\u7248', '2018-09-01', '\u56FE\u4E66', '\u7B2C\u4E00\u4E66\u5BA4', '\u7B2C\u4E00\u4E66\u67B6', '25.00', '\u4E2D\u56FD\u7279\u8272\u793E\u4F1A\u4E3B\u4E49\u7684\u5386\u53F2', '\u5927\u4E8C\u6559\u79D1\u4E66',313,'260\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u610F\u67972018\u5E74\u5408\u8BA2\u672C', '90', '54', '66', '00002', '9771007384158', '\u6742\u5FD7\u3001\u4F5C\u6587\u3001\u9752\u6625\u6587\u5B66', '\u4F5C\u6587\uFF0C\u5C0F\u5C0F\u59D0', '\u9AD8\u82F1', '\u610F\u6797\u6742\u5FD7\u793E', '\u7B2C1\u7248', '2019-01-01', '\u671F\u520A', '\u7B2C\u4E09\u4E66\u5BA4', '\u7B2C\u4E8C\u4E66\u67B6', '29.8', '\u6709\u52A9\u4E8E\u4E2D\u5B66\u751F\u4F5C\u6587\u63D0\u9AD8\uFF0C\u89E3\u51B3\u4E2D\u5B66\u751F\u70E6\u607C\u7684\u671F\u520A', '\u671F\u520A\u5408\u8BA2\u672C',744,'540\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('*\u5C4F\u853D\u7684\u5173\u952E\u5B57*\u601D\u60F3\u548C\u4E2D\u56FD\u7279\u8272\u793E\u4F1A\u4E3B\u4E49\u7406\u8BBA\u4F53\u7CFB\u6982\u8BBA', '5', '2', '5', '00071', '9787040494815', '\u601D\u60F3\u3001\u7406\u8BBA\u3001\u5386\u53F2', '*\u5C4F\u853D\u7684\u5173\u952E\u5B57*\u3001\u4E2D\u56FD\u7279\u8272\u793E\u4F1A\u4E3B\u4E49', '\u9AD8\u82F1', '\u9AD8\u7B49\u6559\u80B2\u51FA\u7248\u793E', '\u7B2C6\u7248', '2018-09-01', '\u56FE\u4E66', '\u7B2C\u4E00\u4E66\u5BA4', '\u7B2C\u4E00\u4E66\u67B6', '25.00', '\u4E2D\u56FD\u7279\u8272\u793E\u4F1A\u4E3B\u4E49\u7684\u5386\u53F2', '\u5927\u4E8C\u6559\u79D1\u4E66',313,'260\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u610F\u67972018\u5E74\u5408\u8BA2\u672C', '90', '54', '66', '00702', '9771007384158', '\u6742\u5FD7\u3001\u4F5C\u6587\u3001\u9752\u6625\u6587\u5B66', '\u4F5C\u6587\uFF0C\u5C0F\u5C0F\u59D0', '\u9AD8\u82F1', '\u610F\u6797\u6742\u5FD7\u793E', '\u7B2C1\u7248', '2019-01-01', '\u671F\u520A', '\u7B2C\u4E09\u4E66\u5BA4', '\u7B2C\u4E8C\u4E66\u67B6', '29.80', '\u6709\u52A9\u4E8E\u4E2D\u5B66\u751F\u4F5C\u6587\u63D0\u9AD8\uFF0C\u89E3\u51B3\u4E2D\u5B66\u751F\u70E6\u607C\u7684\u671F\u520A', '\u671F\u520A\u5408\u8BA2\u672C',744,'540\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u65B0\u7F16\u5211\u6CD5\u7F6A\u540D\u9002\u7528\u6307\u5357', '8', '7', '12', '00003', '9771557384236', '\u6CD5\u5F8B', '\u5211\u6CD5 \u7F6A\u540D \u6CD5\u5F8B', '\u718A\u9009\u56FD', '\u4EBA\u6C11*\u5C4F\u853D\u7684\u5173\u952E\u5B57*\u51FA\u7248', '\u7B2C1\u7248', '2017-05-01', '\u671F\u520A', '\u7B2C\u4E09\u4E66\u5BA4', '\u7B2C\u4E8C\u4E66\u67B6', '9.80', '\u6CD5\u5F8B\u5211\u6CD5\u7684\u4E86\u89E3', '\u671F\u520A\u5408\u8BA2\u672C',264,'54\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('c++\u7A0B\u5E8F\u8BBE\u8BA1(\u7279\u522B\u7248)', '4', '3', '12', '00004', '9559338384236', '\u81EA\u52A8\u5316\u6280\u672F\u3001\u8BA1\u7B97\u673A\u6280\u672F', ' c++ \u7A0B\u5E8F\u8BBE\u8BA1 \u8F6F\u4EF6\u5DE5\u7A0B', 'Special', '\u4EBA\u6C11\u4EA4\u901A\u51FA\u7248\u793E', '\u7B2C2\u7248', '2018-09-03', '\u56FE\u4E66', '\u7B2C\u56DB\u4E66\u5BA4', '\u7B2C\u4E00\u4E66\u67B6', '34.60', 'C++\u8BED\u8A00\u7684\u4E86\u89E3\u4E0E\u638C\u63E1', '\u5927\u4E00\u6559\u79D1\u4E66',504,'344\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u82F1\u8BED\u4E13\u4E1A\u56DB\u7EA7\u5907\u8003', '15', '3', '26', '00005', '9534457984236', '\u7F8E\u6D32\u8BF8\u8BED\u8A00', '\u56DB\u7EA7 \u82F1\u8BED', '\u76DB\u8427', '\u5730\u8D28\u51FA\u7248\u793E', '\u7B2C5\u7248', '2019-02-23', '\u56FE\u4E66', '\u7B2C\u56DB\u4E66\u5BA4', '\u7B2C\u4E09\u4E66\u67B6', '26.00', '\u5907\u8003\u56DB\u7EA7', '\u5927\u5B66\u82F1\u8BED\u56DB\u7EA7\u8003\u8BD5\u63D0\u70BC',198,'194\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u5916\u56FD\u6CD5\u5F8B\u53F2\u7814\u7A76', '10', '7', '9', '00006', '7715534569036', '\u6CD5\u5F8B', '\u5211\u6CD5 \u7F6A\u540D \u6CD5\u5F8B', '\u4F55\u52E4\u534E', '\u4E2D\u56FD\u653F\u6CD5\u5927\u5B66\u51FA\u7248\u793E', '\u7B2C3\u7248', '2018-09-01', '\u56FE\u4E66', '\u7B2C\u4E09\u4E66\u5BA4', '\u7B2C\u4E8C\u4E66\u67B6', '30.80', '\u6CD5\u5F8B\u5211\u6CD5\u7684\u4E86\u89E3', '\u6CD5\u5F8B\u5211\u6CD5\u7684\u4E86\u89E3',334,'670\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u7F16\u7A0B\u9ED1\u9A6C\u771F\u8A00', '13', '9', '8', '00007', '4563552169036', '\u81EA\u52A8\u5316\u6280\u672F\u3001\u8BA1\u7B97\u673A\u6280\u672F', '\u8F6F\u4EF6\u5DE5\u5177\u3001\u5DE5\u5177\u8F6F\u4EF6', '\u738B\u8F76\u7537', '\u4EBA\u6C11\u4EA4\u901A\u51FA\u7248\u793E', '\u7B2C1\u7248', '2018-04-24', '\u56FE\u4E66', '\u7B2C\u56DB\u4E66\u5BA4', '\u7B2C\u4E8C\u4E66\u67B6', '35.00', '\u8BA1\u7B97\u673A\u8BED\u8A00\u7684\u53D1\u5C55\u5386\u7A0B', '\u7F16\u7A0B\u8BED\u8A00\u7684\u62D3\u5C55',264,'145\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u9ED8\u8BFB', '13', '0', '25', '00008', '2453466799036', '\u5C0F\u8BF4', '\u5211\u4FA6 \u72AF\u7F6A', 'priest', '\u4EBA\u6C11\u4EA4\u901A\u51FA\u7248\u793E', '\u7B2C1\u7248', '2018-5-16', '\u56FE\u4E66', '\u7B2C\u4E94\u4E66\u5BA4', '\u7B2C\u4E09\u4E66\u67B6', '36.80', '\u6551\u8D4E\uFF0C\u70E7\u8111\uFF0C\u7834\u6848', '\u63D0\u5347\u63A8\u7406\u80FD\u529B',564,'445\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('*\u5C4F\u853D\u7684\u5173\u952E\u5B57*\u7406\u8BBA\u6982\u8FF0', '9', '2', '7', '00009', '4652377494815', '\u601D\u60F3\u3001\u7406\u8BBA\u3001\u5386\u53F2', '*\u5C4F\u853D\u7684\u5173\u952E\u5B57*\u3001*\u5C4F\u853D\u7684\u5173\u952E\u5B57*\u7406\u8BBA\u3001\u4E2D\u56FD\u7279\u8272\u793E\u4F1A\u4E3B\u4E49', '\u6559\u80B2\u90E8\u793E\u4F1A\u79D1\u5B66\u7814\u7A76\u6240', '\u9AD8\u7B49\u6559\u80B2\u51FA\u7248\u793E', '\u7B2C7\u7248', '2019-04-11', '\u56FE\u4E66', '\u7B2C\u4E00\u4E66\u5BA4', '\u7B2C\u4E00\u4E66\u67B6', '32.00', '\u4E2D\u56FD\u7279\u8272\u793E\u4F1A\u4E3B\u4E49\u7684\u5386\u53F2\u3001*\u5C4F\u853D\u7684\u5173\u952E\u5B57*\u7406\u8BBA\u7684\u53D1\u5C55\u5386\u7A0B', '*\u5C4F\u853D\u7684\u5173\u952E\u5B57*\u7406\u8BBA',453,'540\u5343\u5B57');\r\nINSERT INTO `library_system`.`Books_Info` \r\n(`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES \r\n('\u6D6E\u751F\u7269\u8BED', '17', '3', '27', '000010', '9367566799036', '\u5C0F\u8BF4', '\u67B6\u7A7A\u3001\u7384\u5E7B', '\u88DF\u6924\u53CC\u6811', '\u4E0A\u6D77\u4EA4\u901A\u51FA\u7248\u793E', '\u7B2C5\u7248', '2018-8-16', '\u56FE\u4E66', '\u7B2C\u4E94\u4E66\u5BA4', '\u7B2C\u56DB\u4E66\u67B6', '29.99', '\u8F7B\u5FEB\u5E7D\u9ED8\u7684\u5996\u602A\u5386\u9669\u8BB0', '\u611F\u52A8\u3001\u5E7B\u60F3\u3001\u63D0\u5347\u751F\u6D3B\u4E50\u8DA3',487,'393\u5343\u5B57');\r\n\r\ncreate table Reader(\r\nReaderID varchar(20) not null primary key comment '\u5B66\u53F7' ,\r\nReaderName char(30) not null comment '\u59D3\u540D' ,\r\nApart char(10) comment '\u5B66\u9662' ,\r\nSex char(2) default '\u7537' check (Sex in ('\u7537','\u5973')),\r\nClass char(10) comment '\u73ED\u7EA7' ,\r\nTelNo char(20) comment '\u7535\u8BDD' \r\n)default charset=utf8;\r\ninsert into Reader values(5056,'\u7530\u7530','\u4FE1\u606F\u5B66\u9662','\u7537','6233','00000011');\r\ninsert into Reader values(5156,'\u591A\u591A','\u4F53\u80B2\u5B66\u9662','\u7537','6345','00000012');\r\ninsert into Reader values(5256,'\u7CD6\u7CD6','\u5916\u56FD\u8BED\u5B66\u9662','\u5973','6453','00000013');\r\ninsert into Reader values(5356,'\u60A0\u60A0','\u97F3\u4E50\u821E\u8E48\u5B66\u9662','\u7537','6571','00000014');\r\ninsert into Reader values(5456,'\u4E1C\u4E1C','\u7F8E\u672F\u4E0E\u8BBE\u8BA1\u5B66\u9662','\u5973','6655','00000015');\r\ninsert into Reader values(5556,'\u65FA\u65FA','\u4FE1\u606F\u5B66\u9662','\u5973','6259','00000016');\r\ninsert into Reader values(5656,'\u9752\u9752','\u6CD5\u5B66\u9662','\u7537','6782','00000017');\r\ninsert into Reader values(5756,'\u83F2\u83F2','\u6559\u80B2\u5B66\u9662','\u5973','6890','00000018');\r\ninsert into Reader values(5856,'\u5409\u5409','\u6587\u5B66\u9662','\u5973','6934','00000019');\r\ninsert into Reader values(5956,'\u6668\u6668','\u5546\u5B66\u9662','\u7537','6075','00000020');\r\ninsert into Reader values(5966,'\u8D1D\u8D1D','\u6570\u5B66\u4E0E\u91D1\u878D\u9662','\u7537','6137','00000021');\r\ninsert into Reader values(5976,'\u4E50\u4E50','\u9A6C\u514B\u601D\u4E3B\u4E49\u5B66\u9662','\u5973','6953','00000022');\r\ninsert into Reader values(5986,'\u76FC\u76FC','\u4F53\u80B2\u5B66\u9662','\u7537','6370','00000023');");
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
