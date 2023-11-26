package com.amh.zenevent.pojo;

import java.util.UUID;

public class EventPojo {
	private UUID id;
	private String nomEvent;
	private String dateDebutEvent;
	private String dateFinEvent;
	private String dateCreationEvent;
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
