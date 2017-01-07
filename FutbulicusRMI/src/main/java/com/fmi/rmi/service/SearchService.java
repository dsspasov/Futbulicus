package com.fmi.rmi.service;

import java.util.List;

import com.fmi.futbulicus.model.UserDTO;

public interface SearchService {

	List<UserDTO> search(String username);
	
	List<UserDTO> getUsers();
	
	void search();
}
