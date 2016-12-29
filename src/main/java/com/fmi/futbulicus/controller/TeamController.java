package com.fmi.futbulicus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmi.futbulicus.model.Team;
import com.fmi.futbulicus.repository.TeamRepository;

@Controller
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;
	
	@RequestMapping(value = "/teams")
	public String getTeam(Model model) {
		Team team = teamRepository.findOne(1L);
		model.addAttribute("team", team);
		return "teams";
	}
}
