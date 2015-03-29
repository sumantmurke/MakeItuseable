package com.vars.facade;

import java.util.ArrayList;
import java.util.List;

import com.vars.domain.Project;

public interface ProjectFacade {
	void createProject(Project project);
	Project getProject(Integer id);
	List<Project> getProjectDev(Integer id);
	List<Project> getProjectTest(Integer id);
	ArrayList<Project> getNewProjects();
	void giveTesterResults(String results, String projectTitle);
	List<Project> getCompletedProjects(Integer id);
	void setTesterRating(int i, int j, Integer inputRating, String dateNow);
}
