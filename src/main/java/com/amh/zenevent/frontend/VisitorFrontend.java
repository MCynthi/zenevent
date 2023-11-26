package com.amh.zenevent.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.Event;
import com.amh.zenevent.entities.ServiceEntreprise;
import com.amh.zenevent.entities.Visitor;
import com.amh.zenevent.pojo.HistoriquePojo;
import com.amh.zenevent.repository.VisitorRepository;
import com.amh.zenevent.service.EntrepriseService;
import com.amh.zenevent.service.EventService;
import com.amh.zenevent.service.HistoriqueService;
import com.amh.zenevent.service.ServiceEntrepriseService;
import com.amh.zenevent.service.VisitorService;

@Controller
public class VisitorFrontend {
	@Autowired
	VisitorService visitorService;
	@Autowired
	VisitorRepository visitorRepository;
	@Autowired
	HistoriqueService historiqueService;
	@Autowired
	EventService eventService;
	@Autowired
	ServiceEntrepriseService serviceEntrepriseService;
	@Autowired
	ServiceEntrepriseService serviceEntreprise;
	@Autowired
	EntrepriseService entrepriseService;

	@RequestMapping("/visitor")
	public String listVisitor(Authentication authentication, Model model,
			@RequestParam(name = "motCle", defaultValue = "") UUID mc) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);
		List<Event> listEvent = eventService.listEvent();
		List<Visitor> listVisitor = new ArrayList<Visitor>();

		if (mc != null) {
			listVisitor = visitorService.listVisitorByEntrepriseAndEvent(entreprise.getIdEntreprise(), mc);
		} else {
			listVisitor = visitorService.listVisitorByEntreprise(entreprise.getIdEntreprise());
		}
		
		model.addAttribute("pMotCle", mc);

		model.addAttribute("visitor", listVisitor);
		model.addAttribute("event", listEvent);

		return "visitor/listVisitor.html";
	}

	@RequestMapping(value = "/visitor/edit/{id}")
	public ModelAndView editVisitor(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("visitor/updateVisitor");
		Visitor visitor = visitorService.getVisitorById(id);
		mav.addObject("visitor", visitor);
		return mav;
	}

	@RequestMapping(value = "/visitor/updateVisitor", method = RequestMethod.POST)
	public String updateVisitor(Visitor visitor) {
		UUID id = visitor.getIdVisitor();
		visitorService.updateVisitor(id, visitor);
		return "redirect:/visitor";
	}

	@RequestMapping(value = "/visitor/view/{id}")
	public ModelAndView viewVisitor(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("visitor/viewVisitor");
		Visitor visitor = visitorService.getVisitorById(id);
		Event event = eventService.getEventByVisitor(visitor.getIdVisitor());

		List<ServiceEntreprise> listService = serviceEntrepriseService.listServiceOfVisitorByEvent(event.getIdEvent(),
				visitor.getIdVisitor());

		mav.addObject("event", event);
		mav.addObject("service", listService);
		mav.addObject("visitor", visitor);
		return mav;
	}

	@RequestMapping("/visitor/delete/{id}")
	public String deleteVisitor(@PathVariable("id") UUID id) {
		Visitor visitor = visitorRepository.getVisitorById(id);
		visitor.setDeleted(true);
		visitorRepository.save(visitor);
		return "redirect:/visitor";
	}


	@GetMapping(value = "/visitor/event/{id}")
	public List<Visitor> listVisitorByEvent(@PathVariable UUID id) {
		return visitorService.listVisitorByEvent(id);
	}

	@GetMapping(value = "/visitor/event/{idEvent}/service/{idService}")
	public List<Visitor> listVisitorByEventAndService(@PathVariable UUID idEvent, @PathVariable UUID idService) {
		return visitorService.listVisitorByEventAndService(idEvent, idService);
	}

	@GetMapping(value = "/visitor/user/{id}")
	public List<Visitor> listVisitorByUser(@PathVariable UUID id) {
		return visitorService.listVisitorByUser(id);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/visitor/{idVisitor}/event/{idEvent}")
	public ResponseEntity<?> getVisitorEventInformations(@PathVariable UUID idEvent, @PathVariable UUID idVisitor) {
		Event event = eventService.getEventById(idEvent);
		Visitor visitor = visitorService.getVisitorById(idVisitor);
		List<ServiceEntreprise> service = serviceEntreprise.listServiceOfVisitorByEvent(idEvent, idVisitor);

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("event", event);
		values.put("visitor", visitor);
		values.put("services", service);

		return new ResponseEntity<Map>(values, HttpStatus.OK);
	}

	@PostMapping("/visitor/{idVisitor}")
	public ResponseEntity<Visitor> addNewServiceToVisitor(@PathVariable UUID idVisitor,
			@RequestBody HistoriquePojo historique) {
		Visitor visitor = visitorService.addNewServiceToVisitor(idVisitor, historique);
		return new ResponseEntity<Visitor>(visitor, HttpStatus.OK);
	}

}
