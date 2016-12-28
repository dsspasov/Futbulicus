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

@Entity
@Table(name = "teams")
public class Team {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "number_of_cups")
	private int cupsWon;
	
	@OneToMany(mappedBy = "team")
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
		return "Team [id=" + id + ", name=" + name + ", cupsWon=" + cupsWon + "]";
	}
	
	
}
