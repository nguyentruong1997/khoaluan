package com.migi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.AdminImportInvoiceDao;
import com.migi.dao.AdminProductDao;
import com.migi.dao.AdminTypeDao;
import com.migi.dao.AuthenticateDao;
import com.migi.entity.User;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.ProductTypeDTO;
import com.migi.model.UserDTO;
import com.migi.service.AdminProductService;
import com.migi.service.AdminTypeService;
import com.migi.service.AuthenticateService;

@Service
@Transactional
public class AdminTypeServiceImpl implements AdminTypeService {
	@Autowired
	private AdminTypeDao adminTypeDao;
	@Autowired
	private AuthenticateDao authenticateDao;
	@Autowired
	private AdminProductDao adminProductDao;
	@Override
	public ObjectString addProductType(ProductTypeDTO typeObj) {
		 
		UserDTO user = new UserDTO();
		user.setUserID(typeObj.getUserId());
		ObjectString rs = new ObjectString();
		if(authenticateDao.checkRole(user, "admin")) {
			//if(!adminTypeDao.checkProductTypeExists(typeObj)) {
				String rs1 = adminTypeDao.addProductType(typeObj);
				if("success".equals(rs1) ) {  // && adminTypeDao.checkProductTypeExists(typeObj)
					System.out.println("Nhập danh mục sản phẩm thành công!"); 
					rs.setCodeValue("success","Nhập danh mục sản phẩm thành công!");
				}
				else {
					rs.setCodeValue("fail","Nhập danh mục sản phẩm thất bại!");
				}
//			}
//			else {
//				rs.setCodeValue("fail", "Danh mục sản phẩm đã tồn tại");
//			}
		}
		return rs;
	}

	@Override
	public List<ProductTypeDTO> getProductTypeList() {
//		User user = new User();
//		user.setUserID(typeObj.getUserId());
		List<ProductTypeDTO> list = adminTypeDao.getListProductType();
//		if(authenticateDao.checkRole(user, "admin")) {
//			 
//		}
	    
		
		return list;
	}

	@Override
	public List<ProductTypeDTO> getProductTypeListBySearch(ProductTypeDTO typeObj) {
		return adminTypeDao.getListProductTypeBySearch(typeObj);
	}

	@Override
	public ObjectString updateProductType(ProductTypeDTO typeObj) {
		UserDTO user = new UserDTO();
		user.setUserID(typeObj.getUserId());
		ObjectString rs = new ObjectString();
		if(authenticateDao.checkRole(user, "admin")) {  // co quyen admin
			if(adminTypeDao.checkProductTypeExists(typeObj)) { //co ton tai
				String rs1 = adminTypeDao.updateProductType(typeObj);
				if("success".equals(rs1) ) { //&& adminTypeDao.checkProductTypeExists(typeObj)
					System.out.println("Update danh mục sản phẩm thành công!"); 
					rs.setCodeValue("success","Update danh mục sản phẩm thành công!");
				}
				else {
					rs.setCodeValue("fail","Update danh mục sản phẩm thất bại!");
				}
			}
			else {
				rs.setCodeValue("fail", "Danh mục sản phẩm chưa tồn tại");
			}
		}
		return rs;
	}

	@Override
	public ObjectString deleteProductType(ProductTypeDTO typeObj) {
		UserDTO user = new UserDTO();
		user.setUserID(typeObj.getUserId());
		ObjectString rs = new ObjectString();
		ProductDTO proDto = new ProductDTO();
		if(authenticateDao.checkRole(user, "admin")) {  // check đã có quyền
			if(adminTypeDao.checkProductTypeExists(typeObj)) {  //check đã tồn tại
				proDto.setTypeId(typeObj.getTypeId());
				if (!adminProductDao.checkProductTypeItemExisted(proDto)) {  //check ton tai product dc gan cho type?
					adminTypeDao.removeProductType(typeObj);
					rs.setCodeValue("success", "Xóa danh mục thành công ");;
				}
				else {
					rs.setCodeValue("fail", "Không thể xóa danh mục đang được gắn cho sản phẩm ");
				}
			}
			else {
				rs.setCodeValue("fail", "Danh mục sản phẩm chưa tồn tại");
			}
		}
		
		return rs;
	}

}
