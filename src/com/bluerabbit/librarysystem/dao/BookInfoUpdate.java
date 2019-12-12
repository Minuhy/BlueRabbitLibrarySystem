package com.bluerabbit.librarysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.bluerabbit.librarysystem.beans.BookInfoBeans;
import com.bluerabbit.librarysystem.database.DBUtil;

/**
 * 与书相关的更新类，更新书籍信息，可合并
 * @author minuy
 *
 */
public class BookInfoUpdate {

	public boolean UpdateBookInfo(BookInfoBeans book){
		boolean rs = false;
		String sql = "UPDATE `library_system`.`Books_Info` SET `BookName` = ?, `SumQuantity` = ?, `Quantity` = ?, `LendTime` = ?, `BookBarcode` = ?, `BookClassify` = ?, `BookThem` = ?, `Author` = ?, `Publisher` = ?, `PublishTime` = ?, `PublishDate` = ?, `BookType` = ?, `Stack` = ?, `BookShelf` = ?, `Price` = ?, `ContentText` = ?, `Remark` = ?, `BookPage` = ?, `WordsNumber` = ? WHERE (`BookID` = ?)";
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1,book.getBookName());
			pstm.setString(2,book.getSumQuantity()+"");
			pstm.setString(3,book.getQuantity()+"");
			pstm.setString(4,book.getLendTime()+"");
			pstm.setString(5,book.getBookBarcode());
			pstm.setString(6,book.getBookClassify());
			pstm.setString(7,book.getBookThem());
			pstm.setString(8,book.getAuthor());
			pstm.setString(9,book.getPublisher());
			pstm.setString(10,book.getPublishTime());
			pstm.setString(11,book.getPublishDate());
			pstm.setString(12,book.getBookType());
			pstm.setString(13,book.getStack());
			pstm.setString(14,book.getBookShelf());
			pstm.setString(15,book.getPrice()+"");
			pstm.setString(16,book.getContentText());
			pstm.setString(17,book.getRemark());
			pstm.setString(18,book.getBookPage()+"");
			pstm.setString(19,book.getWordsNumber());
			pstm.setString(20,book.getBookID());
			//执行语句
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("数据更新结果:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}
		
		return rs;
	}

	public boolean AddBook(BookInfoBeans book){
		boolean rs = false;
		String sql = "INSERT INTO `library_system`.`Books_Info` (`BookName`, `SumQuantity`, `Quantity`, `LendTime`, `BookID`, `BookBarcode`, `BookClassify`, `BookThem`, `Author`, `Publisher`, `PublishTime`, `PublishDate`, `BookType`, `Stack`, `BookShelf`, `Price`, `ContentText`, `Remark`,`BookPage`,`WordsNumber`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;

		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1,book.getBookName());
			pstm.setString(2,book.getSumQuantity()+"");
			pstm.setString(3,book.getQuantity()+"");
			pstm.setString(4,book.getLendTime()+"");
			pstm.setString(5,book.getBookID());
			pstm.setString(6,book.getBookBarcode());
			pstm.setString(7,book.getBookClassify());
			pstm.setString(8,book.getBookThem());
			pstm.setString(9,book.getAuthor());
			pstm.setString(10,book.getPublisher());
			pstm.setString(11,book.getPublishTime());
			pstm.setString(12,book.getPublishDate());
			pstm.setString(13,book.getBookType());
			pstm.setString(14,book.getStack());
			pstm.setString(15,book.getBookShelf());
			pstm.setString(16,book.getPrice()+"");
			pstm.setString(17,book.getContentText());
			pstm.setString(18,book.getRemark());
			pstm.setString(19,book.getBookPage()+"");
			pstm.setString(20,book.getWordsNumber());
			//执行语句
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("数据操作结果:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e);
			String ee = String.valueOf(e);
			if(ee.indexOf("Duplicate entry") != (-1)){
				JOptionPane.showMessageDialog(null, "书籍编号不能相同！", "错误", JOptionPane.ERROR_MESSAGE);
			}

		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}

		return rs;
	}

	public boolean DelByBookID(String key){
		//声明结果对象
		boolean rs = false;
		//DELETE FROM Books_Info WHERE BookId='00002';
		String sql = "DELETE FROM Books_Info WHERE BookId=?";
		//获得数据库连接
		Connection conn = DBUtil.getConn();
		//声明句柄对象
		PreparedStatement pstm = null;
		try {
			//获得操作句柄
			pstm = conn.prepareStatement(sql);
			//拼接语句
			pstm.setString(1,key);
			//执行语句
			if(pstm.executeUpdate() == 1){
				rs = true;
			}
			System.out.println("数据操作结果:"+rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//一定要做的事，释放连接
			DBUtil.free(pstm,conn);
		}
		return rs;
	}
}
