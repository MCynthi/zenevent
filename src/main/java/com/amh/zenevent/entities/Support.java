package com.amh.zenevent.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Support {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
			@Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy") })
	@Column(name = "idSupport", length = 16, unique = true, updatable = false, nullable = false)
	private UUID id;
	private String sujet;
	@Column(columnDefinition = "TEXT")
	private String preoccupation;
	private String dateEnregistrement;
	@Column(columnDefinition = "TEXT")
	private String commentaire;
	private boolean isDeleted;
	private boolean statut;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "entreprise_id", nullable = false)
	private Entreprise entreprise;

	public Support() {
		super();
	}

	public Support(String sujet, String preoccupation, Entreprise entreprise) {
		super();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		
		this.sujet = sujet;
		this.preoccupation = preoccupation;
		this.entreprise = entreprise;
		this.isDeleted = false;
		this.statut = false;
		this.dateEnregistrement = formatter.format(date);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getPreoccupation() {
		return preoccupation;
	}

	public void setPreoccupation(String preoccupation) {
		this.preoccupation = preoccupation;
	}

	public String getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(String dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isStatut() {
		return statut;
	}

	public void setStatut(boolean statut) {
		this.statut = statut;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}
