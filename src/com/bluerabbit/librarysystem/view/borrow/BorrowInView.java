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

    //把界面分成四部分，借阅者搜索、借阅者信息、图书搜索、图书借阅列表
    private final JPanel jplReaderSearch;
    private final JPanel jplReaderInfo;
    private final JPanel jplBookSearch;
    private final JPanel bookInfo;
    private final JPanel jplBorrowSetting;

    // 借出设置
    private final ComboJLAndJT cltBorrowDuration; // 借出时长
    private final JPanel jplBookStatus;
    private final JComboBox<String> jcbBookStatus;
    private final JLabel jlbBookStatus;//图书状态
    JLabel jlbBorrowSetting; //借出设置


    // 借阅者搜索
    JLabel jlbReader; // 读者搜索
    JTextField jtfReaderKeyword; // 搜索输入框
    JButton btnSearchReader; // 搜索按钮
    JButton btnNextReader; // 下一个结果
    JButton btnPrevReader; // 上一个结果

    // 借阅书籍搜索
    JLabel jlbBook; // 书籍搜索
    JTextField jtfBookKeyword; // 搜索输入框
    JButton btnSearchBook; // 搜索按钮
    JButton btnNextBook; // 下一个结果
    JButton btnPrevBook; // 上一个结果

    private MyTable tableDataView;
    private DefaultTableModel dtmView;
    private JScrollPane snpView;

    //读者信息
    private final ComboJLAndJT cltReaderId; // 学    号
    private final ComboJLAndJT cltReaderName; // 姓    名
    private final ComboJLAndJT cltReaderApart; // 学    院
    private final ComboJLAndJT cltReaderClass; // 班    级
    private final ComboJLAndJT cltReaderTel; // 联系方式
    private final JPanel jplSex;
    private final JComboBox<String> jcbSex;
    private final JLabel jlbSex;//性别

    private final JButton btnBack;
    private final JButton btnCancel;

    public BorrowInView biv;

    private final String[] selectSex = {"", "男", "女"};//性别框
    private final String[] selectBookStatus = {"正常", "破损", "破损严重","丢失"}; //图书状态框 0正常，1破碎，2破损严重，3丢失

    BorrowInServer server;

    public BorrowInView(BorrowBookView bv) {
        super(bv, "图书归还", true);
        biv = this;
        //获得父视图的大小
        windowsHeight = bv.getHeight();
        windowsWidth = bv.getWidth();

        //界面部分
        mainView = new JPanel();
        functionView = new JPanel();
        contentView = new JPanel();

        //内容部分
        jplReaderSearch = new JPanel();
        jplReaderInfo = new JPanel();
        jplBookSearch = new JPanel();
        bookInfo = new JPanel();
        jplSex = new JPanel();
        jplBookStatus = new JPanel();
        jplBorrowSetting = new JPanel();

        //读者信息搜索
        jtfReaderKeyword = new JTextField(); // 搜索输入框
        jlbReader = new JLabel("读者信息"); // 搜索输入框
        btnSearchReader = new JButton("搜索"); // 搜索按钮
        btnNextReader = new JButton("下一个"); // 下一个结果
        btnPrevReader = new JButton("上一个"); // 上一个结果

        //书籍信息搜索
        jtfBookKeyword = new JTextField(); // 搜索输入框
        jlbBook = new JLabel("已借阅书籍"); // 搜索输入框
        btnSearchBook = new JButton("搜索"); // 搜索按钮
        btnNextBook = new JButton("下一页"); // 下一个结果
        btnPrevBook = new JButton("上一页"); // 上一个结果

        //表格的处理
        snpView = new JScrollPane();
        tableDataView = new MyTable();

        //还入设置
        jlbBorrowSetting = new JLabel("还入信息");
        cltBorrowDuration = new ComboJLAndJT("支付费用", 20, true);//支付费用
        jcbBookStatus = new JComboBox<>();
        jlbBookStatus = new JLabel("图书状态：");//图书状态

        //个人信息 6
        cltReaderId = new ComboJLAndJT("学    号：", 20);//学号
        cltReaderName = new ComboJLAndJT("姓    名：", 20);//姓名
        cltReaderApart = new ComboJLAndJT("学    院：", 20);//学院
        cltReaderClass = new ComboJLAndJT("班    级：", 20);//班级
        cltReaderTel = new ComboJLAndJT("联系方式：", 20);//联系方式
        jcbSex = new JComboBox<>();
        jcbSex.setEnabled(false);
        jlbSex = new JLabel("性    别：");


        btnBack = new JButton("借出");
        btnCancel = new JButton("取消");

        Init();
        resetData();
    }

    private void Init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(windowsWidth - 300, windowsHeight - 20);
        CenterView.CenterByWindow(this);
        //不允许用户调整窗口大小
        this.setResizable(false);

        //设置布局
        mainView.setLayout(new BorderLayout());
        contentView.setLayout(null);
        functionView.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //配置内容视图边框
        contentView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        //内容部分,h:wh-219,w:ww-130
        int unitH = (windowsHeight - 219 + 80) / 13;
        int unitW = (windowsWidth - 154 - 200);

        // -------------------------------------------------------------------------------------/
        jplReaderSearch.setLayout(null);
        jplReaderSearch.setBounds(5, 5, unitW - 10, 40);
        createSearchBar(unitW, jplReaderSearch, jlbReader, jtfReaderKeyword, btnSearchReader, btnPrevReader, btnNextReader);

        // -------------------------------------------------------------------------------------/
        jplReaderInfo.setLayout(new GridLayout(2, 3));
        jplReaderInfo.setBounds(10, 50, unitW - 20, unitH * 2);

        //个人信息 6
        jplReaderInfo.add(cltReaderId);//学号
        jplReaderInfo.add(cltReaderName);//姓名
        jplReaderInfo.add(cltReaderApart);//学院
        jplReaderInfo.add(cltReaderClass);//班级

        jplSex.setLayout(new FlowLayout());

        jplSex.add(jlbSex);
        jplSex.add(jcbSex);
        jplReaderInfo.add(jplSex);//性别
        jcbSex.setModel(new DefaultComboBoxModel<>(selectSex));
        jcbSex.setPreferredSize(new Dimension(127, 27));

        jplReaderInfo.add(cltReaderTel);//联系方式

        // -------------------------------------------------------------------------------------/
        jplBookSearch.setLayout(null);
        jplBookSearch.setBounds(5, unitH * 2 + 50 + 10, unitW - 10, 40);
        createSearchBar(unitW, jplBookSearch, jlbBook, jtfBookKeyword, btnSearchBook, btnPrevBook, btnNextBook);

        // 8
        bookInfo.setLayout(new GridLayout(1, 1));
        bookInfo.setBounds(10, unitH * 2 + 50 + 10 + 50 , unitW - 20, unitH * 5+22+20);
        //bookInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        snpView.getViewport().add(tableDataView);
        bookInfo.add(snpView);

        // -------------------------------------------------------------------------------------/
        jplBorrowSetting.setLayout(null);
        jplBorrowSetting.setBounds(5, unitH * 2 + 60 + 10 + 50 + 10 + unitH * 5 + 10+22, unitW - 10, 40);
        jplBorrowSetting.setBorder(BorderFactory.createEtchedBorder());
        Color color = new Color(236, 233, 216);
        jplBorrowSetting.setBackground(color);
        //还入设置
        jlbBorrowSetting.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        jlbBorrowSetting.setBounds(30, 5, 160, 30);
        jplBorrowSetting.add(jlbBorrowSetting);//借出设置

        cltBorrowDuration.setBounds(unitW - 10 - 200 - 10 - 200 - 10, 1, 200, 35);
        cltBorrowDuration.setBackground(color);
        cltBorrowDuration.setIText("0");
        jplBorrowSetting.add(cltBorrowDuration);//还入缴费

        jcbBookStatus.setModel(new DefaultComboBoxModel<>(selectBookStatus));
        jcbBookStatus.setPreferredSize(new Dimension(127, 27));
        jplBookStatus.setLayout(new FlowLayout());
        jplBookStatus.add(jlbBookStatus);
        jplBookStatus.add(jcbBookStatus);
        jplBookStatus.setBackground(color);
        jplBookStatus.setBounds(unitW - 10 - 200 - 10, 1, 200, 35);
        jplBorrowSetting.add(jplBookStatus);//借出数量


        // -------------------------------------------------------------------------------------/
        //添加监听事件
        btnCancel.addActionListener(e -> biv.dispose());

        BorrowInMouseListener listener = new BorrowInMouseListener(this);
        btnBack.addActionListener(listener);
        btnNextBook.addActionListener(listener);
        btnNextReader.addActionListener(listener);
        btnPrevBook.addActionListener(listener);
        btnPrevReader.addActionListener(listener);
        btnSearchBook.addActionListener(listener);
        btnSearchReader.addActionListener(listener);

        //添加布局
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
     * 创建一个搜索栏
     *
     * @param unitW      宽度
     * @param jPanel     面板
     * @param jLabel     提示
     * @param jtfKeyword 关键词
     * @param btnSearch  搜索
     * @param btnPrev    上一页
     * @param btnNext    下一页
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


    /**
     * 更新书籍信息
     *
     * @param cp   未还书籍
     * @param p    总借次数
     * @param list 书籍信息
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setBookInfo(int cp, int p, List<BookInfoBeans> list) {
// TODO Auto-generated method stub
        //1.设置表格中的标题数据集合
        Vector<String> title = new Vector<>();
        title.add("书名");
        title.add("作者");
        title.add("书刊类别");
        title.add("出版社");
        title.add("出版版次");
        title.add("剩余册数");
        title.add("所在书室");
        title.add("所在书架");
        title.add("图书编号");

        //2.设置表格中的数据集合
        Vector<Vector> data = new Vector<Vector>();
        Vector row = null;


        for (BookInfoBeans b : list) {
            row = new Vector<>();
            row.add(b.getBookName());//书名
            row.add(b.getAuthor());//作者
            row.add(b.getBookClassify());//分类
            row.add(b.getPublisher());//出版社
            row.add(b.getPublishTime());//出版版次
            row.add(b.getQuantity());//剩余册数
            row.add(b.getStack());//所在书室
            row.add(b.getBookShelf());//所在书架
            row.add(b.getBookID());//图书编号
            data.add(row);
        }

        //设置tableModel
        this.dtmView = new DefaultTableModel(data, title);
        //将tableModel绑定在table上
        this.tableDataView.setModel(dtmView);

        //设置表格自适应数据
        tableDataView.FitTableColumns();

        if (p == 0) {
            jlbBook.setText("暂未借阅书籍");
        } else {
            jlbBook.setText("未归还书籍（" + cp + "/" + p + "）");
        }
    }

    /**
     * 更新读者信息
     *
     * @param cp    当前页数
     * @param p     总页数
     * @param beans 读者信息
     */
    public void setReaderInfo(int cp, int p, ReaderInfoBeans beans) {
        //个人信息 6
        cltReaderId.setIText(beans.getReaderID());//学号
        cltReaderName.setIText(beans.getReaderName());//姓名
        cltReaderApart.setIText(beans.getApart());//学院
        cltReaderClass.setIText(beans.getTheClass());//班级

        cltReaderTel.setIText(beans.getTelNo());//联系方式

        if ("男".equals(beans.getSex())) {
            jcbSex.setSelectedIndex(1);
        } else if ("女".equals(beans.getSex())) {
            jcbSex.setSelectedIndex(2);
        }

        jlbReader.setText("读者搜索结果（" + cp + "/" + p + "）");
    }

    /**
     * 重置数据
     */
    public void resetData() {
        // 借阅设置
        jlbBook.setText("已借阅书籍");
        List<BookInfoBeans> beans = new ArrayList<>();
        BookInfoBeans infoBeans = new BookInfoBeans();
        beans.add(infoBeans);
        setBookInfo(0, 0, beans);

        //个人信息 6
        cltReaderId.setIText("");//学号
        cltReaderName.setIText("");//姓名
        cltReaderApart.setIText("");//学院
        cltReaderClass.setIText("");//班级
        cltReaderTel.setIText("");//班级

        jcbSex.setSelectedIndex(0);

        jlbReader.setText("读者信息");
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
