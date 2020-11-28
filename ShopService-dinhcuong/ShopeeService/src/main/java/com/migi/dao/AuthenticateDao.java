package com.migi.dao;

import com.migi.entity.User;
import com.migi.model.UserDTO;

 
public interface AuthenticateDao {
	public UserDTO authenticate(UserDTO emp);
	public boolean checkRole(UserDTO emp,String role);
	public boolean checkDuplicateUser(UserDTO user);
	public String register(UserDTO user);
	public UserDTO getUserById(int userId);
}
