package com.bluerabbit.librarysystem.view.borrow;

import com.bluerabbit.librarysystem.beans.BorrowBeans;
import com.bluerabbit.librarysystem.listener.borrow.BorrowMouseListener;
import com.bluerabbit.librarysystem.service.borrow.BorrowMainServer;
import com.bluerabbit.librarysystem.view.CenterView;
import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.MyTable;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * @author minuhy
 * @date 2023/3/8 15:18
 */
public class BorrowBookView extends JDialog {
    /**
	 * UID
	 */
	private static final long serialVersionUID = -9040238486787055265L;

	public  final int BUTTON_SIZE = 50;

    private final JPanel jPanelBorrow; // 主低面
    private final int windowsHeight; // 窗口高度
    private final int windowsWidth; // 窗口宽度


    private final  JPanel buttonView; // 按钮组
    private final  JPanel toolView; // 工具组
    private final  JPanel tableView; // 表格组
    private  final JPanel infoView; // 信息组
    private  final JPanel pageView; // 分页按钮组

    private  final JLabel btnPrePage; // 上一页按钮
    private  final JLabel tipPage; // 页数提示
    private  final JLabel btnNexPage; // 下一页按钮

    private final  JLabel btnBorrow; // 借阅按钮
    private final  JLabel btnReturn; // 归还按钮
    private  final JLabel btnRenew; // 续借按钮
    private  final JLabel btnClose; // 关闭按钮

    private  final JLabel inqTipWay;
    private  final JLabel inqTipInfo;
    private  final JComboBox<String> chooseWay;
    private  final JTextField chooseInfo;
    private  final JLabel exeInquire;

    private final  MyTable tableDataView;
    private  final JScrollPane snpView;

    BorrowMainServer server;

    public BorrowBookView(MainView mv){
        super(mv,"借阅管理",true);
        windowsHeight = mv.getHeight();
        windowsWidth = mv.getWidth();
        jPanelBorrow = new JPanel();
        buttonView = new JPanel();
        btnBorrow = new JLabel("借阅",JLabel.CENTER);
        btnReturn = new JLabel("归还",JLabel.CENTER);
        btnRenew = new JLabel("续借",JLabel.CENTER);
        btnClose = new JLabel("关闭",JLabel.CENTER);
        infoView = new JPanel();
        toolView = new JPanel();
        inqTipWay = new JLabel("选择搜索方式");
        inqTipInfo = new JLabel("输入关键词");
        chooseWay = new JComboBox<>();
        chooseInfo = new JTextField();
        exeInquire = new JLabel("搜索一下",JLabel.CENTER);
        tableView = new JPanel();
        snpView = new JScrollPane();
        tableDataView = new MyTable();

        pageView = new JPanel();
        btnPrePage = new JLabel("上一页",JLabel.CENTER);
        btnNexPage = new JLabel("下一页",JLabel.CENTER);
        tipPage = new JLabel("0/0",JLabel.CENTER);

        server = new BorrowMainServer(this);

        Init();

        //获取数据
        updateTableData(1);

        showView();
    }

    private void Init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(windowsWidth-100, windowsHeight -118);
        CenterView.CenterByWindow(this);
        //不允许用户调整窗口大小
        this.setResizable(false);

        //边界布局
        jPanelBorrow.setLayout(new BorderLayout());
        //流水布局
        buttonView.setLayout(new FlowLayout(FlowLayout.LEFT));
        //边界布局
        infoView.setLayout(new BorderLayout());
        //流水布局
        toolView.setLayout(new FlowLayout(FlowLayout.LEFT));
        //表格布局
        tableView.setLayout(new GridLayout(1,1));


