package com.vars.facade;

import java.util.ArrayList;

import com.vars.domain.Developer;
import com.vars.domain.Tester;
import com.vars.domain.TestingRating;
import com.vars.domain.User;

public interface UserFacade {
	
	void createUser(User user);
	
	User getUser(String userName);
	
	void updateUser(User user);
	
	boolean checkInUser(String email);

	User getInUser(String inId);
	
	Tester getTester(Integer id);

	User getUserForId(Integer id);

	ArrayList<TestingRating> getRatingForTester(Integer id);

	Developer getDeveloper(Integer id);	
}
