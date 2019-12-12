package com.bluerabbit.librarysystem.service;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.dao.BookInfoUpdate;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
import com.bluerabbit.librarysystem.view.BookInfoUpdateView;
/**
 * 增加书籍信息的服务类
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
		//不为空的信息没填
		if(book == null){
			System.out.println("值没有填写");
			return;
		}
		if(!(new BookInfoUpdate().AddBook(book))){
			//如果失败
			System.out.println("新增失败");
			JOptionPane.showMessageDialog(null, "添加失败！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//调用setTableData方法刷新表格
		bimv.updateTableData();
		//清空输入数据
		new BookInfoReset(bv);
		System.out.println("新增成功!");
		JOptionPane.showMessageDialog(null, "添加成功！");
	}
	
	
	
	private BookInfoBeans getBookInformation(BookInfoUpdateView bv){
//		BookInfoBeans book = new BookInfoBeans();
//		String data = null;
//		String hint = null;
//
//		//借出次数
//		book.setLendTime(bv.getLendTime());
//		
//		hint = "所在书室";
//		data = bv.getStackL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			book.setStack(data);
//		}
//		
//		
//		hint = "所在书架";
//		data = bv.getBookShelfL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			book.setBookShelf(data);
//		}
//		
//		
//		hint = "书名";
//		data = bv.getBookNameL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			book.setBookName(data);
//		}
//		
//		hint = "书刊编号";
//		data = bv.getBookIDL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			book.setBookID(data);
//		}
//		
//		hint = "书刊条码";
//		data = bv.getBookBarcodeL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			book.setBookBarcode(data);
//		}
//		
//		hint = "总册数";
//		data = bv.getSumQuantityL().getText();
//		if(data.equals("")){
//			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}else{
//			//总册数
//			book.setSumQuantity(Integer.valueOf(data));
//			//剩余册数
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

		//借出次数
		book.setLendTime(bv.getLendTime());

		hint = "所在书室";
		data = bv.getStackL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//最大值上限报错
			if(data.length()>=20){
				JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			book.setStack(data);
		}


		hint = "所在书架";
		data = bv.getBookShelfL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//最大值上限报错
			if(data.length()>=20){
				JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			book.setBookShelf(data);
		}


		hint = "书名";
		data = bv.getBookNameL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//最大值上限报错
			if(data.length()>=30){
				JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			book.setBookName(data);
		}

		hint = "书刊编号";
		data = bv.getBookIDL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			//最大值上限报错
			if(data.length()>=30){
				JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			book.setBookID(data);
		}

		hint = "书刊条码";
		data = bv.getBookBarcodeL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{

			//最大值上限报错
			if(data.length()>=30){
				JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			book.setBookBarcode(data);
		}

		hint = "总册数";
		data = bv.getSumQuantityL().getText();
		if(data.equals("")){
			JOptionPane.showMessageDialog(null, hint + "不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}else{
			if(!Pattern.matches("^[+]{0,1}(\\d+)$", data)){
				JOptionPane.showMessageDialog(null,hint + "格式错误！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			//最大值上限报错
			if(data.length()>=9){
				JOptionPane.showMessageDialog(null,"总册数值0-99999999！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			
				//总册数
				book.setSumQuantity(Integer.valueOf(data));
				//剩余册数
				book.setQuantity(Integer.valueOf(data));
		}
		
		hint = "书刊分类";
		data = bv.getBookClassifyL().getText();
		//最大值上限报错
		if(data.length()>=20){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setBookClassify(data);
		
		hint = "主题词";
		data = bv.getBookThemL().getText();
		//最大值上限报错
		if(data.length()>=30){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setBookThem(data);
		
		
		hint = "作者";
		data = bv.getAuthorL().getText();
		//最大值上限报错
		if(data.length()>=40){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setAuthor(data);
		
		
		hint = "出版社";
		data = bv.getPublisherL().getText();
		//最大值上限报错
		if(data.length()>=20){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setPublisher(data);
		
		
		hint = "出版版次";
		data = bv.getPublishTimeL().getText();
		//最大值上限报错
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setPublishTime(data);
		
		
		hint = "出版日期";
		data = bv.getPublishDateL().getText();
		//最大值上限报错
		
		if(!Pattern.matches("((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|(2[0-8]))))))", data)){
			JOptionPane.showMessageDialog(null,hint + "格式错误！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		if(data.length()>=11){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setPublishDate(data);
		
		
		hint = "馆藏分类";
		data = bv.getBookTypeL().getText();
		//最大值上限报错
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setBookType(data);

		
		
		hint = "价格";
		data = bv.getPriceL().getText();
		//最大值上限报错
		if(data.length()>=8){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(data.equals("")){
			data = "0";
		}
		if(!Pattern.matches("^([1-9][0-9]*)?[0-9](\\.[0-9]{1,2})?$", data)){
			JOptionPane.showMessageDialog(null,hint + "格式错误！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setPrice(Double.valueOf(data));

		
		
		hint = "简介";
		data = bv.getContentTextL().getText();
		//最大值上限报错
		if(data.length()>=300){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setContentText(data);
		
		
		hint = "备注";
		data = bv.getRemarkL().getText();
		//最大值上限报错
		if(data.length()>=100){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setRemark(data);

		
		
		hint = "页数";
		data = bv.getBookPageL().getText();
		//最大值上限报错
		if(data.length()>=8){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(data.equals("")){
			data = "1";
		}
		if(!Pattern.matches("^[+]{0,1}(\\d+)$", data)){
			JOptionPane.showMessageDialog(null,hint + "格式错误！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setBookPage(Integer.valueOf(data));

		
		
		hint = "字数";
		data = bv.getWordsNumberL().getText();
		//最大值上限报错
		if(data.length()>=10){
			JOptionPane.showMessageDialog(null,hint + "超出长度！\n请检查修改！", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		book.setWordsNumber(data);

		return book;
	}
	
}
