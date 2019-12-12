package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.beans.AdministratorBeans;
import com.bluerabbit.librarysystem.service.AdminInfoSave;
import com.bluerabbit.librarysystem.view.ChangerPasswdView;
import com.bluerabbit.librarysystem.view.MainView;
import com.bluerabbit.librarysystem.view.PersonalInfoView;
/**
 * ������Ϣ����ҳ��ļ����࣬��������ҳ��
 * @author minuy
 *
 */
public class PersonalInfoView_ActionListener implements ActionListener{
	PersonalInfoView piv;
	AdministratorBeans admin;
	MainView mv;
	public PersonalInfoView_ActionListener(PersonalInfoView personalInfoView,
			AdministratorBeans admin) {
		// TODO Auto-generated constructor stub
		
	}

	public PersonalInfoView_ActionListener(PersonalInfoView personalInfoView,
			MainView mv, AdministratorBeans admin2) {
		// TODO Auto-generated constructor stub
		this.piv = personalInfoView;
		this.admin = admin2;
		this.mv = mv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object clic = e.getSource();
		System.out.println(e);
		if(clic == piv.getTxfChangerPwd()){
			System.out.println("��������");
			new ChangerPasswdView(piv,admin,true);
		}else{
			if(clic == piv.getBtnSave()){
				System.out.println("����");
				new AdminInfoSave().AdminSaveName(piv,admin,mv);
			}else{
				if(clic == piv.getBtnClose()){
					System.out.println("�ر�");
					piv.dispose();
				}
			}
		}
	}

}
