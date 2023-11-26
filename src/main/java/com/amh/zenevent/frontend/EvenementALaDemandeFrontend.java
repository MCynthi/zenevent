package com.amh.zenevent.frontend;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.EvenementALaDemande;
import com.amh.zenevent.service.EntrepriseService;
import com.amh.zenevent.service.EvenementALaDemandeService;

@Controller
public class EvenementALaDemandeFrontend {
	@Autowired
	EvenementALaDemandeService evenementALaDemandeService;
	@Autowired
	EntrepriseService entrepriseService;

	@RequestMapping("/event/demande")
	public String listEventAsk(Model model, Authentication authentication) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);
		List<EvenementALaDemande> listEventByEntreprise = evenementALaDemandeService
				.listEventByEntreprise(entreprise.getIdEntreprise());
		model.addAttribute("eventAsk", listEventByEntreprise);

		return "eventALaDemande/listEventAskClient.html";
	}

	@RequestMapping(value = "/event/demande/add")
	public String addEventAsk(Model model) {
		model.addAttribute("eventAsk", new EvenementALaDemande());
		return "eventALaDemande/askEvent.html";
	}

	@RequestMapping(value = "/event/demande/save", method = RequestMethod.POST)
	public String saveEventAsk(EvenementALaDemande eventAsk, Authentication authentication) {
		String nom = authentication.getName();
		Entreprise entreprise = entrepriseService.getEntrepriseByUsername(nom);
		eventAsk.setEntreprise(entreprise);
		evenementALaDemandeService.saveEventAsk(eventAsk);
		return "redirect:/event/demande";
	}

	@RequestMapping(value = "/event/demande/view/{id}")
	public ModelAndView viewEvent(@PathVariable(name = "id") UUID id) {		
		ModelAndView mav = new ModelAndView("eventALaDemande/viewEventAskClient");
		EvenementALaDemande eventAsk = evenementALaDemandeService.getEventAskById(id);
		mav.addObject("eventAsk", eventAsk);
		return mav;
	}

}
