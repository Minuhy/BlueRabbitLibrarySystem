package com.bluerabbit.librarysystem.beans;

/**
 * ����bean�࣬����һ�����ߵ������Ϣ
 * @author minuy
 *
 */
public class ReaderInfoBeans {
			private String ReaderID; //char(20) not null primary key comment 'ѧ��' ,
			private String ReaderName;// char(30) not null comment '����' ,
			private String Apart;// char(10) not null comment 'ѧԺ' ,
			private String Sex;// char(2) default '��' check (�Ա� in ('��','Ů')),
			private String TheClass;// char(10) not null comment '�༶' ,
			private String TelNo; //char(20) not null comment '�绰' 
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
