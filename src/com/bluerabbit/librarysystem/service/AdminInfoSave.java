package com.bluerabbit.librarysystem.service;

import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.dao.AdministratorBeansDAO;
import com.bluerabbit.librarysystem.database.MD5Utils;
import com.bluerabbit.librarysystem.view.AdminInfoManageView;
import com.bluerabbit.librarysystem.view.AdminInfoUpdateView;
import com.bluerabbit.librarysystem.view.ChangerPasswdView;
import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.PersonalInfoView;
/**
 * 管理员类与数据库的交互逻辑
 * @author minuy
 *
 */
public class AdminInfoSave {
	PersonalInfoView piv;
	AdministratorBeans admin;

	public void search(AdminInfoManageView av){
		String way = av.getChooseWay().getSelectedItem().toString();
		String sqlkey = av.getChooseInfo().getText();

		String sql = "select * form Administrator where ";

		if(way.equals("昵称")){
			sql = sql + "AdminName like ?";
		}else{
			if(way.equals("编号")){
				sql = sql + "AdminID like ?";
			}else{
				if(way.equals("账号")){
					sql = sql + "Account like ?";
				}
			}
		}

		List<AdministratorBeans> list = new AdministratorBeansDAO().inquire(sql,sqlkey);

		av.setTableData(list);

	}

	public void LookAdmin(AdminInfoManageView av){
		int rowIndex = av.getTableDataView().getSelectedRow();

		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "数据获取错误！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//获取账号
		String ac = (String) av.getTableDataView().getValueAt(rowIndex, 2);
		//dao层删除数据的方法
		new AdminInfoUpdateView(av,"查看账号信息",ac,false);
		//调用setTableData方法刷新表格
		av.updateTableData();
		System.out.println("账号" + ac);
	}

