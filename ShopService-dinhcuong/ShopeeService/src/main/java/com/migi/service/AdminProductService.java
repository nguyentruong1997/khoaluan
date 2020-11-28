package com.migi.service;

import java.util.List;

import com.migi.dao.AuthenticateDao;
import com.migi.model.ClassifyDTO;
import com.migi.model.EmployeeDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;

public interface AdminProductService {
	public ObjectString importProduct(List<ProductDTO> list);
	public List<ProductDTO> getProductList(ProductDTO obj);
	public List<ClassifyDTO> getProductClassify(ClassifyDTO obj);
	public ObjectString updateProduct(ProductDTO obj);
	public ObjectString deleteProduct(ProductDTO obj);
	public ObjectString addingNumberProduct(ProductDTO obj);
	
}
