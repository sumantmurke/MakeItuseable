package com.vars.dao;

import java.util.ArrayList;

import com.vars.domain.Bid;
import com.vars.domain.Project;

public interface BidDao {
	void createBid(Bid user);
	Bid getBid(Integer id);
	ArrayList<Bid> getBidsForProjectDev(Integer id);
	ArrayList<Project> getProjectsForTester(Integer id);
	boolean checkIfBidMade(Integer project_id, Integer id);
	void setBidForProject(Bid bid);
}
