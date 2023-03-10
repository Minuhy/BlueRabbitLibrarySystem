package com.bluerabbit.librarysystem.view.borrow;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
import com.bluerabbit.librarysystem.listener.borrow.BorrowOutMouseListener;
import com.bluerabbit.librarysystem.service.borrow.BorrowOutServer;
import com.bluerabbit.librarysystem.view.CenterView;
import com.bluerabbit.librarysystem.view.ComboJLAndJT;

import javax.swing.*;
import java.awt.*;

/**
 * ��������
 *
 * @author minuhy
 * @date 2023/3/8 22:32
 */
public class BorrowOutView extends JDialog {
    private final JPanel mainView;
    private final JPanel contentView;
    private final JPanel functionView;
    private final int windowsHeight;
    private final int windowsWidth;

    //�ѽ���ֳ��Ĳ��֣���������������������Ϣ��ͼ��������ͼ������б�
    private final JPanel jplReaderSearch;
    private final JPanel jplReaderInfo;
    private final JPanel jplBookSearch;
    private final JPanel bookInfo;
    private final JPanel jplBorrowSetting;

    // �������
    private final ComboJLAndJT cltBorrowDuration; // ���ʱ��
    private final ComboJLAndJT cltBorrowNumber; // �������
    JLabel jlbBorrowSetting; //�������


    // ����������
    JLabel jlbReader; // ��������
    JTextField jtfReaderKeyword; // ���������
    JButton btnSearchReader; // ������ť
    JButton btnNextReader; // ��һ�����
    JButton btnPrevReader; // ��һ�����

    // �����鼮����
    JLabel jlbBook; // �鼮����
    JTextField jtfBookKeyword; // ���������
    JButton btnSearchBook; // ������ť
    JButton btnNextBook; // ��һ�����
    JButton btnPrevBook; // ��һ�����

    //������Ϣ
    private final ComboJLAndJT cltReaderId; // ѧ    ��
    private final ComboJLAndJT cltReaderName; // ��    ��
    private final ComboJLAndJT cltReaderApart; // ѧ    Ժ
    private final ComboJLAndJT cltReaderClass; // ��    ��
    private final ComboJLAndJT cltReaderTel; // ��ϵ��ʽ
    private final JPanel jplSex;
    private final JComboBox<String> jcbSex;
    private final JLabel jlbSex;//�Ա�

    private final JButton btnBorrow;
    private final JButton btnCancel;

    public BorrowOutView bov;

    private final String[] select = {"", "��", "Ů"};//�Ա��

    //�鼮��Ϣ
    private final ComboJLAndJT cltBookName;//����
    private final ComboJLAndJT cltBookAuthor;//����
    private final ComboJLAndJT cltBookPublisher;//������
    private final ComboJLAndJT cltBookPublishDate;//��������
    private final ComboJLAndJT cltBookId;//�鿯���
    private final ComboJLAndJT cltBookBarcode;//�鿯����
    private final ComboJLAndJT cltBookStack;//����
    private final ComboJLAndJT cltBookShelf;//���
    private final ComboJLAndJT cltBookPrice;//�۸�
    private final ComboJLAndJT cltBookNumber;//ʣ�����


    BorrowOutServer server;

