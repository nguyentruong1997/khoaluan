package com.migi.dao;

import java.util.List;

import com.migi.entity.Employee;

 
public interface EmployeeDao {
	public void addEmp(Employee emp);
	public void updateEmp(Employee emp);
	public void deleteEmp(String id);
	public Employee getEmpById(String id);
	public List<Employee> getAllEmps();
}
