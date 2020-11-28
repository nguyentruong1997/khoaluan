package com.migi.service;

import java.util.List;

import com.migi.dao.AuthenticateDao;
import com.migi.model.EmployeeDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductTypeDTO;
import com.migi.model.UserDTO;

public interface AdminTypeService {
	public ObjectString addProductType(ProductTypeDTO typeObj);
	public ObjectString updateProductType(ProductTypeDTO typeObj);
	public List<ProductTypeDTO>  getProductTypeList();
	public List<ProductTypeDTO>  getProductTypeListBySearch(ProductTypeDTO typeObj);
	public ObjectString deleteProductType(ProductTypeDTO typeObj);
}
