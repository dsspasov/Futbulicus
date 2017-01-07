package com.fmi.futbulicus.service;

import java.util.List;

import com.fmi.futbulicus.model.UserDTO;

public interface SearchService {
	
	void search();
	
	List<UserDTO> getUsers();
	
	List<UserDTO> search(String username);
}
