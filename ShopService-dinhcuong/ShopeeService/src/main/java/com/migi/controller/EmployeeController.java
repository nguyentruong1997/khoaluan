package com.migi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.migi.model.EmployeeDTO;
import com.migi.service.EmployeeService;
import com.migi.validator.EmployeeValidator;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeValidator employeeValidator;
	@Autowired
	private EmployeeService employeeservice;
	
	@RequestMapping(value="/listEmp",method = RequestMethod.GET)
	public String getAllEmp(HttpServletRequest request) {
		 List<EmployeeDTO> empList = employeeservice.getAllEmps();
		 request.setAttribute("emplist", empList);
		 return "Emp/listEmp";
	}
	@RequestMapping(value="/viewEmp/{EmpId}",method = RequestMethod.GET)
	public String viewEmp(HttpServletRequest request, @PathVariable("EmpId") String EmpId ) {
		EmployeeDTO  emp  = (EmployeeDTO)employeeservice.getEmpById(EmpId);
		 request.setAttribute("emp", emp);
		 return "Emp/viewEmp";
	}
 
	@RequestMapping(value="/deleteEmp/{EmpId}",method = RequestMethod.GET)
	public String deleteEmp(HttpServletRequest request, @PathVariable("EmpId") String manv ) {
		 employeeservice.deleteEmp(manv);
		 return "redirect:/listEmp";
	}
	@RequestMapping(value="/editEmp/{EmpId}",method = RequestMethod.GET)
	public String editEmp(HttpServletRequest request, @PathVariable("EmpId") String manv ) {
		 request.setAttribute("emp", employeeservice.getEmpById(manv));
		 return "Emp/editEmp";
	}
	@RequestMapping(value="/editEmp",method = RequestMethod.POST)
	public String editEmp(HttpServletRequest request,@ModelAttribute("emp") EmployeeDTO emp,BindingResult bindingResult) {
		employeeValidator.validate(emp, bindingResult);
		if(bindingResult.hasErrors()) {
			return "Emp/editEmp";
		}
		 employeeservice.updateEmp(emp);
		 return "redirect:/listEmp";
	}
	@RequestMapping(value="/addEmp",method = RequestMethod.GET)
	public String addEmp(HttpServletRequest request ) {
		 request.setAttribute("emp", new EmployeeDTO());
		 return "Emp/addEmp";
	}
	@RequestMapping(value="/addEmp",method = RequestMethod.POST)
	public String addEmp(HttpServletRequest request,@ModelAttribute("emp") EmployeeDTO emp,BindingResult bindingResult) {
		employeeValidator.validate(emp, bindingResult);
		if(bindingResult.hasErrors()) {
			return "Emp/addEmp";
		}
		 employeeservice.addEmp(emp);
		 return "redirect:/listEmp";
	}
}
