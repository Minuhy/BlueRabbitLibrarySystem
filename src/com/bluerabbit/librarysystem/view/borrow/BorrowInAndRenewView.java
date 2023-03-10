package com.bluerabbit.librarysystem.view.borrow;

import com.bluerabbit.librarysystem.beans.BorrowBeans;
import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
import com.bluerabbit.librarysystem.listener.borrow.BorrowInAndRenewMouseListener;
import com.bluerabbit.librarysystem.service.borrow.BorrowInAndRenewServer;
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
public class BorrowInAndRenewView extends JDialog {
    private final JPanel mainView;
    private final JPanel contentView;
    private final JPanel functionView;
    private final int windowsHeight;
    private final int windowsWidth;

    //�ѽ���ֳ��Ĳ��֣���������������������Ϣ��ͼ��������ͼ������б�
    private final JPanel jplReaderSearch;
    private final JPanel jplReaderInfo;
    private final JPanel jplBookSearch;
    private final JPanel jplBookInfo;
    private final JPanel jplBackSetting;

    // ��������
    private final ComboJLAndJT cltBackPay; // ����ʱ��
    private final JPanel jplBookStatus;
    private final JComboBox<String> jcbBookStatus;
    private final JLabel jlbBookStatus;//ͼ��״̬
    JLabel jlbBackSetting; //��������


    // ����������
    private final JLabel jlbReader; // ��������
    private final JTextField jtfReaderKeyword; // ���������
    private final JButton btnSearchReader; // ������ť
    private final JButton btnNextReader; // ��һ�����
    private final JButton btnPrevReader; // ��һ�����

    // �����鼮����
    private final JLabel jlbBook; // �鼮����
    private final JTextField jtfBookKeyword; // ���������
    private final JButton btnSearchBook; // ������ť
    private final JButton btnNextBook; // ��һ�����
    private final JButton btnPrevBook; // ��һ�����

    private final MyTable tableDataView;
    private final JScrollPane snpView;

    //������Ϣ
    private final ComboJLAndJT cltReaderId; // ѧ    ��
    private final ComboJLAndJT cltReaderName; // ��    ��
    private final ComboJLAndJT cltReaderApart; // ѧ    Ժ
    private final ComboJLAndJT cltReaderClass; // ��    ��
    private final ComboJLAndJT cltReaderTel; // ��ϵ��ʽ
    private final JPanel jplSex;
    private final JComboBox<String> jcbSex;
    private final JLabel jlbSex;//�Ա�

    private final JButton btnRenew;
    private final JButton btnBack;
    private final JButton btnCancel;

    public BorrowInAndRenewView biv;

    private final String[] selectSex = {"", "��", "Ů"};//�Ա��
    private final String[] selectBookStatus = {"����", "����", "��������", "��ʧ"}; //ͼ��״̬�� 0������1���飬2�������أ�3��ʧ

    BorrowInAndRenewServer server;

    boolean isRenew;

    // ����п���ƣ�ֻ��ʼ��һ��
    boolean isFist = true;

    public BorrowInAndRenewView(BorrowBookView bv) {
        this(bv, false);
    }

    public BorrowInAndRenewView(BorrowBookView bv, boolean isRenew) {
        super(bv, "ͼ��黹", true);

        if (isRenew) {
            this.setTitle("ͼ������");
        }

        this.isRenew = isRenew; // ����ģʽ
        biv = this;
        server = new BorrowInAndRenewServer(this);

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
        jplBookInfo = new JPanel();
        jplSex = new JPanel();
        jplBookStatus = new JPanel();
        jplBackSetting = new JPanel();

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
        btnNextBook = new JButton("��һҳ"); // ��һ�����
        btnPrevBook = new JButton("��һҳ"); // ��һ�����

        //���Ĵ���
        snpView = new JScrollPane();
        tableDataView = new MyTable();

        //��������
        if (isRenew) {
            jlbBackSetting = new JLabel("������Ϣ");
        } else {
            jlbBackSetting = new JLabel("������Ϣ");
        }
        if(isRenew){
            cltBackPay = new ComboJLAndJT("��������", 20, true);//֧������
        }else{
            cltBackPay = new ComboJLAndJT("֧������", 20, true);//֧������
        }
        jcbBookStatus = new JComboBox<>();
        jlbBookStatus = new JLabel("ͼ��״̬��");//ͼ��״̬

        //������Ϣ 6
        cltReaderId = new ComboJLAndJT("ѧ    �ţ�", 30);//ѧ��
        cltReaderName = new ComboJLAndJT("��    ����", 30);//����
        cltReaderApart = new ComboJLAndJT("ѧ    Ժ��", 30);//ѧԺ
        cltReaderClass = new ComboJLAndJT("��    ����", 30);//�༶
        cltReaderTel = new ComboJLAndJT("��ϵ��ʽ��", 30);//��ϵ��ʽ
        jcbSex = new JComboBox<>();
        jcbSex.setEnabled(false);
        jlbSex = new JLabel("��    ��");

        btnRenew = new JButton("����");
        btnBack = new JButton("����");
        btnCancel = new JButton("ȡ��");

        Init();
        resetData();
    }

