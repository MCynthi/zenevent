package com.amh.zenevent.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.Event;
import com.amh.zenevent.entities.ServiceEntreprise;
import com.amh.zenevent.entities.User;
import com.amh.zenevent.repository.ServiceEntrepriseRepository;
import com.amh.zenevent.service.EntrepriseService;
import com.amh.zenevent.service.EventService;
import com.amh.zenevent.service.HistoriqueService;
import com.amh.zenevent.service.ServiceEntrepriseService;
import com.amh.zenevent.service.UserService;
import com.amh.zenevent.service.VisitorService;

@Controller
public class DashboardEntreprise {
	@Autowired
	EventService eventService;
	@Autowired
	UserService userService;
	@Autowired
	VisitorService visitorService;
	@Autowired
	ServiceEntrepriseService serviceEntrepriseService;
	@Autowired
	EntrepriseService entrepriseService;
	@Autowired
	HistoriqueService historiqueService;
	@Autowired
	ServiceEntrepriseRepository serviceEntrepriseRepository;

	@RequestMapping("/index")
	public String index(Model model, Authentication authentication) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);

		long totalEvent = eventService.countEventByEntreprise(entreprise.getIdEntreprise());
		long totalVisitor = visitorService.countVisitorByEntreprise(entreprise.getIdEntreprise());
		long totalUser = userService.countUserByEntreprise(entreprise.getIdEntreprise());
		long totalService = serviceEntrepriseService.countServiceByEntreprise(entreprise.getIdEntreprise());

		model.addAttribute("nom", nom);
		model.addAttribute("totalEvent", totalEvent);
		model.addAttribute("entreprise", entreprise);
		model.addAttribute("totalVisitor", totalVisitor);
		model.addAttribute("totalUser", totalUser);
		model.addAttribute("totalService", totalService);

		return "index";
	}

	@RequestMapping("/")
	public String home(Model model, Authentication authentication) {		
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);

		Map<String, Long> visitorServiceCount = new HashMap<String, Long>();
		Map<String, Long> visitorEventCount = new HashMap<String, Long>();
		
		List<Object[]> countVisitorByService = serviceEntrepriseRepository
				.countVisiteurByEntrepriseGroupByService(entreprise.getIdEntreprise());		
		countVisitorByService.forEach(vals -> {
			visitorServiceCount.put(String.valueOf(vals[0]), Long.parseLong((vals[1]).toString()));
		});
		
		
		List<Object[]> countVisitorByEvent = eventService.countVisiteurByEntrepriseGroupByEvenement(entreprise.getIdEntreprise());
		countVisitorByEvent.forEach(vals ->{
			visitorEventCount.put(String.valueOf(vals[0]), Long.parseLong((vals[1]).toString()));
		});
		

		long totalEvent = eventService.countEventByEntreprise(entreprise.getIdEntreprise());
		long totalVisitor = visitorService.countVisitorByEntreprise(entreprise.getIdEntreprise());
		long totalUser = userService.countUserByEntreprise(entreprise.getIdEntreprise());
		long totalUserActif = userService.countUserActifByEntreprise(entreprise.getIdEntreprise());
		long totalUserInactif = userService.countUserInactifByEntreprise(entreprise.getIdEntreprise());

		long totalService = serviceEntrepriseService.countServiceByEntreprise(entreprise.getIdEntreprise());
		List<Event> eventDistinct = eventService.listDistinctEventByEntreprise(entreprise.getIdEntreprise());
		List<ServiceEntreprise> serviceDistinct = serviceEntrepriseService
				.listDistinctServiceById(entreprise.getIdEntreprise());
		List<User> agentActif = userService.listUserActifByEntreprise(entreprise.getIdEntreprise());

		model.addAttribute("nom", nom);
		model.addAttribute("totalEvent", totalEvent);
		model.addAttribute("entreprise", entreprise);
		model.addAttribute("totalVisitor", totalVisitor);
		model.addAttribute("totalUser", totalUser);
		model.addAttribute("userActif", totalUserActif);
		model.addAttribute("userInactif", totalUserInactif);
		model.addAttribute("totalService", totalService);
		model.addAttribute("distinctEvent", eventDistinct);
		model.addAttribute("serviceDistinct", serviceDistinct);
		model.addAttribute("agentActif", agentActif);
		model.addAttribute("countVisitorService", visitorServiceCount);
		model.addAttribute("countVisitorEvent", visitorEventCount);

		return "index.html";
	}

}
