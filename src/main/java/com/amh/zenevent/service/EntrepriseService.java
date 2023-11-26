package com.amh.zenevent.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amh.zenevent.entities.Entreprise;
import com.amh.zenevent.repository.EntrepriseRepository;

@Service
public class EntrepriseService {
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Entreprise> listEntreprise() {
		return entrepriseRepository.listEntreprise();
	}

	public Entreprise getEntrepriseById(UUID id) {
		return entrepriseRepository.getEntrepriseById(id);
	}

	public void addEntreprise(Entreprise entreprise) {
		entreprise.setPassword(passwordEncoder.encode(entreprise.getPassword()));
		entrepriseRepository.save(entreprise);
	}

	public boolean isEntrepriseExist(long id) {
		return entrepriseRepository.existsById(id);
	}

	public void updateEntreprise(long id, Entreprise entreprise) {
		Entreprise checkEntreprise = entrepriseRepository.getById(id);
		if (checkEntreprise != null) {
			checkEntreprise.setNomEntreprise(entreprise.getNomEntreprise());
			checkEntreprise.setEmail(entreprise.getEmail());
			checkEntreprise.setEntrepriseEvents(entreprise.getEntrepriseEvents());
			checkEntreprise.setPassword(passwordEncoder.encode(entreprise.getPassword()));
			checkEntreprise.setTelephone1(entreprise.getTelephone1());
			checkEntreprise.setTelephone2(entreprise.getTelephone2());
			checkEntreprise.setUsername(entreprise.getUsername());
			checkEntreprise.setUsers(entreprise.getUsers());			
		}
		entrepriseRepository.save(checkEntreprise);		
	}

	public long countEntreprise() {
		return entrepriseRepository.countEntreprise();
	}

	public Entreprise loginEntreprise(String username, String password) {
		return entrepriseRepository.loginEntreprise(username,password);
	}

	public Entreprise getEntrepriseByUsername(String username) {
		return entrepriseRepository.getEntrepriseByUsername(username);
	}

	public List<Entreprise> listEntrepriseByEvent(UUID mc) {
		return entrepriseRepository.listEntrepriseByEvent(mc);
	}

}
