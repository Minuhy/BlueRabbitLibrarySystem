package com.bluerabbit.librarysystem.view.borrow;

import com.bluerabbit.librarysystem.beans.BorrowBeans;
import com.bluerabbit.librarysystem.dao.BorrowDao;
import com.bluerabbit.librarysystem.view.CenterView;
import com.bluerabbit.librarysystem.view.ComboJLAndJT;

import javax.swing.*;
import java.awt.*;

/**
 * @author minuhy
 * @date 2023/3/8 18:12
 */
public class BorrowInfoView extends JDialog {
    private final JPanel mainView;
    private final JPanel contentView;
    private final JPanel functionView;
    private final int windowsHeight;
    private final int windowsWidth;

    //把界面分成三部分，借阅信息、书籍信息、借阅人信息
    private final JPanel borrowInfo;
    private final JPanel bookInfo;
    private final JPanel readerInfo;

    //借阅信息
    private final ComboJLAndJT cltBorrowId; // 借阅编号
    private final ComboJLAndJT cltBorrowReturnTime; // 归还时间
    private final ComboJLAndJT cltBorrowTime; // 借阅时间
    private final ComboJLAndJT cltBorrowWillReturn; // 预计归还
    private final ComboJLAndJT cltBorrowNumber; // 借阅数量
    private final ComboJLAndJT cltBorrowCoin; // 预计交费
    private final ComboJLAndJT cltBorrowAdmin; // 借出管理
    private final ComboJLAndJT cltBorrowReturnAdmin; // 还入管理

    //书籍信息
    private final ComboJLAndJT cltBookName;//书名
    private final ComboJLAndJT cltBookAuthor;//作者
    private final ComboJLAndJT cltBookPublisher;//出版社
    private final ComboJLAndJT cltBookPublishDate;//出版日期
    private final ComboJLAndJT cltBookId;//书刊编号
    private final ComboJLAndJT cltBookBarcode;//书刊条码
    private final ComboJLAndJT cltBookStack;//书室
    private final ComboJLAndJT cltBookShelf;//书架
    private final ComboJLAndJT cltBookTotalNumber;//总册数
    private final ComboJLAndJT cltBookNumber;//剩余册数
    private final ComboJLAndJT cltBookPrice;//价格
    private final ComboJLAndJT cltBookLendTime;//借出次数

    //其他信息
    private final ComboJLAndJT cltReaderId; // 学    号
    private final ComboJLAndJT cltReaderName; // 姓    名
    private final ComboJLAndJT cltReaderApart; // 学    院
    private final ComboJLAndJT cltReaderClass; // 班    级
    private final ComboJLAndJT cltReaderTel; // 联系方式
    private final JPanel jplSex;
    private final JComboBox<String> jcbSex;
    private final JLabel jlbSex;//性别

    private final JButton btnOk;

    public BorrowInfoView biv;

    String[] select = {"男", "女"};//性别框

