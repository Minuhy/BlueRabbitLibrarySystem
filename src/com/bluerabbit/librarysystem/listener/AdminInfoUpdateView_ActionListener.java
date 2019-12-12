package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.service.AdminInfoSave;
import com.bluerabbit.librarysystem.view.AdminInfoManageView;
import com.bluerabbit.librarysystem.view.AdminInfoUpdateView;
import com.bluerabbit.librarysystem.view.MainView;
/**
 * 管理员信息详情页面的按钮监听类
 * @author minuy
 *
 */
public class AdminInfoUpdateView_ActionListener implements ActionListener  {
	AdminInfoUpdateView auv;
	AdminInfoManageView wv;
	MainView mv;
	public AdminInfoUpdateView_ActionListener(
			AdminInfoUpdateView adminInfoUpdateView, AdminInfoManageView wv,MainView mv) {
		// TODO Auto-generated constructor stub
		this.auv = adminInfoUpdateView;
		this.wv = wv;
		this.mv = mv;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object clic = e.getSource();
		if(clic == auv.getOk()){
			if(auv.isMode()){
				//增加模式
				//new AddBookInfo(wv).add(auv);
				System.out.println("添加数据");
				new AdminInfoSave().add(auv,wv);
			}else{
				//修改模式
				//这里判断表格有没有被选中也是服务层的，所以放了两个构造方法
				//new FixBookInfo().fix(auv,wv);
				System.out.println("更改数据");
				//new AdminInfoSave().FixAdmin(wv);
				new AdminInfoSave().fix(auv,wv,mv);
			}
		}else{
			if(clic == auv.getCancel()){
				auv.dispose();
			}
		}
	}

}