    public BorrowOutView(BorrowBookView bv) {
        super(bv, "ͼ�����", true);
        bov = this;
        //��ø���ͼ�Ĵ�С
        windowsHeight = bv.getHeight();
        windowsWidth = bv.getWidth();

        //���沿��
        mainView = new JPanel();
        functionView = new JPanel();
        contentView = new JPanel();

        //���ݲ���
        jplReaderSearch = new JPanel();
        jplReaderInfo = new JPanel();
        jplBookSearch = new JPanel();
        bookInfo = new JPanel();
        jplSex = new JPanel();
        jplBorrowSetting = new JPanel();

        //������Ϣ����
        jtfReaderKeyword = new JTextField(); // ���������
        jlbReader = new JLabel("��������"); // ���������
        btnSearchReader = new JButton("����"); // ������ť
        btnNextReader = new JButton("��һ��"); // ��һ�����
        btnPrevReader = new JButton("��һ��"); // ��һ�����

        //�鼮��Ϣ����
        jtfBookKeyword = new JTextField(); // ���������
        jlbBook = new JLabel("�����鼮"); // ���������
        btnSearchBook = new JButton("����"); // ������ť
        btnNextBook = new JButton("��һ��"); // ��һ�����
        btnPrevBook = new JButton("��һ��"); // ��һ�����

        //�������
        cltBorrowDuration = new ComboJLAndJT("���ʱ�����죩", 30, true);//ѧ��
        cltBorrowNumber = new ComboJLAndJT("�������������", 30, true);//����
        jlbBorrowSetting = new JLabel("�������");

        //������Ϣ 6
        cltReaderId = new ComboJLAndJT("ѧ    �ţ�", 30);//ѧ��
        cltReaderName = new ComboJLAndJT("��    ����", 30);//����
        cltReaderApart = new ComboJLAndJT("ѧ    Ժ��", 30);//ѧԺ
        cltReaderClass = new ComboJLAndJT("��    ����", 30);//�༶
        cltReaderTel = new ComboJLAndJT("��ϵ��ʽ��", 30);//��ϵ��ʽ
        jcbSex = new JComboBox<>();
        jcbSex.setEnabled(false);
        jlbSex = new JLabel("��    ��");


        //�鼮��Ϣ 6
        cltBookName = new ComboJLAndJT("��    ����");//����
        cltBookAuthor = new ComboJLAndJT("��    �ߣ�");//����
        cltBookPublisher = new ComboJLAndJT("�� �� �磺");//������
        cltBookPublishDate = new ComboJLAndJT("�������ڣ�");//��������

        cltBookId = new ComboJLAndJT("�鿯��ţ�");//�鿯���
        cltBookBarcode = new ComboJLAndJT("�鿯���룺");//�鿯����
        cltBookStack = new ComboJLAndJT("��    �ң�");//��    ��
        cltBookShelf = new ComboJLAndJT("��    �ܣ�");//��    ��
        cltBookPrice = new ComboJLAndJT("��    ��");//��    ��
        cltBookNumber = new ComboJLAndJT("ʣ�������");//ʣ�����


        btnBorrow = new JButton("���");
        btnCancel = new JButton("ȡ��");

        server = new BorrowOutServer(this);

        Init();
        resetData();
    }

    /**
     * �����鼮��Ϣ
     *
     * @param cp    ��ǰҳ��
     * @param p     ��ҳ��
     * @param beans �鼮��Ϣ
     */
    public void setBookInfo(int cp, int p, BookInfoBeans beans) {
        //�鼮��Ϣ 10
        cltBookName.setIText(beans.getBookName());//����
        cltBookAuthor.setIText(beans.getAuthor());//����
        cltBookPublisher.setIText(beans.getPublisher());//������
        cltBookPublishDate.setIText(beans.getPublishDate());//��������
        cltBookId.setIText(beans.getBookID());//�鿯���
        cltBookBarcode.setIText(beans.getBookBarcode());//�鿯����
        cltBookStack.setIText(beans.getStack());//��    ��
        cltBookShelf.setIText(beans.getBookShelf());//��    ��
        cltBookPrice.setIText(beans.getPrice() + ""); // �۸�
        cltBookNumber.setIText(beans.getQuantity() + ""); // ʣ�����

        jlbBook.setText("�鼮���������" + cp + "/" + p + "��");
    }

    /**
     * ���¶�����Ϣ
     *
     * @param cp    ��ǰҳ��
     * @param p     ��ҳ��
     * @param beans ������Ϣ
     */
    public void setReaderInfo(int cp, int p, ReaderInfoBeans beans) {
        //������Ϣ 6
        cltReaderId.setIText(beans.getReaderID());//ѧ��
        cltReaderName.setIText(beans.getReaderName());//����
        cltReaderApart.setIText(beans.getApart());//ѧԺ
        cltReaderClass.setIText(beans.getTheClass());//�༶

        cltReaderTel.setIText(beans.getTelNo());//��ϵ��ʽ

        if ("��".equals(beans.getSex())) {
            jcbSex.setSelectedIndex(1);
        } else if ("Ů".equals(beans.getSex())) {
            jcbSex.setSelectedIndex(2);
        }

        jlbReader.setText("�������������" + cp + "/" + p + "��");
    }

