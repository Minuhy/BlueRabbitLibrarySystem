package com.bluerabbit.librarysystem.view;

import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
/**
 * �Զ����һ��jtable�࣬����˱�����е�����Ӧ����
 * @author minuy
 *
 */
public class MyTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1423342345324L;
	//��дJTable��Ĺ��췽��
	/*
	 * �����࣬����̳���JTable���Ϊ���
	 */

	public MyTable(){//Vector rowData,Vector columnNames
		super();//���ø���Ĺ��췽��

	}
	
	/**
	 * ��дJTable���getTableHeader()����
	 * ���ñ�б������
	 */
	public JTableHeader getTableHeader(){//������ͷ
		JTableHeader tableHeader=super.getTableHeader();//��ñ��ͷ����
		tableHeader.setReorderingAllowed(false);//���ñ���в�������
		DefaultTableCellRenderer hr=(DefaultTableCellRenderer)tableHeader
				.getDefaultRenderer();//��ñ��ͷ�ĵ�Ԫ�����
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//��������������ʾ
		return tableHeader;
	}
	
	/**
	 * ��дJtbale���getDefaultRenderer(Class<?>columnClass)����
	 * ���ñ�����ݾ���
	 * @param columnClass
	 * @return
	 */
	public TableCellRenderer getDCellRenderer(Class<?> columnClass){//���嵥Ԫ��
		DefaultTableCellRenderer cr=(DefaultTableCellRenderer)super
				.getDefaultRenderer(columnClass);//��ñ��ĵ�Ԫ�����
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//���õ�Ԫ�����ݾ�����ʾ
		return cr;

	}
	/**
	 * ��дJtable���isCellEditable(int row,int column)����
	 * ��ֹ���༭
	 */
	@Override
	public boolean isCellEditable(int row,int column){//��񲻿ɱ༭
		return false;
	}

	@SuppressWarnings("rawtypes")
	/**
	 * ��ͷ����Ӧ�����������ݺ��
	 * @param myTable
	 */
	public void FitTableColumns(){
		JTableHeader header = this.getTableHeader();
		int rowCount = this.getRowCount();
		Enumeration columns = this.getColumnModel().getColumns();
		while(columns.hasMoreElements()){
			TableColumn column = (TableColumn)columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int)this.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(this, column.getIdentifier()
							, false, false, -1, col).getPreferredSize().getWidth();
			for(int row = 0; row<rowCount; row++){
				int preferedWidth = (int)this.getCellRenderer(row, col).getTableCellRendererComponent(this,
						this.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // ���к���Ҫ
			column.setWidth(width+this.getIntercellSpacing().width);
		}
	}

}
