package com.vars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.vars.domain.Bid;
import com.vars.domain.Project;

public class BidDaoImpl extends JdbcDaoSupport implements BidDao {
	
	private static final String INSERT_BID = "INSERT into bid (project_id, tester_id, description, amount) values (?, ?, ?, ?)";
	private static final String CHECK_IF_BID = "SELECT * from bid where project_id = ? && tester_id = ?";
	private static final String GET_BIDS_PROJ = "SELECT * from bid where project_id = ?";
	private static final String GET_TESTERS_PROJ = "select * from project where tester_id = ?";
	private static final String INSERT_BID_IN_PROJECT = "UPDATE project set tester_id =? where id =?";
	private static final String GET_BID = "SELECT * from bid where id=?";
	public void createBid(Bid bid) {
		Project project = new Project();
		project.setProject_id(bid.getProjectId());
		project.setTester_id(bid.getTester());
		getJdbcTemplate().update(INSERT_BID, new Object[] {project.getProject_id(), project.getTester_id(), bid.getDescription(), bid.getAmount()});
	}

	@Override
	public Bid getBid(Integer id) {
		return getJdbcTemplate().queryForObject(GET_BID,
				new Object[] { id }, new RowMapper<Bid>() {
					@Override
					public Bid mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Bid bid = new Bid();
						bid.setId(rs.getInt(1));
						bid.setProjectId(rs.getInt(2));
						bid.setTester(rs.getInt(3));
						bid.setDescription(rs.getString(4));
						bid.setAmount(rs.getFloat(5));
						
						return bid;
					}
				});
	}

	@Override
	public ArrayList<Bid> getBidsForProjectDev(Integer id) {
		// returns a list of all bids related to a particular project
		List<Bid> bids = getJdbcTemplate().query(GET_BIDS_PROJ,
				new Object[] { id }, new RowMapper<Bid>() {
					@Override
					public Bid mapRow(ResultSet rs, int rowNum) throws SQLException {
						Bid bid = new Bid();
						bid.setId(rs.getInt(1));
						bid.setProjectId(rs.getInt(2));
						bid.setTester(rs.getInt(3));
						bid.setDescription(rs.getString(4));
						bid.setAmount(rs.getFloat(5));
						return bid;
					}
				});
		return (ArrayList<Bid>) bids;
	}

	@Override
	public ArrayList<Project> getProjectsForTester(Integer id) {
		List<Project> projects = getJdbcTemplate().query(GET_TESTERS_PROJ,
				new Object[] { id }, new RowMapper<Project>() {
					@Override
					public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
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
	public boolean checkIfBidMade(Integer project_id, Integer id) {
		List<Bid> bids = getJdbcTemplate().query(CHECK_IF_BID,
				new Object[] { project_id, id }, new RowMapper<Bid>() {
					@Override
					public Bid mapRow(ResultSet rs, int rowNum) throws SQLException {
						Bid bid = new Bid();
						bid.setAmount(rs.getFloat(5));
						return bid;
					}
				});
		
		if(bids.size() > 0)
		return true;
		return false;
	}

	@Override
	public void setBidForProject(Bid bid) {
		// TODO Auto-generated method stub
		
		
		getJdbcTemplate().update(INSERT_BID_IN_PROJECT, new Object[]{bid.getTester(), bid.getProjectId()});
	}
}