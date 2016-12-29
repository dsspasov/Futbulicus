package com.fmi.futbulicus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmi.futbulicus.model.Footballer;
import com.fmi.futbulicus.repository.FootballerRepository;

@Controller
public class FootballerController {

	@Autowired
	private FootballerRepository footballerRepository;
	
	@RequestMapping(value = "/footballers")
	public String getFootballer(Model model) {
		Footballer footballer = footballerRepository.findOne(1L);
		model.addAttribute("footballer", footballer);
		System.out.println(footballer.getTeam());
		return "footballers";
	}
	
}