package com.bluerabbit.librarysystem.service;

import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.dao.BookInfoUpdate;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
/**
 * 删除图书信息的逻辑，图书这一类可以合并的 
 * @author minuy
 *
 */
public class DelBookInfo {
	public DelBookInfo(BookInfoManageView bm){
		int rowIndex = bm.getTableDataView().getSelectedRow();
		
		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "请选中要删除的书籍！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(null,"是否删除该书籍","警告",JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			String bookID = (String) bm.getTableDataView().getValueAt(rowIndex, 8);
			//dao层删除数据的方法
			if(!(new BookInfoUpdate().DelByBookID(bookID))){
				//如果删除失败
				JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//调用setTableData方法刷新表格
			bm.updateTableData();
			System.out.println("删除图书的编号" + bookID);
		}
	}
}
