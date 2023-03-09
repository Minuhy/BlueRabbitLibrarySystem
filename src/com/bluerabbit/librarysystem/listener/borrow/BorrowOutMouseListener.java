package com.bluerabbit.librarysystem.listener.borrow;

import com.bluerabbit.librarysystem.view.borrow.BorrowOutView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * @author minuhy
 * @date 2023/3/9 2:22
 */
public class BorrowOutMouseListener implements ActionListener {

    BorrowOutView bov;

    public BorrowOutMouseListener(BorrowOutView bov) {
        this.bov = bov;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object clicked = e.getSource();
        if (clicked == bov.getBtnBorrow()) {
            System.out.println("���");
            bov.getServer().out();
        } else  if (clicked == bov.getBtnPrevBook()) {
            System.out.println("��һ����");
            bov.getServer().prevBook();
        }  else  if (clicked == bov.getBtnNextBook()) {
            System.out.println("��һ����");
            bov.getServer().nextBook();
        } else  if (clicked == bov.getBtnNextReader()) {
            System.out.println("��һ����");
            bov.getServer().nextReader();
        } else if(clicked == bov.getBtnPrevReader()){
            System.out.println("��һ����");
            bov.getServer().prevReader();
        }else if(clicked == bov.getBtnSearchBook()){
            System.out.println("������");
            bov.getServer().searchBook();
        }else if(clicked == bov.getBtnSearchReader()){
            System.out.println("������");
            bov.getServer().searchReader();
        }

    }
}
