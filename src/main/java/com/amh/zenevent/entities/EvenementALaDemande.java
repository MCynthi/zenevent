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
public class EvenementALaDemande {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
			@Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy") })
	@Column(name = "idEvenementALaDemande", length = 16, unique = true, updatable = false, nullable = false)
	private UUID id;
	private String nomEvent;
	private String pays;
	private String ville;
	private String quartier;
	private String lieuDit;
	private String dateDebutEvent;
	private String dateFinEvent;
	private String dateDemande;
	private String motif;
	private String reference;
	private boolean isDeleted;
	private String description;
	private boolean isApproved;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "entreprise_id", nullable = false)
	private Entreprise entreprise;

	public EvenementALaDemande() {
		super();
	}

	public EvenementALaDemande(String nomEvent, String pays, String ville, String quartier, String lieuDit,
			String dateDebutEvent, String dateFinEvent, String motif, String reference, String description,
			Entreprise entreprise) {
		super();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();

		this.nomEvent = nomEvent;
		this.pays = pays;
		this.ville = ville;
		this.quartier = quartier;
		this.lieuDit = lieuDit;
		this.dateDebutEvent = dateDebutEvent;
		this.dateFinEvent = dateFinEvent;
		this.motif = motif;
		this.reference = reference;
		this.isDeleted = false;
		this.description = description;
		this.isApproved = false;
		this.entreprise = entreprise;
		this.dateDemande = formatter.format(date);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNomEvent() {
		return nomEvent;
	}

	public void setNomEvent(String nomEvent) {
		this.nomEvent = nomEvent;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
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

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

}
