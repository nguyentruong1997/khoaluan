package com.migi.service;

import java.util.List;

import com.migi.dao.AuthenticateDao;
import com.migi.model.CartItemDTO;
import com.migi.model.ClassifyDTO;
import com.migi.model.EmployeeDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.InvoicesDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;

public interface ProductService {
	public ProductDTO getProductDetail(ProductDTO obj);
	public List<ClassifyDTO> getProductClassify(ClassifyDTO obj);
	public ClassifyDTO getProductClassifyNumber(ClassifyDTO obj);
	public ObjectString verifyCart(List<InvoicesDTO> list);
	public ObjectString saveCart(List<CartItemDTO> list);
	public ObjectString deleteCart(CartItemDTO  list);
	public List<CartItemDTO> getCartList(CartItemDTO  list);
}
