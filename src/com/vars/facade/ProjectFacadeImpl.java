package com.vars.facade;

import java.util.ArrayList;
import java.util.List;

import com.vars.dao.ProjectDao;
import com.vars.domain.Project;

public class ProjectFacadeImpl implements ProjectFacade {

	private ProjectDao projectDao;
	
	@Override
	public void createProject(Project project) {
		projectDao.createProject(project);
	}

	@Override
	public Project getProject(Integer id) {
		return projectDao.getProject(id);
	}
	
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Override
	public List<Project> getProjectDev(Integer id) {
		return projectDao.getProjectDev(id);	
	}
	
	@Override
	public List<Project> getProjectTest(Integer id){
		return projectDao.getProjectTest(id);
	}
	
	@Override
	public ArrayList<Project> getNewProjects(){
		return projectDao.getNewProjects();
	}

	@Override
	public void giveTesterResults(String results, String projectTitle) {
		projectDao.giveTesterResults(results, projectTitle);
		
	}

	@Override
	public List<Project> getCompletedProjects(Integer id) {
		// TODO Auto-generated method stub
		return projectDao.getCompletedProjects(id);
	}

	@Override
	public void setTesterRating(int developerID, int testerId, Integer inputRating,
			String dateNow) {
		// TODO Auto-generated method stub
		projectDao.setTesterRating(developerID, testerId, inputRating, dateNow);
		
	}

}
