package com.amh.zenevent.frontend;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.EntrepriseEvent;
import com.amh.zenevent.entities.Event;
import com.amh.zenevent.entities.Visitor;
import com.amh.zenevent.repository.EntrepriseEventRepository;
import com.amh.zenevent.repository.EventRepository;
import com.amh.zenevent.service.EntrepriseEventService;
import com.amh.zenevent.service.EntrepriseService;
import com.amh.zenevent.service.EventService;
import com.amh.zenevent.service.VisitorService;

@Controller
public class EventFrontend {
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
	EntrepriseEventRepository entrepriseEventRepository;

	@RequestMapping("/loginId")
	public Boolean successLogin(Authentication authentication) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);
		if (entreprise != null) {
			return true;
		}
		return false;
	}

	@RequestMapping("/event")
	public String listEvent(Model model, Authentication authentication) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);

		List<Event> listEvent = eventService.listEvent();
		List<Event> listEventByEntreprise = entrepriseEventService.listEventByEntreprise(entreprise.getIdEntreprise());

		model.addAttribute("event", listEvent);
		model.addAttribute("eventEntreprise", listEventByEntreprise);

		return "event/listEvent.html";
	}

	@RequestMapping(value = "/event/view/{id}")
	public ModelAndView viewEvent(@PathVariable(name = "id") UUID id) throws Exception {
		ModelAndView mav = new ModelAndView("event/viewEvent");
		Event event = eventService.getEventById(id);
		List<Visitor> listVisitorsByEvent = visitorService.listVisitorsByEvent(id);
		List<Event> otherEvent = eventService.listEventByDateDebut(event.getDateDebutEvent());

		mav.addObject("visitors", listVisitorsByEvent);
		mav.addObject("event", event);
		mav.addObject("otherEvent", otherEvent);
		return mav;
	}

	@RequestMapping(value = "/event/abonne/{id}")
	public String abonnementEvent(Model model, @PathVariable("id") UUID id, Authentication authentication) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);
		Event event = eventRepository.getEventById(id);
		if (event != null) {
			entrepriseEventService.addAbonnement(entreprise, event);		}
		return "redirect:/event";
	}

	@RequestMapping(value = "/event/desabonnement/{id}")
	public String desabonnemtEvent(@PathVariable("id") UUID id, Authentication authentication) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);
		Event event = eventRepository.getEventById(id);
		EntrepriseEvent entrepriseEvent = entrepriseEventService.getEntrepriseEventByIds(entreprise.getIdEntreprise(),
				event.getIdEvent());
		entrepriseEvent.setAbonnement(false);
		eventRepository.save(event);
		entrepriseEventRepository.save(entrepriseEvent);
		return "redirect:/event";
	}

}
