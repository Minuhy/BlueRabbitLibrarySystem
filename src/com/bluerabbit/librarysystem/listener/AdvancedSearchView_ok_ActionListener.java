package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.service.AdvancedSearchServer;
import com.bluerabbit.librarysystem.view.AdvancedSearchView;
import com.bluerabbit.librarysystem.view.BookInfoManageView;

/**
 * 图书管理中高级查询的确定按钮监听类
 * @author minuy
 *
 */
public class AdvancedSearchView_ok_ActionListener implements ActionListener {
	AdvancedSearchView advancedSearchView;
	BookInfoManageView wv;
	public AdvancedSearchView_ok_ActionListener(
			AdvancedSearchView advancedSearchView, BookInfoManageView wv) {
		// TODO Auto-generated constructor stub
		this.advancedSearchView = advancedSearchView;
		this.wv = wv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new AdvancedSearchServer(advancedSearchView,wv);
		advancedSearchView.dispose();
	}

}
