package com.vars.domain;

public class Bid {
	private Integer id;
	private Integer projectId;
	private Integer tester;
	private String description;
	private float amount;
	private String projectName;
	private String testerName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getTester() {
		return tester;
	}
	public void setTester(Integer tester) {
		this.tester = tester;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getProjectName() {
		return projectName;
	}
	public String getTesterName() {
		return testerName;
	}
	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}
	public void setProjectName(String projectName) {
		// TODO Auto-generated method stub
		this.projectName = projectName;
	}
	
}
