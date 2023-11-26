package com.amh.zenevent.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amh.zenevent.entities.Historique;
import com.amh.zenevent.repository.HistoriqueRepository;

@Service
public class HistoriqueService {
	@Autowired
	HistoriqueRepository historiqueRepository;

	public Historique getEventByVisitor(UUID idVisitor) {
		return historiqueRepository.getEventByVisitor(idVisitor);
	}

	public List<Historique> listServiceByVisitor(UUID idVisitor) {
		return historiqueRepository.listServiceByVisitor(idVisitor);
	}

	public List<Historique> listVisitorByEntreprise(UUID idEntreprise) {
		return historiqueRepository.listVisitorByEntreprise(idEntreprise);
	}

	public long countServiceByEvent(UUID idEntreprise) {
		return historiqueRepository.countServiceByEvent(idEntreprise);
	}

}
