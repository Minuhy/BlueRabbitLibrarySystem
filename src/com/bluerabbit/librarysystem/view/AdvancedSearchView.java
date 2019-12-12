package com.bluerabbit.librarysystem.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.bluerabbit.librarysystem.listener.AdvancedSearchView_cancel_ActionListener;
import com.bluerabbit.librarysystem.listener.AdvancedSearchView_ok_ActionListener;
/**
 * 图书管理的高级搜索页面
 * @author minuy
 *
 */
@SuppressWarnings("serial")
public class AdvancedSearchView extends JDialog {
	private JPanel mianView;
	private JPanel contentView;
	private JPanel functionView;
	private int WindowsHeight;
	private int windowsWidth;

	//馆藏信息
	private ComboJLAndJT StackL;//所在书室
	private ComboJLAndJT BookShelfL;//所在书架
	private ComboJLAndJT BookClassifyL;//书刊类别
	private ComboJLAndJT BookTypeL;//馆藏类别

	//书籍信息
	private ComboJLAndJT BookNameL;//书名
	private ComboJLAndJT AuthorL;//作者
	private ComboJLAndJT PublisherL;//出版社
	private ComboJLAndJT BookThemL;//主题词
	private ComboJLAndJT BookIDL;//书刊编号
	private ComboJLAndJT BookBarcodeL;//书刊条码

	private JButton ok;
	private JButton cancel;

	private BookInfoManageView wv;
	boolean editor;

	public AdvancedSearchView(BookInfoManageView wv){
		super(wv,"高级查询",true);
		this.wv = wv;
		//获得父视图的大小
		WindowsHeight = wv.getHeight();
		windowsWidth = wv.getWidth();

		//界面部分
		mianView = new JPanel();
		functionView = new JPanel();
		contentView = new JPanel(new GridLayout(5,2));

		editor = true;
		//馆藏信息
		StackL = new ComboJLAndJT("所在书室：",editor);//所在书室
		BookShelfL = new ComboJLAndJT("所在书架：",editor);//所在书架
		BookClassifyL = new ComboJLAndJT("书刊类别：",editor);//书刊类别
		BookTypeL = new ComboJLAndJT("馆藏类别：",editor);//馆藏类别

		//书籍信息
		BookNameL = new ComboJLAndJT("书    名：",editor);//书名
		AuthorL = new ComboJLAndJT("作    者：",editor);//作者
		PublisherL = new ComboJLAndJT("出 版 社：",editor);//出版社
		BookThemL = new ComboJLAndJT("主 题 词：",editor);//主题词

		BookIDL = new ComboJLAndJT("书刊编号：",editor);//书刊编号
		BookBarcodeL = new ComboJLAndJT("书刊条码：",editor);//书刊条码

		ok = new JButton("确定");
		cancel = new JButton("取消");
		Init();
	}

	private void Init() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-100, WindowsHeight-200);
		CenterView.CenterByWindow(this);
		//不允许用户调整窗口大小
		this.setResizable(false);

		//设置布局
		mianView.setLayout(new BorderLayout());
		functionView.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//配置内容视图边框
		//functionView.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		contentView.setBorder(BorderFactory.createTitledBorder("搜索条件"));

		//馆藏信息
		contentView.add(StackL);//所在书室
		contentView.add(BookShelfL);//所在书架
		contentView.add(BookClassifyL);//书刊类别
		contentView.add(BookTypeL);//馆藏类别


		//书籍信息
		contentView.add(BookNameL);//书名
		contentView.add(AuthorL);//作者
		contentView.add(PublisherL);//出版社
		contentView.add(BookThemL);//主题词


		contentView.add(BookIDL);//书刊编号
		contentView.add(BookBarcodeL);//书刊条码


		//添加监听事件
		cancel.addActionListener(new AdvancedSearchView_cancel_ActionListener(this));
		ok.addActionListener(new AdvancedSearchView_ok_ActionListener(this,wv));


		functionView.add(ok);
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

	public ComboJLAndJT getBookThemL() {
		return BookThemL;
	}


	public ComboJLAndJT getBookIDL() {
		return BookIDL;
	}

	public ComboJLAndJT getBookBarcodeL() {
		return BookBarcodeL;
	}

}
