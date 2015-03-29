package com.vars.domain;

public class User {
	
	private Integer id;
	private String password;
	private String userName;
	private Developer developer;
	private Tester tester;
	private boolean isTester;
	private String firstName;
	private String lastName;
	private String linkedInId;
	private String linkedInUrl;
	
	public String getLinkedInUrl() {
		return linkedInUrl;
	}
	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	public Tester getTester() {
		return tester;
	}
	public void setTester(Tester tester) {
		this.tester = tester;
	}
	public boolean getIsTester() {
		return isTester;
	}
	public void setIsTester(boolean isTester) {
		this.isTester = isTester;
	}
	public String getLinkedInId() {
		return linkedInId;
	}
	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
