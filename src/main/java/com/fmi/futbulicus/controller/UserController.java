package com.fmi.futbulicus.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fmi.futbulicus.model.User;
import com.fmi.futbulicus.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
	
	private static final String STANDINGS_URL = "https://euadmin4.backstage.spotme.com/api/v1/eid/cbe9ff2c721f63e6347ca3f66ce21177/nodehandlers/soccer/stats?type=table&id=426";
	private final String USER_AGENT = "Mozilla/5.0";
	
	@RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
	public String getIndex(HttpServletRequest request, HttpSession session, Model model){
		return "index";
	}
	
	
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
	public String home(HttpSession session) throws IOException{
		if(session.getAttribute("user") == null) {
			User user = getCurrentUser();
			session.setAttribute("user", user);
		}
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		URL obj = new URL(STANDINGS_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + STANDINGS_URL);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		JsonElement jsonElement = gson.fromJson(response.toString(), JsonElement.class);
		jsonObject = jsonElement.getAsJsonObject();
		JsonArray jsonArray = new JsonArray();
		jsonArray.addAll(jsonObject.get("standing").getAsJsonArray());
		System.out.println("JSONARRAAY IS " + jsonArray);
		session.setAttribute("teams", jsonArray);
		return "home";
	}
	
	
	public User getCurrentUser() {
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String username = springUser.getUsername();
		User user = userRepository.findByUsername(username);
		return user;
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public String getUsers(HttpServletRequest request, HttpSession session, Model model){
		List<User> users = (List<User>) userRepository.findAllByOrderByUsername();
		model.addAttribute("users", users);
		return "/users";
	}
	
	@RequestMapping(value="/users/user/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable("id") Integer id, HttpServletRequest request, HttpSession session, Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "/user";
	}
	
	@RequestMapping(value="/users/search", method = RequestMethod.GET)
	public String searchUsers(@RequestParam(name="searchName", required=false) String username, Model model){
		List<User> users;
		if(username == null) {
			users = (List<User>) userRepository.findAll();
		} else {
			users = userRepository.findByUsernameContainingOrderByUsername(username);
		}
		model.addAttribute("users", users);
		return "/users";
	}
	
	
}