    /**
     * ��������
     */
    public void resetData() {
        // ��������

        //�鼮��Ϣ 6
        cltBookName.setIText("");//����
        cltBookAuthor.setIText("");//����
        cltBookPublisher.setIText("");//������
        cltBookPublishDate.setIText("");//��������
        cltBookId.setIText("");//�鿯���
        cltBookBarcode.setIText("");//�鿯����
        cltBookStack.setIText("");//��    ��
        cltBookShelf.setIText("");//��    ��
        cltBookNumber.setIText(""); // ʣ�����
        cltBookPrice.setIText(""); // �۸�
        jlbBook.setText("�����鼮");

        //������Ϣ 6
        cltReaderId.setIText("");//ѧ��
        cltReaderName.setIText("");//����
        cltReaderApart.setIText("");//ѧԺ
        cltReaderClass.setIText("");//�༶
        cltReaderTel.setIText("");//�༶

        jcbSex.setSelectedIndex(0);

        jlbReader.setText("��������");
    }

    private void Init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(windowsWidth - 200, windowsHeight - 56);
        CenterView.CenterByWindow(this);
        //�������û��������ڴ�С
        this.setResizable(false);

        //���ò���
        mainView.setLayout(new BorderLayout());
        contentView.setLayout(null);
        functionView.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //����������ͼ�߿�
        contentView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        //���ݲ���,h:wh-219,w:ww-130
        int unitH = (windowsHeight - 219 + 44) / 12;
        int unitW = (windowsWidth - 154 - 100);


        int heightOffset = 5;

        // ������Ϣ���������� -------------------------------------------------------------------------------------/
        int xNav = 5,y1=5,widthNav=unitW - 10,heightNav=40;
        jplReaderSearch.setLayout(null);
        jplReaderSearch.setBounds(xNav, y1, widthNav, heightNav);
        createSearchBar(unitW, jplReaderSearch, jlbReader, jtfReaderKeyword, btnSearchReader, btnPrevReader, btnNextReader);

        // ������Ϣ��� -------------------------------------------------------------------------------------/
        int xInfo = 10,y2=y1 + heightNav+heightOffset,widthInfo=unitW - 20,height2 = unitH * 2;
        jplReaderInfo.setLayout(new GridLayout(2, 3));
        jplReaderInfo.setBounds(xInfo, y2, widthInfo, height2);

        //������Ϣ 6
        jplReaderInfo.add(cltReaderId);//ѧ��
        jplReaderInfo.add(cltReaderName);//����
        jplReaderInfo.add(cltReaderApart);//ѧԺ
        jplReaderInfo.add(cltReaderClass);//�༶

        jplSex.setLayout(new FlowLayout());

        jplSex.add(jlbSex);
        jplSex.add(jcbSex);
        jplReaderInfo.add(jplSex);//�Ա�
        jcbSex.setModel(new DefaultComboBoxModel<>(select));
        jcbSex.setPreferredSize(new Dimension(187, 27));

        jplReaderInfo.add(cltReaderTel);//��ϵ��ʽ

        // �鼮��Ϣ������ -------------------------------------------------------------------------------------/
        int y3=y2+height2+heightOffset;
        jplBookSearch.setLayout(null);
        jplBookSearch.setBounds(xNav, y3, widthNav, heightNav);
        createSearchBar(unitW, jplBookSearch, jlbBook, jtfBookKeyword, btnSearchBook, btnPrevBook, btnNextBook);

        // �鼮��Ϣ��� -------------------------------------------------------------------------------------/
        int y4=y3+heightNav+heightOffset,height4 = unitH * 5;
        bookInfo.setLayout(new GridLayout(5, 2));
        bookInfo.setBounds(xInfo, y4, widthInfo,height4);

        //�鼮��Ϣ
        bookInfo.add(cltBookName);//����
        bookInfo.add(cltBookAuthor);//����
        bookInfo.add(cltBookPublisher);//������
        bookInfo.add(cltBookPublishDate);//��������
        bookInfo.add(cltBookId);//�鿯���
        bookInfo.add(cltBookBarcode);//�鿯����
        bookInfo.add(cltBookStack);//��    ��
        bookInfo.add(cltBookShelf);//��    ��
        bookInfo.add(cltBookPrice);//�۸�
        bookInfo.add(cltBookNumber);//�ܲ���

        // ������õ����� -------------------------------------------------------------------------------------/
        int y5=y4+height4+heightOffset,heightSetting = 63;
        jplBorrowSetting.setLayout(null);
        jplBorrowSetting.setBounds(xNav, y5, widthNav, heightSetting);
        jplBorrowSetting.setBorder(BorderFactory.createEtchedBorder());
        Color color = new Color(236, 233, 216);
        jplBorrowSetting.setBackground(color);
        //�������
        jlbBorrowSetting.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        jlbBorrowSetting.setBounds(30, 17, 160, 30);
        jplBorrowSetting.add(jlbBorrowSetting);//�������

