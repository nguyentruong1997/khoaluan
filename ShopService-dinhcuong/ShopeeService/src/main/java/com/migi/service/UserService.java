package com.migi.service;

import java.util.List;

import com.migi.model.InvoicesDTO;
import com.migi.model.UserDTO;

public interface UserService {
	public UserDTO getUserInfo(UserDTO user);
	public List<InvoicesDTO> getListOrderWaiting(UserDTO user);
}
