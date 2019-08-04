package com.bean;

public class Supervisor {
	private int userid;
	private String password;
	private String sitename;
	public Supervisor(int userid, String password, String sitename) {
		super();
		this.userid = userid;
		this.password = password;
		this.sitename = sitename;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	@Override
	public String toString() {
		return "Supervisor [userid=" + userid + ", password=" + password + ", sitename=" + sitename + "]";
	}

}
