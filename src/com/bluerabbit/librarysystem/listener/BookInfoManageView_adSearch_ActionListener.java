package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.view.AdvancedSearchView;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
/**
 * ͼ���������߼������İ�ť���� 
 * @author minuy
 *
 */
public class BookInfoManageView_adSearch_ActionListener implements
		ActionListener {
	BookInfoManageView bookInfoManageView;
	public BookInfoManageView_adSearch_ActionListener(
			BookInfoManageView bookInfoManageView) {
		// TODO Auto-generated constructor stub
		this.bookInfoManageView = bookInfoManageView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new AdvancedSearchView(bookInfoManageView);
	}

}
