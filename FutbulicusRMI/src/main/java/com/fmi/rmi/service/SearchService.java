package com.fmi.rmi.service;

import java.io.IOException;
import java.util.List;

import com.fmi.futbulicus.model.Fixture;

public interface SearchService {

	/*List<UserDTO> search(String username);
	
	List<UserDTO> getUsers();
	
	void search();*/
	
	
	List<Fixture> getFootballerByName(String name) throws IOException;
}
