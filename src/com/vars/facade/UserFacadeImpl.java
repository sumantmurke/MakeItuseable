package com.vars.facade;

import java.util.ArrayList;

import com.vars.dao.UserDao;
import com.vars.domain.Developer;
import com.vars.domain.Tester;
import com.vars.domain.TestingRating;
import com.vars.domain.User;

public class UserFacadeImpl implements UserFacade{

	private UserDao userDao;
	
	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		// Accept and insert all params in database
		userDao.createUser(user);
	}

	@Override
	public User getUser(String userName) {
		return userDao.getUser(userName);
	}
	
	@Override
	public User getInUser(String inId) {
		return userDao.getInUser(inId);
	}
	
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
		
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean checkInUser(String email) {
		return userDao.checkInUser(email);
	}

	@Override
	public Tester getTester(Integer id) {
		return userDao.getTester(id);
	}
	@Override
	public User getUserForId(Integer id) {
		// TODO Auto-generated method stub
		return userDao.getUserForId(id);
	}

	@Override
	public ArrayList<TestingRating> getRatingForTester(Integer id) {
		// TODO Auto-generated method stub
		return userDao.getRatingForTester(id);
	}

	public Developer getDeveloper(Integer id) {
		// TODO Auto-generated method stub
		return userDao.getDeveloperForId(id);
	}
}