        // 设置按钮大小
        btnBorrow.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        btnReturn.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        btnRenew.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        btnClose.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));

        btnPrePage.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE/2));
        btnNexPage.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE/2));


        // 设置按钮边框样式
        btnBorrow.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        btnReturn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        btnRenew.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        btnClose.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        btnPrePage.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        btnNexPage.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        // 添加按钮
        buttonView.add(btnBorrow,BorderLayout.CENTER);
        buttonView.add(btnReturn,BorderLayout.CENTER);
        buttonView.add(btnRenew,BorderLayout.CENTER);
        buttonView.add(btnClose,BorderLayout.CENTER);

        pageView.add(btnPrePage,BorderLayout.CENTER);
        pageView.add(tipPage,BorderLayout.CENTER);
        pageView.add(btnNexPage,BorderLayout.CENTER);

        //工具栏的处理
        //提示text
        chooseWay.addItem("学号");
        chooseWay.addItem("书号");
        chooseWay.addItem("姓名");
        chooseWay.addItem("书名");
        chooseWay.addItem("归还时间");

        toolView.add(inqTipWay);
        toolView.add(chooseWay);
        toolView.add(inqTipInfo);
        toolView.add(chooseInfo);
        chooseInfo.setPreferredSize(new Dimension(100,30));
        toolView.add(exeInquire,BorderLayout.CENTER);
        //设置边框
        exeInquire.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        // 设置大小
        exeInquire.setPreferredSize(new Dimension(80,30));

        //创建监听器
        BorrowMouseListener listener = new BorrowMouseListener(this);

        //为搜索按钮添加监听
        exeInquire.addMouseListener(listener);

        //表格的处理
        tableDataView.addMouseListener(listener);

        snpView.getViewport().add(tableDataView);
        tableView.add(snpView);

        jPanelBorrow.add(buttonView, BorderLayout.NORTH);
        jPanelBorrow.add(infoView, BorderLayout.CENTER);
        jPanelBorrow.add(pageView, BorderLayout.PAGE_END);

        infoView.add(toolView, BorderLayout.NORTH);
        infoView.add(snpView, BorderLayout.CENTER);

        //监听事件
        btnBorrow.addMouseListener(listener);
        btnReturn.addMouseListener(listener);
        btnRenew.addMouseListener(listener);
        btnClose.addMouseListener(listener);

        btnPrePage.addMouseListener(listener);
        btnNexPage.addMouseListener(listener);
    }

    private void showView(){
        this.add(jPanelBorrow);
        this.setVisible(true);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setTableData(List<BorrowBeans> list) {
        //1.设置表格中的标题数据集合
        Vector<String> title = new Vector<>();
        title.add("借阅编号");
        title.add("借阅人学号");
        title.add("借阅人姓名");
        title.add("图书编号");
        title.add("图书名称");
        title.add("借书时间");
        title.add("应还时间");
        title.add("归还时间");

        //2.设置表格中的数据集合
        Vector<Vector> data = new Vector<>();

        for(BorrowBeans b : list) {
            Vector row = new Vector();

            row.add(b.getId());
            row.add(b.getReaderId());
            row.add(b.getReaderName());
            row.add(b.getBookId());
            row.add(b.getBookName());
            row.add(b.getCreateTimestamp());
            row.add(b.getWillBackTimestamp());
            row.add(b.getIsReturn());

            data.add(row);
        }

        //设置tableModel
        DefaultTableModel dtmView = new DefaultTableModel(data, title);
        //将tableModel绑定在table上
        this.tableDataView.setModel(dtmView);

        //设置表格自适应数据
        tableDataView.FitTableColumns();
    }


    //给监听类放通
    public JLabel getBtnBorrow() {
        return btnBorrow;
    }

    public JLabel getBtnClose() {
        return btnClose;
    }

    public JLabel getBtnRenew() {
        return btnRenew;
    }

    public JLabel getBtnReturn() {
        return btnReturn;
    }

    //更新表单数据
    public void updateTableData(int page) {
        String keyWord = chooseInfo.getText();
        String way = chooseWay.getSelectedItem().toString();
        server.Search(way,keyWord,page);
    }

    public JLabel getExeInquire() {
        return this.exeInquire;
    }

    public BorrowMainServer getServer(){
        return server;
    }

    public MyTable getTableDataView(){
        return tableDataView;
    }

    public JLabel getBtnPrePage() {
        return btnPrePage;
    }

    public JLabel getBtnNexPage() {
        return btnNexPage;
    }

    public JLabel getTipPage() {
        return tipPage;
    }
}
