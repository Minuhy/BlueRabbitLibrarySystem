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
 * ����Ա�������ݿ�Ľ����߼�
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

		if(way.equals("�ǳ�")){
			sql = sql + "AdminName like ?";
		}else{
			if(way.equals("���")){
				sql = sql + "AdminID like ?";
			}else{
				if(way.equals("�˺�")){
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
			JOptionPane.showMessageDialog(null, "���ݻ�ȡ����", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//��ȡ�˺�
		String ac = (String) av.getTableDataView().getValueAt(rowIndex, 2);
		//dao��ɾ�����ݵķ���
		new AdminInfoUpdateView(av,"�鿴�˺���Ϣ",ac,false);
		//����setTableData����ˢ�±��
		av.updateTableData();
		System.out.println("�˺�" + ac);
	}

	public void FixAdmin(AdminInfoManageView av,MainView mv){
		int rowIndex = av.getTableDataView().getSelectedRow();

		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "���ݻ�ȡ����", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}

		//��ȡ�˺�
		String ac = (String) av.getTableDataView().getValueAt(rowIndex, 2);
		if(ac.equals(mv.getAdminAcc())){
			JOptionPane.showMessageDialog(null, "�����������޸��Լ�����Ϣ��", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//dao��ɾ�����ݵķ���
		new AdminInfoUpdateView(av,"�޸��˺���Ϣ",ac,true,mv);
		//����setTableData����ˢ�±��
		av.updateTableData();
		System.out.println("�˺�" + ac);
	}

	public void DelAdmin(AdminInfoManageView av,MainView mv){
		int rowIndex = av.getTableDataView().getSelectedRow();

		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����˺ţ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(null,"�Ƿ�ɾ�����˺�","����",JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			//��ȡ�˺�
			String ac = (String) av.getTableDataView().getValueAt(rowIndex, 2);

			if(ac.equals(mv.getAdminAcc())){
				JOptionPane.showMessageDialog(null, "����ɾ���Լ� ���˺ţ�", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}

			//dao��ɾ�����ݵķ���
			if(!(new AdministratorBeansDAO().DelAdminByAccout(ac))){
				//���ɾ��ʧ��
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//����setTableData����ˢ�±��
			av.updateTableData();
			System.out.println("ɾ�����˺�" + ac);
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
			JOptionPane.showMessageDialog(null, "�ǳ������ھŸ��ַ����ڣ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(data.equals(admin.getAdminName())){
			JOptionPane.showMessageDialog(null, "�ǳƱ���ɹ���");
			piv.dispose();
			return;
		}else{
			if((new AdministratorBeansDAO().SaveAdminInfoName(admin,data))){
				JOptionPane.showMessageDialog(null, "�ǳƱ���ɹ���");
				mv.updateAdmin(admin);
			}else{
				JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "������������벻ͬ��", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}

		//����MD5����
		String pwMD5 = MD5Utils.MD5Encode(pwd,"utf8");
		AdministratorBeansDAO user = new AdministratorBeansDAO();
		//�ж��Ƿ���ȷ
		if(user.validateByAccountAndPassword(admin.getAccount(), pwMD5)||(!cv.isIsoldPwd())){
			//����ɹ�
			if(newPwd.equals("")){
				JOptionPane.showMessageDialog(null, "���ÿ�������һ����ȫ���գ�");
			}
			//����������
			pwMD5 = MD5Utils.MD5Encode(newPwd,"utf8");
			if((new AdministratorBeansDAO().SaveAdminInfoPwd(admin,pwMD5))){

				JOptionPane.showMessageDialog(null, "������ĳɹ���");

				cv.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			System.out.println("ԭʼ�������");
			JOptionPane.showMessageDialog(null, "ԭʼ�������", "����",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void add(AdminInfoUpdateView auv, AdminInfoManageView wv) {
		// TODO Auto-generated method stub
		AdministratorBeans admin = getAdminInformation(auv);
		//��Ϊ�յ���Ϣû��
		if(admin == null){
			System.out.println("ֵû����д");
			return;
		}
		//������Ϣ
		if(!(new AdministratorBeansDAO().AdminSave(admin))){
			//���ʧ��
			System.out.println("����ʧ��");
			JOptionPane.showMessageDialog(null, "���ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//����setTableData����ˢ�±��
		wv.updateTableData();
		//�����������
		AdminInfoReset(auv);
		System.out.println("�����ɹ�!");
		JOptionPane.showMessageDialog(null, "��ӳɹ���");


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

		hint = "���";
		data = auv.getLid().getGettxt().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			if(!Pattern.matches("^[0-9]*$", data)){
				JOptionPane.showMessageDialog(null,hint + "��ʽ����\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			//���ֵ���ޱ���
			if(data.length()>=9){
				JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			admin.setAdminID(Integer.valueOf(data));
		}

		hint = "�ǳ�";
		data = auv.getLname().getGettxt().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//���ֵ���ޱ���
			if(data.length()>=9){
				JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			admin.setAdminName(data);
		}

		hint = "�˺�";
		data = auv.getLaccout().getGettxt().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//���ֵ���ޱ���
			if(data.length()>=6){
				JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			admin.setAccount(data);
		}
		
		if(auv.getOldPasswd()!=null){
			//��ԭ����
			if(auv.getOldPasswd().equals(auv.getPwfPasswd().getText())){
				//����û��
				admin.setPassword(auv.getOldPasswd());
			}else{
				//�������
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
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ����������˺ţ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}

		//��ȡ�˺�
		String ac = (String) av.getTableDataView().getValueAt(rowIndex, 2);

		//��ȡ�˺���Ϣ
		AdministratorBeans admin =  new AdministratorBeansDAO().getAdminInfoByAcc(ac);

		new ChangerPasswdView(av, admin,false);	
	}

	public void fix(AdminInfoUpdateView auv, AdminInfoManageView wv,MainView mv) {
		// TODO Auto-generated method stub
		AdministratorBeans admin = getAdminInformation(auv);
		//��Ϊ�յ���Ϣû��
		if(admin == null){
			System.out.println("ֵû����д");
			return;
		}
		//������Ϣ
		if(!(new AdministratorBeansDAO().SaveAdminInfo(admin))){
			//���ʧ��
			System.out.println("����ʧ��");
			JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//����setTableData����ˢ�±��
		wv.updateTableData();

		System.out.println("�޸ĳɹ�!");
		if(mv.getAdminAcc().equals(admin.getAccount())){
			mv.updateAdmin(admin);
			System.out.println("�����˵�ǰ��Ϣ");
		}
		
		JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
	}
}
