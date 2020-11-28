package com.migi.service;

import java.util.List;

import com.migi.dao.AuthenticateDao;
import com.migi.model.CartItemDTO;
import com.migi.model.EmployeeDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.InvoicesDTO;
import com.migi.model.ObjectString;
import com.migi.model.UserDTO;

public interface AdminInvoiceUserService {
	public ObjectString finishInvoice(InvoicesDTO list);
	public List<InvoicesDTO> getListOrderAdminFilter(InvoicesDTO item);
	public ObjectString discardInvoice(InvoicesDTO item);
	public List<InvoicesDTO> getDetailOrderById(InvoicesDTO item);
 
}
