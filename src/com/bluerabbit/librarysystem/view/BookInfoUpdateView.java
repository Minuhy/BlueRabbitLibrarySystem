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
 * ͼ�������ҳ��
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

	//�ѽ���ֳ������֣��ݲ���Ϣ���鼮��Ϣ��������Ϣ
	private JPanel collecInfo;
	private JPanel bookInfo;
	private JPanel otherInfo;

	//�ݲ���Ϣ
	private ComboJLAndJT StackL;//��������
	private ComboJLAndJT BookShelfL;//�������
	private ComboJLAndJT BookClassifyL;//�鿯���
	private ComboJLAndJT BookTypeL;//�ݲ����

	//�鼮��Ϣ
	private ComboJLAndJT BookNameL;//����
	private ComboJLAndJT AuthorL;//����
	private ComboJLAndJT PublisherL;//������
	private ComboJLAndJT PublishTimeL;//������
	private ComboJLAndJT BookThemL;//�����
	private ComboJLAndJT PublishDateL;//��������
	private ComboJLAndJT BookIDL;//�鿯���
	private ComboJLAndJT BookBarcodeL;//�鿯����

	//������Ϣ
	private ComboJLAndJT PriceL;//�۸�
	private ComboJLAndJT SumQuantityL;//�ܲ���
	private ComboJLAndJT ContentTextL;//���
	private ComboJLAndJT RemarkL;//��ע
	private ComboJLAndJT BookPageL;//ҳ��
	private ComboJLAndJT WordsNumberL;//����

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
		//��ø���ͼ�Ĵ�С
		WindowsHeight = wv.getHeight();
		windowsWidth = wv.getWidth();

		//���沿��
		mianView = new JPanel();
		functionView = new JPanel();
		contentView = new JPanel();

		//���ݲ���
		collecInfo = new JPanel();
		bookInfo = new JPanel();
		otherInfo = new JPanel();

		//�ݲ���Ϣ
		StackL = new ComboJLAndJT("�������ң�",editor);//��������
		BookShelfL = new ComboJLAndJT("������ܣ�",editor);//�������
		BookClassifyL = new ComboJLAndJT("�鿯���",editor);//�鿯���
		BookTypeL = new ComboJLAndJT("�ݲ����",editor);//�ݲ����

		//�鼮��Ϣ
		BookNameL = new ComboJLAndJT("��    ����",editor);//����
		AuthorL = new ComboJLAndJT("��    �ߣ�",editor);//����
		PublisherL = new ComboJLAndJT("�� �� �磺",editor);//������
		PublishTimeL = new ComboJLAndJT("�����Σ�",editor);//������
		BookThemL = new ComboJLAndJT("�� �� �ʣ�",editor);//�����

		PublishDateL = new ComboJLAndJT("�������ڣ�",editor);//��������

		BookIDL = new ComboJLAndJT("�鿯��ţ�",editor);//�鿯���
		BookBarcodeL = new ComboJLAndJT("�鿯���룺",editor);//�鿯����

		//������Ϣ
		PriceL = new ComboJLAndJT("��    ��",editor);//�۸�
		SumQuantityL = new ComboJLAndJT("�� �� ����",editor);//�ܲ���
		ContentTextL = new ComboJLAndJT("��    �飺",editor);//���
		RemarkL = new ComboJLAndJT("��    ע��",editor);//��ע
		BookPageL = new ComboJLAndJT("ҳ    ����",editor);//ҳ��
		WordsNumberL = new ComboJLAndJT("��    ����",editor);//����


		ok = new JButton("ȷ��");
		cancel = new JButton("ȡ��");

		if(bookID == null){
			bookID = "";
		}
		if(!bookID.equals("")){
			//��Ϊ�գ����޸���Ϣ�������������ʼ����Ϣ
			System.out.println("����ͼ����Ϣ��");
			loader(bookID);
			//�޸�ģʽ
			mode = false;
			
			ok = new JButton("����");
			BookIDL.getGettxt().setEditable(false);
		}

		Init();
	}

	private void loader(String bookID) {
		// TODO Auto-generated method stub
		BookInfoBeans bookInfo = null;
		//��ȡͼ����Ϣ
		bookInfo = new BookInfoDAO().getABookInfoByBookID(bookID);

		//����ͼ����Ϣ
		//�ݲ���Ϣ
		StackL.setIText(bookInfo.getStack());//��������
		BookShelfL.setIText(bookInfo.getBookShelf());//�������
		BookClassifyL.setIText(bookInfo.getBookClassify());//�鿯���
		BookTypeL.setIText(bookInfo.getBookType());//�ݲ����

		//�鼮��Ϣ
		BookNameL.setIText(bookInfo.getBookName());//����
		AuthorL.setIText(bookInfo.getAuthor());//����
		PublisherL.setIText(bookInfo.getPublisher());//������
		PublishTimeL.setIText(bookInfo.getPublishTime());//������
		BookThemL.setIText(bookInfo.getBookThem());//�����
		PublishDateL.setIText(bookInfo.getPublishDate());//��������
		BookIDL.setIText(bookInfo.getBookID());//�鿯���
		BookBarcodeL.setIText(bookInfo.getBookBarcode());//�鿯����

		//������Ϣ
		PriceL.setIText(bookInfo.getPrice() + "");//�۸�
		SumQuantityL.setIText(bookInfo.getSumQuantity() + "");//�ܲ���
		ContentTextL.setIText(bookInfo.getContentText());//���
		RemarkL.setIText(bookInfo.getRemark());//��ע
		BookPageL.setIText(bookInfo.getBookPage() + "");//ҳ��
		WordsNumberL.setIText(bookInfo.getWordsNumber());//����

		//������Ϣ
		Quantity = bookInfo.getQuantity();
		LendTime = bookInfo.getLendTime();
	}

	private void Init() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-100, WindowsHeight-100);
		CenterView.CenterByWindow(this);
		//�������û��������ڴ�С
		this.setResizable(false);

		//���ò���
		mianView.setLayout(new BorderLayout());
		contentView.setLayout(null);
		functionView.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//����������ͼ�߿�
		contentView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

		//���ݲ���,h:wh-219,w:ww-130
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


		//�ݲ���Ϣ
		collecInfo.add(StackL);//��������
		collecInfo.add(BookShelfL);//�������
		collecInfo.add(BookClassifyL);//�鿯���
		collecInfo.add(BookTypeL);//�ݲ����


		//�鼮��Ϣ
		bookInfo.add(BookNameL);//����
		bookInfo.add(AuthorL);//����
		bookInfo.add(PublisherL);//������
		bookInfo.add(PublishTimeL);//������
		bookInfo.add(BookThemL);//�����

		bookInfo.add(PublishDateL);//��������
		//Ĭ����ʾһ������
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		PublishDateL.setIText(df.format(new Date()));
		//��ֹ�༭
		PublishDateL.getGettxt().setEditable(false);
		PublishDateL.getGettxt().addMouseListener(new BookInfoUpdateView_DateChoose_MouseListener(this,PublishDateL.getGettxt()));


		bookInfo.add(BookIDL);//�鿯���
		bookInfo.add(BookBarcodeL);//�鿯����


		//������Ϣ
		otherInfo.add(PriceL);//�۸�
		otherInfo.add(SumQuantityL);//�ܲ���
		otherInfo.add(ContentTextL);//���
		otherInfo.add(RemarkL);//��ע
		otherInfo.add(BookPageL);//ҳ��
		otherInfo.add(WordsNumberL);//����


		//��Ӽ����¼�
		cancel.addActionListener(new BookInfoUpdate_cancel_ActionListener(this));
		ok.addActionListener(new BookInfoUpdate_ok_ActionListener(this,wv));

		//��Ӳ���
		contentView.add(collecInfo);
		contentView.add(bookInfo);
		contentView.add(otherInfo);

		//������Ա༭
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
