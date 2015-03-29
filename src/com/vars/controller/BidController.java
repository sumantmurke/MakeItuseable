package com.vars.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vars.domain.Bid;
import com.vars.domain.Project;
import com.vars.domain.Tester;
import com.vars.domain.User;
import com.vars.facade.BidFacade;
import com.vars.facade.ProjectFacade;
import com.vars.facade.UserFacade;

@Controller
public class BidController {

	private BidFacade bidFacade;
	private ProjectFacade projectFacade;
	private UserFacade userFacade;

	public void setProjectFacade(ProjectFacade projectFacade) {
		this.projectFacade = projectFacade;
	}

	public void setBidFacade(BidFacade bidFacade) {
		this.bidFacade = bidFacade;
	}
	

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}
	
	@RequestMapping(value = "tester_proposal.htm", method = RequestMethod.POST)
	public ModelAndView tester_proposal(@RequestParam("projectId") Integer projectId, 
			@RequestParam("proposal") Float proposal, 
			@RequestParam("proposalDescription") String proposalDescription,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("Proposal amount: "+proposal);
		System.out.println("Proposal Desc: "+proposalDescription);
		Bid bid = new Bid();
		User user = (User) session.getAttribute("user");

		Tester tester = user.getTester();
		bid.setTester(tester.getId());
		bid.setAmount(proposal);
		bid.setDescription(proposalDescription);
		bid.setProjectId(projectId);
		bidFacade.createBid(bid);
		//add code for calling userFacade.updateBid and do whole wiring for bid db
		
		ArrayList<Project> newProjects = projectFacade.getNewProjects();
		ArrayList<Project> myProjects = bidFacade.getProjectsForTester(user.getTester().getId());
		modelAndView.setViewName("tester_home");
		
		if (newProjects.size() <= 0) {
			modelAndView.addObject("newProjects", null);
		} else {
			
			for (Project project : newProjects) {
				if(bidFacade.checkIfBidMade(project.getProject_id(), tester.getId()))
				{
				project.setTester_id(user.getTester().getId());
				}
			}
			modelAndView.addObject("newProjects", newProjects);
		}

		if (myProjects.size() <= 0) {
			modelAndView.addObject("myProjects", null);
		} else {
			modelAndView.addObject("myProjects", myProjects);
		}
		
		return modelAndView;
	}
	
	//@RequestMapping(value = "/project/bids/{id}.htm", method = RequestMethod.GET)
	//public ModelAndView passProject(@PathVariable("id") String id) {
		//Project project = projectFacade.getProject(Integer.parseInt(id));
		//return new ModelAndView("tester_proposal", "project", project);
	//}
	
	@RequestMapping(value = "/project/bids/{id}.htm", method = RequestMethod.GET)
	public ModelAndView getTesterProjects(@PathVariable("id") Integer id) {
		Project project = projectFacade.getProject(id);
		return new ModelAndView("tester_proposal", "project", project);
	}
	
	@RequestMapping(value = "/bids/{projectId}.htm", method = RequestMethod.GET)
	public ModelAndView showBids(@PathVariable("projectId") Integer id) {
		
		ArrayList<Bid> bids = bidFacade.getBidsForProject(id);
		if(bids.size() <= 0)
			return new ModelAndView("project_bids", "bids", null);
		else
			for (Bid bid : bids) {
				Project project = projectFacade.getProject(bid.getProjectId());
				bid.setProjectName(project.getTitle());
				
				Tester tester = userFacade.getTester(bid.getTester());
				User user = userFacade.getUserForId((tester.getUserId()));
				bid.setTesterName(user.getFirstName());
			}
			return new ModelAndView("project_bids", "bids", bids);
	}
	
	@RequestMapping(value = "/bids/selectBid/{bidId}.htm", method = RequestMethod.POST)
	public ModelAndView selectBid(@RequestParam("bidId") Integer bidId) {
		System.out.println("Inside selecting a bid - controller: "+bidId);
		Bid bid = bidFacade.getBid(bidId);
		bidFacade.setBidForProject(bid);
		return new ModelAndView("project_bids");
	}
}
