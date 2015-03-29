package com.vars.domain;

public class Project {
	private Integer project_id;
	private String title;
	private String description;
	private Integer developer_id;
	private Integer tester_id;
	private String domain;
	private String project_url;
	private Integer min_budget;
	private Integer max_budget;
	private String project_skills;
	private String project_users;
	private String developerName;
	private String results;
	private String testerName;
	
	
	
	public String getDeveloperName() {
		return developerName;
	}
	public Integer getProject_id() {
		return project_id;
	}
	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDeveloper_id() {
		return developer_id;
	}
	public void setDeveloper_id(Integer developer_id) {
		this.developer_id = developer_id;
	}
	public Integer getTester_id() {
		return tester_id;
	}
	public void setTester_id(Integer tester_id) {
		this.tester_id = tester_id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getProject_users() {
		return project_users;
	}
	public void setProject_users(String project_users) {
		this.project_users = project_users;
	}
	public String getProject_skills() {
		return project_skills;
	}
	public void setProject_skills(String project_skills) {
		this.project_skills = project_skills;
	}
	public Integer getMax_budget() {
		return max_budget;
	}
	public void setMax_budget(Integer max_budget) {
		this.max_budget = max_budget;
	}
	public Integer getMin_budget() {
		return min_budget;
	}
	public void setMin_budget(Integer min_budget) {
		this.min_budget = min_budget;
	}
	public String getProject_url() {
		return project_url;
	}
	public void setProject_url(String project_url) {
		this.project_url = project_url;
	}
	public void setDeveloperName(String firstName) {
		// TODO Auto-generated method stub
		this.developerName = firstName;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	public void setTesterName(String firstName) {
		// TODO Auto-generated method stub
		this.testerName = firstName;
	}
	public String getTesterName() {
		return testerName;
	}
	
	}
