package com.bluerabbit.librarysystem.listener.borrow;

import com.bluerabbit.librarysystem.view.borrow.BorrowInAndRenewView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author minuhy
 * @date 2023/3/9 21:34
 */
public class BorrowInAndRenewMouseListener implements ActionListener {
    BorrowInAndRenewView b3v;
    public BorrowInAndRenewMouseListener(BorrowInAndRenewView b3v) {
        this.b3v = b3v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object clicked = e.getSource();
        if (clicked == b3v.getBtnRenew()) {
            System.out.println("����");
            b3v.getServer().renew();
        } else if (clicked == b3v.getBtnBack()) {
            System.out.println("����");
            b3v.getServer().back();
        } else  if (clicked == b3v.getBtnPrevBook()) {
            System.out.println("��һҳ");
            b3v.getServer().prevBook();
        }  else  if (clicked == b3v.getBtnNextBook()) {
            System.out.println("��һҳ");
            b3v.getServer().nextBook();
        } else  if (clicked == b3v.getBtnNextReader()) {
            System.out.println("��һ����");
            b3v.getServer().nextReader();
        } else if(clicked == b3v.getBtnPrevReader()){
            System.out.println("��һ����");
            b3v.getServer().prevReader();
        }else if(clicked == b3v.getBtnSearchBook()){
            System.out.println("������");
            b3v.getServer().searchBook();
        }else if(clicked == b3v.getBtnSearchReader()){
            System.out.println("������");
            b3v.getServer().searchReader();
        }

    }
}
