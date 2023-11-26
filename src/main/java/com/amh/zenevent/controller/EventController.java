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

import com.amh.zenevent.entities.Event;
import com.amh.zenevent.repository.EventRepository;
import com.amh.zenevent.service.EntrepriseService;
import com.amh.zenevent.service.EventService;

@RestController
@RequestMapping("/api")
public class EventController {
	@Autowired
	EventService eventService; 
	@Autowired
	EventRepository eventRepository;
	@Autowired
	EntrepriseService entrepriseService;
	
	@GetMapping(value = "/events")
	public List<Event> listEvent(){
		return eventService.listEvent();
	}
	
	@GetMapping(value = "/events/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable UUID id){
		Event event = eventService.getEventById(id);
		if (event != null) {
			return new ResponseEntity<Event>(event,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PostMapping("/events")
	public ResponseEntity<Event> addEvent(@RequestBody Event event){
		try {
			event.setIsDeleted(false);
			eventService.addEvent(event);
			return new ResponseEntity<Event>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/events/{id}")
	public ResponseEntity<?> updateEvent(@PathVariable UUID id, @RequestBody Event event){
		
		if (eventService.isEventExist(id)) {
			eventService.updateEvent(id,event);
			return new ResponseEntity<>(HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/events/{id}")
	public void deleteEvent(@PathVariable UUID id) {
		Event event = eventRepository.getEventById(id);
		event.setIsDeleted(true);
		eventRepository.save(event);
	}
	
	@GetMapping("/events/count")
	public long countEvent() {
		return eventService.countEvent();
	}
	
	@GetMapping(value = "/events/entreprise/{id}")
	public List<Event> listEventByEntreprise(@PathVariable UUID id){
		return eventService.listEventByEntreprise(id);
	}

}
