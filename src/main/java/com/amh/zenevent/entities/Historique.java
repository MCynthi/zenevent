package com.amh.zenevent.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "user", "serviceEntreprise", "event", "visitor" })
@Entity
public class Historique implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idHistorique;
	private String dateEnregistrement;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_id", nullable = false)
	private ServiceEntreprise serviceEntreprise;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "event_id")
	private Event event;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "visitor_id", nullable = false)
	private Visitor visitor;

	public Historique(User user, ServiceEntreprise serviceEntreprise, Event event, Visitor visitor) {
		super();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		this.user = user;
		this.serviceEntreprise = serviceEntreprise;
		this.event = event;
		this.visitor = visitor;
		this.dateEnregistrement = formatter.format(date);
	}

	public Historique() {
		super();
	}

	public long getId() {
		return idHistorique;
	}

	public void setId(long idHistorique) {
		this.idHistorique = idHistorique;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ServiceEntreprise getServiceEntreprise() {
		return serviceEntreprise;
	}

	public void setServiceEntreprise(ServiceEntreprise serviceEntreprise) {
		this.serviceEntreprise = serviceEntreprise;
	}

	public long getIdHistorique() {
		return idHistorique;
	}

	public void setIdHistorique(long idHistorique) {
		this.idHistorique = idHistorique;
	}

	public String getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(String dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

}
