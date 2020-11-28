package com.migi.service.impl;

import java.util.ArrayList;
import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.EmployeeDao;
import com.migi.entity.Employee;
import com.migi.model.EmployeeDTO;
import com.migi.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDao employeeDao;
	
	public String validateDBinput(EmployeeDTO empDTO) {
		String result="";
		if(empDTO.getManhanvien()==null || empDTO.getManhanvien().length()==0) {
			result = "Lỗi :Mã nhân viên null\n";  
		}
		else
		if(empDTO.getManhanvien().length()>10) {
			result = "Lỗi :Mã nhân viên có độ dài lớn hơn 10\n";  
		}
		if(empDTO.getHo()==null || empDTO.getHo().length()==0) {
			result += "Lỗi :Họ null\n";  
		}
		else if(empDTO.getHo().length()>20) {
			result += "Lỗi :Họ có độ dài lớn hơn 20\n";  
		}
		if(empDTO.getTen()==null || empDTO.getTen().length()==0) {
			result += "Lỗi :Tên null\n";
		}
		else if(empDTO.getTen().length()>10) {
			result += "Lỗi :Tên có độ dài lớn hơn 10\n";
		}
		if(empDTO.getDiachi()!=null && empDTO.getDiachi().length()>50) {
			result += "Lỗi : Địa chỉ có độ dài lớn hơn 50\n";
		}
		return result;
	}
	
	@Override
	public String addEmp(EmployeeDTO empDTO) {	
		String result="";
		result=validateDBinput(empDTO);
		
		if(result.equals("")) {
			if(employeeDao.getEmpById(empDTO.getManhanvien()) == null) {
				Employee emp = new Employee();
				emp.setManhanvien(empDTO.getManhanvien());
				emp.setTen(empDTO.getTen());
				emp.setHo(empDTO.getHo());
				emp.setDiachi(empDTO.getDiachi());
				employeeDao.addEmp(emp);
				result="ADD SUCCESSFUL";
			}
			else {
				result="Lỗi :  Nhân viên "+empDTO.getManhanvien() +" existed";
			}
		}
		
		return result;
	}

	@Override
	public String updateEmp(EmployeeDTO empDTO) {
		String result="";
		result=validateDBinput(empDTO);
		if(result.equals("")) {
			Employee emp = employeeDao.getEmpById(empDTO.getManhanvien());
			if(emp!=null) {
				emp.setTen(empDTO.getTen());
				emp.setHo(empDTO.getHo());
				emp.setDiachi(empDTO.getDiachi());
				employeeDao.updateEmp(emp);
				result="Update Successful";
			}
			else
				result="Nhân viên "+empDTO.getManhanvien()+ " not existed";
		}
		
		return result;
	}

	@Override
	public void deleteEmp(String id) {
		Employee emp = employeeDao.getEmpById(id);
		if(emp!=null) {
			employeeDao.deleteEmp(id);
		}
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEmpById(String id) {
		String result="";
		
		if(id.length()>10) {
			result = "Lỗi :Mã nhân viên có độ dài lớn hơn 10\n";  
		}
		if(result.equals("")) {
			Employee emp = employeeDao.getEmpById(id) ;
			if(emp!=null) {
				EmployeeDTO empDTO = new EmployeeDTO();
				empDTO.setManhanvien(emp.getManhanvien());
				empDTO.setTen(emp.getTen());
				empDTO.setHo(emp.getHo());
				empDTO.setDiachi(emp.getDiachi());
				return (T) empDTO;
			}
			else {
				result ="Lỗi :  Nhân viên "+ id +" not existed";
			}
		}
		return (T)result;
	}

	@Override
	public List<EmployeeDTO> getAllEmps() {
		List<Employee> emps =  employeeDao.getAllEmps();
		List<EmployeeDTO> empDTOs = new ArrayList<>();
		for(Employee emp : emps) {
			EmployeeDTO empDTO = new EmployeeDTO();
			empDTO.setManhanvien(emp.getManhanvien());
			empDTO.setHo(emp.getHo());
			empDTO.setTen(emp.getTen());
			empDTO.setDiachi(emp.getDiachi());
			empDTOs.add(empDTO);
		}
		return empDTOs;
	}
}
