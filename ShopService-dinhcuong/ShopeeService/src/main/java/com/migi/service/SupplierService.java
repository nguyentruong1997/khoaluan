package com.migi.service;

import java.util.List;

import com.migi.model.InvoicesDTO;
import com.migi.model.ObjectString;
import com.migi.model.SupplierDTO;
import com.migi.model.UserDTO;

public interface SupplierService {
	 
	public List<SupplierDTO> getListSupplier(SupplierDTO user);
	 
	public ObjectString addSupplier(SupplierDTO obj);
	public ObjectString removeSupplier(SupplierDTO obj);
	public ObjectString updateSupplier(SupplierDTO obj);
}
