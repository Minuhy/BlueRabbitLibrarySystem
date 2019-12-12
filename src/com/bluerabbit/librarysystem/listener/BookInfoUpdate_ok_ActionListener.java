package com.bluerabbit.librarysystem.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bluerabbit.librarysystem.service.AddBookInfo;
import com.bluerabbit.librarysystem.service.FixBookInfo;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
import com.bluerabbit.librarysystem.view.BookInfoUpdateView;
/**
 * �鼮�����ȷ�ϰ�ť������
 * @author minuy
 *
 */
public class BookInfoUpdate_ok_ActionListener implements ActionListener {
	BookInfoUpdateView biuv;
	BookInfoManageView bimv;
	public BookInfoUpdate_ok_ActionListener(
			BookInfoUpdateView bookInfoUpdateView,BookInfoManageView bimv) {
		// TODO Auto-generated constructor stub
		this.biuv = bookInfoUpdateView;
		this.bimv = bimv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(biuv.isMode()){
			//����ģʽ
			new AddBookInfo(bimv).add(biuv);
			System.out.println("�������");
		}else{
			//�޸�ģʽ
			//�����жϱ����û�б�ѡ��Ҳ�Ƿ����ģ����Է����������췽��
			new FixBookInfo().fix(biuv,bimv);
			System.out.println("��������");
		}
	}

}
