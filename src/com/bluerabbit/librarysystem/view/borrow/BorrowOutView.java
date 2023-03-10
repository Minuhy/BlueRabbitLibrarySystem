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
 * 借出处理框
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

    //把界面分成四部分，借阅者搜索、借阅者信息、图书搜索、图书借阅列表
    private final JPanel jplReaderSearch;
    private final JPanel jplReaderInfo;
    private final JPanel jplBookSearch;
    private final JPanel bookInfo;
    private final JPanel jplBorrowSetting;

    // 借出设置
    private final ComboJLAndJT cltBorrowDuration; // 借出时长
    private final ComboJLAndJT cltBorrowNumber; // 借出数量
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

    //读者信息
    private final ComboJLAndJT cltReaderId; // 学    号
    private final ComboJLAndJT cltReaderName; // 姓    名
    private final ComboJLAndJT cltReaderApart; // 学    院
    private final ComboJLAndJT cltReaderClass; // 班    级
    private final ComboJLAndJT cltReaderTel; // 联系方式
    private final JPanel jplSex;
    private final JComboBox<String> jcbSex;
    private final JLabel jlbSex;//性别

    private final JButton btnBorrow;
    private final JButton btnCancel;

    public BorrowOutView bov;

    private final String[] select = {"", "男", "女"};//性别框

    //书籍信息
    private final ComboJLAndJT cltBookName;//书名
    private final ComboJLAndJT cltBookAuthor;//作者
    private final ComboJLAndJT cltBookPublisher;//出版社
    private final ComboJLAndJT cltBookPublishDate;//出版日期
    private final ComboJLAndJT cltBookId;//书刊编号
    private final ComboJLAndJT cltBookBarcode;//书刊条码
    private final ComboJLAndJT cltBookStack;//书室
    private final ComboJLAndJT cltBookShelf;//书架
    private final ComboJLAndJT cltBookPrice;//价格
    private final ComboJLAndJT cltBookNumber;//剩余册数


    BorrowOutServer server;

    public BorrowOutView(BorrowBookView bv) {
        super(bv, "图书借阅", true);
        bov = this;
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
        jplBorrowSetting = new JPanel();

        //读者信息搜索
        jtfReaderKeyword = new JTextField(); // 搜索输入框
        jlbReader = new JLabel("搜索读者"); // 搜索输入框
        btnSearchReader = new JButton("搜索"); // 搜索按钮
        btnNextReader = new JButton("下一个"); // 下一个结果
        btnPrevReader = new JButton("上一个"); // 上一个结果

        //书籍信息搜索
        jtfBookKeyword = new JTextField(); // 搜索输入框
        jlbBook = new JLabel("搜索书籍"); // 搜索输入框
        btnSearchBook = new JButton("搜索"); // 搜索按钮
        btnNextBook = new JButton("下一个"); // 下一个结果
        btnPrevBook = new JButton("上一个"); // 上一个结果

        //借出设置
        cltBorrowDuration = new ComboJLAndJT("借出时长（天）", 30, true);//学号
        cltBorrowNumber = new ComboJLAndJT("借出数量（本）", 30, true);//姓名
        jlbBorrowSetting = new JLabel("借出设置");

        //个人信息 6
        cltReaderId = new ComboJLAndJT("学    号：", 30);//学号
        cltReaderName = new ComboJLAndJT("姓    名：", 30);//姓名
        cltReaderApart = new ComboJLAndJT("学    院：", 30);//学院
        cltReaderClass = new ComboJLAndJT("班    级：", 30);//班级
        cltReaderTel = new ComboJLAndJT("联系方式：", 30);//联系方式
        jcbSex = new JComboBox<>();
        jcbSex.setEnabled(false);
        jlbSex = new JLabel("性    别：");


        //书籍信息 6
        cltBookName = new ComboJLAndJT("书    名：");//书名
        cltBookAuthor = new ComboJLAndJT("作    者：");//作者
        cltBookPublisher = new ComboJLAndJT("出 版 社：");//出版社
        cltBookPublishDate = new ComboJLAndJT("出版日期：");//出版日期

        cltBookId = new ComboJLAndJT("书刊编号：");//书刊编号
        cltBookBarcode = new ComboJLAndJT("书刊条码：");//书刊条码
        cltBookStack = new ComboJLAndJT("书    室：");//书    室
        cltBookShelf = new ComboJLAndJT("书    架：");//书    架
        cltBookPrice = new ComboJLAndJT("价    格：");//价    格
        cltBookNumber = new ComboJLAndJT("剩余册数：");//剩余册数


        btnBorrow = new JButton("借出");
        btnCancel = new JButton("取消");

        server = new BorrowOutServer(this);

        Init();
        resetData();
    }

    /**
     * 更新书籍信息
     *
     * @param cp    当前页数
     * @param p     总页数
     * @param beans 书籍信息
     */
    public void setBookInfo(int cp, int p, BookInfoBeans beans) {
        //书籍信息 10
        cltBookName.setIText(beans.getBookName());//书名
        cltBookAuthor.setIText(beans.getAuthor());//作者
        cltBookPublisher.setIText(beans.getPublisher());//出版社
        cltBookPublishDate.setIText(beans.getPublishDate());//出版日期
        cltBookId.setIText(beans.getBookID());//书刊编号
        cltBookBarcode.setIText(beans.getBookBarcode());//书刊条码
        cltBookStack.setIText(beans.getStack());//书    室
        cltBookShelf.setIText(beans.getBookShelf());//书    架
        cltBookPrice.setIText(beans.getPrice() + ""); // 价格
        cltBookNumber.setIText(beans.getQuantity() + ""); // 剩余册数

        jlbBook.setText("书籍搜索结果（" + cp + "/" + p + "）");
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

        //书籍信息 6
        cltBookName.setIText("");//书名
        cltBookAuthor.setIText("");//作者
        cltBookPublisher.setIText("");//出版社
        cltBookPublishDate.setIText("");//出版日期
        cltBookId.setIText("");//书刊编号
        cltBookBarcode.setIText("");//书刊条码
        cltBookStack.setIText("");//书    室
        cltBookShelf.setIText("");//书    架
        cltBookNumber.setIText(""); // 剩余册数
        cltBookPrice.setIText(""); // 价格
        jlbBook.setText("搜索书籍");

        //个人信息 6
        cltReaderId.setIText("");//学号
        cltReaderName.setIText("");//姓名
        cltReaderApart.setIText("");//学院
        cltReaderClass.setIText("");//班级
        cltReaderTel.setIText("");//班级

        jcbSex.setSelectedIndex(0);

        jlbReader.setText("搜索读者");
    }

    private void Init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(windowsWidth - 200, windowsHeight - 56);
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
        int unitH = (windowsHeight - 219 + 44) / 12;
        int unitW = (windowsWidth - 154 - 100);


        int heightOffset = 5;

        // 读者信息搜索导航栏 -------------------------------------------------------------------------------------/
        int xNav = 5,y1=5,widthNav=unitW - 10,heightNav=40;
        jplReaderSearch.setLayout(null);
        jplReaderSearch.setBounds(xNav, y1, widthNav, heightNav);
        createSearchBar(unitW, jplReaderSearch, jlbReader, jtfReaderKeyword, btnSearchReader, btnPrevReader, btnNextReader);

        // 读者信息表格 -------------------------------------------------------------------------------------/
        int xInfo = 10,y2=y1 + heightNav+heightOffset,widthInfo=unitW - 20,height2 = unitH * 2;
        jplReaderInfo.setLayout(new GridLayout(2, 3));
        jplReaderInfo.setBounds(xInfo, y2, widthInfo, height2);

        //个人信息 6
        jplReaderInfo.add(cltReaderId);//学号
        jplReaderInfo.add(cltReaderName);//姓名
        jplReaderInfo.add(cltReaderApart);//学院
        jplReaderInfo.add(cltReaderClass);//班级

        jplSex.setLayout(new FlowLayout());

        jplSex.add(jlbSex);
        jplSex.add(jcbSex);
        jplReaderInfo.add(jplSex);//性别
        jcbSex.setModel(new DefaultComboBoxModel<>(select));
        jcbSex.setPreferredSize(new Dimension(187, 27));

        jplReaderInfo.add(cltReaderTel);//联系方式

        // 书籍信息搜索栏 -------------------------------------------------------------------------------------/
        int y3=y2+height2+heightOffset;
        jplBookSearch.setLayout(null);
        jplBookSearch.setBounds(xNav, y3, widthNav, heightNav);
        createSearchBar(unitW, jplBookSearch, jlbBook, jtfBookKeyword, btnSearchBook, btnPrevBook, btnNextBook);

        // 书籍信息表格 -------------------------------------------------------------------------------------/
        int y4=y3+heightNav+heightOffset,height4 = unitH * 5;
        bookInfo.setLayout(new GridLayout(5, 2));
        bookInfo.setBounds(xInfo, y4, widthInfo,height4);

        //书籍信息
        bookInfo.add(cltBookName);//书名
        bookInfo.add(cltBookAuthor);//作者
        bookInfo.add(cltBookPublisher);//出版社
        bookInfo.add(cltBookPublishDate);//出版日期
        bookInfo.add(cltBookId);//书刊编号
        bookInfo.add(cltBookBarcode);//书刊条码
        bookInfo.add(cltBookStack);//书    室
        bookInfo.add(cltBookShelf);//书    架
        bookInfo.add(cltBookPrice);//价格
        bookInfo.add(cltBookNumber);//总册数

        // 借出设置导航栏 -------------------------------------------------------------------------------------/
        int y5=y4+height4+heightOffset,heightSetting = 63;
        jplBorrowSetting.setLayout(null);
        jplBorrowSetting.setBounds(xNav, y5, widthNav, heightSetting);
        jplBorrowSetting.setBorder(BorderFactory.createEtchedBorder());
        Color color = new Color(236, 233, 216);
        jplBorrowSetting.setBackground(color);
        //借出设置
        jlbBorrowSetting.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        jlbBorrowSetting.setBounds(30, 17, 160, 30);
        jplBorrowSetting.add(jlbBorrowSetting);//借出设置

        cltBorrowDuration.setBounds(unitW - 10 - 200 - 10 - 200 - 10, 1, 200, 60);
        cltBorrowDuration.setBackground(color);
        cltBorrowDuration.setIText("30");
        jplBorrowSetting.add(cltBorrowDuration);//借出时长

        cltBorrowNumber.setBounds(unitW - 10 - 200 - 10, 1, 200, 60);
        cltBorrowNumber.setBackground(color);
        cltBorrowNumber.setIText("1");
        jplBorrowSetting.add(cltBorrowNumber);//借出数量

        // -------------------------------------------------------------------------------------/
        //添加监听事件
        btnCancel.addActionListener(e -> bov.dispose());

        BorrowOutMouseListener listener = new BorrowOutMouseListener(this);
        btnBorrow.addActionListener(listener);
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
        functionView.add(btnBorrow);
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
