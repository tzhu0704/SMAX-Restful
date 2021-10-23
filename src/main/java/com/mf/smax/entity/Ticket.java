package com.mf.smax.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Entity
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	private String displaylabel = "";
	private String description =  "";
	private String actualservice="";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplaylabel() {
		return displaylabel;
	}

	public void setDisplaylabel(String displaylabel) {
		this.displaylabel = displaylabel;
	}



	public String getActualservice() {
		return actualservice;
	}

	public void setActualservice(String actualservice) {
		this.actualservice = actualservice;
	}

	public Ticket() {
		super();
	}

	
}
