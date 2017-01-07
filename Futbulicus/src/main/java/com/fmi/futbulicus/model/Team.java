package com.fmi.futbulicus.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	
	@Column(name = "plays")
	@Expose
	private int plays;
	
	@Column(name = "wins")
	@Expose
	private int wins;
	
	@Column(name = "draws")
	@Expose
	private int draws;
	
	@Column(name = "losses")
	@Expose
	private int losses;
	
	@Column(name = "goals_for")
	@Expose
	private int goalsFor;
	
	@Column(name = "goals_against")
	@Expose
	private int goalsAgainst;
	
	@Column(name = "goals_differential")
	@Expose
	private int goalsDifferential;
	
	@Column(name = "points")
	@Expose
	private int points;
	
	@Column(name = "number_of_cups")
	@Expose
	private int cupsWon;
	
	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
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

	public int getPlays() {
		return plays;
	}

	public void setPlays(int plays) {
		this.plays = plays;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}
	
	public int getGoalsDifferential() {
		return goalsFor - goalsAgainst;
	}

	public int getPoints() {
		return 3 * wins + draws;
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
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(this);
	}
	
	
	
}
