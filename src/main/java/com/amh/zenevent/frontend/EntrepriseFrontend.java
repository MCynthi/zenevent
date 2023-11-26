package com.amh.zenevent.frontend;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.Event;
import com.amh.zenevent.entities.ServiceEntreprise;
import com.amh.zenevent.entities.User;
import com.amh.zenevent.entities.Visitor;
import com.amh.zenevent.repository.EntrepriseEventRepository;
import com.amh.zenevent.repository.EventRepository;
import com.amh.zenevent.service.EntrepriseEventService;
import com.amh.zenevent.service.EntrepriseService;
import com.amh.zenevent.service.EventService;
import com.amh.zenevent.service.ServiceEntrepriseService;
import com.amh.zenevent.service.UserService;
import com.amh.zenevent.service.VisitorService;

@Controller
@RequestMapping("/gestionnaire")
public class EntrepriseFrontend {
	@Autowired
	EventService eventService; 
	@Autowired
	EventRepository eventRepository;
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	VisitorService visitorService;
	@Autowired
	EntrepriseEventService entrepriseEventService;
	@Autowired
	UserService userService;
	@Autowired
	EntrepriseEventRepository entrepriseEventRepository;
	@Autowired
	ServiceEntrepriseService serviceEntrepriseService;
	
	
	
	@RequestMapping(value ="/entreprises")
	public String listEntreprise(Model model){		
		List<Entreprise> listEntreprises = entrepriseService.listEntreprise();	
		model.addAttribute("entreprise",listEntreprises);
		
		return "entreprise/listEntreprise.html";
	}
	
	
	@RequestMapping(value = "/entreprise/view/{id}")
	public ModelAndView viewEntreprise(@PathVariable(name = "id") UUID id) throws Exception {
		ModelAndView mav = new ModelAndView("entreprise/viewEntreprise");
		Entreprise entreprise = entrepriseService.getEntrepriseById(id);
		
		List<Event> listEvent = eventService.listDistinctEventByEntreprise(entreprise.getIdEntreprise());
		List<User> listUser = userService.listUserByEntreprise(entreprise.getIdEntreprise());
		List<Visitor> listVisitor = visitorService.listVisitorByEntreprise(entreprise.getIdEntreprise());
		List<ServiceEntreprise> listservice = serviceEntrepriseService.listServiceByEntreprise(entreprise.getIdEntreprise());
		
		long totalEvent = eventService.countEventByEntreprise(entreprise.getIdEntreprise());
		long totalVisiteur = visitorService.countVisitorByEntreprise(entreprise.getIdEntreprise());
		long totalAgent = userService.countUserByEntreprise(entreprise.getIdEntreprise());
		
		
		mav.addObject("entreprise", entreprise);
		mav.addObject("listEvent", listEvent);
		mav.addObject("user", listUser);
		mav.addObject("service", listservice);
		mav.addObject("visitor", listVisitor);
		mav.addObject("totalEvent", totalEvent);
		mav.addObject("totalVisiteur", totalVisiteur);
		mav.addObject("totalAgent", totalAgent);
		return mav;
	}
	
	
}
