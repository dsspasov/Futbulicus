package com.fmi.futbulicus.service;

import java.util.List;

import com.fmi.futbulicus.model.User;

public interface SearchService {
	
	void search();
	
	List<User> getUsers();
	
	List<User> search(String username);
}
