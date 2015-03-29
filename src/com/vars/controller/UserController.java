package com.vars.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vars.domain.Developer;
import com.vars.domain.Project;
import com.vars.domain.Tester;
import com.vars.domain.TestingRating;
import com.vars.domain.User;
import com.vars.facade.UserFacade;

@Controller
public class UserController {

	private UserFacade userFacade;
	
	@RequestMapping(value = "/showUser.htm", method = RequestMethod.GET)
	public ModelAndView showUser() {
		return new ModelAndView("create_user");
	}

	@RequestMapping(value = "/showProfile.htm", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		ArrayList<TestingRating> ratings = null;
		modelAndView.setViewName("/user_profile");
		Float ratingAverage = 0.0f;
		User user = (User) session.getAttribute("user");
		Tester tester = new Tester();
		
		if(user.getIsTester())
		{
			tester.setUserId(user.getId());
			
			ratings = userFacade.getRatingForTester(user.getTester().getId());
			for (TestingRating testingRating : ratings) {
				ratingAverage += testingRating.getRating();
				Developer developer = userFacade.getDeveloper(testingRating.getDeveloperId());
				User userDev = userFacade.getUserForId((developer.getUserId()));
				testingRating.setDeveloperName(userDev.getFirstName());
			}
			ratingAverage = ratingAverage/ratings.size();
			ratingAverage = (float)(Math.round(ratingAverage*100.0)/100.0);
			tester.setAverageRating(ratingAverage);
			user.getTester().setAverageRating(ratingAverage);
			if(ratings.size() > 0)
				modelAndView.addObject("ratings", ratings);
			modelAndView.addObject("tester", tester);
		}
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	@RequestMapping(value = "/showProfile.htm", method = RequestMethod.POST)
	public ModelAndView updateInfo(@RequestParam("fname") String fname, 
			@RequestParam("lname") String lname, 
			@RequestParam("email") String email,
			@RequestParam("password") String password){	
		User user = new User();
		Developer developer = new Developer();
		Tester tester = new Tester();
		
		user.setId(2);
		// get user object from session
		int userType = 0;
		
		if(userType == 0) {
			//developer.setFirstName(fname);
			//developer.setLastName(lname);
		} else {
			//tester.setFirstName(fname);
			//tester.setLastName(lname);
		}
		user.setUserName(email);
		user.setPassword(password);
		user.setDeveloper(developer);
		user.setTester(tester);
		user.setFirstName(fname);
		user.setLastName(lname);
		userFacade.updateUser(user);
		return new ModelAndView("hello");
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}
}
