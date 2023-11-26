package com.amh.zenevent.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amh.zenevent.entities.Event;
import com.amh.zenevent.repository.EventRepository;

@Service
public class EventService {
	@Autowired
	EventRepository eventRepository;

	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Date date = new Date();

	public List<Event> listEvent() {
		return eventRepository.listEvent();
	}

	public Event getEventById(UUID id) {
		return eventRepository.getEventById(id);
	}

	public void addEvent(Event event) throws ParseException {

//		Date checkDateFin = formatter.parse(event.getDateFinEvent());
//		Date currentDate = formatter.parse(event.getDateCreationEvent());
//
//		int comparaison = currentDate.compareTo(checkDateFin);
//
//		if (comparaison > 0) {
//			event.setStatus(Status.A_VENIR);
//		} else if (comparaison > 0) {
//			event.setStatus(Status.TERMINE);
//		} else {
//			event.setStatus(Status.EN_COURS);
//		}

		eventRepository.save(event);

	}

	public boolean isEventExist(UUID id) {
		return eventRepository.existsById(id);
	}

	public void updateEvent(UUID id, Event event) {
		Event checkEvent = eventRepository.getEventById(id);
		if (checkEvent != null) {
			checkEvent.setNomEvent(event.getNomEvent());
			checkEvent.setDescription(event.getDescription());
			checkEvent.setPaysEvent(event.getPaysEvent());
			checkEvent.setVille(event.getVille());
			checkEvent.setQuartier(event.getQuartier());
			checkEvent.setLieuDit(event.getLieuDit());
			checkEvent.setDateDebutEvent(event.getDateDebutEvent());
			checkEvent.setDateFinEvent(event.getDateFinEvent());
			checkEvent.setEntrepriseEvents(event.getEntrepriseEvents());
			checkEvent.setIsDeleted(event.getIsDeleted());
		}
		eventRepository.save(checkEvent);

	}

	public long countEvent() {
		return eventRepository.countEvent();
	}

	public List<Event> listEventByEntreprise(UUID id) {
		return eventRepository.listEventByEntreprise(id);
	}

	public List<Event> listEventByDateDebut(String date) throws Exception {
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		//Date checkDate = formatter.parse(date);
		return eventRepository.listEventByDateDebut(date);
	}

	public Event getEventByVisitor(UUID idVisitor) {
		return eventRepository.getEventByVisitor(idVisitor);
	}

	public Event getEventByUser(UUID idUser) {
		return eventRepository.getEventByUser(idUser);
	}

	public long countEventByEntreprise(UUID idEntreprise) {
		return eventRepository.countEventByEntreprise(idEntreprise);
	}

	public List<Event> listDistinctEventByEntreprise(UUID idEntreprise) {
		return eventRepository.listDistinctEventByEntreprise(idEntreprise);
	}

	public List<Object[]> countVisiteurByEntrepriseGroupByEvenement(UUID idEntreprise) {
		return eventRepository.countVisiteurByEntrepriseGroupByEvenement(idEntreprise);
	}

}
