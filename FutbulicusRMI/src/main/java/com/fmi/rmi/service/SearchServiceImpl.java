package com.fmi.rmi.service;

import java.util.LinkedList;
import java.util.List;

import com.fmi.futbulicus.model.UserDTO;
import com.fmi.rmi.dao.ISearchDAO;

public class SearchServiceImpl implements SearchService {

	@Override
	public void search() {
		System.out.println("Hello Futbulicus RMI!");
	}

	@Override
	public List<UserDTO> search(String username) {
		
		List<UserDTO> users;
		if(username == null) {
			users = ISearchDAO.getInstance().findAll();
		} else {
			users = ISearchDAO.getInstance().findByUsernameContainingOrderByUsername(username);
		}
		return users;
	}

	@Override
	public List<UserDTO> getUsers() {
		List<UserDTO> users = new LinkedList<UserDTO>();
		UserDTO user = new UserDTO();
		user.setUsername("Mite");
		users.add(user);
		return users;
	}

}
