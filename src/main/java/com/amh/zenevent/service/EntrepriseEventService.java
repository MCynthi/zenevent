package com.amh.zenevent.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.entities.EntrepriseEvent;
import com.amh.zenevent.entities.Event;
import com.amh.zenevent.repository.EntrepriseEventRepository;

@Service
public class EntrepriseEventService {
	@Autowired
	EntrepriseEventRepository entrepriseEventRepository;
	
	public void addAbonnement(Entreprise entreprise, Event event) {
		EntrepriseEvent entrepriseEvent = new EntrepriseEvent(entreprise, event);
		entrepriseEventRepository.save(entrepriseEvent);
	}

	public List<Event> listEventByEntreprise(UUID idEntreprise) {
		return entrepriseEventRepository.listEventByEntreprise(idEntreprise);
	}

	public EntrepriseEvent getEntrepriseEventByIds(UUID idEntreprise, UUID idEvent) {
		return entrepriseEventRepository.getEntrepriseEventByIds(idEntreprise,idEvent);
	}
}
