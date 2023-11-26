package com.amh.zenevent.pojo;

public class HistoriquePojo {
	private long id;
	private String dateEnregistrement;
	private UserPojo user;
	private ServicePojo service;
	private EventPojo event;
	private VisitorPojo visitor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDateEnregistrement() {
		return dateEnregistrement;
	}

	public UserPojo getUser() {
		return user;
	}

	public void setUser(UserPojo user) {
		this.user = user;
	}

	public ServicePojo getService() {
		return service;
	}

	public void setService(ServicePojo service) {
		this.service = service;
	}

	public EventPojo getEvent() {
		return event;
	}

	public void setEvent(EventPojo event) {
		this.event = event;
	}

	public VisitorPojo getVisitor() {
		return visitor;
	}

	public void setVisitor(VisitorPojo visitor) {
		this.visitor = visitor;
	}

	public void setDateEnregistrement(String dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}
}
