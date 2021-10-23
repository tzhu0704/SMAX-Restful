package com.mf.smax.entity;


import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class AlertFromAlertManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private String commonAnnotations = "";
	private String commonLabels =  "";
	private String status =  "";

//	{

	public String getCommonAnnotations() {
		return commonAnnotations;
	}

	public void setCommonAnnotations(String commonAnnotations) {
		this.commonAnnotations = commonAnnotations;
	}

	public String getCommonLabels() {
		return commonLabels;
	}

	public void setCommonLabels(String commonLabels) {
		this.commonLabels = commonLabels;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
