package com.migi.dao;

import com.migi.model.CustomerDTO;
import com.migi.model.UserDTO;

public interface UserDao {
	public UserDTO getUserInfo(UserDTO user);
	public String createNewCustomer(UserDTO user);
	public boolean checkCustomerExist(String email);
	public UserDTO getCustomerByEmail(String email);
}
