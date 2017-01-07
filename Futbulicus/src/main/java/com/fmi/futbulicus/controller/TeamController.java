package com.fmi.futbulicus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fmi.futbulicus.model.Team;
import com.fmi.futbulicus.repository.TeamRepository;

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
	
	@RequestMapping(value = "/teams/team/{id}", method = RequestMethod.GET)
	public String getTeam(@PathVariable("id") Long id, Model model) {
		Team team = teamRepository.findOne(id);
		model.addAttribute("team", team);
		return "/team";
	}
	
	
}