    public BorrowInfoView(BorrowBookView bv, String borrowDataId) {
        super(bv, "借阅详情", true);
        System.out.println("借阅编号：" + borrowDataId);
        biv = this;
        //获得父视图的大小
        windowsHeight = bv.getHeight();
        windowsWidth = bv.getWidth();

        //界面部分
        mainView = new JPanel();
        functionView = new JPanel();
        contentView = new JPanel();
        jplSex = new JPanel();

        //内容部分
        borrowInfo = new JPanel();
        bookInfo = new JPanel();
        readerInfo = new JPanel();

        //借阅信息 8
        cltBorrowId = new ComboJLAndJT("借阅编号：");//借阅编号
        cltBorrowReturnTime = new ComboJLAndJT("归还时间：");//归还时间
        cltBorrowTime = new ComboJLAndJT("借阅时间：");//借阅时间
        cltBorrowWillReturn = new ComboJLAndJT("预计归还：");//预计归还时间
        cltBorrowNumber = new ComboJLAndJT("借阅数量：");//借阅书籍的数量
        cltBorrowCoin = new ComboJLAndJT("预计交费：");//预计交费
        cltBorrowAdmin = new ComboJLAndJT("借出管理：");//借出管理员
        cltBorrowReturnAdmin = new ComboJLAndJT("还入管理：");//还入管理员

        //书籍信息 6
        cltBookName = new ComboJLAndJT("书    名：");//书名
        cltBookAuthor = new ComboJLAndJT("作    者：");//作者
        cltBookPublisher = new ComboJLAndJT("出 版 社：");//出版社
        cltBookPublishDate = new ComboJLAndJT("出版日期：");//出版日期

        cltBookId = new ComboJLAndJT("书刊编号：");//书刊编号
        cltBookBarcode = new ComboJLAndJT("书刊条码：");//书刊条码
        cltBookStack = new ComboJLAndJT("书    室：");//书    室
        cltBookShelf = new ComboJLAndJT("书    架：");//书    架
        cltBookTotalNumber = new ComboJLAndJT("总 册 数：");//总册数
        cltBookNumber = new ComboJLAndJT("剩余册数：");//剩余册数
        cltBookPrice = new ComboJLAndJT("价    格：");//价格
        cltBookLendTime = new ComboJLAndJT("借出次数：");//借出次数

        //个人信息 6
        cltReaderId = new ComboJLAndJT("学    号：", 30);//学号
        cltReaderName = new ComboJLAndJT("姓    名：", 30);//姓名
        cltReaderApart = new ComboJLAndJT("学    院：", 30);//学院
        cltReaderClass = new ComboJLAndJT("班    级：", 30);//班级
        cltReaderTel = new ComboJLAndJT("联系方式：", 30);//联系方式
        jcbSex = new JComboBox<>();
        jcbSex.setEnabled(false);
        jlbSex = new JLabel("性    别：");


        btnOk = new JButton("确定");

        if (borrowDataId == null) {
            borrowDataId = "";
        }

        if (!borrowDataId.equals("")) {
            //不为空，是修改信息，在这里载入初始化信息
            System.out.println("加载借阅信息！");
            loader(borrowDataId);
        } else {
            this.dispose();
            JOptionPane.showMessageDialog(bv, "查无此记录");
        }

        Init();
    }

    private void loader(String borrowDataId) {
        //获取借阅信息
        BorrowDao dao = new BorrowDao();
        BorrowBeans beans = dao.getBorrowInfoByBorrowID(borrowDataId);

        if (beans == null) {
            JOptionPane.showMessageDialog(this, "没有查到数据");
            return;
        }

        // 如果有还回的管理员记录，则查
        try {
            if (beans.getReturnAdminId() != null) {
                if (!beans.getReturnAdminId().equals("")) {
                    if (Integer.parseInt(beans.getReturnAdminId()) != 0) {
                        BorrowBeans returnBeans = dao.getBorrowInfoInByBorrowID(borrowDataId);
                        if (returnBeans != null) {
                            beans.setReturnAdminName(returnBeans.getReturnAdminName());
                        }
                    }
                }
            } else {
                beans.setReturnAdminId("未还入");
            }
        } catch (Exception e) {
            System.out.println("查找归还管理员时出错：" + e.getMessage());
        }


        //设置借阅信息
        //借阅信息
        cltBorrowId.setIText(beans.getId());//借阅编号
        cltBorrowReturnTime.setIText(beans.getIsReturn());//归还时间
        cltBorrowTime.setIText(beans.getCreateTimestamp());//借阅时间
        cltBorrowWillReturn.setIText(beans.getWillBackTimestamp());//预计归还时间
        cltBorrowNumber.setIText(beans.getBookNumber());//借阅书籍的数量
        cltBorrowCoin.setIText(beans.getPenalty());//预计交费
        cltBorrowAdmin.setIText(beans.getBorrowAdminName() + "（" + beans.getBorrowAdminId() + "）");//借出管理员
        cltBorrowReturnAdmin.setIText(beans.getReturnAdminName() + "（" + beans.getReturnAdminId() + "）");//还入管理员

        //书籍信息
        cltBookName.setIText(beans.getBookName());//书名
        cltBookAuthor.setIText(beans.getBookAuthor());//作者
        cltBookPublisher.setIText(beans.getBookPublisher());//出版社
        cltBookPublishDate.setIText(beans.getBookPublishDate());//出版日期

        cltBookId.setIText(beans.getBookId());//书刊编号
        cltBookBarcode.setIText(beans.getBookBarcode());//书刊条码
        cltBookStack.setIText(beans.getBookStack());//书    室
        cltBookShelf.setIText(beans.getBookShelf());//书    架

        cltBookTotalNumber.setIText(beans.getBookTotalNumber());//总册数
        cltBookNumber.setIText(beans.getBookSurplusNumber());//剩余册数
        cltBookPrice.setIText(beans.getBookPrice());//价格
        cltBookLendTime.setIText(beans.getBookLendTime());//借出次数

        //个人信息
        cltReaderId.setIText(beans.getReaderId());//学号
        cltReaderName.setIText(beans.getReaderName());//姓名
        cltReaderApart.setIText(beans.getReaderApart());//学院
        cltReaderClass.setIText(beans.getReaderClass());//班级
        cltReaderTel.setIText(beans.getReaderTel());//联系方式

        if (beans.getReaderSex().equals("女")) {
            select[0] = "女";
            select[1] = "男";
        }
    }

