package com.bluerabbit.librarysystem.view.borrow;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
import com.bluerabbit.librarysystem.listener.borrow.BorrowInMouseListener;
import com.bluerabbit.librarysystem.service.borrow.BorrowInServer;
import com.bluerabbit.librarysystem.view.CenterView;
import com.bluerabbit.librarysystem.view.ComboJLAndJT;
import com.bluerabbit.librarysystem.view.MyTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author minuhy
 * @date 2023/3/9 21:06
 */
public class BorrowInView extends JDialog {
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

    private MyTable tableDataView;
    private DefaultTableModel dtmView;

    //������Ϣ
    private final ComboJLAndJT cltReaderId; // ѧ    ��
    private final ComboJLAndJT cltReaderName; // ��    ��
    private final ComboJLAndJT cltReaderApart; // ѧ    Ժ
    private final ComboJLAndJT cltReaderClass; // ��    ��
    private final ComboJLAndJT cltReaderTel; // ��ϵ��ʽ
    private final JPanel jplSex;
    private final JComboBox<String> jcbSex;
    private final JLabel jlbSex;//�Ա�

    private final JButton btnBack;
    private final JButton btnCancel;

    public BorrowInView biv;

    private final String[] select = {"", "��", "Ů"};//�Ա��


    BorrowInServer server;

    public BorrowInView(BorrowBookView bv) {
        super(bv, "ͼ��黹", true);
        biv = this;
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
        jlbReader = new JLabel("������Ϣ"); // ���������
        btnSearchReader = new JButton("����"); // ������ť
        btnNextReader = new JButton("��һ��"); // ��һ�����
        btnPrevReader = new JButton("��һ��"); // ��һ�����

        //�鼮��Ϣ����
        jtfBookKeyword = new JTextField(); // ���������
        jlbBook = new JLabel("�ѽ����鼮"); // ���������
        btnSearchBook = new JButton("����"); // ������ť
        btnNextBook = new JButton("��һ��"); // ��һ�����
        btnPrevBook = new JButton("��һ��"); // ��һ�����

        tableDataView = new MyTable();

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


        btnBack = new JButton("���");
        btnCancel = new JButton("ȡ��");

        Init();
        resetData();
    }

    /**
     * �����鼮��Ϣ
     *
     * @param cp   δ���鼮
     * @param p    �ܽ����
     * @param list �鼮��Ϣ
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setBookInfo(int cp, int p, List<BookInfoBeans> list) {
// TODO Auto-generated method stub
        //1.���ñ���еı������ݼ���
        Vector<String> title = new Vector<>();
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
        Vector<Vector> data = new Vector<Vector>();
        Vector row = null;


        for (BookInfoBeans b : list) {
            row = new Vector<>();
            row.add(b.getBookName());//����
            row.add(b.getAuthor());//����
            row.add(b.getBookClassify());//����
            row.add(b.getPublisher());//������
            row.add(b.getPublishTime());//������
            row.add(b.getQuantity());//ʣ�����
            row.add(b.getStack());//��������
            row.add(b.getBookShelf());//�������
            row.add(b.getBookID());//ͼ����
            data.add(row);
        }

        //����tableModel
        this.dtmView = new DefaultTableModel(data, title);
        //��tableModel����table��
        this.tableDataView.setModel(dtmView);

        //���ñ������Ӧ����
        tableDataView.FitTableColumns();

        if(p == 0){
            jlbBook.setText("��δ�����鼮");
        }else {
            jlbBook.setText("δ�黹�鼮��" + cp + "/" + p + "��");
        }
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
        jlbBook.setText("�ѽ����鼮");
        List<BookInfoBeans> beans = new ArrayList<>();
        BookInfoBeans infoBeans = new BookInfoBeans();
        beans.add(infoBeans);
        setBookInfo(0,0,beans);

        //������Ϣ 6
        cltReaderId.setIText("");//ѧ��
        cltReaderName.setIText("");//����
        cltReaderApart.setIText("");//ѧԺ
        cltReaderClass.setIText("");//�༶
        cltReaderTel.setIText("");//�༶

        jcbSex.setSelectedIndex(0);

        jlbReader.setText("������Ϣ");
    }

    private void Init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(windowsWidth - 200, windowsHeight - 20);
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
        int unitH = (windowsHeight - 219 + 80) / 13;
        int unitW = (windowsWidth - 154 - 100);

        // -------------------------------------------------------------------------------------/
        jplReaderSearch.setLayout(null);
        jplReaderSearch.setBounds(5, 5, unitW - 10, 50);
        createSearchBar(unitW, jplReaderSearch, jlbReader, jtfReaderKeyword, btnSearchReader, btnPrevReader, btnNextReader);

        // -------------------------------------------------------------------------------------/
        jplReaderInfo.setLayout(new GridLayout(2, 3));
        jplReaderInfo.setBounds(10, 60, unitW - 20, unitH * 2);

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

        // -------------------------------------------------------------------------------------/
        jplBookSearch.setLayout(null);
        jplBookSearch.setBounds(5, unitH * 2 + 60 + 10, unitW - 10, 50);
        createSearchBar(unitW, jplBookSearch, jlbBook, jtfBookKeyword, btnSearchBook, btnPrevBook, btnNextBook);

        // 8
        bookInfo.setLayout(new GridLayout(5, 2));
        bookInfo.setBounds(10, unitH * 2 + 60 + 10 + 50 + 10, unitW - 20, unitH * 5);
        //bookInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        bookInfo.add(tableDataView);

        // -------------------------------------------------------------------------------------/
        jplBorrowSetting.setLayout(null);
        jplBorrowSetting.setBounds(5, unitH * 2 + 60 + 10 + 50 + 10 + unitH * 5 + 10, unitW - 10, 65);
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
        btnCancel.addActionListener(e -> biv.dispose());

        BorrowInMouseListener listener = new BorrowInMouseListener(this);
        btnBack.addActionListener(listener);
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
        functionView.add(btnBack);
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
        jLabel.setBounds(30, 10, 240, 30);
        jPanel.add(jLabel);

        jtfKeyword.setBounds(unitW - 10 - 60 - 10 - 60 - 10 - 60 - 10 - 200 - 10, 10, 200, 30);
        jPanel.add(jtfKeyword);

        btnSearch.setBounds(unitW - 10 - 60 - 10 - 60 - 10 - 60 - 10, 10, 60, 30);
        jPanel.add(btnSearch);

        btnPrev.setBounds(unitW - 10 - 60 - 10 - 60 - 10, 10, 60, 30);
        jPanel.add(btnPrev);
        btnNext.setBounds(unitW - 10 - 60 - 10, 10, 60, 30);
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

    public JButton getBtnBack() {
        return btnBack;
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

    public BorrowInServer getServer() {
        return server;
    }

    public ComboJLAndJT getCltReaderId() {
        return cltReaderId;
    }


}
