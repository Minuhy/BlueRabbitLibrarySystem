package com.bluerabbit.librarysystem.listener.borrow;

import com.bluerabbit.librarysystem.view.SetButtonUporDown;
import com.bluerabbit.librarysystem.view.borrow.BorrowBookView;
import com.bluerabbit.librarysystem.view.borrow.BorrowInAndRenewView;
import com.bluerabbit.librarysystem.view.borrow.BorrowInfoView;
import com.bluerabbit.librarysystem.view.borrow.BorrowOutView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author minuhy
 * @date 2023/3/8 16:03
 */
public class BorrowMouseListener implements java.awt.event.MouseListener {

    BorrowBookView bv;

    public BorrowMouseListener(BorrowBookView borrowBookView) {
        this.bv = borrowBookView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object clicked = e.getSource();
        if (clicked == bv.getBtnClose()) {
            System.out.println("�ر�");
            this.bv.dispose();
        } else if(clicked == bv.getBtnBorrow()){
            System.out.println("����");
            new BorrowOutView(bv);
            bv.updateTableData(1);
        }else if(clicked == bv.getExeInquire()){
            System.out.println("����");
            bv.updateTableData(1);
        }else if(clicked == bv.getBtnRenew()){
            System.out.println("����");
            new BorrowInAndRenewView(bv,true);
            bv.updateTableData(1);
        }else if(clicked == bv.getBtnReturn()){
            System.out.println("�黹");
            new BorrowInAndRenewView(bv);
            bv.updateTableData(1);
        }else if(clicked == bv.getBtnNexPage()){
            System.out.println("��һҳ");
            if(bv.getServer().getCurrentPage() < bv.getServer().getTotalPage()){
                bv.updateTableData(bv.getServer().getCurrentPage()+1);
            }else{
                System.out.println("���һҳ��");
            }
        }else if(clicked == bv.getBtnPrePage()){
            System.out.println("��һҳ");
            if(bv.getServer().getCurrentPage() > 1){
                bv.updateTableData(bv.getServer().getCurrentPage()-1);
            }else{
                System.out.println("��ǰһҳ��");
            }
        }else if(clicked == bv.getTableDataView()){
            System.out.println("���");
            mouse2Clicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object clicked = e.getSource();
        if(clicked instanceof JLabel){
            SetButtonUporDown.setButtonDown((JLabel) clicked);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object clicked = e.getSource();
        if(clicked instanceof JLabel){
            SetButtonUporDown.SetButtonUp((JLabel) clicked);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object clicked = e.getSource();
        if(clicked instanceof JLabel){
            SetButtonUporDown.SetButtonUp((JLabel) clicked);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object clicked = e.getSource();
        if(clicked instanceof JLabel){
            SetButtonUporDown.reSetButton((JLabel) clicked);
        }
    }



    private static  boolean flag = false;		//˫���¼���ִ��ʱ��Ϊ��
    private static int clickNum = 1;		//ָʾ�����������Ĭ��Ϊ����

    public void mouse2Clicked(MouseEvent e) {
        flag= false;
        if (clickNum==2) {
            //���������Ϊ2����˫���¼�
            this.mouseClickedTwice(e);
            //�������clickNum��Ϊ1
            clickNum=1;
            flag=true;
            return;
        }
        //�½���ʱ����˫�������Ϊ500ms
        java.util.Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            //ָʾ��ʱ��ִ�д���
            int num = 0;
            public void run() {
                // ˫���¼��Ѿ�ִ�У�ȡ����ʱ������
                if(flag) {
                    num=0;
                    clickNum=1;
                    this.cancel();
                    return;
                }
                //��ʱ���ٴ�ִ�У����õ����¼���Ȼ��ȡ����ʱ������
                if (num==1) {
                    mouseClickedOnce(e);
                    flag=true;
                    clickNum=1;
                    num=0;
                    this.cancel();
                    return;
                }
                clickNum++;
                num++;
            }
        },new Date(), 500);
    }
    protected void mouseClickedOnce(MouseEvent e) {
        // �����¼�
		System.out.println("�����¼� 1");
        Object clicked = e.getSource();
        if(bv.getTableDataView().equals(clicked)){
            System.out.println("���");
            // TODO
        }
    }
    private void mouseClickedTwice(MouseEvent e) {
        // ˫���¼�
        System.out.println("˫���¼� 2");
        Object clicked = e.getSource();
        if(bv.getTableDataView().equals(clicked)){
            System.out.println("���");
            // TODO

            new BorrowInfoView(bv,
                    (String) bv.getTableDataView()
                    .getValueAt(
                            bv.getTableDataView()
                                    .getSelectedRow(), 0));
        }
    }
}
