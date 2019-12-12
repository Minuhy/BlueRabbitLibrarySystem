package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.view.LibraryManagementSelectView;
import com.bluerabbit.librarysystem.view.MainView;
/**
 * 主视图借阅管理借书按钮监听类
 * @author minuy
 *
 */
public class LibraryManagementSelectView_Borrow_ActionListener implements
		ActionListener {
	
	LibraryManagementSelectView lmv;
	MainView mv;
	
	public LibraryManagementSelectView_Borrow_ActionListener(
			LibraryManagementSelectView libraryManagementSelectView,MainView mv) {
		// TODO Auto-generated constructor stub
		this.lmv = libraryManagementSelectView;
		this.mv = mv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("选择了借书！");
		lmv.dispose();
		JOptionPane.showMessageDialog(null, "停用！", "错误", JOptionPane.ERROR_MESSAGE);
		return;
		//new BorrowBookView(mv);
	}

}
