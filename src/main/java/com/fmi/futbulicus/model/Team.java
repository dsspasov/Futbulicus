package com.fmi.futbulicus.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "teams")
public class Team {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	private Long id;
	
	@Column(name = "name")
	@Expose
	private String name;
	
	@Column(name = "number_of_cups")
	private int cupsWon;
	
	@OneToMany
	private Collection<Footballer> footballers = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCupsWon() {
		return cupsWon;
	}

	public void setCupsWon(int cupsWon) {
		this.cupsWon = cupsWon;
	}
	
	

	public Collection<Footballer> getFootballers() {
		return footballers;
	}

	public void setFootballers(Collection<Footballer> footballers) {
		this.footballers = footballers;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
