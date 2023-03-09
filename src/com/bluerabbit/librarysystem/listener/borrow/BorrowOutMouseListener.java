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
            System.out.println("借出");
            bov.getServer().out();
        } else  if (clicked == bov.getBtnPrevBook()) {
            System.out.println("上一本书");
            bov.getServer().prevBook();
        }  else  if (clicked == bov.getBtnNextBook()) {
            System.out.println("下一本书");
            bov.getServer().nextBook();
        } else  if (clicked == bov.getBtnNextReader()) {
            System.out.println("下一个人");
            bov.getServer().nextReader();
        } else if(clicked == bov.getBtnPrevReader()){
            System.out.println("上一个人");
            bov.getServer().prevReader();
        }else if(clicked == bov.getBtnSearchBook()){
            System.out.println("搜索书");
            bov.getServer().searchBook();
        }else if(clicked == bov.getBtnSearchReader()){
            System.out.println("搜索人");
            bov.getServer().searchReader();
        }

    }
}
