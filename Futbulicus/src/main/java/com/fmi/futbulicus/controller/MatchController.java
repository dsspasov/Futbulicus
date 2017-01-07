package com.fmi.futbulicus.controller;

import static com.fmi.futbulicus.utils.ApiUtils.makeRequestToApi;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fmi.futbulicus.model.Match;
import com.fmi.futbulicus.repository.MatchRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class MatchController {
	
	private static final String URL = "https://euadmin4.backstage.spotme.com/api/v1/eid/cbe9ff2c721f63e6347ca3f66ce21177/nodehandlers/soccer/stats?";
	
	@Autowired
	private MatchRepository matchRepository;
	@RequestMapping(value = "/matches", method = RequestMethod.GET)
	public String getMatches(Model model) {
		model.addAttribute("date", LocalDate.now().toString());
		return "/matches";
	}
	
	/*@RequestMapping(value = "/matches/match/{id}", method = RequestMethod.GET)
	public String getMatch(@PathVariable("id") Long id, Model model) {
		Match match = matchRepository.findOne(id);
		model.addAttribute("match", match);
		return "/match";
	}*/
	
	
	@RequestMapping(value = "/matches/search", method = RequestMethod.GET)
	public String getMatchesByDate(Model model, HttpServletRequest request) throws IOException{
		
		/**Get Params*/
		String date = request.getParameter("date");
		String competitionID = request.getParameter("competition");
		
		if(date != null && competitionID != null) {
			/**Prepare request params*/
			HashMap<String, String> requestParams = new HashMap<>();
			/**result*/
			JsonArray matches = new JsonArray();
			
			/**for each match day get the all fixtures*/
			requestParams.put("type", "fixtures");
			requestParams.put("id", competitionID);
			String fixtures = makeRequestToApi(URL, requestParams);
			requestParams.clear();
			JsonObject jsonFixtures = new Gson().fromJson(fixtures, JsonObject.class);
			
			JsonArray fixturesArray = new JsonArray();
			fixturesArray.addAll(jsonFixtures.get("fixtures").getAsJsonArray());
			
			/** for each fixture check if date == date*/
			for(int j = 0; j<fixturesArray.size(); j++) {
				String fixtureDate = fixturesArray.get(j).getAsJsonObject().get("date").getAsString();
				if(fixtureDate.contains(date)){
					matches.add(fixturesArray.get(j));
				}
			}			
			
			model.addAttribute("matches", matches);
		}
				
		return "/matches";
	}
}
