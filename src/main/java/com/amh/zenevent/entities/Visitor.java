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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "historiques"})
@Entity
public class Visitor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum Impression{
		PEU_SATISFAIT, SATISFAIT, TRES_SATISFAIT
	}
	public enum Sexe{
		FEMININ, MASCULIN, AUTRE
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
    @Column(name = "id", length = 16, unique = true, updatable = false, nullable = false)
	private UUID idVisitor;
	private String nomVisitor;
	private String email;
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	@Enumerated(EnumType.STRING)
	private Impression impression;
	private String telephone1;
	private String telephone2;
	private String metier;
	private String secteurActivite;
	private String age;
	private String commentaire;
	private String dateEnregistrement;
	@OneToMany(mappedBy = "visitor")
	private Collection<Historique> historiques;
	private boolean isDeleted;
	
	
	
	public Visitor(String nomVisitor, String email, Sexe sexe, Impression impression, String telephone1,
			String telephone2, String metier, String secteurActivite, User user,String commentaire, String age, ServiceEntreprise serviceEntreprise) {
		super();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		this.nomVisitor = nomVisitor;
		this.email = email;
		this.sexe = sexe;
		this.impression = impression;
		this.telephone1 = telephone1;
		this.telephone2 = telephone2;
		this.metier = metier;
		this.secteurActivite = secteurActivite;
		this.commentaire = commentaire;
		this.age = age;
		this.isDeleted = false;
		this.dateEnregistrement = formatter.format(date);
	}
	
	public Visitor() {
		super();
	}

	public UUID getIdVisitor() {
		return idVisitor;
	}
	public void setIdVisitor(UUID idVisitor) {
		this.idVisitor = idVisitor;
	}
	public String getNomVisitor() {
		return nomVisitor;
	}
	public void setNomVisitor(String nomVisitor) {
		this.nomVisitor = nomVisitor;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	public Impression getImpression() {
		return impression;
	}
	public void setImpression(Impression impression) {
		this.impression = impression;
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
	public String getMetier() {
		return metier;
	}
	public void setMetier(String metier) {
		this.metier = metier;
	}
	public String getSecteurActivite() {
		return secteurActivite;
	}
	public void setSecteurActivite(String secteurActivite) {
		this.secteurActivite = secteurActivite;
	}

	public String getDateEnregistrement() {
		return dateEnregistrement;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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

	public Collection<Historique> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(Collection<Historique> historiques) {
		this.historiques = historiques;
	}
}