    private void Init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(windowsWidth - 200, windowsHeight - 40);
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
        int unitH = (windowsHeight - 219 + 60) / 12;
        int unitW = (windowsWidth - 154 - 100);
        // 8
        borrowInfo.setLayout(new GridLayout(4, 2));
        borrowInfo.setBounds(0, 0, unitW, unitH * 4);
        borrowInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        // 6
        readerInfo.setLayout(new GridLayout(2, 3));
        readerInfo.setBounds(0, unitH * 4, unitW, unitH * 2);
        readerInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        // 8
        bookInfo.setLayout(new GridLayout(6, 2));
        bookInfo.setBounds(0, unitH * 6 + 1, unitW, unitH * 6 - 4);

        //借阅信息 8
        borrowInfo.add(cltBorrowId);//借阅编号
        borrowInfo.add(cltBorrowReturnTime);//归还时间
        borrowInfo.add(cltBorrowTime);//借阅时间
        borrowInfo.add(cltBorrowWillReturn);//预计归还时间
        borrowInfo.add(cltBorrowNumber);//借阅书籍的数量
        borrowInfo.add(cltBorrowCoin);//预计交费
        borrowInfo.add(cltBorrowAdmin);//借出管理员
        borrowInfo.add(cltBorrowReturnAdmin);//还入管理员

        //书籍信息 6
        bookInfo.add(cltBookName);//书名
        bookInfo.add(cltBookAuthor);//作者
        bookInfo.add(cltBookPublisher);//出版社
        bookInfo.add(cltBookPublishDate);//出版日期
        bookInfo.add(cltBookId);//书刊编号
        bookInfo.add(cltBookBarcode);//书刊条码
        bookInfo.add(cltBookStack);//书    室
        bookInfo.add(cltBookShelf);//书    架
        bookInfo.add(cltBookTotalNumber);//总册数
        bookInfo.add(cltBookNumber);//剩余册数
        bookInfo.add(cltBookPrice);//价格
        bookInfo.add(cltBookLendTime);//借出次数

        //个人信息 6
        readerInfo.add(cltReaderId);//学号
        readerInfo.add(cltReaderName);//姓名
        readerInfo.add(cltReaderApart);//学院
        readerInfo.add(cltReaderClass);//班级
        readerInfo.add(cltReaderTel);//联系方式

        jplSex.setLayout(new FlowLayout());

        jplSex.add(jlbSex);
        jplSex.add(jcbSex);
        readerInfo.add(jplSex);//性别
        jcbSex.setModel(new DefaultComboBoxModel<>(select));
        jcbSex.setPreferredSize(new Dimension(187, 27));

        //添加监听事件
        btnOk.addActionListener(e -> biv.dispose());

        //添加布局
        contentView.add(borrowInfo);
        contentView.add(bookInfo);
        contentView.add(readerInfo);
        functionView.add(btnOk);

        mainView.add(functionView, BorderLayout.SOUTH);
        mainView.add(contentView, BorderLayout.CENTER);
        this.add(mainView);
        this.setVisible(true);
    }
}
