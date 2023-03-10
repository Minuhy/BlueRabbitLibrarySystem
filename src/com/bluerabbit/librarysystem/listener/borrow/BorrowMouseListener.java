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
            System.out.println("关闭");
            this.bv.dispose();
        } else if(clicked == bv.getBtnBorrow()){
            System.out.println("借阅");
            new BorrowOutView(bv);
            bv.updateTableData(1);
        }else if(clicked == bv.getExeInquire()){
            System.out.println("搜索");
            bv.updateTableData(1);
        }else if(clicked == bv.getBtnRenew()){
            System.out.println("续借");
            new BorrowInAndRenewView(bv,true);
            bv.updateTableData(1);
        }else if(clicked == bv.getBtnReturn()){
            System.out.println("归还");
            new BorrowInAndRenewView(bv);
            bv.updateTableData(1);
        }else if(clicked == bv.getBtnNexPage()){
            System.out.println("下一页");
            if(bv.getServer().getCurrentPage() < bv.getServer().getTotalPage()){
                bv.updateTableData(bv.getServer().getCurrentPage()+1);
            }else{
                System.out.println("最后一页了");
            }
        }else if(clicked == bv.getBtnPrePage()){
            System.out.println("上一页");
            if(bv.getServer().getCurrentPage() > 1){
                bv.updateTableData(bv.getServer().getCurrentPage()-1);
            }else{
                System.out.println("最前一页了");
            }
        }else if(clicked == bv.getTableDataView()){
            System.out.println("表格");
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



    private static  boolean flag = false;		//双击事件已执行时置为真
    private static int clickNum = 1;		//指示鼠标点击次数，默认为单击

    public void mouse2Clicked(MouseEvent e) {
        flag= false;
        if (clickNum==2) {
            //鼠标点击次数为2调用双击事件
            this.mouseClickedTwice(e);
            //调用完毕clickNum置为1
            clickNum=1;
            flag=true;
            return;
        }
        //新建定时器，双击检测间隔为500ms
        java.util.Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            //指示定时器执行次数
            int num = 0;
            public void run() {
                // 双击事件已经执行，取消定时器任务
                if(flag) {
                    num=0;
                    clickNum=1;
                    this.cancel();
                    return;
                }
                //定时器再次执行，调用单击事件，然后取消定时器任务
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
        // 单击事件
		System.out.println("单击事件 1");
        Object clicked = e.getSource();
        if(bv.getTableDataView().equals(clicked)){
            System.out.println("表格");
            // TODO
        }
    }
    private void mouseClickedTwice(MouseEvent e) {
        // 双击事件
        System.out.println("双击事件 2");
        Object clicked = e.getSource();
        if(bv.getTableDataView().equals(clicked)){
            System.out.println("表格");
            // TODO

            new BorrowInfoView(bv,
                    (String) bv.getTableDataView()
                    .getValueAt(
                            bv.getTableDataView()
                                    .getSelectedRow(), 0));
        }
    }
}
