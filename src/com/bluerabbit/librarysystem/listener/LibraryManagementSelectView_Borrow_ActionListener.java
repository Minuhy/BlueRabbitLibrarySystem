package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.view.LibraryManagementSelectView;
import com.bluerabbit.librarysystem.view.MainView;
/**
 * ����ͼ���Ĺ�����鰴ť������
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
		System.out.println("ѡ���˽��飡");
		lmv.dispose();
		JOptionPane.showMessageDialog(null, "ͣ�ã�", "����", JOptionPane.ERROR_MESSAGE);
		return;
		//new BorrowBookView(mv);
	}

}
