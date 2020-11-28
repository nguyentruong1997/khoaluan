package com.migi.service;

import java.util.List;

import com.migi.dao.AuthenticateDao;
import com.migi.entity.User;
import com.migi.model.EmployeeDTO;
import com.migi.model.ObjectString;
import com.migi.model.UserDTO;

public interface AuthenticateService {
	public UserDTO login(UserDTO user);
	public boolean checkRole(UserDTO emp,String role);
    public ObjectString register(UserDTO user);
}
