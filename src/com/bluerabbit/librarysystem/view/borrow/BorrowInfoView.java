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

    //�ѽ���ֳ������֣�������Ϣ���鼮��Ϣ����������Ϣ
    private final JPanel borrowInfo;
    private final JPanel bookInfo;
    private final JPanel readerInfo;

    //������Ϣ
    private final ComboJLAndJT cltBorrowId; // ���ı��
    private final ComboJLAndJT cltBorrowReturnTime; // �黹ʱ��
    private final ComboJLAndJT cltBorrowTime; // ����ʱ��
    private final ComboJLAndJT cltBorrowWillReturn; // Ԥ�ƹ黹
    private final ComboJLAndJT cltBorrowNumber; // ��������
    private final ComboJLAndJT cltBorrowCoin; // Ԥ�ƽ���
    private final ComboJLAndJT cltBorrowAdmin; // �������
    private final ComboJLAndJT cltBorrowReturnAdmin; // �������

    //�鼮��Ϣ
    private final ComboJLAndJT cltBookName;//����
    private final ComboJLAndJT cltBookAuthor;//����
    private final ComboJLAndJT cltBookPublisher;//������
    private final ComboJLAndJT cltBookPublishDate;//��������
    private final ComboJLAndJT cltBookId;//�鿯���
    private final ComboJLAndJT cltBookBarcode;//�鿯����
    private final ComboJLAndJT cltBookStack;//����
    private final ComboJLAndJT cltBookShelf;//���
    private final ComboJLAndJT cltBookTotalNumber;//�ܲ���
    private final ComboJLAndJT cltBookNumber;//ʣ�����
    private final ComboJLAndJT cltBookPrice;//�۸�
    private final ComboJLAndJT cltBookLendTime;//�������

    //������Ϣ
    private final ComboJLAndJT cltReaderId; // ѧ    ��
    private final ComboJLAndJT cltReaderName; // ��    ��
    private final ComboJLAndJT cltReaderApart; // ѧ    Ժ
    private final ComboJLAndJT cltReaderClass; // ��    ��
    private final ComboJLAndJT cltReaderTel; // ��ϵ��ʽ
    private final JPanel jplSex;
    private final JComboBox<String> jcbSex;
    private final JLabel jlbSex;//�Ա�

    private final JButton btnOk;

    public BorrowInfoView biv;

    String[] select = {"��", "Ů"};//�Ա��

    public BorrowInfoView(BorrowBookView bv, String borrowDataId) {
        super(bv, "��������", true);
        System.out.println("���ı�ţ�" + borrowDataId);
        biv = this;
        //��ø���ͼ�Ĵ�С
        windowsHeight = bv.getHeight();
        windowsWidth = bv.getWidth();

        //���沿��
        mainView = new JPanel();
        functionView = new JPanel();
        contentView = new JPanel();
        jplSex = new JPanel();

        //���ݲ���
        borrowInfo = new JPanel();
        bookInfo = new JPanel();
        readerInfo = new JPanel();

        //������Ϣ 8
        cltBorrowId = new ComboJLAndJT("���ı�ţ�");//���ı��
        cltBorrowReturnTime = new ComboJLAndJT("�黹ʱ�䣺");//�黹ʱ��
        cltBorrowTime = new ComboJLAndJT("����ʱ�䣺");//����ʱ��
        cltBorrowWillReturn = new ComboJLAndJT("Ԥ�ƹ黹��");//Ԥ�ƹ黹ʱ��
        cltBorrowNumber = new ComboJLAndJT("����������");//�����鼮������
        cltBorrowCoin = new ComboJLAndJT("Ԥ�ƽ��ѣ�");//Ԥ�ƽ���
        cltBorrowAdmin = new ComboJLAndJT("�������");//�������Ա
        cltBorrowReturnAdmin = new ComboJLAndJT("�������");//�������Ա

        //�鼮��Ϣ 6
        cltBookName = new ComboJLAndJT("��    ����");//����
        cltBookAuthor = new ComboJLAndJT("��    �ߣ�");//����
        cltBookPublisher = new ComboJLAndJT("�� �� �磺");//������
        cltBookPublishDate = new ComboJLAndJT("�������ڣ�");//��������

        cltBookId = new ComboJLAndJT("�鿯��ţ�");//�鿯���
        cltBookBarcode = new ComboJLAndJT("�鿯���룺");//�鿯����
        cltBookStack = new ComboJLAndJT("��    �ң�");//��    ��
        cltBookShelf = new ComboJLAndJT("��    �ܣ�");//��    ��
        cltBookTotalNumber = new ComboJLAndJT("�� �� ����");//�ܲ���
        cltBookNumber = new ComboJLAndJT("ʣ�������");//ʣ�����
        cltBookPrice = new ComboJLAndJT("��    ��");//�۸�
        cltBookLendTime = new ComboJLAndJT("���������");//�������

        //������Ϣ 6
        cltReaderId = new ComboJLAndJT("ѧ    �ţ�", 30);//ѧ��
        cltReaderName = new ComboJLAndJT("��    ����", 30);//����
        cltReaderApart = new ComboJLAndJT("ѧ    Ժ��", 30);//ѧԺ
        cltReaderClass = new ComboJLAndJT("��    ����", 30);//�༶
        cltReaderTel = new ComboJLAndJT("��ϵ��ʽ��", 30);//��ϵ��ʽ
        jcbSex = new JComboBox<>();
        jcbSex.setEnabled(false);
        jlbSex = new JLabel("��    ��");


        btnOk = new JButton("ȷ��");

        if (borrowDataId == null) {
            borrowDataId = "";
        }

        if (!borrowDataId.equals("")) {
            //��Ϊ�գ����޸���Ϣ�������������ʼ����Ϣ
            System.out.println("���ؽ�����Ϣ��");
            loader(borrowDataId);
        } else {
            this.dispose();
            JOptionPane.showMessageDialog(bv, "���޴˼�¼");
        }

        Init();
    }

    private void loader(String borrowDataId) {
        //��ȡ������Ϣ
        BorrowDao dao = new BorrowDao();
        BorrowBeans beans = dao.getBorrowInfoByBorrowID(borrowDataId);

        if (beans == null) {
            JOptionPane.showMessageDialog(this, "û�в鵽����");
            return;
        }

        // ����л��صĹ���Ա��¼�����
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
                beans.setReturnAdminId("δ����");
            }
        } catch (Exception e) {
            System.out.println("���ҹ黹����Աʱ����" + e.getMessage());
        }


        //���ý�����Ϣ
        //������Ϣ
        cltBorrowId.setIText(beans.getId());//���ı��
        cltBorrowReturnTime.setIText(beans.getIsReturn());//�黹ʱ��
        cltBorrowTime.setIText(beans.getCreateTimestamp());//����ʱ��
        cltBorrowWillReturn.setIText(beans.getWillBackTimestamp());//Ԥ�ƹ黹ʱ��
        cltBorrowNumber.setIText(beans.getBookNumber());//�����鼮������
        cltBorrowCoin.setIText(beans.getPenalty());//Ԥ�ƽ���
        cltBorrowAdmin.setIText(beans.getBorrowAdminName() + "��" + beans.getBorrowAdminId() + "��");//�������Ա
        cltBorrowReturnAdmin.setIText(beans.getReturnAdminName() + "��" + beans.getReturnAdminId() + "��");//�������Ա

        //�鼮��Ϣ
        cltBookName.setIText(beans.getBookName());//����
        cltBookAuthor.setIText(beans.getBookAuthor());//����
        cltBookPublisher.setIText(beans.getBookPublisher());//������
        cltBookPublishDate.setIText(beans.getBookPublishDate());//��������

        cltBookId.setIText(beans.getBookId());//�鿯���
        cltBookBarcode.setIText(beans.getBookBarcode());//�鿯����
        cltBookStack.setIText(beans.getBookStack());//��    ��
        cltBookShelf.setIText(beans.getBookShelf());//��    ��

        cltBookTotalNumber.setIText(beans.getBookTotalNumber());//�ܲ���
        cltBookNumber.setIText(beans.getBookSurplusNumber());//ʣ�����
        cltBookPrice.setIText(beans.getBookPrice());//�۸�
        cltBookLendTime.setIText(beans.getBookLendTime());//�������

        //������Ϣ
        cltReaderId.setIText(beans.getReaderId());//ѧ��
        cltReaderName.setIText(beans.getReaderName());//����
        cltReaderApart.setIText(beans.getReaderApart());//ѧԺ
        cltReaderClass.setIText(beans.getReaderClass());//�༶
        cltReaderTel.setIText(beans.getReaderTel());//��ϵ��ʽ

        if (beans.getReaderSex().equals("Ů")) {
            select[0] = "Ů";
            select[1] = "��";
        }
    }

    private void Init() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(windowsWidth - 200, windowsHeight - 40);
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

        //������Ϣ 8
        borrowInfo.add(cltBorrowId);//���ı��
        borrowInfo.add(cltBorrowReturnTime);//�黹ʱ��
        borrowInfo.add(cltBorrowTime);//����ʱ��
        borrowInfo.add(cltBorrowWillReturn);//Ԥ�ƹ黹ʱ��
        borrowInfo.add(cltBorrowNumber);//�����鼮������
        borrowInfo.add(cltBorrowCoin);//Ԥ�ƽ���
        borrowInfo.add(cltBorrowAdmin);//�������Ա
        borrowInfo.add(cltBorrowReturnAdmin);//�������Ա

        //�鼮��Ϣ 6
        bookInfo.add(cltBookName);//����
        bookInfo.add(cltBookAuthor);//����
        bookInfo.add(cltBookPublisher);//������
        bookInfo.add(cltBookPublishDate);//��������
        bookInfo.add(cltBookId);//�鿯���
        bookInfo.add(cltBookBarcode);//�鿯����
        bookInfo.add(cltBookStack);//��    ��
        bookInfo.add(cltBookShelf);//��    ��
        bookInfo.add(cltBookTotalNumber);//�ܲ���
        bookInfo.add(cltBookNumber);//ʣ�����
        bookInfo.add(cltBookPrice);//�۸�
        bookInfo.add(cltBookLendTime);//�������

        //������Ϣ 6
        readerInfo.add(cltReaderId);//ѧ��
        readerInfo.add(cltReaderName);//����
        readerInfo.add(cltReaderApart);//ѧԺ
        readerInfo.add(cltReaderClass);//�༶
        readerInfo.add(cltReaderTel);//��ϵ��ʽ

        jplSex.setLayout(new FlowLayout());

        jplSex.add(jlbSex);
        jplSex.add(jcbSex);
        readerInfo.add(jplSex);//�Ա�
        jcbSex.setModel(new DefaultComboBoxModel<>(select));
        jcbSex.setPreferredSize(new Dimension(187, 27));

        //��Ӽ����¼�
        btnOk.addActionListener(e -> biv.dispose());

        //��Ӳ���
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
