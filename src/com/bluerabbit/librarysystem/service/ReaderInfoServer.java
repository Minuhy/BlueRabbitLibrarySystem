package com.bluerabbit.librarysystem.service;

import java.util.List;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.beans.ReaderInfoBeans;
import com.bluerabbit.librarysystem.dao.ReaderInfoBeansDAO;
import com.bluerabbit.librarysystem.view.ReaderInfoManageView;
import com.bluerabbit.librarysystem.view.ReaderInfoUpdateView;

/**
 * 为读者信息维护准备的一个服务类
 * @author minuy
 *
 */
public class ReaderInfoServer {
	ReaderInfoUpdateView riud;
	ReaderInfoManageView rv;
	public ReaderInfoServer(ReaderInfoManageView rv,ReaderInfoUpdateView riud) {
		// TODO Auto-generated constructor stub
		this.rv = rv;
		this.riud = riud;
	}

	public void add() {
		// TODO Auto-generated method stub
		ReaderInfoBeans reader = getUpdataInfo();
		if(reader == null){
			return;
		}
		if(!(new ReaderInfoBeansDAO().SaveAReaderInfo(reader))){
			JOptionPane.showMessageDialog(null,"保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null,"保存成功！");
		//清空面板输入
		SetViewNull();
		//更新管理面板数据
		rv.updateTableData();
	}


	public void search(){
		String key = rv.getChooseWay().getSelectedItem().toString();
		String sql = "select *  from Reader where ";
		if(key.equals("学号")){
			sql = sql + "ReaderID like ?";
		}else{
			if(key.equals("姓名")){
				sql = sql + "ReaderName like ?";
			}else{
				if(key.equals("班级")){
					sql = sql + "Class like ?";
				}else{
					if(key.equals("学院")){
						sql = sql + "Apart like ?";
					}
				}
			}
		}
		
		List<ReaderInfoBeans> rs = new ReaderInfoBeansDAO().getDataBySql(sql,rv.getChooseInfo().getText());
		
		rv.setTableData(rs);
	}



	/**
	 * 清空面板输入
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void SetViewNull() {
		// TODO Auto-generated method stub
		riud.getCltApart().getGettxt().setText("");
		riud.getCltReaderID().getGettxt().setText("");
		riud.getCltReaderName().getGettxt().setText("");
		riud.getCltTheClass().getGettxt().setText("");
		riud.getCltTelNo().getGettxt().setText("");
		String[] select = {"男","女"};
		riud.getJcbSex().setModel(new DefaultComboBoxModel(select));
	}

	public void fix() {
		// TODO Auto-generated method stub
		ReaderInfoBeans bookupdate = getUpdataInfo();
		if(bookupdate == null){
			return;
		}
		if(!(new ReaderInfoBeansDAO().UpReaderInfo(bookupdate,riud.getReaderId()))){
			JOptionPane.showMessageDialog(null,"保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null,"保存成功！");
		//更新管理面板数据
		rv.updateTableData();

		riud.dispose();
	}

	public void del() {
		// TODO Auto-generated method stub
		int rowIndex = rv.getTableDataView().getSelectedRow();

		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "请选中要删除的读者信息！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(null,"是否删除该读者信息","警告",JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			//获取学号
			String ac = (String) rv.getTableDataView().getValueAt(rowIndex, 0);

			//dao层删除数据的方法
			if(!(new ReaderInfoBeansDAO().DelReaderById(ac))){
				//如果删除失败
				JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//调用setTableData方法刷新表格
			rv.updateTableData();
			System.out.println("删除的读者" + ac);
			JOptionPane.showMessageDialog(null,"已删除读者" + ac);
		}
	}

	private ReaderInfoBeans getUpdataInfo(){
		ReaderInfoBeans readerInfo = new ReaderInfoBeans();
		String data = null;
		String hint = null;

		hint = "学号";
		data = riud.getCltReaderID().getText();
		if(!Pattern.matches("[A-Za-z0-9\\-]+", data)){
			JOptionPane.showMessageDialog(null,hint + "格式错误！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		//最大值上限报错
		if(data.length()>=20){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		readerInfo.setReaderID(data);

		hint = "姓名";
		data = riud.getCltReaderName().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		//最大值上限报错
		if(data.length()>=30){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		readerInfo.setReaderName(data);

		hint = "学院";
		data = riud.getCltApart().getText();
		//最大值上限报错
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		readerInfo.setApart(data);

		hint = "班级";
		data = riud.getCltTheClass().getText();
		//最大值上限报错
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		readerInfo.setTheClass(data);

		hint = "联系方式";
		data = riud.getCltTelNo().getText();
		//最大值上限报错
		if(data.length()>=20){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		readerInfo.setTelNo(data);

		data = (String)riud.getJcbSex().getSelectedItem();
		if(data.equals("女")){
			readerInfo.setSex("女");
		}else{
			readerInfo.setSex("男");
		}

		return readerInfo;
	}

	public void FixInfoGet(boolean mode){
		int rowIndex = rv.getTableDataView().getSelectedRow();

		if(mode){
			if(rowIndex == -1) {
				JOptionPane.showMessageDialog(null, "请选中要修改的读者信息！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(rowIndex == -1){
			System.out.println("出错！");
			return;
		}

		String readerID = (String) rv.getTableDataView().getValueAt(rowIndex, 0);
		if(readerID.equals("")){
			JOptionPane.showMessageDialog(null, "表单信息错误！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//打印选中ID
		System.out.println(readerID);

		if(mode){
			//修改模式，能修改
			new ReaderInfoUpdateView(rv,"修改读者信息",readerID,true);
		}else{
			//查看模式
			new ReaderInfoUpdateView(rv,"读者信息详情",readerID,false);
		}
	}

}
