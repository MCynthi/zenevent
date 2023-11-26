package com.amh.zenevent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.amh.zenevent.entities.Event;
import com.amh.zenevent.entities.ServiceEntreprise;
import com.amh.zenevent.entities.Visitor;
import com.amh.zenevent.pojo.HistoriquePojo;
import com.amh.zenevent.repository.VisitorRepository;
import com.amh.zenevent.service.EventService;
import com.amh.zenevent.service.HistoriqueService;
import com.amh.zenevent.service.ServiceEntrepriseService;
import com.amh.zenevent.service.VisitorService;

@RestController
@RequestMapping("/api")
public class VisitorController {
	@Autowired
	VisitorService visitorService;
	@Autowired
	VisitorRepository visitorRepository;
	@Autowired
	HistoriqueService historiqueService;
	@Autowired
	EventService eventService;
	@Autowired
	ServiceEntrepriseService serviceEntreprise;

	@GetMapping(value = "/visitors")
	public List<Visitor> listVisitor() {
		return visitorService.listVisitor();
	}

	@GetMapping(value = "/visitors/{id}")
	public ResponseEntity<Visitor> getVisitorById(@PathVariable UUID id) {
		Visitor visitor = visitorService.getVisitorById(id);
		if (visitor != null) {
			return new ResponseEntity<Visitor>(visitor, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/visitors")
	public ResponseEntity<Visitor> addVisitor(@RequestBody HistoriquePojo historique) {
		Visitor visitor = visitorService.addVisitorFromHistoriquePojo(historique);
		return new ResponseEntity<Visitor>(visitor, HttpStatus.OK);
	}

	@PutMapping("/visitors/{id}")
	public ResponseEntity<?> updateVisitor(@PathVariable UUID id, @RequestBody Visitor visitor) {

		if (visitorService.isVisitorExist(id)) {
			visitorService.updateVisitor(id, visitor);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/visitors/{id}")
	public void deleteVisitor(@PathVariable UUID id) {
		Visitor visitor = visitorRepository.getVisitorById(id);
		visitor.setDeleted(true);
		visitorRepository.save(visitor);
	}

	@GetMapping("/visitors/count")
	public long countVisitor() {
		return visitorService.countVisitor();
	}

	@GetMapping(value = "/visitors/event/{id}")
	public List<Visitor> listVisitorByEvent(@PathVariable UUID id) {
		return visitorService.listVisitorByEvent(id);
	}

	@GetMapping(value = "/visitors/event/{idEvent}/service/{idService}")
	public List<Visitor> listVisitorByEventAndService(@PathVariable UUID idEvent, @PathVariable UUID idService) {
		return visitorService.listVisitorByEventAndService(idEvent, idService);
	}

	@GetMapping(value = "/visitors/user/{id}")
	public List<Visitor> listVisitorByUser(@PathVariable UUID id) {
		return visitorService.listVisitorByUser(id);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/visitor/{idVisitor}/event/{idEvent}")
	public ResponseEntity<?> getVisitorEventInformations(@PathVariable UUID idEvent, @PathVariable UUID idVisitor) {
		Event event = eventService.getEventById(idEvent);
		Visitor visitor = visitorService.getVisitorById(idVisitor);
		List<ServiceEntreprise> service = serviceEntreprise.listServiceOfVisitorByEvent(idEvent, idVisitor);

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("event", event);
		values.put("visitor", visitor);
		values.put("services", service);

		return new ResponseEntity<Map>(values, HttpStatus.OK);
	}
	
	@PostMapping("/visitors/{idVisitor}")
	public ResponseEntity<Visitor> addNewServiceToVisitor(@PathVariable UUID idVisitor, @RequestBody HistoriquePojo historique) {
		Visitor visitor = visitorService.addNewServiceToVisitor(idVisitor,historique);
		return new ResponseEntity<Visitor>(visitor, HttpStatus.OK);
	}
	
	@GetMapping(value = "/visitors/entreprise/{id}")
	public List<Visitor> listVisitorByEntreprise(@PathVariable UUID id) {
		return visitorService.listVisitorByEntreprise(id);
	}
	
	@GetMapping(value = "/visitors/entreprise/{idEntreprise}/event/{idEvent}")
	public List<Visitor> listVisitorByEntrepriseAndEvent(@PathVariable UUID idEntreprise, @PathVariable UUID idEvent ) {
		return visitorService.listVisitorByEntrepriseAndEvent(idEntreprise, idEvent);
	}

}
