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

    private final JPanel jPanelBorrow; // ������
    private final int windowsHeight; // ���ڸ߶�
    private final int windowsWidth; // ���ڿ��


    private final  JPanel buttonView; // ��ť��
    private final  JPanel toolView; // ������
    private final  JPanel tableView; // �����
    private  final JPanel infoView; // ��Ϣ��
    private  final JPanel pageView; // ��ҳ��ť��

    private  final JLabel btnPrePage; // ��һҳ��ť
    private  final JLabel tipPage; // ҳ����ʾ
    private  final JLabel btnNexPage; // ��һҳ��ť

    private final  JLabel btnBorrow; // ���İ�ť
    private final  JLabel btnReturn; // �黹��ť
    private  final JLabel btnRenew; // ���谴ť
    private  final JLabel btnClose; // �رհ�ť

    private  final JLabel inqTipWay;
    private  final JLabel inqTipInfo;
    private  final JComboBox<String> chooseWay;
    private  final JTextField chooseInfo;
    private  final JLabel exeInquire;

    private final  MyTable tableDataView;
    private  final JScrollPane snpView;

    BorrowMainServer server;

    public BorrowBookView(MainView mv){
        super(mv,"���Ĺ���",true);
        windowsHeight = mv.getHeight();
        windowsWidth = mv.getWidth();
        jPanelBorrow = new JPanel();
        buttonView = new JPanel();
        btnBorrow = new JLabel("����",JLabel.CENTER);
        btnReturn = new JLabel("�黹",JLabel.CENTER);
        btnRenew = new JLabel("����",JLabel.CENTER);
        btnClose = new JLabel("�ر�",JLabel.CENTER);
        infoView = new JPanel();
        toolView = new JPanel();
        inqTipWay = new JLabel("ѡ��������ʽ");
        inqTipInfo = new JLabel("����ؼ���");
        chooseWay = new JComboBox<>();
        chooseInfo = new JTextField();
        exeInquire = new JLabel("����һ��",JLabel.CENTER);
        tableView = new JPanel();
        snpView = new JScrollPane();
        tableDataView = new MyTable();

        pageView = new JPanel();
        btnPrePage = new JLabel("��һҳ",JLabel.CENTER);
        btnNexPage = new JLabel("��һҳ",JLabel.CENTER);
        tipPage = new JLabel("0/0",JLabel.CENTER);

        server = new BorrowMainServer(this);

        Init();

        //��ȡ����
        updateTableData(1);

        showView();
    }

    private void Init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(windowsWidth-100, windowsHeight -118);
        CenterView.CenterByWindow(this);
        //�������û��������ڴ�С
        this.setResizable(false);

        //�߽粼��
        jPanelBorrow.setLayout(new BorderLayout());
        //��ˮ����
        buttonView.setLayout(new FlowLayout(FlowLayout.LEFT));
        //�߽粼��
        infoView.setLayout(new BorderLayout());
        //��ˮ����
        toolView.setLayout(new FlowLayout(FlowLayout.LEFT));
        //��񲼾�
        tableView.setLayout(new GridLayout(1,1));


        // ���ð�ť��С
        btnBorrow.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        btnReturn.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        btnRenew.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
        btnClose.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));

        btnPrePage.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE/2));
        btnNexPage.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE/2));


        // ���ð�ť�߿���ʽ
        btnBorrow.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        btnReturn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        btnRenew.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        btnClose.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        btnPrePage.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        btnNexPage.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        // ��Ӱ�ť
        buttonView.add(btnBorrow,BorderLayout.CENTER);
        buttonView.add(btnReturn,BorderLayout.CENTER);
        buttonView.add(btnRenew,BorderLayout.CENTER);
        buttonView.add(btnClose,BorderLayout.CENTER);

        pageView.add(btnPrePage,BorderLayout.CENTER);
        pageView.add(tipPage,BorderLayout.CENTER);
        pageView.add(btnNexPage,BorderLayout.CENTER);

        //�������Ĵ���
        //��ʾtext
        chooseWay.addItem("ѧ��");
        chooseWay.addItem("���");
        chooseWay.addItem("����");
        chooseWay.addItem("����");
        chooseWay.addItem("�黹ʱ��");

        toolView.add(inqTipWay);
        toolView.add(chooseWay);
        toolView.add(inqTipInfo);
        toolView.add(chooseInfo);
        chooseInfo.setPreferredSize(new Dimension(100,30));
        toolView.add(exeInquire,BorderLayout.CENTER);
        //���ñ߿�
        exeInquire.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        // ���ô�С
        exeInquire.setPreferredSize(new Dimension(80,30));

        //����������
        BorrowMouseListener listener = new BorrowMouseListener(this);

        //Ϊ������ť��Ӽ���
        exeInquire.addMouseListener(listener);

        //���Ĵ���
        tableDataView.addMouseListener(listener);

        snpView.getViewport().add(tableDataView);
        tableView.add(snpView);

        jPanelBorrow.add(buttonView, BorderLayout.NORTH);
        jPanelBorrow.add(infoView, BorderLayout.CENTER);
        jPanelBorrow.add(pageView, BorderLayout.PAGE_END);

        infoView.add(toolView, BorderLayout.NORTH);
        infoView.add(snpView, BorderLayout.CENTER);

        //�����¼�
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
        //1.���ñ���еı������ݼ���
        Vector<String> title = new Vector<>();
        title.add("���ı��");
        title.add("������ѧ��");
        title.add("����������");
        title.add("ͼ����");
        title.add("ͼ������");
        title.add("����ʱ��");
        title.add("Ӧ��ʱ��");
        title.add("�黹ʱ��");

        //2.���ñ���е����ݼ���
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

        //����tableModel
        DefaultTableModel dtmView = new DefaultTableModel(data, title);
        //��tableModel����table��
        this.tableDataView.setModel(dtmView);

        //���ñ������Ӧ����
        tableDataView.FitTableColumns();
    }


    //���������ͨ
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

    //���±�����
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
