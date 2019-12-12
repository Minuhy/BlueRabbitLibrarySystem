package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.service.ReaderInfoServer;
import com.bluerabbit.librarysystem.view.ReaderInfoManageView;
import com.bluerabbit.librarysystem.view.ReaderInfoUpdateView;
/**
 * ���߹���ҳ������ҳ��ļ����࣬�����������ҳ��İ�ť����¼�
 * @author minuy
 *
 */
public class ReaderInfoUpdateView_ActionListener implements ActionListener{
	ReaderInfoUpdateView riud;
	ReaderInfoManageView rv;
	public ReaderInfoUpdateView_ActionListener(
			ReaderInfoUpdateView readerInfoUpdate, ReaderInfoManageView rv) {
		// TODO Auto-generated constructor stub
		this.riud = readerInfoUpdate;
		this.rv = rv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object clic = e.getSource();
		if(clic == riud.getOk()){
			System.out.println("ȷ��");
			if(riud.isMode()){
				//add
				new ReaderInfoServer(rv,riud).add();
			}else{
				//fix
				new ReaderInfoServer(rv, riud).fix();
			}
			
		}else{
			if(clic == riud.getCancel()){
				System.out.println("ȡ��");
				riud.dispose();
			}
		}
	}

}
