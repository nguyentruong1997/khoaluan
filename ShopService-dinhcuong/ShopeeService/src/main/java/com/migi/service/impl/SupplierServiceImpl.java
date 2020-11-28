package com.migi.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.AdminProductDao;
import com.migi.dao.AuthenticateDao;
import com.migi.dao.ProductDao;
import com.migi.dao.SupplierDao;
import com.migi.dao.UserDao;
import com.migi.entity.Product;
import com.migi.entity.User;
import com.migi.model.InvoicesDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.SupplierDTO;
import com.migi.model.UserDTO;
import com.migi.service.SupplierService;
import com.migi.service.UserService;
import com.migi.utils.DBconnection;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private AuthenticateDao authenticateDao;
	 
	
	@Override
	public List<SupplierDTO> getListSupplier(SupplierDTO obj) {
		return supplierDao.getListSupplier(obj);
	}

	@Override
	public ObjectString addSupplier(SupplierDTO obj) {
		UserDTO user = new UserDTO();
		user.setUserID(obj.getUserId());
		ObjectString rs = new ObjectString();
		if(authenticateDao.checkRole(user, "admin")) {
			//if(!adminTypeDao.checkProductTypeExists(typeObj)) {
				String rs1 = supplierDao.addSupplier(obj);  
				if("success".equals(rs1)  ) {   //&& supplierDao.checkSupplierExists(obj)
					System.out.println("Nhập hãng sản phẩm thành công!"); 
					rs.setCodeValue("success","Nhập hãng sản phẩm thành công!");
				}
				else {
					rs.setCodeValue("fail","Nhập hãng sản phẩm thất bại!");
				}
		}
		return rs;// TODO Auto-generated method stub
		 
	}

	@Override
	public ObjectString removeSupplier(SupplierDTO obj) {
		UserDTO user = new UserDTO();
		user.setUserID(obj.getUserId());
		ObjectString rs = new ObjectString();
		if(authenticateDao.checkRole(user, "admin")) {  // check đã có quyền
			if(supplierDao.checkSupplierExists(obj)) {  //check đã tồn tại hang sx
				if (!supplierDao.checkProductSupplierExisted(obj)) {  //check ton tai product dc gan cho hangsx?
					supplierDao.removeSupplier(obj);
					rs.setCodeValue("success", "Xóa hãng sản phẩm thành công ");;
				}
				else {
					rs.setCodeValue("fail", "Không thể xóa Hãng sản phẩm đang được gắn cho sản phẩm ");
				}
			}
			else {
				rs.setCodeValue("fail", "Hãng sản phẩm chưa tồn tại");
			}
		}
		return rs;
	}

	@Override
	public ObjectString updateSupplier(SupplierDTO obj) {
		UserDTO user = new UserDTO();
		user.setUserID(obj.getUserId());
		ObjectString rs = new ObjectString();
		if(authenticateDao.checkRole(user, "admin")) {  // co quyen admin
			if(supplierDao.checkSupplierExists(obj)) { //co ton tai
				String rs1 = supplierDao.updateSupplier(obj);
				if("success".equals(rs1)  ) { //&& supplierDao.checkSupplierExists(obj)
					System.out.println("Update hãng sản phẩm thành công!"); 
					rs.setCodeValue("success","Update hãng sản phẩm thành công!");
				}
				else {
					rs.setCodeValue("fail","Update hãng sản phẩm thất bại!");
				}
			}
			else {
				rs.setCodeValue("fail", "Hãng sản phẩm chưa tồn tại");
			}
		}
		return rs;
	}
 
}
