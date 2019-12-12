package com.bluerabbit.librarysystem.service;

import java.util.List;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.dao.BookInfoInquire;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
/**
 * ͼ�����ͨ�����߼�������
 * @author minuy
 *
 */
public class SourchBookInfo {

	//setTableData

	public SourchBookInfo(BookInfoManageView bookInfoManageView) {
		// TODO Auto-generated constructor stub
		//��ȡ�����������
		String tpye = (String)bookInfoManageView.getChooseWay().getSelectedItem();

		String info = bookInfoManageView.getChooseInfo().getText();

		System.out.println(tpye + ":" + info);
		//�������������������SQL���

		String sql = "SELECT * from Books_Info WHERE ";
		if(tpye.equals("�鿯���")){
			sql = sql + "BookID like ?";
		}else{
			if(tpye.equals("�鿯����")){
				sql = sql + "BookName like ?";
			}else{
				if(tpye.equals("�鿯����")){
					sql = sql + "Author like ?";
				}
			}
		}

		//DAOִ�У���ȡ����
		BookInfoInquire data = new BookInfoInquire();
		//��ȡ����
		List<BookInfoBeans> list = data.InquireBookInfoByAKey(sql,info); 

		//�������ݸ��±�
		bookInfoManageView.setTableData(list);
	}

}