	public void FixAdmin(AdminInfoManageView av,MainView mv){
		int rowIndex = av.getTableDataView().getSelectedRow();

		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "数据获取错误！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		//获取账号
		String ac = (String) av.getTableDataView().getValueAt(rowIndex, 2);
		if(ac.equals(mv.getAdminAcc())){
			JOptionPane.showMessageDialog(null, "不能在这里修改自己的信息！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//dao层删除数据的方法
		new AdminInfoUpdateView(av,"修改账号信息",ac,true,mv);
		//调用setTableData方法刷新表格
		av.updateTableData();
		System.out.println("账号" + ac);
	}

	public void DelAdmin(AdminInfoManageView av,MainView mv){
		int rowIndex = av.getTableDataView().getSelectedRow();

		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "请选中要删除的账号！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(null,"是否删除该账号","警告",JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			//获取账号
			String ac = (String) av.getTableDataView().getValueAt(rowIndex, 2);

			if(ac.equals(mv.getAdminAcc())){
				JOptionPane.showMessageDialog(null, "不能删除自己 的账号！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}

			//dao层删除数据的方法
			if(!(new AdministratorBeansDAO().DelAdminByAccout(ac))){
				//如果删除失败
				JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//调用setTableData方法刷新表格
			av.updateTableData();
			System.out.println("删除的账号" + ac);
		}
	}

	public void AdminSaveName(PersonalInfoView personalInfoView,
			AdministratorBeans admin,
			MainView mv){
		this.piv = personalInfoView;
		this.admin = admin;
		String data;
		data = piv.getTxfName().getText();
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null, "昵称限制在九个字符以内！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(data.equals(admin.getAdminName())){
			JOptionPane.showMessageDialog(null, "昵称保存成功！");
			piv.dispose();
			return;
		}else{
			if((new AdministratorBeansDAO().SaveAdminInfoName(admin,data))){
				JOptionPane.showMessageDialog(null, "昵称保存成功！");
				mv.updateAdmin(admin);
			}else{
				JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void AdminPwdSave(ChangerPasswdView cv,AdministratorBeans admin){
		String pwd = cv.getChangPWD1().getText();
		System.out.println(pwd);
		String newPwd = cv.getChangPWD2().getText();
		System.out.println(newPwd);
		String newPwd2 = cv.getChangPWD3().getText();
		System.out.println(newPwd2);
		if(!newPwd.equals(newPwd2)){
			JOptionPane.showMessageDialog(null, "两次输入的密码不同！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		//进行MD5加密
		String pwMD5 = MD5Utils.MD5Encode(pwd,"utf8");
		AdministratorBeansDAO user = new AdministratorBeansDAO();
		//判断是否正确
		if(user.validateByAccountAndPassword(admin.getAccount(), pwMD5)||(!cv.isIsoldPwd())){
			//如果成功
			if(newPwd.equals("")){
				JOptionPane.showMessageDialog(null, "设置空密码有一定安全风险！");
			}
			//加密新密码
			pwMD5 = MD5Utils.MD5Encode(newPwd,"utf8");
			if((new AdministratorBeansDAO().SaveAdminInfoPwd(admin,pwMD5))){

				JOptionPane.showMessageDialog(null, "密码更改成功！");

				cv.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			System.out.println("原始密码错误！");
			JOptionPane.showMessageDialog(null, "原始密码错误！", "错误",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void add(AdminInfoUpdateView auv, AdminInfoManageView wv) {
		// TODO Auto-generated method stub
		AdministratorBeans admin = getAdminInformation(auv);
		//不为空的信息没填
		if(admin == null){
			System.out.println("值没有填写");
			return;
		}
		//保存信息
		if(!(new AdministratorBeansDAO().AdminSave(admin))){
			//如果失败
			System.out.println("新增失败");
			JOptionPane.showMessageDialog(null, "添加失败！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//调用setTableData方法刷新表格
		wv.updateTableData();
		//清空输入数据
		AdminInfoReset(auv);
		System.out.println("新增成功!");
		JOptionPane.showMessageDialog(null, "添加成功！");


	}

	private void AdminInfoReset(AdminInfoUpdateView auv) {
		// TODO Auto-generated method stub
		auv.getLid().setIText("");
		auv.getLname().setIText("");
		auv.getLaccout().setIText("");
		auv.getPwfPasswd().setText("");
	}

	@SuppressWarnings("deprecation")
	private AdministratorBeans getAdminInformation(AdminInfoUpdateView auv){
		AdministratorBeans admin = new AdministratorBeans();
		String data = null;
		String hint = null;

		hint = "编号";
		data = auv.getLid().getGettxt().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			if(!Pattern.matches("^[0-9]*$", data)){
				JOptionPane.showMessageDialog(null,hint + "格式错误！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			//最大值上限报错
			if(data.length()>=9){
				JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			admin.setAdminID(Integer.valueOf(data));
		}

		hint = "昵称";
		data = auv.getLname().getGettxt().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//最大值上限报错
			if(data.length()>=9){
				JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			admin.setAdminName(data);
		}

		hint = "账号";
		data = auv.getLaccout().getGettxt().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//最大值上限报错
			if(data.length()>=6){
				JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			admin.setAccount(data);
		}
		
		if(auv.getOldPasswd()!=null){
			//有原密码
			if(auv.getOldPasswd().equals(auv.getPwfPasswd().getText())){
				//密码没变
				admin.setPassword(auv.getOldPasswd());
			}else{
				//密码变了
				admin.setPassword(MD5Utils.MD5Encode(auv.getPwfPasswd().getText(),"utf8"));
			}
		}else{
			admin.setPassword(MD5Utils.MD5Encode(auv.getPwfPasswd().getText(),"utf8"));
		}
		admin.setIfsuper(auv.getJcbIfsup().isSelected());

		return admin;
	}

	public void ChangerAdminPwd(AdminInfoManageView av) {
		// TODO Auto-generated method stub
		int rowIndex = av.getTableDataView().getSelectedRow();

		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "请选中要重置密码的账号！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		//获取账号
		String ac = (String) av.getTableDataView().getValueAt(rowIndex, 2);

		//获取账号信息
		AdministratorBeans admin =  new AdministratorBeansDAO().getAdminInfoByAcc(ac);

		new ChangerPasswdView(av, admin,false);	
	}

	public void fix(AdminInfoUpdateView auv, AdminInfoManageView wv,MainView mv) {
		// TODO Auto-generated method stub
		AdministratorBeans admin = getAdminInformation(auv);
		//不为空的信息没填
		if(admin == null){
			System.out.println("值没有填写");
			return;
		}
		//保存信息
		if(!(new AdministratorBeansDAO().SaveAdminInfo(admin))){
			//如果失败
			System.out.println("新增失败");
			JOptionPane.showMessageDialog(null, "修改失败！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//调用setTableData方法刷新表格
		wv.updateTableData();

		System.out.println("修改成功!");
		if(mv.getAdminAcc().equals(admin.getAccount())){
			mv.updateAdmin(admin);
			System.out.println("更改了当前信息");
		}
		
		JOptionPane.showMessageDialog(null, "修改成功！");
	}
}
