package com.migi.dao;

import java.util.List;

import com.migi.entity.Employee;
import com.migi.entity.User;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.InvoicesDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;

public interface AdminInvoiceUserDao {
	public String addImportInvoiceProduct();
	public String addImportInvoiceDetails(ProductDTO emp);
	public String updatePriceImportInvoice(EnterCouponDTO emp);
	public String UpdateNumberProduct(InvoicesDTO obj);
	public String changeStatusInvoice(InvoicesDTO obj,int statusInvoice);
	public List<InvoicesDTO> getListOrderAdminFilter(InvoicesDTO item); 
	public List<InvoicesDTO> getDetailOrderById(InvoicesDTO item);
}
