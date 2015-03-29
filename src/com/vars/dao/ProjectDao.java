package com.vars.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.vars.domain.Project;

public interface ProjectDao {
	void createProject(Project user);
	Project getProject(Integer id);
	List<Project> getProjectDev(Integer id);
	List<Project> getProjectTest(Integer id);
	ArrayList<Project> getNewProjects();
	void giveTesterResults(String results, String projectTitle);
	List<Project> getCompletedProjects(Integer id);
	void setTesterRating(int developerID, int testerId, Integer inputRating,
			String date);
}
