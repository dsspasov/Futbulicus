package com.fmi.futbulicus.controller;

import static com.fmi.futbulicus.utils.ApiUtils.makeRequestToApi;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

@Controller
public class UserController {
	
	private static final String STANDINGS_URL = "https://euadmin4.backstage.spotme.com/api/v1/eid/cbe9ff2c721f63e6347ca3f66ce21177/nodehandlers/soccer/stats?";
	
	@RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
	public String getIndex(HttpServletRequest request, HttpSession session, Model model){
		return "index";
	}
	
	
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(HttpSession session) throws IOException{
		return "home";
	}
	
	@RequestMapping(value="/standings/{id}")
	public String getStandings(@PathVariable("id") String id, HttpSession session) throws JsonSyntaxException, IOException {
		
		Gson gson = new Gson();
		
		HashMap<String, String> requestParams = new HashMap<>();
		requestParams.put("type", "table");
		requestParams.put("id", id);
		
		JsonObject response = gson.fromJson(makeRequestToApi(STANDINGS_URL, requestParams), JsonElement.class).getAsJsonObject();
		System.out.println("RESPONSE IS " + response);
		JsonArray jsonArray = new JsonArray();
		jsonArray.addAll(response.get("standing").getAsJsonArray());
		System.out.println("STANDINGS ARE " + jsonArray);
		session.setAttribute("leagueCaption", response.get("leagueCaption").getAsString());
		session.setAttribute("leagueId", id);
		session.setAttribute("teams", jsonArray);
		return "/standing";
	}
	

	
	
}
