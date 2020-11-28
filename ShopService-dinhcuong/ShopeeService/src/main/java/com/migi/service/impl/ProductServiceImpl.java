package com.migi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.AdminImportInvoiceDao;
import com.migi.dao.AdminProductDao;
import com.migi.dao.AdminTypeDao;
import com.migi.dao.AuthenticateDao;
import com.migi.dao.ProductDao;
import com.migi.dao.UserDao;
import com.migi.entity.InvoiceDetails;
import com.migi.entity.User;
import com.migi.model.CartItemDTO;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.InvoicesDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.ProductTypeDTO;
import com.migi.model.UserDTO;
import com.migi.service.AdminProductService;
import com.migi.service.AuthenticateService;
import com.migi.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AuthenticateDao authenticateDao;
	@Override
	public ProductDTO getProductDetail(ProductDTO obj) {
		return productDao.getProductDetail(obj);
	}
	@Override
	public List<ClassifyDTO> getProductClassify(ClassifyDTO obj) {
		return productDao.getProductClassify(obj);
	}
	@Override
	public ClassifyDTO getProductClassifyNumber(ClassifyDTO obj) {
		// TODO Auto-generated method stub
		return productDao.getProductClassifyNumber(obj);
	}
	@Override
	public ObjectString verifyCart(List<InvoicesDTO> list) {
		InvoicesDTO invoice  = list.get(0);  
		ObjectString rs = new ObjectString();
		rs.setCodeValue("success","Đặt hàng thành công!");
		int customerId = 0; 
	    if(!userDao.checkCustomerExist(invoice.getUser().getEmail().trim())) {
			String rs2 = userDao.createNewCustomer(invoice.getUser());
			if(!"success".equals(rs2)) {
				rs.setCodeValue("fail", "Tạo khach hang không thành công!");
				return rs;
			}
		}
		Date date = new Date();
		StringBuilder invoiceCode = new StringBuilder("HdInvoice_"+date.toString());
		UserDTO user = userDao.getCustomerByEmail(invoice.getUser().getEmail());
		customerId = user.getCustomerId(); //get customerID
		String rs1 = productDao.createInvoice(customerId, date,invoice.getSumPrice());
		if("success".equals(rs1)) {
			InvoicesDTO newInvoice = productDao.getInvoices(customerId, invoiceCode.toString());
			for(InvoicesDTO item : list) {
				// create invoice detail for 1 cartItem
				item.setMaHoaDon(newInvoice.getMaHoaDon());
				productDao.insertInvoiceDetail(item);
				rs.setUser(user);
			}
		}
		else {
			rs.setCodeValue("fail", "Tạo hóa đơn không thành công!");
			return rs;
		}
		return rs;
	}
	@Override
	public ObjectString saveCart(List<CartItemDTO> list) {
		ObjectString rs = new ObjectString();
		if(list!=null && list.size()>0) {
			CartItemDTO cartItem = list.get(0);
			UserDTO user = new UserDTO();
			user.setUserID(cartItem.getUserID()); // get UserId de authorize user
			rs.setCodeValue("fail", "Lưu giỏ hàng thất bại!");
			if (authenticateDao.checkRole(user, "user")) {
				String rs1 = productDao.deleteCart(cartItem.getUserID());
				if("success".equals(rs1)) {
					String rs2 = productDao.insertCart(list);
					if("success".equals(rs2)) {
						rs.setCodeValue("success", "Lưu giỏ hàng thành công!");
					}
				}
				
				//for(CartItemDTO item : list) {
//					boolean check = adminProductDao.checkCartItem(cartItem.getUserID(),cartItem.getProductID(),cartItem.getClassify().getId());
//					if(check) {
//						//update soluong
//						int cartId = adminProductDao.getCartItemId(cartItem.getUserID(),cartItem.getProductID(),cartItem.getClassify().getId());
//						adminProductDao.updateCart(cartItem.getUserID(),cartItem.getProductID(),cartItem.getClassify().getId(),cartItem.getSl());
//					}
//					else { //insert
//						//adminProductDao.checkProductExisted(item)
					//productDao.insertCart(cartItem.getUserID(),cartItem.getProductID(),cartItem.getClassify().getId(),cartItem.getSl());
					//}
				//delete cartItem sl = 0
			 }
		}
		return rs;
	}
	@Override
	public ObjectString deleteCart(CartItemDTO cartItem) {
		UserDTO user = new UserDTO();
		user.setUserID(cartItem.getUserID()); // get UserId de authorize user
		ObjectString rs = new ObjectString();
		rs.setCodeValue("fail", "Xóa giỏ hàng thất bại!");
		if (authenticateDao.checkRole(user, "user")) {
			String rs1 = productDao.deleteCart(cartItem.getUserID());
			if("success".equals(rs1)) {
				rs.setCodeValue("success", "Xóa giỏ hàng thành công!");
			}
		 }
		return rs;
	}
	@Override
	public List<CartItemDTO> getCartList(CartItemDTO obj) {
		return productDao.getCartList(obj);
	}
	
	 

}
