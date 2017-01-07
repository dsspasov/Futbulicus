package com.fmi.futbulicus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fmi.futbulicus.model.Footballer;
import com.fmi.futbulicus.repository.FootballerRepository;

@Controller
public class FootballerController {

	@Autowired
	private FootballerRepository footballerRepository;
	
	@RequestMapping(value = "/footballers", method = RequestMethod.GET)
	public String getFootballer(Model model) {
		Iterable<Footballer> footballers = footballerRepository.findAll();
		model.addAttribute("footballers", footballers);
		return "/footballers";
	}
	
	@RequestMapping(value = "/footballers/footballer/{id}", method = RequestMethod.GET)
	public String getFootballer(@PathVariable("id") Long id, Model model) {
		Footballer footballer = footballerRepository.findOne(id);
		model.addAttribute("footballer", footballer);
		return "/footballer";
	}
	
}