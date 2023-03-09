package com.bluerabbit.librarysystem.beans;

/**
 * 读者bean类，储存一个读者的相关信息
 * @author minuy
 *
 */
public class ReaderInfoBeans {
			private String ReaderID; //char(20) not null primary key comment '学号' ,
			private String ReaderName;// char(30) not null comment '姓名' ,
			private String Apart;// char(10) not null comment '学院' ,
			private String Sex;// char(2) default '男' check (性别 in ('男','女')),
			private String TheClass;// char(10) not null comment '班级' ,
			private String TelNo; //char(20) not null comment '电话' 
			public String getReaderID() {
				return ReaderID;
			}
			public void setReaderID(String readerID) {
				ReaderID = readerID;
			}
			public String getReaderName() {
				return ReaderName;
			}
			public void setReaderName(String readerName) {
				ReaderName = readerName;
			}
			public String getApart() {
				return Apart;
			}
			public void setApart(String apart) {
				Apart = apart;
			}
			public String getSex() {
				return Sex;
			}
			public void setSex(String sex) {
				Sex = sex;
			}
			public String getTheClass() {
				return TheClass;
			}
			public void setTheClass(String class1) {
				TheClass = class1;
			}
			public String getTelNo() {
				return TelNo;
			}
			public void setTelNo(String telNo) {
				TelNo = telNo;
			}

	@Override
	public String toString() {
		return "ReaderInfoBeans{" +
				"ReaderID='" + ReaderID + '\'' +
				", ReaderName='" + ReaderName + '\'' +
				", Apart='" + Apart + '\'' +
				", Sex='" + Sex + '\'' +
				", TheClass='" + TheClass + '\'' +
				", TelNo='" + TelNo + '\'' +
				'}';
	}
}
