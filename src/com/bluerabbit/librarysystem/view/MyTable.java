package com.bluerabbit.librarysystem.view;

import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * �Զ����һ��JTable�࣬����˱�����е�����Ӧ����
 *
 * @author minuhy
 */
public class MyTable extends JTable {
    /**
     * UID
     */
    private static final long serialVersionUID = 1423342345324L;

    /**
     * ��дJTable��Ĺ��췽��
     * �����࣬����̳���JTable���Ϊ���
     */
    public MyTable() {//Vector rowData,Vector columnNames
        super();//���ø���Ĺ��췽��
    }

    /**
     * ��дJTable���getTableHeader()����
     * ���ñ�б������
     */
    public JTableHeader getTableHeader() {//������ͷ
        JTableHeader tableHeader = super.getTableHeader();//��ñ��ͷ����
        tableHeader.setReorderingAllowed(false);//���ñ���в�������
        DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader
                .getDefaultRenderer();//��ñ��ͷ�ĵ�Ԫ�����
        hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//��������������ʾ
        return tableHeader;
    }

    /**
     * ��дJTable���isCellEditable(int row,int column)����
     * ��ֹ���༭
     */
    @Override
    public boolean isCellEditable(int row, int column) {//��񲻿ɱ༭
        return false;
    }

    /**
     * ��ͷ����Ӧ�����������ݺ��
     */
    @SuppressWarnings("rawtypes")
    public void FitTableColumns() {
        JTableHeader header = this.getTableHeader();
        int rowCount = this.getRowCount();
        Enumeration columns = this.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) this.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(this, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferredWidth = (int) this.getCellRenderer(row, col).getTableCellRendererComponent(this,
                        this.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferredWidth);
            }
            header.setResizingColumn(column); // ���к���Ҫ
            column.setWidth(width + this.getIntercellSpacing().width);
        }
    }

}
