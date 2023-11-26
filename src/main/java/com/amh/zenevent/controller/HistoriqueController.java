package com.amh.zenevent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amh.zenevent.entities.Historique;
import com.amh.zenevent.repository.HistoriqueRepository;

@RestController
@RequestMapping("/api")
public class HistoriqueController {
	@Autowired
	HistoriqueRepository historiqueRepository;
	
	@GetMapping(value ="/historiques")
	List<Historique> listHitorique(){
		return historiqueRepository.listHitorique();		
	}
	
	@PostMapping(value ="/historiques")
	public Historique addHitorique(@RequestBody Historique historique){
		return historiqueRepository.save(historique);		
	}

}
