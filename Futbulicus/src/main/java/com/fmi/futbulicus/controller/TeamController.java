package com.fmi.futbulicus.controller;

import static com.fmi.futbulicus.utils.ApiUtils.makeRequestToApi;

import java.io.IOException;
import java.util.HashMap;

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

	private static final String PLAYERS_TEAM_URL = "https://euadmin4.backstage.spotme.com/api/v1/eid/cbe9ff2c721f63e6347ca3f66ce21177/nodehandlers/soccer/stats?";
	
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
		HashMap<String, String> requestParams = new HashMap<>();
		requestParams.put("type", "team");
		requestParams.put("id", id);
		String team = makeRequestToApi(PLAYERS_TEAM_URL, requestParams);
		JsonObject jsonTeam = new Gson().fromJson(team, JsonObject.class);
		System.out.println("Response is :" + jsonTeam);
		//System.out.println("Response's players are: " + jsonTeam.get("players").getAsJsonObject());
		model.addAttribute("team", jsonTeam);
		
		requestParams.put("type", "players");
		JsonObject players = new Gson().fromJson(makeRequestToApi(PLAYERS_TEAM_URL, requestParams), JsonObject.class);
		JsonArray playersArray = new JsonArray();
		playersArray.addAll(players.get("players").getAsJsonArray());
		
		model.addAttribute("players", playersArray);
		
		System.out.println("PLAYERS ARE:" + playersArray);
		
		return "/team";
	}
	
	
}
