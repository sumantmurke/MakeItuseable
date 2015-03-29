package com.vars.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.vars.domain.Project;

public class ProjectDaoImpl extends JdbcDaoSupport implements ProjectDao {

	private static final String GET_PROJECT = "select * from project where id = ?";
	private static final String INSERT_PROJECT = "INSERT into project (title, description, domain, devp_id,project_url,min_budget,max_budget,project_skills,project_users) values (?, ?, ?, ?,?,?,?,?,?)";
	private static final String GET_PROJECTS_DEV = "select * from project where devp_id = ? order by id";
	private static final String GET_PROJECTS_TESTER = "select * from project where tester_id IS NULL order by id";
	private static final String INSERT_TESTING_RESULTS = "update project set results = ? where title = ?";
	private static final String GET_COMPLETED_PROJECTS_DEV = "select * from project where devp_id = ? and results IS NOT NULL order by id";
	private static final String INSERT_RATING = "INSERT into tester_rating (tester_id, developer_id, rating, ratingdate) values (?, ?, ?, ?)";
	
	@Override
	public void createProject(Project project) {
		// TODO Auto-generated method stub
		getJdbcTemplate().update( INSERT_PROJECT, new Object[] { project.getTitle(), project.getDescription(), project.getDomain(), project.getDeveloper_id(),project.getProject_url(),project.getMin_budget(),project.getMax_budget(),project.getProject_skills(),project.getProject_users() });
	}

	@Override
	public void setTesterRating(int developerID, int testerId,
			Integer inputRating, String date) {
		// TODO Auto-generated method stub
		getJdbcTemplate().update( INSERT_RATING,
				new Object[] { testerId, developerID, inputRating, date });
	}
	
	@Override
	public Project getProject(Integer id) {
		System.out.println("Getting project for id"+ id);
		return getJdbcTemplate().queryForObject(GET_PROJECT,
				new Object[] { id }, new RowMapper<Project>() {
					@Override
					public Project mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Project project = new Project();
						project.setProject_id(rs.getInt(1));
						project.setTitle(rs.getString(2));
						project.setDescription(rs.getString(3));
						project.setDomain(rs.getString(4));
						project.setDeveloper_id(rs.getInt(5));
						project.setTester_id(rs.getInt(6));
						project.setResults(rs.getString(12));
						return project;
					}
				});
	}

	@Override
	public List<Project> getProjectDev(Integer id) {
		List<Project> projects = getJdbcTemplate().query(GET_PROJECTS_DEV,
				new Object[] { id }, new RowMapper<Project>() {
					@Override
					public Project mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Project project = new Project();
						project.setProject_id(rs.getInt(1));
						project.setTitle(rs.getString(2));
						project.setDescription(rs.getString(3));
						project.setDomain(rs.getString(4));
						project.setDeveloper_id(rs.getInt(5));
						project.setTester_id(rs.getInt(6));
						return project;
					}
				});

		return projects;
	}

	@Override
	public List<Project> getProjectTest(Integer id) {
		List<Project> projects = getJdbcTemplate().query(GET_PROJECTS_TESTER,
				new Object[] { id }, new RowMapper<Project>() {
					@Override
					public Project mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Project project = new Project();
						project.setProject_id(rs.getInt(1));
						project.setTitle(rs.getString(2));
						project.setDescription(rs.getString(3));
						project.setDomain(rs.getString(4));
						project.setDeveloper_id(rs.getInt(5));
						project.setTester_id(rs.getInt(6));
						return project;
					}
				});

		return projects;
	}
	
	@Override
	public ArrayList<Project> getNewProjects() {
		
		List<Project> projects = getJdbcTemplate().query(GET_PROJECTS_TESTER,
				new Object[] { }, new RowMapper<Project>() {
					@Override
					public Project mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Project project = new Project();
						project.setProject_id(rs.getInt(1));
						project.setTitle(rs.getString(2));
						project.setDescription(rs.getString(3));
						project.setDomain(rs.getString(4));
						project.setDeveloper_id(rs.getInt(5));
						project.setTester_id(rs.getInt(6));
						return project;
					}
				});

		return (ArrayList<Project>) projects;
	}

	@Override
	public void giveTesterResults(String results, String projectTitle) {
		System.out.println("insert testing results for"+ projectTitle+ " as"+ results);
		getJdbcTemplate().update( INSERT_TESTING_RESULTS, new Object[] { results, projectTitle });
	}

	@Override
	public List<Project> getCompletedProjects(Integer id) {
		// TODO Auto-generated method stub
		// GET_COMPLETED_PROJECTS_DEV
		List<Project> projects = getJdbcTemplate().query(GET_COMPLETED_PROJECTS_DEV,
				new Object[] { id }, new RowMapper<Project>() {
					@Override
					public Project mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Project project = new Project();
						project.setProject_id(rs.getInt(1));
						project.setTitle(rs.getString(2));
						project.setDescription(rs.getString(3));
						project.setDomain(rs.getString(4));
						project.setDeveloper_id(rs.getInt(5));
						project.setTester_id(rs.getInt(6));
						return project;
					}
				});

		return projects;
	}
}
