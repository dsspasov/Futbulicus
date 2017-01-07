package com.fmi.rmi.dao;

import java.util.List;

import com.fmi.futbulicus.model.UserDTO;


public interface ISearchDAO {
	
	static ISearchDAO getInstance() {
		return SearchDAO.getInstance();
	}
	
	List<UserDTO> findByUsernameContainingOrderByUsername(String searchUsername);

	List<UserDTO> findAll();
}
