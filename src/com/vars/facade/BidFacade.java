package com.vars.facade;

import java.util.ArrayList;

import com.vars.domain.Bid;
import com.vars.domain.Project;

public interface BidFacade {
	void createBid(Bid bid);
	Bid getBid(Integer id);
	ArrayList<Bid> getBidsForProject(Integer id);
	ArrayList<Project> getProjectsForTester(Integer id);
	boolean checkIfBidMade(Integer project_id, Integer id);
	void setBidForProject(Bid bid);
}
