package com.migi.dao;

import java.util.List;

import com.migi.entity.Employee;
import com.migi.entity.User;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;

public interface AdminImportInvoiceDao {
	public String addImportInvoiceProduct();
	public String addImportInvoiceDetails(ProductDTO emp);
	public String updatePriceImportInvoice(EnterCouponDTO emp);
}
