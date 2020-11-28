package com.migi.dao;

import java.util.Date;
import java.util.List;

import com.migi.entity.Employee;
import com.migi.entity.User;
import com.migi.model.CartItemDTO;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.InvoicesDTO;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;

public interface ProductDao {
	public ProductDTO getProductDetail(ProductDTO obj);
	public List<ClassifyDTO> getProductClassify(ClassifyDTO obj);
	public ClassifyDTO getProductClassifyNumber(ClassifyDTO obj);
	public String createInvoice(int customerId,Date date,float sumPrice);
	public InvoicesDTO getInvoices(int customerId,String code);
	public String insertInvoiceDetail(InvoicesDTO invoice);
	public List<InvoicesDTO> getListOrderWaiting(UserDTO user); 
	public List<InvoicesDTO> getListOrderWaitingCustomer(UserDTO user);
	public String deleteCart(int userID);
	public String insertCart(List<CartItemDTO>  cart);
	public List<CartItemDTO> getCartList(CartItemDTO obj);
}
