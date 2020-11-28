package com.migi.dao;

import java.util.List;

import com.migi.entity.Employee;
import com.migi.entity.User;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.ProductTypeDTO;
import com.migi.model.UserDTO;

public interface AdminTypeDao {
	public boolean checkProductTypeExists(ProductTypeDTO emp);
	public String addProductType(ProductTypeDTO productTypeDTO);
	public String updateProductType(ProductTypeDTO productTypeDTO);
	public List<ProductTypeDTO> getListProductType();
	public List<ProductTypeDTO> getListProductTypeBySearch(ProductTypeDTO productTypeDTO);
	public String removeProductType(ProductTypeDTO productTypeDTO);
}
