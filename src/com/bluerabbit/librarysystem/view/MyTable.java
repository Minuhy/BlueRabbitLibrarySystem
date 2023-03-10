package com.bluerabbit.librarysystem.view;

import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * 自定义的一个JTable类，添加了标题居中等自适应方法
 *
 * @author minuhy
 */
public class MyTable extends JTable {
    /**
     * UID
     */
    private static final long serialVersionUID = 1423342345324L;

    /**
     * 重写JTable类的构造方法
     * 创建类，该类继承自JTable类成为表格
     */
    public MyTable() {//Vector rowData,Vector columnNames
        super();//调用父类的构造方法
    }

    /**
     * 重写JTable类的getTableHeader()方法
     * 设置表盒标题居中
     */
    public JTableHeader getTableHeader() {//定义表格头
        JTableHeader tableHeader = super.getTableHeader();//获得表格头对象
        tableHeader.setReorderingAllowed(false);//设置表格列不可重排
        DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader
                .getDefaultRenderer();//获得表格头的单元格对象
        hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//设置列名居中显示
        return tableHeader;
    }

    /**
     * 重写JTable类的isCellEditable(int row,int column)方法
     * 禁止表格编辑
     */
    @Override
    public boolean isCellEditable(int row, int column) {//表格不可编辑
        return false;
    }

    /**
     * 表头自适应函数，存数据后调
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
            header.setResizingColumn(column); // 此行很重要
            column.setWidth(width + this.getIntercellSpacing().width);
        }
    }

}
