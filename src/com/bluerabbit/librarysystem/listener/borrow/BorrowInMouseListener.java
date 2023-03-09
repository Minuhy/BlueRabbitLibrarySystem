package com.bluerabbit.librarysystem.listener.borrow;

import com.bluerabbit.librarysystem.view.borrow.BorrowInView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author minuhy
 * @date 2023/3/9 21:34
 */
public class BorrowInMouseListener implements ActionListener {
    BorrowInView biv;
    public BorrowInMouseListener(BorrowInView borrowInView) {
        this.biv = borrowInView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
