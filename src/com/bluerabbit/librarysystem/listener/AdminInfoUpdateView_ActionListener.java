package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.service.AdminInfoSave;
import com.bluerabbit.librarysystem.view.AdminInfoManageView;
import com.bluerabbit.librarysystem.view.AdminInfoUpdateView;
import com.bluerabbit.librarysystem.view.MainView;
/**
 * ����Ա��Ϣ����ҳ��İ�ť������
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
				//����ģʽ
				//new AddBookInfo(wv).add(auv);
				System.out.println("�������");
				new AdminInfoSave().add(auv,wv);
			}else{
				//�޸�ģʽ
				//�����жϱ����û�б�ѡ��Ҳ�Ƿ����ģ����Է����������췽��
				//new FixBookInfo().fix(auv,wv);
				System.out.println("��������");
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
