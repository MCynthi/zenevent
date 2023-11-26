package com.amh.zenevent.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.amh.zenevent.entities.Visitor.Impression;
import com.amh.zenevent.entities.Visitor.Sexe;

public class VisitorPojo {
	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private long id;
	private String nomVisitor;
	private String email;
	private Sexe sexe;
	private String telephone1;
	private String telephone2;
	private String metier;
	private String secteurActivite;
	private String age;
	private String commentaire;
	private Impression impression;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Impression getImpression() {
		return impression;
	}

	public void setImpression(Impression impression) {
		this.impression = impression;
	}

}
