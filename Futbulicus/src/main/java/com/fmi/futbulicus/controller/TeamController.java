package com.fmi.futbulicus.controller;

import static com.fmi.futbulicus.utils.ApiUtils.makeRequestToApi;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fmi.futbulicus.model.Team;
import com.fmi.futbulicus.repository.TeamRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;
	
	@RequestMapping(value = "/teams", method = RequestMethod.GET)
	public String getTeams(Model model) {
		Iterable<Team> teams = teamRepository.findAll();
		model.addAttribute("teams", teams);
		return "/teams";
	}
	
	@RequestMapping(value = "/teams/team/{id}")
	public String getTeam(@PathVariable("id") String id, HttpServletRequest request, Model model) throws IOException {
		System.out.println("ID is " + id);
		String team = makeRequestToApi("http://api.football-data.org/v1/teams/" + id);
		JsonObject jsonTeam = new Gson().fromJson(team, JsonObject.class);
		System.out.println("Response is :" + jsonTeam);
		//System.out.println("Response's players are: " + jsonTeam.get("players").getAsJsonObject());
		model.addAttribute("team", jsonTeam);
		
		JsonObject players = new Gson().fromJson(makeRequestToApi("http://api.football-data.org/v1/teams/" + id + "/players"), JsonObject.class);
		JsonArray playersArray = new JsonArray();
		playersArray.addAll(players.get("players").getAsJsonArray());
		
		model.addAttribute("players", playersArray);
		
		return "/team";
	}
	
	
}
