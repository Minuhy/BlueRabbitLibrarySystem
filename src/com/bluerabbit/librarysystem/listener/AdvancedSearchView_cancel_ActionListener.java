package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.view.AdvancedSearchView;

/**
 * 图书管理中高级查询的关闭按钮监听类
 * @author minuy
 *
 */
public class AdvancedSearchView_cancel_ActionListener implements ActionListener {
	AdvancedSearchView advancedSearchView;
	public AdvancedSearchView_cancel_ActionListener(
			AdvancedSearchView advancedSearchView) {
		// TODO Auto-generated constructor stub
		this.advancedSearchView = advancedSearchView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		advancedSearchView.dispose();
	}

}
