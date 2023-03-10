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

    //把界面分成四部分，借阅者搜索、借阅者信息、图书搜索、图书借阅列表
    private final JPanel jplReaderSearch;
    private final JPanel jplReaderInfo;
    private final JPanel jplBookSearch;
    private final JPanel jplBookInfo;
    private final JPanel jplBackSetting;

    // 还入设置
    private final ComboJLAndJT cltBackPay; // 还入时长
    private final JPanel jplBookStatus;
    private final JComboBox<String> jcbBookStatus;
    private final JLabel jlbBookStatus;//图书状态
    JLabel jlbBackSetting; //还入设置


    // 借阅者搜索
    private final JLabel jlbReader; // 读者搜索
    private final JTextField jtfReaderKeyword; // 搜索输入框
    private final JButton btnSearchReader; // 搜索按钮
    private final JButton btnNextReader; // 下一个结果
    private final JButton btnPrevReader; // 上一个结果

    // 借阅书籍搜索
    private final JLabel jlbBook; // 书籍搜索
    private final JTextField jtfBookKeyword; // 搜索输入框
    private final JButton btnSearchBook; // 搜索按钮
    private final JButton btnNextBook; // 下一个结果
    private final JButton btnPrevBook; // 上一个结果

    private final MyTable tableDataView;
    private final JScrollPane snpView;

    //读者信息
    private final ComboJLAndJT cltReaderId; // 学    号
    private final ComboJLAndJT cltReaderName; // 姓    名
    private final ComboJLAndJT cltReaderApart; // 学    院
    private final ComboJLAndJT cltReaderClass; // 班    级
    private final ComboJLAndJT cltReaderTel; // 联系方式
    private final JPanel jplSex;
    private final JComboBox<String> jcbSex;
    private final JLabel jlbSex;//性别

    private final JButton btnRenew;
    private final JButton btnBack;
    private final JButton btnCancel;

    public BorrowInAndRenewView biv;

    private final String[] selectSex = {"", "男", "女"};//性别框
    private final String[] selectBookStatus = {"正常", "破损", "破损严重", "丢失"}; //图书状态框 0正常，1破碎，2破损严重，3丢失

    BorrowInAndRenewServer server;

    boolean isRenew;

    // 表格列宽控制，只初始化一次
    boolean isFist = true;

    public BorrowInAndRenewView(BorrowBookView bv) {
        this(bv, false);
    }

    public BorrowInAndRenewView(BorrowBookView bv, boolean isRenew) {
        super(bv, "图书归还", true);

        if (isRenew) {
            this.setTitle("图书续借");
        }

        this.isRenew = isRenew; // 续借模式
        biv = this;
        server = new BorrowInAndRenewServer(this);

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
        jplBookInfo = new JPanel();
        jplSex = new JPanel();
        jplBookStatus = new JPanel();
        jplBackSetting = new JPanel();

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
        if (isRenew) {
            jlbBackSetting = new JLabel("续借信息");
        } else {
            jlbBackSetting = new JLabel("还入信息");
        }
        if(isRenew){
            cltBackPay = new ComboJLAndJT("续借天数", 20, true);//支付费用
        }else{
            cltBackPay = new ComboJLAndJT("支付费用", 20, true);//支付费用
        }
        jcbBookStatus = new JComboBox<>();
        jlbBookStatus = new JLabel("图书状态：");//图书状态

        //个人信息 6
        cltReaderId = new ComboJLAndJT("学    号：", 30);//学号
        cltReaderName = new ComboJLAndJT("姓    名：", 30);//姓名
        cltReaderApart = new ComboJLAndJT("学    院：", 30);//学院
        cltReaderClass = new ComboJLAndJT("班    级：", 30);//班级
        cltReaderTel = new ComboJLAndJT("联系方式：", 30);//联系方式
        jcbSex = new JComboBox<>();
        jcbSex.setEnabled(false);
        jlbSex = new JLabel("性    别：");

        btnRenew = new JButton("续借");
        btnBack = new JButton("还入");
        btnCancel = new JButton("取消");

        Init();
        resetData();
    }

    private void Init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(windowsWidth - 80, windowsHeight - 50);
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
        int unitH = (windowsHeight - 219 + 50) / 12;
        int unitW = (windowsWidth - 154 + 20);


        int heightOffset = 5;

        // 读者信息导航栏-------------------------------------------------------------------------------------/
        int xNav = 5, y1 = 5, widthNav = unitW - 10, heightNav = 40;
        jplReaderSearch.setLayout(null);
        jplReaderSearch.setBounds(xNav, y1, widthNav, heightNav);
        createSearchBar(unitW, jplReaderSearch, jlbReader, jtfReaderKeyword, btnSearchReader, btnPrevReader, btnNextReader);

        // 读者信息表格 -------------------------------------------------------------------------------------/
        int xInfo = 10, y2 = y1 + heightNav + heightOffset, widthInfo = unitW - 20, height2 = unitH * 2+2;
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
        jcbSex.setModel(new DefaultComboBoxModel<>(selectSex));
        jcbSex.setPreferredSize(new Dimension(187, 27));

        jplReaderInfo.add(cltReaderTel);//联系方式

        // 书籍信息搜索 -------------------------------------------------------------------------------------/
        int y3 = y2 + height2 + heightOffset;
        jplBookSearch.setLayout(null);
        jplBookSearch.setBounds(xNav, y3, widthNav, heightNav);
        createSearchBar(unitW, jplBookSearch, jlbBook, jtfBookKeyword, btnSearchBook, btnPrevBook, btnNextBook);

        // 已借阅书籍列表 -------------------------------------------------------------------------------------/
        int y4 = y3 + heightNav + heightOffset, height4 = unitH * 5 + 16;
        // 8
        jplBookInfo.setLayout(new GridLayout(1, 1));
        jplBookInfo.setBounds(xInfo, y4, widthInfo, height4);

        snpView.getViewport().add(tableDataView);
        jplBookInfo.add(snpView);


        // 还入设置 -------------------------------------------------------------------------------------/
        int y5 = y4 + height4 + heightOffset, heightSetting = 40;
        jplBackSetting.setLayout(null);
        jplBackSetting.setBounds(xNav, y5, widthNav, heightSetting);
        jplBackSetting.setBorder(BorderFactory.createEtchedBorder());
        Color color = new Color(236, 233, 216);
        jplBackSetting.setBackground(color);
        //还入设置
        jlbBackSetting.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        jlbBackSetting.setBounds(30, 5, 160, 30);
        jplBackSetting.add(jlbBackSetting);//借出设置

        cltBackPay.setBounds(unitW - 10 - 200 - 10 - 200 - 10, 1, 200, 35);
        cltBackPay.setBackground(color);
        if(isRenew){
            cltBackPay.setIText("30");
        }else{
            cltBackPay.setIText("0");
        }
        jplBackSetting.add(cltBackPay);//还入缴费

        jcbBookStatus.setModel(new DefaultComboBoxModel<>(selectBookStatus));
        jcbBookStatus.setPreferredSize(new Dimension(127, 27));
        jplBookStatus.setLayout(new FlowLayout());
        jplBookStatus.add(jlbBookStatus);
        jplBookStatus.add(jcbBookStatus);
        jplBookStatus.setBackground(color);
        jplBookStatus.setBounds(unitW - 10 - 200 - 10, 1, 200, 35);
        jplBackSetting.add(jplBookStatus);//还入状态


        // -------------------------------------------------------------------------------------/
        //添加监听事件
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

        //添加布局
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
        // 月白
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
     * 更新书籍信息
     *
     * @param cp   未还书籍
     * @param p    总借次数
     * @param list 书籍信息
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setBookInfo(int cp, int p, List<BorrowBeans> list,String keyword) {

        // 获取列宽
        int[] colW = new int[14];
        try {
            for (int i = 0; i < 14; i++) {
                colW[i] = this.tableDataView.getColumnModel().getColumn(i).getPreferredWidth();
                System.out.print(colW[i] + ", ");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("获取列宽失败：" + e.getMessage());
        }

        //1.设置表格中的标题数据集合
        Vector<String> title = new Vector<>();
        title.add("编号");
        title.add("书名");
        title.add("作者");
        title.add("出版社");
        title.add("书号");
        title.add("ISBN");
        title.add("价格");
        title.add("所在书室");
        title.add("所在书架");
        title.add("借出时间");
        title.add("数量");
        title.add("预计归还");
        title.add("归还时间");
        title.add("操作");

        //2.设置表格中的数据集合
        Vector<Vector> data = new Vector<>();
        Vector row;


        for (BorrowBeans b : list) {
            row = new Vector<>();
            row.add(b.getId()); // 编号
            row.add(b.getBookName()); // 书名
            row.add(b.getBookAuthor()); // 作者
            row.add(b.getBookPublisher()); // 出版社
            row.add(b.getBookId()); // 书籍编号
            row.add(b.getBookBarcode()); // ISBN
            row.add(b.getBookPrice()); // 价格
            row.add(b.getBookStack()); // 所在书室
            row.add(b.getBookShelf()); // 所在书架
            row.add(b.getCreateTimestamp()); // 借出时间
            row.add(b.getBookNumber()); // 数量
            row.add(b.getWillBackTimestamp()); // 预计归还
            row.add(b.getIsReturn()); // 归还时间
            row.add(b.getReturnAdminId()); // 归还操作
            data.add(row);
            // System.out.println(b);
        }

        //设置tableModel
        DefaultTableModel dtmView = new DefaultTableModel(data, title);
        //将tableModel绑定在table上
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

        if (sumGet < (sumStand - 100)) { // 误差太大了，使用标准方案
            colW = colWidthStand;
        }

        // 设置列宽
        for (int i = 0; i < 14; i++) {
            this.tableDataView.getColumnModel().getColumn(i).setPreferredWidth(colW[i]);
        }

        //设置表格自适应数据
        // tableDataView.FitTableColumns();

        if (p == 0) {
            jlbBook.setText("暂未借阅书籍");
        } else {
            if(keyword!=null){
                jlbBook.setText("未还搜索结果（" + cp + "/" + p + "）");
            }else {
                jlbBook.setText("未归还书籍（" + cp + "/" + p + "）");
            }
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

        server.loadBookByReader();
    }

    /**
     * 重置数据
     */
    public void resetData() {
        // 借阅设置
        jlbBook.setText("已借阅书籍");
        List<BorrowBeans> beans = new ArrayList<>();
        BorrowBeans infoBeans = new BorrowBeans();
        beans.add(infoBeans);
        setBookInfo(0, 0, beans,null);

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
