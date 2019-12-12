package com.bluerabbit.librarysystem.beans;

/**
 * ��bean�࣬����һ�������Ϣ
 * @author minuy
 *
 */
public class BookInfoBeans {
	/**
BookName varchar(30) not null comment '����' , 
SumQuantity int not null comment '�ܲ���' ,
Quantity int not null comment 'ʣ�����' ,
LendTime int not null comment '�������',
BookID varchar(30) not null primary key comment '�鿯���' ,
BookBarcode varchar(30) not null comment '�鿯����',
BookClassify varchar(20) comment '�鿯����',
BookThem varchar(30) comment '�����',
author varchar(40) comment '����' ,
Publisher varchar(20) comment '������' ,
PublishTime varchar(10) comment '������',
PublishDate date comment '��������' ,
BookType varchar(10) comment '�ݲط���',
Stack varchar(20) not null comment '��������' ,
BookShelf varchar(20) not null comment '�������' ,
Price float comment '�۸�' ,
ContentText varchar(300) comment '���',
Remark varchar(100) comment '��ע' ,
BookPage int comment 'ҳ��' ,
WordsNumber varchar(20) comment '����'  

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
