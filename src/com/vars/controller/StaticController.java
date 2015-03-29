package com.vars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StaticController {
	
	@RequestMapping(value="/hello.htm")
	public ModelAndView sayHello() {
		ModelAndView modelAndView = new ModelAndView("hello");
		
		modelAndView.addObject("hello", "Vaibhav");
		return modelAndView;
	}
	
	@RequestMapping(value="/about.htm")
	public ModelAndView aboutUs() {
		return new ModelAndView("about");
	}

}
