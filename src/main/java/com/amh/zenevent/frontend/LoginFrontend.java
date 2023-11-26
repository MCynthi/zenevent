package com.amh.zenevent.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amh.zenevent.repository.EventRepository;
import com.amh.zenevent.service.EntrepriseService;

@Controller
public class LoginFrontend {
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	EventRepository eventRepository;	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	String login() {
		return "login";
	}
	
	
}
