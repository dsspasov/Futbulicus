//package com.fmi.futbulicus.model;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
//import com.fmi.futbulicus.utils.Status;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.annotations.Expose;
//
//@Entity
//@Table (name = "matches")
//@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.ANY, setterVisibility = Visibility.NONE)
//public class Match {
//
//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Expose
//	private Long id;
//	
//	@Column(name = "date")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date date;
//	
//	@Column(name = "result")
//	@Expose
//	private String result;
//	
//	@OneToOne
//	@Expose
//	private Team host;
//	
//	@OneToOne
//	@Expose
//	private Team guest;
//
//	@Column(name = "status")
//	@Enumerated(EnumType.STRING)
//	private Status status;
//	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//	public String getResult() {
//		return result;
//	}
//
//	public void setResult(String result) {
//		this.result = result;
//	}
//
//	public Team getHost() {
//		return host;
//	}
//
//	public void setHost(Team host) {
//		this.host = host;
//	}
//
//	public Team getGuest() {
//		return guest;
//	}
//
//	public void setGuest(Team guest) {
//		this.guest = guest;
//	}
//	
//	public Status getStatus() {
//		return status;
//	}
//
//	public void setStatus(Status status) {
//		this.status = status;
//	}
//
//	@Override
//	public String toString() {
//		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//		return gson.toJson(this);
//	}
//	
//	
//}
