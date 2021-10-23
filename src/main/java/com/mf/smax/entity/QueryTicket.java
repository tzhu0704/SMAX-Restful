package com.mf.smax.entity;


import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class QueryTicket implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ticketID = "";

	public String getTicketID() {
		return ticketID;
	}

	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	private String ticketType =  "";


	public QueryTicket() {
		super();
	}

	
}
