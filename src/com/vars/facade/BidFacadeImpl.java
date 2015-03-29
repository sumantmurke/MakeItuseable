package com.vars.facade;

import java.util.ArrayList;

import com.vars.dao.BidDao;
import com.vars.domain.Bid;
import com.vars.domain.Project;

public class BidFacadeImpl implements BidFacade {

	private BidDao bidDao;
	
	@Override
	public void createBid(Bid bid) {
		bidDao.createBid(bid);
		// TODO Auto-generated method stub
	}

	@Override
	public Bid getBid(Integer id) {
		return bidDao.getBid(id);
		
	}

	@Override
	public ArrayList<Bid> getBidsForProject(Integer id) {
		// TODO Auto-generated method stub
		return bidDao.getBidsForProjectDev(id);
	}

	@Override
	public ArrayList<Project> getProjectsForTester(Integer id) {
		// TODO Auto-generated method stub
		return bidDao.getProjectsForTester(id);
	}

	public void setBidDao(BidDao bidDao) {
		this.bidDao = bidDao;
	}

	@Override
	public boolean checkIfBidMade(Integer project_id, Integer id) {
		// TODO Auto-generated method stub
		return bidDao.checkIfBidMade(project_id, id);
	}

	@Override
	public void setBidForProject(Bid bid) {
		// TODO Auto-generated method stub
		bidDao.setBidForProject(bid);
	}

	
}