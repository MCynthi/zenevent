package com.amh.zenevent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vue")
public class HomeController {
	
	@RequestMapping(value = "/")
	public String viewHomePage() {
		return "index";		
	}
	
	@GetMapping(value = "/login")
	public String loginPage(Model model) {
		return "login";		
	}
	
	@GetMapping(value = "/header")
	public String header(Model model) {
		return "layouts/header.html";		
	}
	
	@GetMapping(value = "/event")
	public String addEvent(Model model) {
		return "event/listEvent.html";		
	}
	
	

}
