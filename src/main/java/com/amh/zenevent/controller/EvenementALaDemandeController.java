package com.amh.zenevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amh.zenevent.entities.EvenementALaDemande;
import com.amh.zenevent.service.EvenementALaDemandeService;

public class EvenementALaDemandeController {
	@Autowired
	EvenementALaDemandeService evenementALaDemandeService;
	
	@RequestMapping(value = "/event/demande")
	public String askEvent(Model model) {
		model.addAttribute("event",new EvenementALaDemande());
		return "event/askEvent.html";
	}
	
	@RequestMapping(value="/event/demande/save", method = RequestMethod.POST)
	public String saveEventAsk(EvenementALaDemande eventAsk){
		evenementALaDemandeService.saveEventAsk(eventAsk);
		return "redirect:/event";
	}

}
