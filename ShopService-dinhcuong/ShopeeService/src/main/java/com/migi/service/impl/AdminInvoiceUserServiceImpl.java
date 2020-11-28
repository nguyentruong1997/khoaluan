package com.migi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.AdminImportInvoiceDao;
import com.migi.dao.AdminInvoiceUserDao;
import com.migi.dao.AdminProductDao;
import com.migi.dao.AdminTypeDao;
import com.migi.dao.AuthenticateDao;
import com.migi.entity.User;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.InvoicesDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductTypeDTO;
import com.migi.model.UserDTO;
import com.migi.service.AdminInvoiceUserService;
import com.migi.service.AdminProductService;
import com.migi.service.AuthenticateService;

@Service
@Transactional
public class AdminInvoiceUserServiceImpl implements AdminInvoiceUserService {

	@Autowired
	private AdminProductDao adminProductDao;
	@Autowired
	private AdminInvoiceUserDao adminInvoiceUserDao;
	@Autowired
	private AuthenticateDao authenticateDao;
	
	@Override
	public ObjectString finishInvoice(InvoicesDTO invoice) {
		ObjectString rs = new ObjectString();
		if(invoice!=null ) {
			UserDTO user = new UserDTO();
			user.setUserID(invoice.getUserID()); // get UserId de authorize user
			rs.setCodeValue("fail", "Xác nhận giao hàng thất bại!");
			if (authenticateDao.checkRole(user, "admin") ) { //user acc or guest
				List<InvoicesDTO> list = adminInvoiceUserDao.getDetailOrderById(invoice);
				if(list!=null && list.size()>0) { //check list null && size 
					for(InvoicesDTO obj : list) {
						ClassifyDTO classifyItem = adminProductDao.getProductClassifyItem(obj.getClassify().getId(),obj.getMaSanPham());
						if(classifyItem.getNumber() - obj.getSoLuong() >=0 ) {
							classifyItem.setNumber(classifyItem.getNumber()-obj.getSoLuong());
							String rs1 = adminProductDao.updateClassifiedProduct(classifyItem);  //update classify table
							if("success".equals(rs1) ) {
								ClassifyDTO emp = new ClassifyDTO(); 
								emp.setProductCode(obj.getMaSanPham());
								List<ClassifyDTO> lstClass = adminProductDao.getProductClassify(emp); //get all classify by productId in DB
								int soluongton = 0;
								for(ClassifyDTO dto : lstClass) {
									soluongton += dto.getNumber();
								}
								adminProductDao.updateProductNumber(obj.getMaSanPham(),soluongton);
							}
						}
						else {
							rs.setCodeValue("fail", "Order có sản phẩm vượt quá số lượng sẵn có!");
							return rs;
						}
					}
					//cap nhap soluongton
					
					String rs3 = adminInvoiceUserDao.changeStatusInvoice(invoice,1); //chuyen trang thai da giao hang- don hang da hoan thanh
					if("success".equals(rs3)) 
						rs.setCodeValue("success", "Xác nhận giao hàng thành công!");
				}
			}
			 
		}
		return rs;
	}

	@Override
	public List<InvoicesDTO> getListOrderAdminFilter(InvoicesDTO item) {
		UserDTO user = new UserDTO();
		user.setUserID(item.getUserId()); // get UserId de authorize admin
//		ObjectString rs = new ObjectString();
//		rs.setCodeValue("success", "Nhập sản phẩm thành công!");
		if (authenticateDao.checkRole(user, "admin")) {
			return adminInvoiceUserDao.getListOrderAdminFilter(item);
		}
		return null;
	}
	@Override
	public ObjectString discardInvoice(InvoicesDTO item) {
		ObjectString rs = new ObjectString();
		String rs2 = adminInvoiceUserDao.changeStatusInvoice(item,0); //chuyen trang thai da giao hang- don hang da hoan thanh
		if("success".equals(rs2)) 
			rs.setCodeValue("success", "Hủy đơn hàng thành công!");
		return rs;
	}

	@Override
	public List<InvoicesDTO> getDetailOrderById(InvoicesDTO item) {
		UserDTO user = new UserDTO();
		user.setUserID(item.getUserId()); // get UserId de authorize admin
		if (authenticateDao.checkRole(user, "admin")) {
			return adminInvoiceUserDao.getDetailOrderById(item);
		}
		return null;
	}

 
	 

}
