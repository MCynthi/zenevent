package com.amh.zenevent.frontend;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.EvenementALaDemande;
import com.amh.zenevent.entities.Event;
import com.amh.zenevent.repository.EvenementALaDemandeRepository;
import com.amh.zenevent.repository.EventRepository;
import com.amh.zenevent.repository.ServiceEntrepriseRepository;
import com.amh.zenevent.service.CategoryService;
import com.amh.zenevent.service.EntrepriseService;
import com.amh.zenevent.service.EvenementALaDemandeService;
import com.amh.zenevent.service.EventService;
import com.amh.zenevent.service.HistoriqueService;
import com.amh.zenevent.service.ServiceEntrepriseService;
import com.amh.zenevent.service.UserService;
import com.amh.zenevent.service.VisitorService;

@Controller
@RequestMapping("/gestionnaire")
public class GestionnaireFrontend {
	@Autowired
	EventService eventService;
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
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
	@Autowired
	EventRepository eventRepository;
	@Autowired
	EvenementALaDemandeService evenementALaDemandeService;
	@Autowired
	EvenementALaDemandeRepository evenementALaDemandeRepository;

	@RequestMapping("/")
	public String home(Model model, @RequestParam(name = "motCle", defaultValue = "") UUID mc,
			@RequestParam(name = "keyEntreprise", defaultValue = "") UUID idEntreprise) {

		Map<String, Long> visitorServiceCount = new HashMap<String, Long>();
		Map<String, Long> visitorEventCount = new HashMap<String, Long>();
		List<Event> listEvent = eventService.listEvent();
		List<Entreprise> listEntreprise = new ArrayList<Entreprise>();
		long totalService = 0;
		long totalEvenement = 0;
		long totalVisiteur = 0;
		long totalAgent = 0;

		if (mc != null) {
			listEntreprise = entrepriseService.listEntrepriseByEvent(mc);
		} else {
			listEntreprise = entrepriseService.listEntreprise();
		}

		if (idEntreprise != null) {
			totalService = serviceEntrepriseRepository.countServiceByEntreprise(idEntreprise);
			totalEvenement = eventService.countEventByEntreprise(idEntreprise);
			totalVisiteur = visitorService.countVisitorByEntreprise(idEntreprise);
			totalAgent = userService.countUserActifByEntreprise(idEntreprise);
		}

		model.addAttribute("event", listEvent);
		model.addAttribute("pMotCle", mc);
		model.addAttribute("entreprise", listEntreprise);

		long countEntreprise = entrepriseService.countEntreprise();
		long countEvent = eventService.countEvent();
		long countCategory = categoryService.countCategory();

		model.addAttribute("countVisitorService", visitorServiceCount);
		model.addAttribute("countVisitorEvent", visitorEventCount);

		model.addAttribute("totalEntreprise", countEntreprise);
		model.addAttribute("totalEvent", countEvent);
		model.addAttribute("totalCategory", countCategory);

		model.addAttribute("totalService", totalService);
		model.addAttribute("totalEvenement", totalEvenement);
		model.addAttribute("totalVisiteur", totalVisiteur);
		model.addAttribute("totalAgent", totalAgent);
		model.addAttribute("pKeyEntreprise", idEntreprise);

		return "gestionnaire/index.html";
	}

	@RequestMapping("/event")
	public String listEventGestionnaire(Model model) {
		List<Event> listEvent = eventService.listEvent();
		model.addAttribute("event", listEvent);

		return "eventGestionnaire/listEvent.html";
	}

	@RequestMapping(value = "/event/view/{id}")
	public ModelAndView viewEventGestionnaire(@PathVariable(name = "id") UUID id) throws Exception {
		ModelAndView mav = new ModelAndView("eventGestionnaire/viewEvent");
		Event event = eventService.getEventById(id);
		List<Entreprise> listEntreprise = entrepriseService.listEntrepriseByEvent(event.getIdEvent());

		mav.addObject("entreprise", listEntreprise);
		mav.addObject("event", event);
		return mav;
	}

	@RequestMapping(value = "/event/add", method = RequestMethod.GET)
	public String addEvent(Model model) {
		model.addAttribute("event", new Event());
		return "eventGestionnaire/addEvent.html";
	}

	@RequestMapping(value = "/event/saveEvent", method = RequestMethod.POST)
	public String saveEvent(Event event) throws ParseException {
		eventService.addEvent(event);
		return "/gestionnaire/";
	}

	@RequestMapping(value = "/event/edit/{id}")
	public ModelAndView editEvent(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("event/updateEvent");
		Event event = eventService.getEventById(id);
		mav.addObject("event", event);
		return mav;
	}

	@RequestMapping(value = "/event/updateEvent", method = RequestMethod.POST)
	public String updateEvent(Event event) {
		UUID id = event.getIdEvent();
		eventService.updateEvent(id, event);
		return "redirect:/event";
	}

	@RequestMapping(value = "/event/delete/{id}")
	public String deleteEvent(@PathVariable("id") UUID id) {
		Event event = eventService.getEventById(id);
		event.setIsDeleted(true);
		eventRepository.save(event);
		return "redirect:/gestionnaire/event";
	}

	@RequestMapping("/event/demande")
	public String listEventAsk(Model model) {
		List<EvenementALaDemande> listEventAsk = evenementALaDemandeService.listEventALaDemande();
		model.addAttribute("eventAsk", listEventAsk);
		return "eventALaDemande/listEventAskGestionnaire.html";
	}

	@RequestMapping(value = "/event/demande/view/{id}")
	public ModelAndView viewEvent(@PathVariable(name = "id") UUID id) {
		ModelAndView mav = new ModelAndView("eventALaDemande/viewEventAskGestionnaire");
		EvenementALaDemande eventAsk = evenementALaDemandeService.getEventAskById(id);
		mav.addObject("eventAsk", eventAsk);
		return mav;
	}

	@RequestMapping(value = "/event/demande/accept/{id}")
	public String acceptEvent(@PathVariable(name = "id") UUID id) {
		EvenementALaDemande eventAsk = evenementALaDemandeService.getEventAskById(id);
		eventAsk.setApproved(true);

		Event newEvent = new Event();
		newEvent.setNomEvent(eventAsk.getNomEvent());
		newEvent.setPaysEvent(eventAsk.getPays());
		newEvent.setVille(eventAsk.getVille());
		newEvent.setQuartier(eventAsk.getQuartier());
		newEvent.setLieuDit(eventAsk.getLieuDit());
		newEvent.setDescription(eventAsk.getDescription());
		newEvent.setDateDebutEvent(eventAsk.getDateDebutEvent());
		newEvent.setDateFinEvent(eventAsk.getDateDebutEvent());
		newEvent.setDeleted(false);
		eventRepository.save(newEvent);
		return "redirect:/gestionnaire/event/demande";
	}

	@RequestMapping(value = "/event/demande/refuse/{id}")
	public String refuseEvent(@PathVariable(name = "id") UUID id) {
		EvenementALaDemande eventAsk = evenementALaDemandeService.getEventAskById(id);
		eventAsk.setDeleted(true);
		evenementALaDemandeRepository.save(eventAsk);
		return "redirect:/gestionnaire/event/demande";
	}

}
