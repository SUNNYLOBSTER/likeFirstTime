package com.min.edu.vo;

public class UsersVO {
	
	private String name;
	private String addr;
	private String age;
	private String email;
	private String delflag;
	private String head;
	private String shoulder;
	private String foot;
	
	public UsersVO() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UsersVO [name=" + name + ", addr=" + addr + ", age=" + age + ", email=" + email + ", delflag=" + delflag
				+ ", head=" + head + ", shoulder=" + shoulder + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getShoulder() {
		return shoulder;
	}
	public void setShoulder(String shoulder) {
		this.shoulder = shoulder;
	}
	
	
	
}
