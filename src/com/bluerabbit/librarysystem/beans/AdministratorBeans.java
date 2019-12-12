package com.bluerabbit.librarysystem.beans;

/**
 * 管理员bean类,储存管理员数据
 * @author minuy
 *
 */
public class AdministratorBeans {
	private int AdminID;
	private String AdminName;
	private String Account;
	private String Password;
	private boolean ifsuper;
	
	public boolean isIfsuper() {
		return ifsuper;
	}

	public void setIfsuper(boolean ifsuper) {
		this.ifsuper = ifsuper;
	}

	public String getAccount() {
		return Account;
	}
	
	public int getAdminID() {
		return AdminID;
	}

	public String getAdminName() {
		return AdminName;
	}

	public void setAdminName(String adminName) {
		AdminName = adminName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public void setAdminID(int adminID) {
		AdminID = adminID;
	}

	public void setAccount(String account) {
		Account = account;
	}

}
