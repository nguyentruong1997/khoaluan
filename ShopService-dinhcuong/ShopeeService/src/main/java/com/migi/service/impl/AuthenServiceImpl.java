package com.migi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.AuthenticateDao;
import com.migi.dao.EmployeeDao;
import com.migi.entity.Employee;
import com.migi.entity.User;
import com.migi.model.EmployeeDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;
import com.migi.service.AuthenticateService;
import com.migi.service.EmployeeService;

@Service
@Transactional
public class AuthenServiceImpl implements AuthenticateService {
	@Autowired
	AuthenticateDao authenticateDao;

	@Override
	public UserDTO login(UserDTO userDto) {
//		// TODO Auto-generated method stub
//		User user = new User();
//		user.setUserName(userDto.getUserName());
//		user.setPassWord(userDto.getPassWord());
		UserDTO userdto = authenticateDao.authenticate(userDto);
		return userdto;
	}

	@Override
	public boolean checkRole(UserDTO emp, String role) {
//		User user = new User();
//		user.setUserID(emp.getUserID());
		boolean check = authenticateDao.checkRole(emp, role);
		return check;
	}

	@Override
	public ObjectString register(UserDTO user) {
		ObjectString rs = new ObjectString();
		if (!authenticateDao.checkDuplicateUser(user)) { // check đã tồn tại
			String kq = authenticateDao.register(user);
			if("success".equals(kq)) {
				rs.setCodeValue("success", "Đăng ký thành công");
			}
			else {
				rs.setCodeValue("fail", "Đăng ký thất bại");
			}
		} else {
			rs.setCodeValue("fail", "Username hoặc Email đã bị trùng. Vui lòng nhập lại ");
		}

		return rs;
	}

}
