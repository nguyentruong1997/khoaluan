package com.migi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.AuthenticateDao;
import com.migi.dao.ProductDao;
import com.migi.dao.UserDao;
import com.migi.entity.Product;
import com.migi.model.InvoicesDTO;
import com.migi.model.UserDTO;
import com.migi.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	ProductDao productDao;
	
	@Override
	public UserDTO getUserInfo(UserDTO user) {
		return  userDao.getUserInfo(user);
	}

	@Override
	public List<InvoicesDTO> getListOrderWaiting(UserDTO user) {
		if(user.getUserID()!=-1) {
			return productDao.getListOrderWaiting(user);
		}
		else
			return productDao.getListOrderWaitingCustomer(user);
		
	}

}
