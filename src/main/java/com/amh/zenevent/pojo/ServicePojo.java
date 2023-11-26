package com.amh.zenevent.pojo;

import java.util.UUID;

import com.amh.zenevent.entities.Category;

public class ServicePojo {
	private UUID id;
	private Category category;
	private String nomService;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getNomService() {
		return nomService;
	}

	public void setNomService(String nomService) {
		this.nomService = nomService;
	}

}
