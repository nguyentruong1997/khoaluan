package com.migi.dao;

import java.util.List;

import com.migi.model.CustomerDTO;
import com.migi.model.SupplierDTO;
import com.migi.model.UserDTO;

public interface SupplierDao {
	public List<SupplierDTO> getListSupplier(SupplierDTO obj);
	public boolean checkSupplierExists(SupplierDTO obj);
	public boolean checkProductSupplierExisted(SupplierDTO obj);
	public String addSupplier(SupplierDTO obj);
	public String removeSupplier(SupplierDTO obj);
	public String updateSupplier(SupplierDTO obj);
};
