package com.bluerabbit.librarysystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.dao.BookInfoDAO;
import com.bluerabbit.librarysystem.listener.BookInfoUpdateView_DateChoose_MouseListener;
import com.bluerabbit.librarysystem.listener.BookInfoUpdate_cancel_ActionListener;
import com.bluerabbit.librarysystem.listener.BookInfoUpdate_ok_ActionListener;
/**
 * 图书详情表单页面
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class BookInfoUpdateView extends JDialog {
	private boolean mode;//t:add.f:fix
	private int Quantity;
	private int LendTime;
	private JPanel mianView;
	private JPanel contentView;
	private JPanel functionView;
	private int WindowsHeight;
	private int windowsWidth;

	//把界面分成三部分，馆藏信息、书籍信息、其他信息
	private JPanel collecInfo;
	private JPanel bookInfo;
	private JPanel otherInfo;

	//馆藏信息
	private ComboJLAndJT StackL;//所在书室
	private ComboJLAndJT BookShelfL;//所在书架
	private ComboJLAndJT BookClassifyL;//书刊类别
	private ComboJLAndJT BookTypeL;//馆藏类别

	//书籍信息
	private ComboJLAndJT BookNameL;//书名
	private ComboJLAndJT AuthorL;//作者
	private ComboJLAndJT PublisherL;//出版社
	private ComboJLAndJT PublishTimeL;//出版版次
	private ComboJLAndJT BookThemL;//主题词
	private ComboJLAndJT PublishDateL;//出版日期
	private ComboJLAndJT BookIDL;//书刊编号
	private ComboJLAndJT BookBarcodeL;//书刊条码

	//其他信息
	private ComboJLAndJT PriceL;//价格
	private ComboJLAndJT SumQuantityL;//总册数
	private ComboJLAndJT ContentTextL;//简介
	private ComboJLAndJT RemarkL;//备注
	private ComboJLAndJT BookPageL;//页数
	private ComboJLAndJT WordsNumberL;//字数

	private JButton ok;
	private JButton cancel;

	private BookInfoManageView wv;
	boolean editor;
	
	public boolean isEditor() {
		return this.editor;
	}

	public BookInfoUpdateView(BookInfoManageView wv,String title,String bookID,boolean editor){
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

		//内容部分
		collecInfo = new JPanel();
		bookInfo = new JPanel();
		otherInfo = new JPanel();

		//馆藏信息
		StackL = new ComboJLAndJT("所在书室：",editor);//所在书室
		BookShelfL = new ComboJLAndJT("所在书架：",editor);//所在书架
		BookClassifyL = new ComboJLAndJT("书刊类别：",editor);//书刊类别
		BookTypeL = new ComboJLAndJT("馆藏类别：",editor);//馆藏类别

		//书籍信息
		BookNameL = new ComboJLAndJT("书    名：",editor);//书名
		AuthorL = new ComboJLAndJT("作    者：",editor);//作者
		PublisherL = new ComboJLAndJT("出 版 社：",editor);//出版社
		PublishTimeL = new ComboJLAndJT("出版版次：",editor);//出版版次
		BookThemL = new ComboJLAndJT("主 题 词：",editor);//主题词

		PublishDateL = new ComboJLAndJT("出版日期：",editor);//出版日期

		BookIDL = new ComboJLAndJT("书刊编号：",editor);//书刊编号
		BookBarcodeL = new ComboJLAndJT("书刊条码：",editor);//书刊条码

		//其他信息
		PriceL = new ComboJLAndJT("价    格：",editor);//价格
		SumQuantityL = new ComboJLAndJT("总 册 数：",editor);//总册数
		ContentTextL = new ComboJLAndJT("简    介：",editor);//简介
		RemarkL = new ComboJLAndJT("备    注：",editor);//备注
		BookPageL = new ComboJLAndJT("页    数：",editor);//页数
		WordsNumberL = new ComboJLAndJT("字    数：",editor);//字数


		ok = new JButton("确定");
		cancel = new JButton("取消");

		if(bookID == null){
			bookID = "";
		}
		if(!bookID.equals("")){
			//不为空，是修改信息，在这里载入初始化信息
			System.out.println("加载图书信息！");
			loader(bookID);
			//修改模式
			mode = false;
			
			ok = new JButton("更新");
			BookIDL.getGettxt().setEditable(false);
		}

		Init();
	}

	private void loader(String bookID) {
		// TODO Auto-generated method stub
		BookInfoBeans bookInfo = null;
		//获取图书信息
		bookInfo = new BookInfoDAO().getABookInfoByBookID(bookID);

		//设置图书信息
		//馆藏信息
		StackL.setIText(bookInfo.getStack());//所在书室
		BookShelfL.setIText(bookInfo.getBookShelf());//所在书架
		BookClassifyL.setIText(bookInfo.getBookClassify());//书刊类别
		BookTypeL.setIText(bookInfo.getBookType());//馆藏类别

		//书籍信息
		BookNameL.setIText(bookInfo.getBookName());//书名
		AuthorL.setIText(bookInfo.getAuthor());//作者
		PublisherL.setIText(bookInfo.getPublisher());//出版社
		PublishTimeL.setIText(bookInfo.getPublishTime());//出版版次
		BookThemL.setIText(bookInfo.getBookThem());//主题词
		PublishDateL.setIText(bookInfo.getPublishDate());//出版日期
		BookIDL.setIText(bookInfo.getBookID());//书刊编号
		BookBarcodeL.setIText(bookInfo.getBookBarcode());//书刊条码

		//其他信息
		PriceL.setIText(bookInfo.getPrice() + "");//价格
		SumQuantityL.setIText(bookInfo.getSumQuantity() + "");//总册数
		ContentTextL.setIText(bookInfo.getContentText());//简介
		RemarkL.setIText(bookInfo.getRemark());//备注
		BookPageL.setIText(bookInfo.getBookPage() + "");//页数
		WordsNumberL.setIText(bookInfo.getWordsNumber());//字数

		//借书信息
		Quantity = bookInfo.getQuantity();
		LendTime = bookInfo.getLendTime();
	}

	private void Init() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-100, WindowsHeight-100);
		CenterView.CenterByWindow(this);
		//不允许用户调整窗口大小
		this.setResizable(false);

		//设置布局
		mianView.setLayout(new BorderLayout());
		contentView.setLayout(null);
		functionView.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//配置内容视图边框
		contentView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

		//内容部分,h:wh-219,w:ww-130
		int unitH = (WindowsHeight - 219)/9;
		int unitW = (windowsWidth - 154);
		//4
		collecInfo.setLayout(new GridLayout(2,2));
		collecInfo.setBounds(0, 0, unitW, unitH*2);
		collecInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		//8
		bookInfo.setLayout(new GridLayout(4,2));
		bookInfo.setBounds(0, unitH*2, unitW, unitH*4);
		bookInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		//6
		otherInfo.setLayout(new GridLayout(3,2));
		otherInfo.setBounds(0, unitH*6, unitW, unitH*3-5);


		//馆藏信息
		collecInfo.add(StackL);//所在书室
		collecInfo.add(BookShelfL);//所在书架
		collecInfo.add(BookClassifyL);//书刊类别
		collecInfo.add(BookTypeL);//馆藏类别


		//书籍信息
		bookInfo.add(BookNameL);//书名
		bookInfo.add(AuthorL);//作者
		bookInfo.add(PublisherL);//出版社
		bookInfo.add(PublishTimeL);//出版版次
		bookInfo.add(BookThemL);//主题词

		bookInfo.add(PublishDateL);//出版日期
		//默认显示一个日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		PublishDateL.setIText(df.format(new Date()));
		//禁止编辑
		PublishDateL.getGettxt().setEditable(false);
		PublishDateL.getGettxt().addMouseListener(new BookInfoUpdateView_DateChoose_MouseListener(this,PublishDateL.getGettxt()));


		bookInfo.add(BookIDL);//书刊编号
		bookInfo.add(BookBarcodeL);//书刊条码


		//其他信息
		otherInfo.add(PriceL);//价格
		otherInfo.add(SumQuantityL);//总册数
		otherInfo.add(ContentTextL);//简介
		otherInfo.add(RemarkL);//备注
		otherInfo.add(BookPageL);//页数
		otherInfo.add(WordsNumberL);//字数


		//添加监听事件
		cancel.addActionListener(new BookInfoUpdate_cancel_ActionListener(this));
		ok.addActionListener(new BookInfoUpdate_ok_ActionListener(this,wv));

		//添加布局
		contentView.add(collecInfo);
		contentView.add(bookInfo);
		contentView.add(otherInfo);

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

	public ComboJLAndJT getStackL() {
		return StackL;
	}

	public ComboJLAndJT getBookShelfL() {
		return BookShelfL;
	}

	public ComboJLAndJT getBookClassifyL() {
		return BookClassifyL;
	}

	public ComboJLAndJT getBookTypeL() {
		return BookTypeL;
	}

	public ComboJLAndJT getBookNameL() {
		return BookNameL;
	}

	public ComboJLAndJT getAuthorL() {
		return AuthorL;
	}

	public ComboJLAndJT getPublisherL() {
		return PublisherL;
	}

	public ComboJLAndJT getPublishTimeL() {
		return PublishTimeL;
	}

	public ComboJLAndJT getBookThemL() {
		return BookThemL;
	}

	public ComboJLAndJT getPublishDateL() {
		return PublishDateL;
	}

	public ComboJLAndJT getBookIDL() {
		return BookIDL;
	}

	public ComboJLAndJT getBookBarcodeL() {
		return BookBarcodeL;
	}

	public ComboJLAndJT getPriceL() {
		return PriceL;
	}

	public ComboJLAndJT getSumQuantityL() {
		return SumQuantityL;
	}

	public ComboJLAndJT getContentTextL() {
		return ContentTextL;
	}

	public ComboJLAndJT getRemarkL() {
		return RemarkL;
	}

	public ComboJLAndJT getBookPageL() {
		return BookPageL;
	}

	public ComboJLAndJT getWordsNumberL() {
		return WordsNumberL;
	}

	public int getQuantity() {
		return Quantity;
	}

	public int getLendTime() {
		return LendTime;
	}

	public boolean isMode() {
		return mode;
	}
}
