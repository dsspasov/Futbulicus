package com.fmi.futbulicus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fmi.futbulicus.model.Match;
import com.fmi.futbulicus.repository.MatchRepository;

@Controller
@RequestMapping("/matches")
public class MatchController {
	
	@Autowired
	private MatchRepository matchRepository;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMatches(Model model) {
		Iterable<Match> matches = matchRepository.findAll();
		model.addAttribute("matches", matches);
		return "matches";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getMatch(@PathVariable("id") Long id, Model model) {
		Match match = matchRepository.findOne(id);
		model.addAttribute("match", match);
		return "matches";
	}
}
