package com.fmi.futbulicus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fmi.futbulicus.model.User;
import com.fmi.futbulicus.repository.UserRepository;

@Controller
public class HelloController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String helloWorld(Model model){		
		User user = userRepository.findByName("Chicho simo");
		System.out.println(user.getPassword());
		model.addAttribute("user", user);
		return "hello";
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(Model model){
		User user = new User();
		user.setName("Chicho simo");
		user.setPassword("ne sam gejo");
		userRepository.save(user);
		return "/home";
	}
}
