package com.fmi.futbulicus.controller;

import static com.fmi.futbulicus.utils.ApiUtils.makeRequestToApi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fmi.futbulicus.model.Fixture;
import com.fmi.futbulicus.service.SearchService;
//import com.fmi.futbulicus.model.Match;
//import com.fmi.futbulicus.repository.MatchRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@Controller
public class MatchController {

	private static final String URL = "https://euadmin4.backstage.spotme.com/api/v1/eid/cbe9ff2c721f63e6347ca3f66ce21177/nodehandlers/soccer/stats?";

	@Autowired
	private ApplicationContext context;

	@Bean(name = "SearchServiceClient")
	public SearchService getSearchService() {
		RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
		rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/SearchService");
		rmiProxyFactoryBean.setServiceInterface(SearchService.class);
		rmiProxyFactoryBean.afterPropertiesSet();
		return (SearchService) rmiProxyFactoryBean.getObject();
	}

	@RequestMapping(value = "/matches", method = RequestMethod.GET)
	public String getMatches(Model model) {
		model.addAttribute("date", LocalDate.now().toString());
		model.addAttribute("panel", "match");
		return "/matches";
	}

	@RequestMapping(value = "/matches/search-by-date", method = RequestMethod.GET)
	public String getMatchesByDate(Model model, HttpServletRequest request) throws IOException {

		/** Get Params */
		String date = request.getParameter("date");
		String competitionID = request.getParameter("competition");

		if (date != null && competitionID != null) {
			/** Prepare request params */
			HashMap<String, String> requestParams = new HashMap<>();
			/** result */
			JsonArray matches = new JsonArray();

			/** for each match day get the all fixtures */
			requestParams.put("type", "fixtures");
			requestParams.put("id", competitionID);
			String fixtures = makeRequestToApi(URL, requestParams);
			requestParams.clear();
			JsonObject jsonFixtures = new Gson().fromJson(fixtures, JsonObject.class);

			JsonArray fixturesArray = new JsonArray();
			fixturesArray.addAll(jsonFixtures.get("fixtures").getAsJsonArray());

			/** for each fixture check if date == date */
			for (int j = 0; j < fixturesArray.size(); j++) {
				String fixtureDate = fixturesArray.get(j).getAsJsonObject().get("date").getAsString();
				if (fixtureDate.contains(date)) {
					matches.add(fixturesArray.get(j));
				}
			}

			model.addAttribute("matches", matches);
			model.addAttribute("date", LocalDate.now().toString());
			model.addAttribute("panel", "match");
		}

		return "/matches";
	}

	@RequestMapping(value = "/matches/search-by-team", method = RequestMethod.GET)
	public String searchTeam(@RequestParam(name = "name", required = false) String teamName, Model model)
			throws IOException {
		SearchService searchService = (SearchService) context.getBean("SearchServiceClient");
		if (teamName == null || teamName.trim().equals("")) {
			return "redirect:/matches";
		} else {
			List<Fixture> teamFixtures = searchService.getFootballerByName(teamName);
			model.addAttribute("fixtures", teamFixtures);
			model.addAttribute("panel", "fixture");
			model.addAttribute("date", LocalDate.now().toString());
			return "/matches";
		}
	}

	@RequestMapping(value = "/fixtures/{id}")
	public String getFixtures(@PathVariable("id") String id, HttpSession session)
			throws JsonSyntaxException, IOException {
		Gson gson = new Gson();

		HashMap<String, String> requestParams = new HashMap<>();
		requestParams.put("id", id);
		requestParams.put("type", "fixtures");

		JsonObject response = gson.fromJson(makeRequestToApi(URL, requestParams), JsonElement.class).getAsJsonObject();
		System.out.println("Response IS");
		JsonArray jsonArray = new JsonArray();
		jsonArray.addAll(response.get("fixtures").getAsJsonArray());
		List<Fixture> list = new ArrayList<>();
		for (JsonElement obj : jsonArray) {
			System.out.println("JSONELEMENT:" + obj);
			Fixture fixture = new Fixture();
			fixture.setHomeTeamName(obj.getAsJsonObject().get("homeTeamName").getAsString());
			fixture.setAwayTeamName(obj.getAsJsonObject().get("awayTeamName").getAsString());
			fixture.setDate(obj.getAsJsonObject().get("date").getAsString());
			if (!obj.getAsJsonObject().get("result").getAsJsonObject().get("goalsHomeTeam").isJsonNull()
					&& !obj.getAsJsonObject().get("result").getAsJsonObject().get("goalsAwayTeam").isJsonNull()) {
				fixture.setResultGoalsHomeTeam(
						obj.getAsJsonObject().get("result").getAsJsonObject().get("goalsHomeTeam").getAsInt());
				fixture.setResultGoalsAwayTeam(
						obj.getAsJsonObject().get("result").getAsJsonObject().get("goalsAwayTeam").getAsInt());
			} else {
				fixture.setResultGoalsHomeTeam(0);
				fixture.setResultGoalsAwayTeam(0);
			}
			fixture.setMatchday(obj.getAsJsonObject().get("matchday").getAsInt());
			fixture.setStatus(obj.getAsJsonObject().get("status").getAsString());
			list.add(fixture);
		}
		Collections.sort(list, (o1, o2) -> o1.getMatchday().compareTo(o2.getMatchday()));

		JsonElement jsonElement = gson.toJsonTree(list, new TypeToken<List<Fixture>>() {
		}.getType());

		jsonArray = jsonElement.getAsJsonArray();
		session.setAttribute("fixtures", jsonArray);

		return "/fixtures";
	}
}
