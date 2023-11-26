package com.amh.zenevent.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "entreprise"})
@Entity
public class ServiceEntreprise implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@Column(name = "idService", length = 16, unique = true, updatable = false, nullable = false)
	private UUID idService;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	private String nomService;
	@ManyToOne
	@JoinColumn(name = "entreprise_id")
	private Entreprise entreprise;
	@OneToMany(mappedBy = "serviceEntreprise")
	private Collection<Historique> historiques;
	private boolean isDeleted;
	
	
	public ServiceEntreprise(Category category, String nomService, Entreprise entreprise, boolean isDeleted) {
		super();
		this.category = category;
		this.nomService = nomService;
		this.entreprise = entreprise;
		this.isDeleted = false;
	}
	
	public ServiceEntreprise() {
		super();
	}
	
	public UUID getIdService() {
		return idService;
	}

	public void setIdService(UUID idService) {
		this.idService = idService;
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

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
