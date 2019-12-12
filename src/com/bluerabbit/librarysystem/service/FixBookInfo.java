package com.bluerabbit.librarysystem.service;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.dao.BookInfoUpdate;
import com.bluerabbit.librarysystem.view.BookInfoManageView;
import com.bluerabbit.librarysystem.view.BookInfoUpdateView;
/**
 * 修改图书信息的服务类
 * @author minuy
 *
 */
public class FixBookInfo {
	public FixBookInfo(){

	}
	/**
	 * 获取数据给详情页面
	 * @param wv
	 * @param mode true 更新，false 查看
	 */
	public FixBookInfo(BookInfoManageView wv,boolean mode) {
		// TODO Auto-generated constructor stub
		int rowIndex = wv.getTableDataView().getSelectedRow();

		if(mode){
			if(rowIndex == -1) {
				JOptionPane.showMessageDialog(null, "请选中要修改的书籍！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(rowIndex == -1){
			System.out.println("出错！");
			return;
		}
		
		String bookID = (String) wv.getTableDataView().getValueAt(rowIndex, 8);
		if(bookID.equals("")){
			JOptionPane.showMessageDialog(null, "表单信息错误！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//打印选中ID
		System.out.println(bookID);
		
		if(mode){
			new BookInfoUpdateView(wv,"修改图书信息",bookID,true);
		}else{
			new BookInfoUpdateView(wv,"图书信息详情",bookID,false);
		}
	}

	public void fix(BookInfoUpdateView biuv,BookInfoManageView wv) {
		// TODO Auto-generated method stub
		//在获取数据的时候已经验证合法性
		BookInfoBeans book = getBookInformation(biuv);
		//不为空的信息没填
		if(book == null){
			System.out.println("值没有填写");
			return;
		}
		//调用更新图书信息
		if(!(new BookInfoUpdate().UpdateBookInfo(book))){
			//如果失败
			System.out.println("更新失败");
			JOptionPane.showMessageDialog(null, "更新失败！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//调用setTableData方法刷新表格
		wv.updateTableData();
		System.out.println("更新成功!");
		JOptionPane.showMessageDialog(null, "更新成功！");
	}

	private BookInfoBeans getBookInformation(BookInfoUpdateView bv){
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

