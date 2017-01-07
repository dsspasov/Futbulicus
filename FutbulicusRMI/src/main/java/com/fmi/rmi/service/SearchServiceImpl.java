package com.fmi.rmi.service;

import static com.fmi.rmi.utils.ApiUtils.makeRequestToApi;







import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import com.fmi.futbulicus.model.Fixture;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SearchServiceImpl implements SearchService {

	private static final String URL = "https://euadmin4.backstage.spotme.com/api/v1/eid/cbe9ff2c721f63e6347ca3f66ce21177/nodehandlers/soccer/stats?";

	@Override
	public List<Fixture> getFootballerByName(String name) throws IOException {
		
		HashMap<String, String> requestParams = new HashMap<>();

		
		//id: 436, 438, 434, 426, 430
		List<String> competitionsID = new LinkedList<String>();
		competitionsID.add("436");
		competitionsID.add("438");
		competitionsID.add("434");
		competitionsID.add("426");
		competitionsID.add("430");
		requestParams.put("type", "fixtures");
		JsonArray fixturesArray = new JsonArray();
		for(String id : competitionsID) {
			requestParams.put("id", id); 
			String fixtures = makeRequestToApi(URL, requestParams);
			JsonObject jsonFixtures = new Gson().fromJson(fixtures, JsonObject.class);
			
			fixturesArray.addAll(jsonFixtures.get("fixtures").getAsJsonArray());
		}
		
		List<Fixture> fixtures = new LinkedList<Fixture>();
		
		for(int j = 0; j<fixturesArray.size(); j++) {
			String homeTeamName = fixturesArray.get(j).getAsJsonObject().get("homeTeamName").getAsString();
			String awayTeamName = fixturesArray.get(j).getAsJsonObject().get("awayTeamName").getAsString();
			String status = fixturesArray.get(j).getAsJsonObject().get("status").getAsString();
			
			String patternString = "(?i:.*"+ name +".*)";

			
			if(( Pattern.matches(patternString, homeTeamName) || Pattern.matches(patternString, awayTeamName)) && status.equalsIgnoreCase("FINISHED")) {
				Fixture fixture = new Fixture();
				fixture.setDate(fixturesArray.get(j).getAsJsonObject().get("date").getAsString());
				fixture.setMatchday(fixturesArray.get(j).getAsJsonObject().get("matchday").getAsInt());
				fixture.setHomeTeamName(homeTeamName);
				fixture.setAwayTeamName(awayTeamName);
				fixture.setStatus(status);
				fixture.setResultGoalsHomeTeam(fixturesArray.get(j).getAsJsonObject()
						.get("result").getAsJsonObject().get("goalsHomeTeam").getAsInt());
				fixture.setResultGoalsAwayTeam(fixturesArray.get(j).getAsJsonObject()
						.get("result").getAsJsonObject().get("goalsAwayTeam").getAsInt());
				
				fixtures.add(fixture);
			}
		}
		
		return fixtures;
	}

}
