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
 * ͼ�����ĸ߼�����ҳ��
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

	//�ݲ���Ϣ
	private ComboJLAndJT StackL;//��������
	private ComboJLAndJT BookShelfL;//�������
	private ComboJLAndJT BookClassifyL;//�鿯���
	private ComboJLAndJT BookTypeL;//�ݲ����

	//�鼮��Ϣ
	private ComboJLAndJT BookNameL;//����
	private ComboJLAndJT AuthorL;//����
	private ComboJLAndJT PublisherL;//������
	private ComboJLAndJT BookThemL;//�����
	private ComboJLAndJT BookIDL;//�鿯���
	private ComboJLAndJT BookBarcodeL;//�鿯����

	private JButton ok;
	private JButton cancel;

	private BookInfoManageView wv;
	boolean editor;

	public AdvancedSearchView(BookInfoManageView wv){
		super(wv,"�߼���ѯ",true);
		this.wv = wv;
		//��ø���ͼ�Ĵ�С
		WindowsHeight = wv.getHeight();
		windowsWidth = wv.getWidth();

		//���沿��
		mianView = new JPanel();
		functionView = new JPanel();
		contentView = new JPanel(new GridLayout(5,2));

		editor = true;
		//�ݲ���Ϣ
		StackL = new ComboJLAndJT("�������ң�",editor);//��������
		BookShelfL = new ComboJLAndJT("������ܣ�",editor);//�������
		BookClassifyL = new ComboJLAndJT("�鿯���",editor);//�鿯���
		BookTypeL = new ComboJLAndJT("�ݲ����",editor);//�ݲ����

		//�鼮��Ϣ
		BookNameL = new ComboJLAndJT("��    ����",editor);//����
		AuthorL = new ComboJLAndJT("��    �ߣ�",editor);//����
		PublisherL = new ComboJLAndJT("�� �� �磺",editor);//������
		BookThemL = new ComboJLAndJT("�� �� �ʣ�",editor);//�����

		BookIDL = new ComboJLAndJT("�鿯��ţ�",editor);//�鿯���
		BookBarcodeL = new ComboJLAndJT("�鿯���룺",editor);//�鿯����

		ok = new JButton("ȷ��");
		cancel = new JButton("ȡ��");
		Init();
	}

	private void Init() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(windowsWidth-100, WindowsHeight-200);
		CenterView.CenterByWindow(this);
		//�������û��������ڴ�С
		this.setResizable(false);

		//���ò���
		mianView.setLayout(new BorderLayout());
		functionView.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//����������ͼ�߿�
		//functionView.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		contentView.setBorder(BorderFactory.createTitledBorder("��������"));

		//�ݲ���Ϣ
		contentView.add(StackL);//��������
		contentView.add(BookShelfL);//�������
		contentView.add(BookClassifyL);//�鿯���
		contentView.add(BookTypeL);//�ݲ����


		//�鼮��Ϣ
		contentView.add(BookNameL);//����
		contentView.add(AuthorL);//����
		contentView.add(PublisherL);//������
		contentView.add(BookThemL);//�����


		contentView.add(BookIDL);//�鿯���
		contentView.add(BookBarcodeL);//�鿯����


		//��Ӽ����¼�
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
