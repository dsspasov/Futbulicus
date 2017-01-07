package com.fmi.futbulicus.model;

import java.io.Serializable;

public class Fixture implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String date;
	private String status;
	private String matchDay;
	private String homeTeamName;
	private String awayTeamName;
	private int resultGoalsHomeTeam;
	private int resultGoalsAwayTeam;
	
	public Fixture(){
		
	}

	
	
	public Fixture(String date, String status, String matchDay,
			String homeTeamName, String awayTeamName, int resultGoalsHomeTeam,
			int resultGoalsAwayTeam) {
		super();
		this.date = date;
		this.status = status;
		this.matchDay = matchDay;
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

	public String getMatchDay() {
		return matchDay;
	}

	public void setMatchDay(String matchDay) {
		this.matchDay = matchDay;
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
	
}
