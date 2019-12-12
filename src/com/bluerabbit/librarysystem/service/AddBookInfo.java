package com.bluerabbit.librarysystem.service;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.dao.BookInfoUpdate;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
import com.bluerabbit.librarysystem.view.BookInfoUpdateView;
/**
 * �����鼮��Ϣ�ķ�����
 * @author minuy
 *
 */
public class AddBookInfo {
	BookInfoManageView bimv;
	public AddBookInfo(BookInfoManageView bimv){
		this.bimv = bimv;
	}
	
	public void add(BookInfoUpdateView bv){
		BookInfoBeans book = getBookInformation(bv);
		//��Ϊ�յ���Ϣû��
		if(book == null){
			System.out.println("ֵû����д");
			return;
		}
		if(!(new BookInfoUpdate().AddBook(book))){
			//���ʧ��
			System.out.println("����ʧ��");
			JOptionPane.showMessageDialog(null, "���ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//����setTableData����ˢ�±��
		bimv.updateTableData();
		//�����������
		new BookInfoReset(bv);
		System.out.println("�����ɹ�!");
		JOptionPane.showMessageDialog(null, "��ӳɹ���");
	}
	
	
	
	private BookInfoBeans getBookInformation(BookInfoUpdateView bv){
//		BookInfoBeans book = new BookInfoBeans();
//		String data = null;
//		String hint = null;
//
//		//�������
//		book.setLendTime(bv.getLendTime());
//		
//		hint = "��������";
//		data = bv.getStackL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			book.setStack(data);
//		}
//		
//		
//		hint = "�������";
//		data = bv.getBookShelfL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			book.setBookShelf(data);
//		}
//		
//		
//		hint = "����";
//		data = bv.getBookNameL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			book.setBookName(data);
//		}
//		
//		hint = "�鿯���";
//		data = bv.getBookIDL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			book.setBookID(data);
//		}
//		
//		hint = "�鿯����";
//		data = bv.getBookBarcodeL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			book.setBookBarcode(data);
//		}
//		
//		hint = "�ܲ���";
//		data = bv.getSumQuantityL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			//�ܲ���
//			book.setSumQuantity(Integer.valueOf(data));
//			//ʣ�����
//			book.setQuantity(Integer.valueOf(data));
//		}
//		
//		book.setBookClassify(bv.getBookClassifyL().getText());
//		book.setBookThem(bv.getBookThemL().getText());
//		book.setAuthor(bv.getAuthorL().getText());
//		book.setPublisher(bv.getPublisherL().getText());
//		book.setPublishTime(bv.getPublishTimeL().getText());
//		book.setPublishDate(bv.getPublishDateL().getText());
//		book.setBookType(bv.getBookTypeL().getText());
//		
//		if(bv.getPriceL().getText().equals("")){
//			book.setPrice(0);
//		}else{
//			book.setPrice(Double.valueOf(bv.getPriceL().getText()));
//		}
//		
//		book.setContentText(bv.getContentTextL().getText());
//		book.setRemark(bv.getRemarkL().getText());
//		
//		if(bv.getBookPageL().getText().equals("")){
//			book.setBookPage(0);
//		}else{
//			book.setBookPage(Integer.valueOf(bv.getBookPageL().getText()));
//		}
//		
//		book.setWordsNumber(bv.getWordsNumberL().getText());
//		
//		return book;
		
		BookInfoBeans book = new BookInfoBeans();
		String data = null;
		String hint = null;

		//�������
		book.setLendTime(bv.getLendTime());

		hint = "��������";
		data = bv.getStackL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//���ֵ���ޱ���
			if(data.length()>=20){
				JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			book.setStack(data);
		}


		hint = "�������";
		data = bv.getBookShelfL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//���ֵ���ޱ���
			if(data.length()>=20){
				JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			book.setBookShelf(data);
		}


		hint = "����";
		data = bv.getBookNameL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//���ֵ���ޱ���
			if(data.length()>=30){
				JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			book.setBookName(data);
		}

		hint = "�鿯���";
		data = bv.getBookIDL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//���ֵ���ޱ���
			if(data.length()>=30){
				JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			book.setBookID(data);
		}

		hint = "�鿯����";
		data = bv.getBookBarcodeL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{

			//���ֵ���ޱ���
			if(data.length()>=30){
				JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			book.setBookBarcode(data);
		}

		hint = "�ܲ���";
		data = bv.getSumQuantityL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "����Ϊ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			if(!Pattern.matches("^[+]{0,1}(\\d+)$", data)){
				JOptionPane.showMessageDialog(null,hint + "��ʽ����\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			//���ֵ���ޱ���
			if(data.length()>=9){
				JOptionPane.showMessageDialog(null,"�ܲ���ֵ0-99999999��\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			
				//�ܲ���
				book.setSumQuantity(Integer.valueOf(data));
				//ʣ�����
				book.setQuantity(Integer.valueOf(data));
		}
		
		hint = "�鿯����";
		data = bv.getBookClassifyL().getText();
		//���ֵ���ޱ���
		if(data.length()>=20){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setBookClassify(data);
		
		hint = "�����";
		data = bv.getBookThemL().getText();
		//���ֵ���ޱ���
		if(data.length()>=30){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setBookThem(data);
		
		
		hint = "����";
		data = bv.getAuthorL().getText();
		//���ֵ���ޱ���
		if(data.length()>=40){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setAuthor(data);
		
		
		hint = "������";
		data = bv.getPublisherL().getText();
		//���ֵ���ޱ���
		if(data.length()>=20){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setPublisher(data);
		
		
		hint = "������";
		data = bv.getPublishTimeL().getText();
		//���ֵ���ޱ���
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setPublishTime(data);
		
		
		hint = "��������";
		data = bv.getPublishDateL().getText();
		//���ֵ���ޱ���
		
		if(!Pattern.matches("((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|(2[0-8]))))))", data)){
			JOptionPane.showMessageDialog(null,hint + "��ʽ����\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		if(data.length()>=11){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setPublishDate(data);
		
		
		hint = "�ݲط���";
		data = bv.getBookTypeL().getText();
		//���ֵ���ޱ���
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setBookType(data);

		
		
		hint = "�۸�";
		data = bv.getPriceL().getText();
		//���ֵ���ޱ���
		if(data.length()>=8){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(data.equals("")){
			data = "0";
		}
		if(!Pattern.matches("^([1-9][0-9]*)?[0-9](\\.[0-9]{1,2})?$", data)){
			JOptionPane.showMessageDialog(null,hint + "��ʽ����\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setPrice(Double.valueOf(data));

		
		
		hint = "���";
		data = bv.getContentTextL().getText();
		//���ֵ���ޱ���
		if(data.length()>=300){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setContentText(data);
		
		
		hint = "��ע";
		data = bv.getRemarkL().getText();
		//���ֵ���ޱ���
		if(data.length()>=100){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setRemark(data);

		
		
		hint = "ҳ��";
		data = bv.getBookPageL().getText();
		//���ֵ���ޱ���
		if(data.length()>=8){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(data.equals("")){
			data = "1";
		}
		if(!Pattern.matches("^[+]{0,1}(\\d+)$", data)){
			JOptionPane.showMessageDialog(null,hint + "��ʽ����\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setBookPage(Integer.valueOf(data));

		
		
		hint = "����";
		data = bv.getWordsNumberL().getText();
		//���ֵ���ޱ���
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null,hint + "�������ȣ�\n�����޸ģ�", "����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setWordsNumber(data);

		return book;
	}
	
}