        cltBorrowDuration.setBounds(unitW - 10 - 200 - 10 - 200 - 10, 1, 200, 60);
        cltBorrowDuration.setBackground(color);
        cltBorrowDuration.setIText("30");
        jplBorrowSetting.add(cltBorrowDuration);//���ʱ��

        cltBorrowNumber.setBounds(unitW - 10 - 200 - 10, 1, 200, 60);
        cltBorrowNumber.setBackground(color);
        cltBorrowNumber.setIText("1");
        jplBorrowSetting.add(cltBorrowNumber);//�������

        // -------------------------------------------------------------------------------------/
        //��Ӽ����¼�
        btnCancel.addActionListener(e -> bov.dispose());

        BorrowOutMouseListener listener = new BorrowOutMouseListener(this);
        btnBorrow.addActionListener(listener);
        btnNextBook.addActionListener(listener);
        btnNextReader.addActionListener(listener);
        btnPrevBook.addActionListener(listener);
        btnPrevReader.addActionListener(listener);
        btnSearchBook.addActionListener(listener);
        btnSearchReader.addActionListener(listener);

        //��Ӳ���
        contentView.add(jplReaderSearch);
        contentView.add(jplReaderInfo);
        contentView.add(jplBookSearch);
        contentView.add(bookInfo);
        contentView.add(jplBorrowSetting);
        functionView.add(btnBorrow);
        functionView.add(btnCancel);

        mainView.add(functionView, BorderLayout.SOUTH);
        mainView.add(contentView, BorderLayout.CENTER);
        this.add(mainView);
        this.setVisible(true);
    }

    /**
     * ����һ��������
     *
     * @param unitW      ���
     * @param jPanel     ���
     * @param jLabel     ��ʾ
     * @param jtfKeyword �ؼ���
     * @param btnSearch  ����
     * @param btnPrev    ��һҳ
     * @param btnNext    ��һҳ
     */
    private void createSearchBar(int unitW, JPanel jPanel, JLabel jLabel, JTextField jtfKeyword, JButton btnSearch, JButton btnPrev, JButton btnNext) {
        jPanel.setBorder(BorderFactory.createEtchedBorder());
        jPanel.setBackground(new Color(236, 233, 216));

        jLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        jLabel.setBounds(30, 5, 240, 30);
        jPanel.add(jLabel);

        jtfKeyword.setBounds(unitW - 10 - 60 - 10 - 60 - 10 - 60 - 10 - 200 - 10, 5, 200, 30);
        jPanel.add(jtfKeyword);

        btnSearch.setBounds(unitW - 10 - 60 - 10 - 60 - 10 - 60 - 10, 5, 60, 30);
        jPanel.add(btnSearch);

        btnPrev.setBounds(unitW - 10 - 60 - 10 - 60 - 10, 5, 60, 30);
        jPanel.add(btnPrev);
        btnNext.setBounds(unitW - 10 - 60 - 10, 5, 60, 30);
        jPanel.add(btnNext);
    }

    public JButton getBtnSearchReader() {
        return btnSearchReader;
    }

    public JButton getBtnNextReader() {
        return btnNextReader;
    }

    public JButton getBtnPrevReader() {
        return btnPrevReader;
    }

    public JButton getBtnSearchBook() {
        return btnSearchBook;
    }

    public JButton getBtnNextBook() {
        return btnNextBook;
    }

    public JButton getBtnPrevBook() {
        return btnPrevBook;
    }

    public JButton getBtnBorrow() {
        return btnBorrow;
    }

    public ComboJLAndJT getCltBorrowDuration() {
        return cltBorrowDuration;
    }

    public ComboJLAndJT getCltBorrowNumber() {
        return cltBorrowNumber;
    }

    public JTextField getJtfReaderKeyword() {
        return jtfReaderKeyword;
    }

    public JTextField getJtfBookKeyword() {
        return jtfBookKeyword;
    }

    public BorrowOutServer getServer() {
        return server;
    }

    public ComboJLAndJT getCltReaderId() {
        return cltReaderId;
    }

    public ComboJLAndJT getCltBookId() {
        return cltBookId;
    }

    public ComboJLAndJT getCltBookNumber() {
        return cltBookNumber;
    }



}
