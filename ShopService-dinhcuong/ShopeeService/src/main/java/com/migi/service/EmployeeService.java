package com.migi.service;

import java.util.List;
 
import com.migi.model.EmployeeDTO;

public interface EmployeeService {
	public String addEmp(EmployeeDTO emp);
	public String updateEmp(EmployeeDTO emp);
	public void deleteEmp(String id);
	public <T> T getEmpById(String id);
	public List<EmployeeDTO> getAllEmps();
}
