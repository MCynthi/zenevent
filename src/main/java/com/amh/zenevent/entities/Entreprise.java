package com.amh.zenevent.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "events", "users", "ServiceEntreprises" })
@Entity
public class Entreprise implements Serializable{
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
	@Column(name = "idEntreprise", length = 16, unique = true, updatable = false, nullable = false)
	private UUID idEntreprise;
	private String nomEntreprise;
	private String email;
	private String telephone1;
	private String telephone2;
	private String username;
	private String password;
	@OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY)
	private Collection<User> users;
	@OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY)
	private Collection<ServiceEntreprise> serviceEntreprises;
	@OneToMany(mappedBy = "entreprise")
	private Collection<EntrepriseEvent> entrepriseEvents;
	@OneToMany(mappedBy = "entreprise")
	private Collection<EvenementALaDemande> evenementALaDemande;
	@OneToMany(mappedBy = "entreprise")
	private Collection<Support> supports;
	private Boolean isDeleted;
	
	public Entreprise() {
		super();
	}

	public Entreprise(String nomEntreprise, String email, String telephone1, String telephone2, String username,
			String password, Collection<User> users) {
		super();
		this.nomEntreprise = nomEntreprise;
		this.email = email;
		this.telephone1 = telephone1;
		this.telephone2 = telephone2;
		this.username = username;
		this.password = password;
		this.users = users;
		this.isDeleted = false;
	}
	
	public UUID getIdEntreprise() {
		return idEntreprise;
	}

	public void setIdEntreprise(UUID idEntreprise) {
		this.idEntreprise = idEntreprise;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<ServiceEntreprise> getServiceEntreprises() {
		return serviceEntreprises;
	}

	public void setServiceEntreprises(Collection<ServiceEntreprise> serviceEntreprises) {
		this.serviceEntreprises = serviceEntreprises;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Collection<EntrepriseEvent> getEntrepriseEvents() {
		return entrepriseEvents;
	}

	public void setEntrepriseEvents(Collection<EntrepriseEvent> entrepriseEvents) {
		this.entrepriseEvents = entrepriseEvents;
	}

	public Collection<EvenementALaDemande> getEvenementALaDemande() {
		return evenementALaDemande;
	}

	public void setEvenementALaDemande(Collection<EvenementALaDemande> evenementALaDemande) {
		this.evenementALaDemande = evenementALaDemande;
	}

	public Collection<Support> getSupports() {
		return supports;
	}

	public void setSupports(Collection<Support> supports) {
		this.supports = supports;
	}
	
}
