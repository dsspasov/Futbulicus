package com.fmi.rmi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.fmi.futbulicus.model.UserDTO;
import com.fmi.rmi.config.DBManager;

public class SearchDAO implements ISearchDAO{

	private static SearchDAO instance = null;

	private SearchDAO() {

	}

	public static synchronized SearchDAO getInstance() {
		if (instance == null) {
			instance = new SearchDAO();
		}
		return instance;
	}
	
	
	@Override
	public List<UserDTO> findByUsernameContainingOrderByUsername(String searchUsername) {
		String sql = "SELECT * FROM users AS u WHERE u.username LIKE ? ORDER BY u.username";
		List<UserDTO> users = new LinkedList<UserDTO>();
		try (PreparedStatement pr = DBManager.getDBManager().getConnection().prepareStatement(sql)) {
			pr.setString(1, "%"+searchUsername+"%");
			try (ResultSet rs = pr.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String role = rs.getString("role");
					String password = rs.getString("password");
					
					UserDTO user = new UserDTO();
					user.setId(id);
					user.setUsername(username);
					user.setPassword(password);
					user.setRole(role);
					
					users.add(user);
				}
			}			
		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<UserDTO> findAll() {
		String sql = "SELECT * FROM users AS u ORDER BY u.username";
		List<UserDTO> users = new LinkedList<UserDTO>();
		try (PreparedStatement pr = DBManager.getDBManager().getConnection().prepareStatement(sql)) {
			try (ResultSet rs = pr.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String role = rs.getString("role");
					String password = rs.getString("password");
					
					UserDTO user = new UserDTO();
					user.setId(id);
					user.setUsername(username);
					user.setPassword(password);
					user.setRole(role);
					
					users.add(user);
				}
			}			
		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return users;
	}

}
