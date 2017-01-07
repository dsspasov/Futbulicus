package com.fmi.futbulicus.model;

import java.io.Serializable;

import com.google.gson.Gson;

public class Fixture implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String date;
	private String status;
	private Integer matchday;
	private String homeTeamName;
	private String awayTeamName;
	private int resultGoalsHomeTeam;
	private int resultGoalsAwayTeam;
	
	public Fixture(){
		
	}

	
	
	public Fixture(String date, String status, Integer matchDay,
			String homeTeamName, String awayTeamName, int resultGoalsHomeTeam,
			int resultGoalsAwayTeam) {
		super();
		this.date = date;
		this.status = status;
		this.matchday = matchDay;
		this.homeTeamName = homeTeamName;
		this.awayTeamName = awayTeamName;
		this.resultGoalsHomeTeam = resultGoalsHomeTeam;
		this.resultGoalsAwayTeam = resultGoalsAwayTeam;
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getMatchday() {
		return matchday;
	}



	public void setMatchday(Integer matchday) {
		this.matchday = matchday;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public int getResultGoalsHomeTeam() {
		return resultGoalsHomeTeam;
	}

	public void setResultGoalsHomeTeam(int resultGoalsHomeTeam) {
		this.resultGoalsHomeTeam = resultGoalsHomeTeam;
	}

	public int getResultGoalsAwayTeam() {
		return resultGoalsAwayTeam;
	}

	public void setResultGoalsAwayTeam(int resultGoalsAwayTeam) {
		this.resultGoalsAwayTeam = resultGoalsAwayTeam;
	}



	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}	
}
