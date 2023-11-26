package com.amh.zenevent.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amh.zenevent.entities.Category;
import com.amh.zenevent.entities.ServiceEntreprise;
import com.amh.zenevent.entities.Visitor;
import com.amh.zenevent.repository.EventRepository;
import com.amh.zenevent.repository.HistoriqueRepository;
import com.amh.zenevent.repository.ServiceEntrepriseRepository;
import com.amh.zenevent.service.CategoryService;
import com.amh.zenevent.service.HistoriqueService;
import com.amh.zenevent.service.ServiceEntrepriseService;
import com.amh.zenevent.service.VisitorService;

@RestController
@RequestMapping("/api")
public class ServiceEntrepriseController {
	@Autowired
	ServiceEntrepriseRepository serviceRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	ServiceEntrepriseService serviceEntrepriseService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	VisitorService visitorService;
	@Autowired
	HistoriqueService historiqueService;
	@Autowired
	HistoriqueRepository historiqueRepository;

	@GetMapping(value = "/services")
	public List<ServiceEntreprise> listService() {
		return serviceEntrepriseService.listService();
	}
	
	@GetMapping(value = "/services/entreprise/{idEntreprise}")
	public List<ServiceEntreprise> listServiceOfEntreprise(@PathVariable UUID idEntreprise) {
		return serviceEntrepriseService.listServiceByEntreprise(idEntreprise);
	}


	@GetMapping(value = "/services/{id}")
	public ResponseEntity<ServiceEntreprise> getServiceById(@PathVariable UUID id) {
		ServiceEntreprise service = serviceEntrepriseService.getServiceById(id);
		if (service != null) {
			return new ResponseEntity<ServiceEntreprise>(service, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/services")
	public ResponseEntity<ServiceEntreprise> addservice(@RequestBody ServiceEntreprise service) {
		try {
			service.setDeleted(false);
			serviceEntrepriseService.addservice(service);
			return new ResponseEntity<ServiceEntreprise>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ServiceEntreprise>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/services/{id}")
	public ResponseEntity<?> updateservice(@PathVariable UUID id, @RequestBody ServiceEntreprise service) {

		if (serviceEntrepriseService.isServiceExist(id)) {
			serviceEntrepriseService.updateservice(id, service);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/services/{id}")
	public void deleteservice(@PathVariable UUID id) {
		ServiceEntreprise service = serviceRepository.getServiceById(id);
		service.setDeleted(true);
		serviceRepository.save(service);
	}

	@GetMapping("/services/count")
	public long countservice() {
		return serviceEntrepriseService.countService();
	}

	@GetMapping(value = "/services/category/{idCategory}")
	public List<ServiceEntreprise> listServiceByCategory(@PathVariable long idCategory) {
		Category category = categoryService.getCategoryById(idCategory);
		if (category != null) {
			return serviceEntrepriseService.listServiceByCategory(idCategory);
		}
		return null;
	}

	@GetMapping(value = "/services/event/{idEvent}/entreprise/{idEntreprise}")
	public List<ServiceEntreprise> listServiceOfEntrepriseByEvent(@PathVariable long idEvent,
			@PathVariable long idEntreprise) {
		return serviceEntrepriseService.listServiceOfEntrepriseByEvent(idEvent, idEntreprise);
	}

	@GetMapping(value = "/services/categorie/{idCategory}/event/{idEvent}")
	public List<ServiceEntreprise> listServiceByCategoryAndEvent(@PathVariable long idCategory,
			@PathVariable long idEvent) {
		return serviceEntrepriseService.listServiceByCategoryAndEvent(idCategory, idEvent);
	}

	@GetMapping(value = "/services/visitor/{idVisitor}")
	public List<ServiceEntreprise> listServiceByVisitor(@PathVariable UUID idVisitor) {
		Visitor visitor = visitorService.getVisitorById(idVisitor);

		if (visitor != null) {
			return serviceEntrepriseService.listServiceByVisitor(idVisitor);
		}
		return null;
	}

}
