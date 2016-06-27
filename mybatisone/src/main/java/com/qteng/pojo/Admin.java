package com.qteng.pojo;

public class Admin {
	private Integer id;
	private String account;
	private String password;
	private String pwd;
	private String email;

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"id=" + id +
				", account='" + account + '\'' +
				", password='" + password + '\'' +
				", pwd='" + pwd + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	public Admin(String account, String password, String pwd, String email) {
		this.account = account;
		this.password = password;
		this.pwd = pwd;
		this.email = email;
	}

	public Admin() {

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
