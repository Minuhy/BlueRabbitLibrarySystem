package com.bluerabbit.librarysystem.beans;

/**
 * 书bean类，储存一本书的信息
 * @author minuy
 *
 */
public class BookInfoBeans {
	/**
BookName varchar(30) not null comment '书名' , 
SumQuantity int not null comment '总册数' ,
Quantity int not null comment '剩余册数' ,
LendTime int not null comment '借出次数',
BookID varchar(30) not null primary key comment '书刊编号' ,
BookBarcode varchar(30) not null comment '书刊条码',
BookClassify varchar(20) comment '书刊分类',
BookThem varchar(30) comment '主题词',
author varchar(40) comment '作者' ,
Publisher varchar(20) comment '出版社' ,
PublishTime varchar(10) comment '出版版次',
PublishDate date comment '出版日期' ,
BookType varchar(10) comment '馆藏分类',
Stack varchar(20) not null comment '所在书室' ,
BookShelf varchar(20) not null comment '所在书架' ,
Price float comment '价格' ,
ContentText varchar(300) comment '简介',
Remark varchar(100) comment '备注' ,
BookPage int comment '页数' ,
WordsNumber varchar(20) comment '字数'  

	 */
	
	private String BookName;
	private int SumQuantity;
	private int Quantity;
	private int LendTime;
	private String BookID;
	private String BookBarcode;
	private String BookClassify;
	private String BookThem;
	private String Author;
	private String Publisher;
	private String PublishTime;
	private String PublishDate;
	private String BookType;
	private String Stack;
	private String BookShelf;
	private double Price;
	private String ContentText;
	private String Remark;
	private int BookPage;
	private String WordsNumber;
	
	
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		this.BookName = bookName;
	}
	public int getSumQuantity() {
		return SumQuantity;
	}
	public void setSumQuantity(int sumQuantity) {
		this.SumQuantity = sumQuantity;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		this.Quantity = quantity;
	}
	public int getLendTime() {
		return LendTime;
	}
	public void setLendTime(int lendTime) {
		this.LendTime = lendTime;
	}
	public String getBookID() {
		return BookID;
	}
	public void setBookID(String bookID) {
		this.BookID = bookID;
	}
	public String getBookBarcode() {
		return BookBarcode;
	}
	public void setBookBarcode(String bookBarcode) {
		this.BookBarcode = bookBarcode;
	}
	public String getBookClassify() {
		return BookClassify;
	}
	public void setBookClassify(String bookClassify) {
		this.BookClassify = bookClassify;
	}
	public String getBookThem() {
		return BookThem;
	}
	public void setBookThem(String bookThem) {
		this.BookThem = bookThem;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String Author) {
		this.Author = Author;
	}
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		this.Publisher = publisher;
	}
	public String getPublishTime() {
		return PublishTime;
	}
	public void setPublishTime(String publishTime) {
		this.PublishTime = publishTime;
	}
	public String getPublishDate() {
		return PublishDate;
	}
	public void setPublishDate(String publishDate) {
		this.PublishDate = publishDate;
	}
	public String getBookType() {
		return BookType;
	}
	public void setBookType(String bookType) {
		this.BookType = bookType;
	}
	public String getStack() {
		return Stack;
	}
	public void setStack(String stack) {
		this.Stack = stack;
	}
	public String getBookShelf() {
		return BookShelf;
	}
	public void setBookShelf(String bookShelf) {
		this.BookShelf = bookShelf;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		this.Price = price;
	}
	public String getContentText() {
		return ContentText;
	}
	public void setContentText(String contentText) {
		this.ContentText = contentText;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		this.Remark = remark;
	}
	public int getBookPage() {
		return BookPage;
	}
	public void setBookPage(int bookPage) {
		this.BookPage = bookPage;
	}
	public String getWordsNumber() {
		return WordsNumber;
	}
	public void setWordsNumber(String wordsNumber) {
		this.WordsNumber = wordsNumber;
	}
}
