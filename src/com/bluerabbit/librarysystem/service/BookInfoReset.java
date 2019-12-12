package com.bluerabbit.librarysystem.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bluerabbit.librarysystem.view.BookInfoUpdateView;
/**
 * ͼ����Ϣ���µ��߼�
 * @author minuy
 *
 */
public class BookInfoReset {
	//���ͼ����ϸҳ����Ϣ
	public BookInfoReset(BookInfoUpdateView biuv){
		biuv.getBookNameL().getGettxt().setText("");
		biuv.getSumQuantityL().getGettxt().setText("");
		biuv.getBookIDL().getGettxt().setText("");
		biuv.getBookBarcodeL().getGettxt().setText("");
		biuv.getBookClassifyL().getGettxt().setText("");
		biuv.getBookThemL().getGettxt().setText("");
		biuv.getAuthorL().getGettxt().setText("");
		biuv.getPublisherL().getGettxt().setText("");
		biuv.getPublishTimeL().getGettxt().setText("");
		
		biuv.getBookTypeL().getGettxt().setText("");
		biuv.getStackL().getGettxt().setText("");
		biuv.getBookShelfL().getGettxt().setText("");
		biuv.getPriceL().getGettxt().setText("");
		biuv.getContentTextL().getGettxt().setText("");
		biuv.getRemarkL().getGettxt().setText("");
		biuv.getBookPageL().getGettxt().setText("");
		biuv.getWordsNumberL().getGettxt().setText("");
		
		//����ʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		biuv.getPublishDateL().setIText(df.format(new Date()));
	}
}
