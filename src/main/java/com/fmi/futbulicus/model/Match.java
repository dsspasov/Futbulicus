package com.fmi.futbulicus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "matches")
public class Match {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "result")
	private String result;
	
	@OneToOne
	private Team host;
	
	@OneToOne
	private Team guest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Team getHost() {
		return host;
	}

	public void setHost(Team host) {
		this.host = host;
	}

	public Team getGuest() {
		return guest;
	}

	public void setGuest(Team guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", result=" + result + ", host=" + host + ", guest=" + guest + "]";
	}
	
	
}