    private void Init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(windowsWidth - 80, windowsHeight - 50);
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
        int unitH = (windowsHeight - 219 + 50) / 12;
        int unitW = (windowsWidth - 154 + 20);


        int heightOffset = 5;

        // ������Ϣ������-------------------------------------------------------------------------------------/
        int xNav = 5, y1 = 5, widthNav = unitW - 10, heightNav = 40;
        jplReaderSearch.setLayout(null);
        jplReaderSearch.setBounds(xNav, y1, widthNav, heightNav);
        createSearchBar(unitW, jplReaderSearch, jlbReader, jtfReaderKeyword, btnSearchReader, btnPrevReader, btnNextReader);

        // ������Ϣ��� -------------------------------------------------------------------------------------/
        int xInfo = 10, y2 = y1 + heightNav + heightOffset, widthInfo = unitW - 20, height2 = unitH * 2+2;
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
        jcbSex.setModel(new DefaultComboBoxModel<>(selectSex));
        jcbSex.setPreferredSize(new Dimension(187, 27));

        jplReaderInfo.add(cltReaderTel);//��ϵ��ʽ

        // �鼮��Ϣ���� -------------------------------------------------------------------------------------/
        int y3 = y2 + height2 + heightOffset;
        jplBookSearch.setLayout(null);
        jplBookSearch.setBounds(xNav, y3, widthNav, heightNav);
        createSearchBar(unitW, jplBookSearch, jlbBook, jtfBookKeyword, btnSearchBook, btnPrevBook, btnNextBook);

        // �ѽ����鼮�б� -------------------------------------------------------------------------------------/
        int y4 = y3 + heightNav + heightOffset, height4 = unitH * 5 + 16;
        // 8
        jplBookInfo.setLayout(new GridLayout(1, 1));
        jplBookInfo.setBounds(xInfo, y4, widthInfo, height4);

        snpView.getViewport().add(tableDataView);
        jplBookInfo.add(snpView);


        // �������� -------------------------------------------------------------------------------------/
        int y5 = y4 + height4 + heightOffset, heightSetting = 40;
        jplBackSetting.setLayout(null);
        jplBackSetting.setBounds(xNav, y5, widthNav, heightSetting);
        jplBackSetting.setBorder(BorderFactory.createEtchedBorder());
        Color color = new Color(236, 233, 216);
        jplBackSetting.setBackground(color);
        //��������
        jlbBackSetting.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        jlbBackSetting.setBounds(30, 5, 160, 30);
        jplBackSetting.add(jlbBackSetting);//�������

        cltBackPay.setBounds(unitW - 10 - 200 - 10 - 200 - 10, 1, 200, 35);
        cltBackPay.setBackground(color);
        if(isRenew){
            cltBackPay.setIText("30");
        }else{
            cltBackPay.setIText("0");
        }
        jplBackSetting.add(cltBackPay);//����ɷ�

        jcbBookStatus.setModel(new DefaultComboBoxModel<>(selectBookStatus));
        jcbBookStatus.setPreferredSize(new Dimension(127, 27));
        jplBookStatus.setLayout(new FlowLayout());
        jplBookStatus.add(jlbBookStatus);
        jplBookStatus.add(jcbBookStatus);
        jplBookStatus.setBackground(color);
        jplBookStatus.setBounds(unitW - 10 - 200 - 10, 1, 200, 35);
        jplBackSetting.add(jplBookStatus);//����״̬


        // -------------------------------------------------------------------------------------/
        //��Ӽ����¼�
        btnCancel.addActionListener(e -> biv.dispose());

        BorrowInAndRenewMouseListener listener = new BorrowInAndRenewMouseListener(this);
        btnBack.addActionListener(listener);
        btnRenew.addActionListener(listener);
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
        contentView.add(jplBookInfo);
        contentView.add(jplBackSetting);
        if (isRenew) {
            functionView.add(btnRenew);
        } else {
            functionView.add(btnBack);
        }
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
        // �°�
        jPanel.setBackground(new Color(212, 227, 237));

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


