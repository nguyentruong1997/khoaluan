package com.migi.dao;

import java.util.List;

import com.migi.entity.Employee;
import com.migi.entity.User;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;

public interface AdminProductDao {
	public String addProductInfo(ProductDTO emp);
	public String addClassifiedProduct(ProductDTO enterCoupon);
	public boolean checkProductExisted(ProductDTO emp);
	public boolean checkProductTypeItemExisted(ProductDTO emp);
	public List<ProductDTO> getProductList(ProductDTO emp);
	public List<ClassifyDTO> getProductClassify(ClassifyDTO emp);
	public String updateProductInfo(ProductDTO obj);
	public String updateClassifiedProduct(ClassifyDTO obj);
	public ClassifyDTO getProductClassifyItem(int maphanloai,String masp);
	public String updateProductNumber(String masp, int soluongton);
}
