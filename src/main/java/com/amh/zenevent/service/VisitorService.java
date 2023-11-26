package com.amh.zenevent.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amh.zenevent.entities.Event;
import com.amh.zenevent.entities.Historique;
import com.amh.zenevent.entities.ServiceEntreprise;
import com.amh.zenevent.entities.User;
import com.amh.zenevent.entities.Visitor;
import com.amh.zenevent.pojo.HistoriquePojo;
import com.amh.zenevent.pojo.VisitorPojo;
import com.amh.zenevent.repository.HistoriqueRepository;
import com.amh.zenevent.repository.VisitorRepository;

@Service
public class VisitorService {
	@Autowired
	VisitorRepository visitorRepository;
	@Autowired
	HistoriqueRepository historiqueRepository;

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

	public List<Visitor> listVisitor() {
		return visitorRepository.listVisitor();
	}

	public Visitor getVisitorById(UUID id) {
		return visitorRepository.getVisitorById(id);
	}

	public void addVisitor(Visitor visitor) {
		visitor.setDateEnregistrement(formatter.format(new Date()));
		visitorRepository.save(visitor);
	}

	public boolean isVisitorExist(UUID id) {
		return visitorRepository.isVisitorExist(id);
	}

	public void updateVisitor(UUID id, Visitor visitor) {
		Visitor checkVisitor = visitorRepository.getVisitorById(id);
		checkVisitor.setEmail(visitor.getEmail());
		checkVisitor.setImpression(visitor.getImpression());
		checkVisitor.setMetier(visitor.getMetier());
		checkVisitor.setNomVisitor(visitor.getNomVisitor());
		checkVisitor.setSecteurActivite(visitor.getSecteurActivite());
		checkVisitor.setCommentaire(visitor.getCommentaire());
		checkVisitor.setSexe(visitor.getSexe());
		checkVisitor.setTelephone1(visitor.getTelephone1());
		checkVisitor.setTelephone2(visitor.getTelephone2());
		checkVisitor.setAge(visitor.getAge());
		visitorRepository.save(checkVisitor);
	}

	public long countVisitor() {
		return visitorRepository.countVisitor();
	}

	public List<Visitor> listVisitorByEventAndService(UUID idEvent, UUID idService) {
		return visitorRepository.listVisitorByEventAndService(idEvent, idService);
	}

	public List<Visitor> listVisitorByEvent(UUID id) {
		return visitorRepository.listVisitorByEvent(id);
	}

	public List<Visitor> listVisitorByUser(UUID id) {
		return visitorRepository.listVisitorByUser(id);
	}

	@Transactional
	public Visitor addVisitorFromHistoriquePojo(HistoriquePojo historiquePojo) {

		Event event = new Event();
		event.setIdEvent(historiquePojo.getEvent().getId());

		ServiceEntreprise service = new ServiceEntreprise();
		service.setIdService(historiquePojo.getService().getId());

		User user = new User();
		user.setIdUser(historiquePojo.getUser().getId());

		VisitorPojo visitorPojo = historiquePojo.getVisitor();
		Visitor visitor = new Visitor(visitorPojo.getNomVisitor(), visitorPojo.getEmail(), visitorPojo.getSexe(),
				visitorPojo.getImpression(), visitorPojo.getTelephone1(), visitorPojo.getTelephone2(),
				visitorPojo.getMetier(), visitorPojo.getSecteurActivite(), user, visitorPojo.getCommentaire(),
				visitorPojo.getAge(), service);

		Historique historique = new Historique(user, service, event, visitor);

		visitorRepository.save(visitor);
		historiqueRepository.save(historique);
		return visitor;
	}


	@Transactional
	public Visitor addNewServiceToVisitor(UUID idVisitor, HistoriquePojo historiquePojo) {
		Visitor visitor = visitorRepository.getVisitorById(idVisitor);
		Event event = new Event();
		event.setIdEvent(historiquePojo.getEvent().getId());

		ServiceEntreprise service = new ServiceEntreprise();
		service.setIdService(historiquePojo.getService().getId());

		User user = new User();
		user.setIdUser(historiquePojo.getUser().getId());

		Historique historique = new Historique(user, service, event, visitor);

		visitorRepository.save(visitor);
		historiqueRepository.save(historique);
		return visitor;
	}

	public List<Visitor> listVisitorByUserAndEvent(UUID idUser, UUID idEvent) {
		return visitorRepository.listVisitorByUserAndEvent(idUser,idEvent);
	}

	public List<Visitor> searchVisitor(String mc) {
		return visitorRepository.searchVisitor(mc);
	}

	public long countVisitorByEntreprise(UUID idEntreprise) {
		return visitorRepository.countVisitorByEntreprise(idEntreprise);
	}

	public List<Visitor> listVisitorByEntreprise(UUID idEntreprise) {
		return visitorRepository.listVisitorByEntreprise(idEntreprise);
	}

	public List<Visitor> listVisitorsByEvent(UUID id) {
		return visitorRepository.listVisitorsByEvent(id);
	}

	public List<Visitor> listVisitorsByService(UUID id) {
		return visitorRepository.listVisitorsByService(id);
	}

	public List<Visitor> listVisitorByEntrepriseAndEvent(UUID idEntreprise, UUID idEvent) {
		return visitorRepository.listVisitorByEntrepriseAndEvent(idEntreprise,idEvent);
	}

	public List<Visitor> listDistinctVisitorByEntreprise(UUID idEntreprise) {
		return visitorRepository.listDistinctVisitorByEntreprise(idEntreprise);
	}

	public List<Visitor> listVisitorByEntrepriseAndMotCle(UUID idEntreprise, UUID mc) {
		return visitorRepository.listVisitorByEntrepriseAndMotCle(idEntreprise,mc);
	}


}
