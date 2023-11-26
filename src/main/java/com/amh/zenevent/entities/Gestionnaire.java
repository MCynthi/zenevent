package com.amh.zenevent.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Gestionnaire {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
			@Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy") })
	@Column(name = "idEntreprise", length = 16, unique = true, updatable = false, nullable = false)
	private UUID idGestionnaire;
	private String nom;
	
	public Gestionnaire() {
		super();
	}
	
	public Gestionnaire(UUID idGestionnaire, String nom) {
		super();
		this.idGestionnaire = idGestionnaire;
		this.nom = nom;
	}
	
	public UUID getIdGestionnaire() {
		return idGestionnaire;
	}
	public void setIdGestionnaire(UUID idGestionnaire) {
		this.idGestionnaire = idGestionnaire;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}
