package com.amh.zenevent.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amh.zenevent.entities.Support;
import com.amh.zenevent.repository.SupportRepository;

@Service
public class SupportService {
	@Autowired 
	SupportRepository supportRepository;

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	
	public List<Support> listAllSupport() {
		return supportRepository.listAllSupport();
	}

	public List<Support> listSupportFerme() {
		return supportRepository.listSupportFerme();
	}

	public List<Support> listSupportOuvert() {
		return supportRepository.listSupportOuvert();
	}

	public void saveSupport(Support support) {
		support.setDateEnregistrement(formatter.format(new Date()));
		supportRepository.save(support);
		
	}

	public Support getSupportById(UUID id) {
		return supportRepository.getSupportById(id);
	}

	public void updateSupport(UUID id, Support support) {
		Support checkSupport = supportRepository.getSupportById(id);
		if (checkSupport != null) {
			checkSupport.setSujet(support.getSujet());
			checkSupport.setPreoccupation(support.getPreoccupation());
		}
		supportRepository.save(checkSupport);
		
	}

	public long totalSupportOuvertByEntreprise(UUID idEntreprise) {
		return supportRepository.totalSupportOuvertByEntreprise(idEntreprise);
	}

	public long totalSupportFermeByEntreprise(UUID idEntreprise) {
		return supportRepository.totalSupportFermeByEntreprise(idEntreprise);
	}

	public List<Support> listSupportFermeByEntreprise(UUID idEntreprise) {
		return supportRepository.listSupportFermeByEntreprise(idEntreprise);
	}

	public List<Support> listSupportOuvertByEntreprise(UUID idEntreprise) {
		return supportRepository.listSupportOuvertByEntreprise(idEntreprise);
	}

	public void sendAnswerSupport(UUID id, Support support) {
		Support checkSupport = supportRepository.getSupportById(id);
		if (checkSupport != null) {
			checkSupport.setCommentaire(support.getCommentaire());;
			checkSupport.setStatut(true);
		}
		supportRepository.save(checkSupport);
	}


}
