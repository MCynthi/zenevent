package com.amh.zenevent.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum Status{
		TERMINE, EN_COURS, A_VENIR
	}
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
        parameters = {
            @Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        }
    )
    @Column(name = "idEvent", length = 16, unique = true, updatable = false, nullable = false)
	private UUID idEvent;
	private String nomEvent;
	private String paysEvent;
	private String ville;
	private String quartier;
	private String lieuDit;
	private String dateDebutEvent;
	private String dateFinEvent;
	private String dateCreationEvent;
	private boolean isDeleted;
	private String description;
	@Enumerated(EnumType.STRING)
	private Status status;
	@OneToMany(mappedBy = "event")
	private Collection<EntrepriseEvent> entrepriseEvents;	
	@OneToMany(mappedBy = "event")
	private Collection<Historique> historiques;
	
	public Event() {
		super();
	}
	

	public Event(String nomEvent, String paysEvent, String ville, String quartier, String lieuDit,
			String dateDebutEvent, String dateFinEvent, String description) {
		super();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		
		this.nomEvent = nomEvent;
		this.paysEvent = paysEvent;
		this.ville = ville;
		this.quartier = quartier;
		this.lieuDit = lieuDit;
		this.dateDebutEvent = dateDebutEvent;
		this.dateFinEvent = dateFinEvent;
		this.description = description;
		this.isDeleted = false;
		this.dateCreationEvent = formatter.format(date);
	}

	public UUID getIdEvent() {
		return idEvent;
	}


	public void setIdEvent(UUID idEvent) {
		this.idEvent = idEvent;
	}


	public String getNomEvent() {
		return nomEvent;
	}

	public void setNomEvent(String nomEvent) {
		this.nomEvent = nomEvent;
	}

	public String getDateDebutEvent() {
		return dateDebutEvent;
	}

	public void setDateDebutEvent(String dateDebutEvent) {
		this.dateDebutEvent = dateDebutEvent;
	}

	public String getDateFinEvent() {
		return dateFinEvent;
	}

	public void setDateFinEvent(String dateFinEvent) {
		this.dateFinEvent = dateFinEvent;
	}

	public String getDateCreationEvent() {
		return dateCreationEvent;
	}

	public void setDateCreationEvent(String dateCreationEvent) {
		this.dateCreationEvent = dateCreationEvent;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Collection<EntrepriseEvent> getEntrepriseEvents() {
		return entrepriseEvents;
	}

	public void setEntrepriseEvents(Collection<EntrepriseEvent> entrepriseEvents) {
		this.entrepriseEvents = entrepriseEvents;
	}

	public Collection<Historique> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(Collection<Historique> historiques) {
		this.historiques = historiques;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	public String getLieuDit() {
		return lieuDit;
	}

	public void setLieuDit(String lieuDit) {
		this.lieuDit = lieuDit;
	}

	public String getPaysEvent() {
		return paysEvent;
	}


	public void setPaysEvent(String paysEvent) {
		this.paysEvent = paysEvent;
	}


	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

}
