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
            System.out.println("续借");
            b3v.getServer().renew();
        } else if (clicked == b3v.getBtnBack()) {
            System.out.println("还入");
            b3v.getServer().back();
        } else  if (clicked == b3v.getBtnPrevBook()) {
            System.out.println("上一页");
            b3v.getServer().prevBook();
        }  else  if (clicked == b3v.getBtnNextBook()) {
            System.out.println("下一页");
            b3v.getServer().nextBook();
        } else  if (clicked == b3v.getBtnNextReader()) {
            System.out.println("下一个人");
            b3v.getServer().nextReader();
        } else if(clicked == b3v.getBtnPrevReader()){
            System.out.println("上一个人");
            b3v.getServer().prevReader();
        }else if(clicked == b3v.getBtnSearchBook()){
            System.out.println("搜索书");
            b3v.getServer().searchBook();
        }else if(clicked == b3v.getBtnSearchReader()){
            System.out.println("搜索人");
            b3v.getServer().searchReader();
        }

    }
}
