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
 * Ϊ������Ϣά��׼����һ��������
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
			JOptionPane.showMessageDialog(null,"����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null,"����ɹ���");
		//����������
		SetViewNull();
		//���¹����������
		rv.updateTableData();
	}


	public void search(){
		String key = rv.getChooseWay().getSelectedItem().toString();
		String sql = "select *  from Reader where ";
		if(key.equals("ѧ��")){
			sql = sql + "ReaderID like ?";
		}else{
			if(key.equals("����")){
				sql = sql + "ReaderName like ?";
			}else{
				if(key.equals("�༶")){
					sql = sql + "Class like ?";
				}else{
					if(key.equals("ѧԺ")){
						sql = sql + "Apart like ?";
					}
				}
			}
		}
		
		List<ReaderInfoBeans> rs = new ReaderInfoBeansDAO().getDataBySql(sql,rv.getChooseInfo().getText());
		
		rv.setTableData(rs);
	}



	/**
	 * ����������
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void SetViewNull() {
		// TODO Auto-generated method stub
		riud.getCltApart().getGettxt().setText("");
		riud.getCltReaderID().getGettxt().setText("");
		riud.getCltReaderName().getGettxt().setText("");
		riud.getCltTheClass().getGettxt().setText("");
		riud.getCltTelNo().getGettxt().setText("");
		String[] select = {"��","Ů"};
		riud.getJcbSex().setModel(new DefaultComboBoxModel(select));
	}

	public void fix() {
		// TODO Auto-generated method stub
		ReaderInfoBeans bookupdate = getUpdataInfo();
		if(bookupdate == null){
			return;
		}
		if(!(new ReaderInfoBeansDAO().UpReaderInfo(bookupdate,riud.getReaderId()))){
			JOptionPane.showMessageDialog(null,"����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null,"����ɹ���");
		//���¹����������
		rv.updateTableData();

		riud.dispose();
	}

	public void del() {
		// TODO Auto-generated method stub
		int rowIndex = rv.getTableDataView().getSelectedRow();

		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���Ķ�����Ϣ��", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(null,"�Ƿ�ɾ���ö�����Ϣ","����",JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			//��ȡѧ��
			String ac = (String) rv.getTableDataView().getValueAt(rowIndex, 0);

			//dao��ɾ�����ݵķ���
			if(!(new ReaderInfoBeansDAO().DelReaderById(ac))){
				//���ɾ��ʧ��
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//����setTableData����ˢ�±��
			rv.updateTableData();
			System.out.println("ɾ���Ķ���" + ac);
			JOptionPane.showMessageDialog(null,"��ɾ������" + ac);
		}
	}

	private ReaderInfoBeans getUpdataInfo(){
		ReaderInfoBeans readerInfo = new ReaderInfoBeans();
		String data = null;
		String hint = null;

		hint = "ѧ��";
		data = riud.getCltReaderID().getText();
		if(!Pattern.matches("[A-Za-z0-9\\-]+", data)){
			JOptionPane.showMessageDialog(null,hint + "��ʽ����\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		//���ֵ���ޱ���
		if(data.length()>=20){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		readerInfo.setReaderID(data);

		hint = "����";
		data = riud.getCltReaderName().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		//���ֵ���ޱ���
		if(data.length()>=30){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		readerInfo.setReaderName(data);

		hint = "ѧԺ";
		data = riud.getCltApart().getText();
		//���ֵ���ޱ���
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		readerInfo.setApart(data);

		hint = "�༶";
		data = riud.getCltTheClass().getText();
		//���ֵ���ޱ���
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		readerInfo.setTheClass(data);

		hint = "��ϵ��ʽ";
		data = riud.getCltTelNo().getText();
		//���ֵ���ޱ���
		if(data.length()>=20){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		readerInfo.setTelNo(data);

		data = (String)riud.getJcbSex().getSelectedItem();
		if(data.equals("Ů")){
			readerInfo.setSex("Ů");
		}else{
			readerInfo.setSex("��");
		}

		return readerInfo;
	}

	public void FixInfoGet(boolean mode){
		int rowIndex = rv.getTableDataView().getSelectedRow();

		if(mode){
			if(rowIndex == -1) {
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵĶ�����Ϣ��", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(rowIndex == -1){
			System.out.println("����");
			return;
		}

		String readerID = (String) rv.getTableDataView().getValueAt(rowIndex, 0);
		if(readerID.equals("")){
			JOptionPane.showMessageDialog(null, "����Ϣ����", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//��ӡѡ��ID
		System.out.println(readerID);

		if(mode){
			//�޸�ģʽ�����޸�
			new ReaderInfoUpdateView(rv,"�޸Ķ�����Ϣ",readerID,true);
		}else{
			//�鿴ģʽ
			new ReaderInfoUpdateView(rv,"������Ϣ����",readerID,false);
		}
	}

}
