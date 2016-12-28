package com.fmi.futbulicus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fmi.futbulicus.model.User;
import com.fmi.futbulicus.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginGet(HttpServletRequest request, HttpSession session, Model model){
		if(session.getAttribute("user") != null) {
			return "home";
		} else {
			return "/login";
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginPost(HttpSession session, @RequestParam("name") String name, @RequestParam("password") String password){
		String hashedPassword = DigestUtils.md5Hex(password);
		User user = userRepository.findByNameAndPassword(name, hashedPassword);
		if(user != null) {
			session.setAttribute("user", user);
			return "redirect:/home";
		} else {
			return "login";
		}
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerGet(HttpSession session){
		if(session.getAttribute("user") != null) {
			return "home";
		} else {
			return "/register";
		}
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerPost(HttpSession session, @RequestParam("name") String name, @RequestParam("password") String password){
		User user = userRepository.findByName(name);
		if(user != null) {
			return "register";
		} else {
			String hashedPassword = DigestUtils.md5Hex(password);
			User newUser = new User();
			newUser.setName(name);
			newUser.setPassword(hashedPassword);
			newUser = userRepository.save(newUser);
			session.setAttribute("user", newUser);
			return "redirect:/home";
		}
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(HttpSession session){
		if(session.getAttribute("user") != null) {
			return "home";
		} else {
			return "login";
		}
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:login";
	}
}
