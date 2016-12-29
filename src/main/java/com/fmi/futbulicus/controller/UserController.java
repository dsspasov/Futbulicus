package com.fmi.futbulicus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsManager;
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginGet(HttpServletRequest request, HttpSession session, Model model){
		if(session.getAttribute("user") != null) {
			return "home";
		} else {
			return "login";
		}
	}
	
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerGet(Authentication auth, HttpSession session){
		if(session.getAttribute("user") != null) {
			return "home";
		} else {
			return "register";
		}
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerPost(HttpSession session, @RequestParam("username") String name, @RequestParam("password") String password){
		User user = userRepository.findByUsername(name);
		if(user != null) {
			UserDetails userDetails = userDetailsManager.loadUserByUsername(user.getUsername());
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
					userDetails.getAuthorities());
	        authenticationManager.authenticate(auth);
			return "home";
		} else {
			String hashedPassword = passwordEncoder.encode(password);
			User newUser = new User();
			newUser.setUsername(name);
			newUser.setPassword(hashedPassword);
			newUser.setRole("USER");
			newUser = userRepository.save(newUser);
	
			UserDetails userDetails = userDetailsManager.loadUserByUsername(name);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
					userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);

			session.setAttribute("user", newUser);
			return "home";
		}
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(HttpSession session){
		if(session.getAttribute("user") == null) {
			User user = getCurrentUser();
			session.setAttribute("user", user);
		}
		return "home";
	}
	
	
	public User getCurrentUser() {
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String username = springUser.getUsername();
		User user = userRepository.findByUsername(username);
		return user;
	}
}
