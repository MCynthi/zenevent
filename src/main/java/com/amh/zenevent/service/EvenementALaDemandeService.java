package com.amh.zenevent.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amh.zenevent.entities.EvenementALaDemande;
import com.amh.zenevent.repository.EvenementALaDemandeRepository;

@Service
public class EvenementALaDemandeService {
	@Autowired
	EvenementALaDemandeRepository evenementALaDemandeRepository;

	public List<EvenementALaDemande> listEventALaDemande() {
		return evenementALaDemandeRepository.listEventALaDemande();
	}

	public void saveEventAsk(EvenementALaDemande eventAsk) {
		evenementALaDemandeRepository.save(eventAsk);
	}

	public List<EvenementALaDemande> listEventByEntreprise(UUID idEntreprise) {
		return evenementALaDemandeRepository.listEventByEntreprise(idEntreprise);
	}

	public EvenementALaDemande getEventAskById(UUID id) {
		return evenementALaDemandeRepository.getEventAskById(id);
	}

}