    /**
     * �����鼮��Ϣ
     *
     * @param cp   δ���鼮
     * @param p    �ܽ����
     * @param list �鼮��Ϣ
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setBookInfo(int cp, int p, List<BorrowBeans> list,String keyword) {

        // ��ȡ�п�
        int[] colW = new int[14];
        try {
            for (int i = 0; i < 14; i++) {
                colW[i] = this.tableDataView.getColumnModel().getColumn(i).getPreferredWidth();
                System.out.print(colW[i] + ", ");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("��ȡ�п�ʧ�ܣ�" + e.getMessage());
        }

        //1.���ñ���еı������ݼ���
        Vector<String> title = new Vector<>();
        title.add("���");
        title.add("����");
        title.add("����");
        title.add("������");
        title.add("���");
        title.add("ISBN");
        title.add("�۸�");
        title.add("��������");
        title.add("�������");
        title.add("���ʱ��");
        title.add("����");
        title.add("Ԥ�ƹ黹");
        title.add("�黹ʱ��");
        title.add("����");

        //2.���ñ���е����ݼ���
        Vector<Vector> data = new Vector<>();
        Vector row;


        for (BorrowBeans b : list) {
            row = new Vector<>();
            row.add(b.getId()); // ���
            row.add(b.getBookName()); // ����
            row.add(b.getBookAuthor()); // ����
            row.add(b.getBookPublisher()); // ������
            row.add(b.getBookId()); // �鼮���
            row.add(b.getBookBarcode()); // ISBN
            row.add(b.getBookPrice()); // �۸�
            row.add(b.getBookStack()); // ��������
            row.add(b.getBookShelf()); // �������
            row.add(b.getCreateTimestamp()); // ���ʱ��
            row.add(b.getBookNumber()); // ����
            row.add(b.getWillBackTimestamp()); // Ԥ�ƹ黹
            row.add(b.getIsReturn()); // �黹ʱ��
            row.add(b.getReturnAdminId()); // �黹����
            data.add(row);
            // System.out.println(b);
        }

        //����tableModel
        DefaultTableModel dtmView = new DefaultTableModel(data, title);
        //��tableModel����table��
        this.tableDataView.setModel(dtmView);


        int[] colWidthStand = new int[]{39, 144, 62, 95, 42, 103, 40, 76, 73, 97, 39, 95, 97, 48};

        if (isFist) {
            colW = colWidthStand;
            isFist = false;
        }

        int sumStand = 0;
        for (int i : colWidthStand) {
            sumStand += i;
        }

        int sumGet = 0;
        for (int i : colW) {
            sumGet += i;
        }

        if (sumGet < (sumStand - 100)) { // ���̫���ˣ�ʹ�ñ�׼����
            colW = colWidthStand;
        }

        // �����п�
        for (int i = 0; i < 14; i++) {
            this.tableDataView.getColumnModel().getColumn(i).setPreferredWidth(colW[i]);
        }

        //���ñ������Ӧ����
        // tableDataView.FitTableColumns();

        if (p == 0) {
            jlbBook.setText("��δ�����鼮");
        } else {
            if(keyword!=null){
                jlbBook.setText("δ�����������" + cp + "/" + p + "��");
            }else {
                jlbBook.setText("δ�黹�鼮��" + cp + "/" + p + "��");
            }
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

        server.loadBookByReader();
    }

    /**
     * ��������
     */
    public void resetData() {
        // ��������
        jlbBook.setText("�ѽ����鼮");
        List<BorrowBeans> beans = new ArrayList<>();
        BorrowBeans infoBeans = new BorrowBeans();
        beans.add(infoBeans);
        setBookInfo(0, 0, beans,null);

        //������Ϣ 6
        cltReaderId.setIText("");//ѧ��
        cltReaderName.setIText("");//����
        cltReaderApart.setIText("");//ѧԺ
        cltReaderClass.setIText("");//�༶
        cltReaderTel.setIText("");//�༶

        jcbSex.setSelectedIndex(0);

        jlbReader.setText("������Ϣ");
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

    public ComboJLAndJT getCltBackPay() {
        return cltBackPay;
    }

    public JTextField getJtfReaderKeyword() {
        return jtfReaderKeyword;
    }

    public JTextField getJtfBookKeyword() {
        return jtfBookKeyword;
    }

    public BorrowInAndRenewServer getServer() {
        return server;
    }

    public ComboJLAndJT getCltReaderId() {
        return cltReaderId;
    }

    public Object getBtnRenew() {
        return btnRenew;
    }

    public MyTable getTableDataView() {
        return tableDataView;
    }

    public JComboBox<String> getJcbBookStatus() {
        return jcbBookStatus;
    }
}
