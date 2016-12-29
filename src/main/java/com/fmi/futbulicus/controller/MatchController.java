package com.fmi.futbulicus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmi.futbulicus.model.Match;
import com.fmi.futbulicus.repository.MatchRepository;

@Controller(value = "/matches")
public class MatchController {
	
	@Autowired
	private MatchRepository matchRepository;
	
	@RequestMapping
	public String getMatch(Model model) {
		Match matches = matchRepository.findOne(1L);
		System.out.println(matches);
		model.addAttribute("matches", matches);
		return "matches";
	}
}
