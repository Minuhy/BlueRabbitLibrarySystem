package com.bluerabbit.librarysystem.service;

import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.dao.BookInfoUpdate;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
/**
 * ɾ��ͼ����Ϣ���߼���ͼ����һ����Ժϲ��� 
 * @author minuy
 *
 */
public class DelBookInfo {
	public DelBookInfo(BookInfoManageView bm){
		int rowIndex = bm.getTableDataView().getSelectedRow();
		
		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����鼮��", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(null,"�Ƿ�ɾ�����鼮","����",JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			String bookID = (String) bm.getTableDataView().getValueAt(rowIndex, 8);
			//dao��ɾ�����ݵķ���
			if(!(new BookInfoUpdate().DelByBookID(bookID))){
				//���ɾ��ʧ��
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//����setTableData����ˢ�±��
			bm.updateTableData();
			System.out.println("ɾ��ͼ��ı��" + bookID);
		}
	}
}
