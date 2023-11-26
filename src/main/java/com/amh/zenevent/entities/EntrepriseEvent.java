package com.amh.zenevent.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "entreprise", "event" })
@Entity
public class EntrepriseEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEntrepriseEvent;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "entreprise_id")
	private Entreprise entreprise;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "event_id")
	private Event event;

	private boolean abonnement;

	public EntrepriseEvent() {
		super();
	}

	public EntrepriseEvent(Entreprise entreprise, Event event) {
		super();
		this.entreprise = entreprise;
		this.event = event;
		this.abonnement = true;
	}

	public long getIdEntrepriseEvent() {
		return idEntrepriseEvent;
	}

	public void setIdEntrepriseEvent(long idEntrepriseEvent) {
		this.idEntrepriseEvent = idEntrepriseEvent;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public boolean isAbonnement() {
		return abonnement;
	}

	public void setAbonnement(boolean abonnement) {
		this.abonnement = abonnement;
	}

}
