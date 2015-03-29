package com.vars.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vars.domain.Bid;
import com.vars.domain.Developer;
import com.vars.domain.Project;
import com.vars.domain.Tester;
import com.vars.domain.User;
import com.vars.facade.BidFacade;
import com.vars.facade.ProjectFacade;
import com.vars.facade.UserFacade;
import com.vars.facade.UserFacadeImpl;

@Controller
public class ProjectController {

	private ProjectFacade projectFacade;
	private BidFacade bidFacade;
	private UserFacade userFacade;
	
	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	public void setBidFacade(BidFacade bidFacade) {
		this.bidFacade = bidFacade;
	}

	public void setProjectFacade(ProjectFacade projectFacade) {
		this.projectFacade = projectFacade;
	}

	@RequestMapping(value = "/project.htm", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("projectTitle") String projectTitle,
			@RequestParam("domain") String domain,
			@RequestParam("description") String description,
			@RequestParam("projectURL") String projectURL,
			@RequestParam("minBudget") int minBudget,
			@RequestParam("maxBudget") int maxBudget,
			@RequestParam("projectSkills") String projectSkills,
			@RequestParam("projectUsers") String projectUsers,
						HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		Project project = new Project();
		project.setTitle(projectTitle);
		project.setDescription(description);
		project.setDeveloper_id(user.getDeveloper().getId());
		project.setDomain(domain);
		project.setProject_url(projectURL);
		project.setMin_budget(minBudget);
		project.setMax_budget(maxBudget);
		project.setProject_skills(projectSkills);
		project.setProject_users(projectUsers);
		
		projectFacade.createProject(project);
		return new ModelAndView("owner_home");
	}
	
	@RequestMapping(value = "/viewProjects.htm", method = RequestMethod.GET)
	public ModelAndView showProjectsForDev(HttpSession session) {
		//getProjectDev needs developer Id to fetch 
		System.out.println("at Controller for view projects");
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) session.getAttribute("user"); 
		if(user != null) {
			if(user.getIsTester()) {
				ArrayList<Project> newProjects = projectFacade.getNewProjects();
				
				System.out.println("New Projects count"+ newProjects.size());
				System.out.println("getting my projects for tester" +user.getTester().getId());
				ArrayList<Project> myProjects = bidFacade.getProjectsForTester(user.getTester().getId());
				System.out.println("My Projects count"+ myProjects.size());
				
				modelAndView.setViewName("tester_home");
				
				if(newProjects.size() <= 0) {
					modelAndView.addObject("newProjects", null);
				} else {
					System.out.println("In new Projects!!");
					for (Project project : newProjects) {
						Developer developer =  userFacade.getDeveloper(project.getDeveloper_id());
						User userd = userFacade.getUserForId((developer.getUserId()));
						project.setDeveloperName(userd.getFirstName());
					}
												
					modelAndView.addObject("newProjects", newProjects);
					System.out.println("out of new Projects!!");
				}
				
				if(myProjects.size() <= 0)
				{
					modelAndView.addObject("myProjects", null);
				}
				else
				{
					System.out.println("In my Projects!!");
					for (Project project : myProjects) {
						System.out.println("project " + project.getTitle());
						System.out.println("project " + project.getDeveloper_id());
						Developer developer =  userFacade.getDeveloper(project.getDeveloper_id());
						User userd = userFacade.getUserForId((developer.getUserId()));
						project.setDeveloperName(userd.getFirstName());
					}
					modelAndView.addObject("myProjects", myProjects);
					System.out.println("out of my Projects!!");
				}
				
			} 
			else 
			{
				modelAndView.setViewName("owner_home");
				List<Project> projects = projectFacade.getProjectDev(user.getDeveloper().getId());
				if(projects.size() <= 0)
					modelAndView.addObject("projects", null);
				else
					modelAndView.addObject("projects", projects);
				
				List<Project> myCompletedProjects =
						projectFacade.getCompletedProjects(user.getDeveloper().getId());
				if(myCompletedProjects.size() <= 0)
					modelAndView.addObject("myCompletedProjects", null);
				else
					modelAndView.addObject("myCompletedProjects", myCompletedProjects);
			}
		} else {
			modelAndView = new ModelAndView("auth/user_login");
		}
		System.out.println("returning from view projects!");
		return modelAndView;
	}
	

	@RequestMapping(value = "/viewTesterProjects.htm", method = RequestMethod.GET)
	public ModelAndView getProjectTest() {
		//getProjectDev needs tester Id to fetch
		int id = 1;
		List<Project> projects = projectFacade.getProjectTest(id);;
		return new ModelAndView("tester_home", "projects", projects);
	}
	
	@RequestMapping(value = "/project/tester_project/{id}.htm", method = RequestMethod.GET)
	public ModelAndView showProject(@PathVariable("id") Integer id) {
		System.out.println("at tester_project start" + id);
		Project project = null;
		if(id != null)
		project = projectFacade.getProject(id);
		project.setProject_id(id);
		Developer developer =  userFacade.getDeveloper(project.getDeveloper_id());
		User userd = userFacade.getUserForId((developer.getUserId()));
		project.setDeveloperName(userd.getFirstName());
		System.out.println("at tester_project end " + id);
		return new ModelAndView("tester_project", "project", project);
	}
	
	@RequestMapping(value = "/project/devp_project/{id}.htm", method = RequestMethod.GET)
	public ModelAndView showProjectToDevp(@PathVariable("id") Integer id) {
		Project project = projectFacade.getProject(id);
		project.setProject_id(id);
		Developer developer =  userFacade.getDeveloper(project.getDeveloper_id());
		User userd = userFacade.getUserForId((developer.getUserId()));
		project.setDeveloperName(userd.getFirstName());
		if (project.getTester_id() >0 )
		{
		Tester tester = userFacade.getTester(project.getTester_id());
		User user = userFacade.getUserForId((tester.getUserId()));
		project.setTesterName(user.getFirstName());
		}
		return new ModelAndView("devp_project", "project", project);
	}
	
	@RequestMapping(value = "/tester_project.htm", method = RequestMethod.POST)
	public ModelAndView giveTesterResults(@RequestParam("results") String results,
			@RequestParam("projectTitle") String projectTitle) {
		System.out.println("insert tester suggestions for projectTitle "+ projectTitle);
		projectFacade.giveTesterResults(results, projectTitle);
		
		return new ModelAndView("tester_home");
	}
	
	@RequestMapping(value = "selectRating/{inputRating}.htm", method = RequestMethod.POST)
	public ModelAndView giveRating(@RequestParam("inputRating") Integer inputRating,
			@RequestParam("developerID") Integer developerID
			, @RequestParam("testerID") Integer testerID) throws ParseException {
		System.out.println("Rating is "+inputRating +"developerId"+ developerID);
		// Get Developer ID, tester ID and Rating s from page
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = dateFormat.format(currentDate.getTime()); 
		System.out.println("date is "+ dateNow);
				
		projectFacade.setTesterRating(developerID ,testerID, inputRating, dateNow );
		return new ModelAndView("owner_home");
	}
}
