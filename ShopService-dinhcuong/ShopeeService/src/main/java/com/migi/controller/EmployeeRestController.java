package com.migi.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.migi.model.EmployeeDTO;
import com.migi.model.FileDTO;
import com.migi.model.ObjectString;
import com.migi.service.EmployeeService;

@RestController
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeservice;

	// API tra ve list Employee
	@GetMapping(value = "/list")
	public List<EmployeeDTO> getAllEmployeeAPI() {
		List<EmployeeDTO> empList = employeeservice.getAllEmps();
		return empList;
	}

	// API tra ve 1 Employee theo ID
	@RequestMapping(value = "/emp/{EmpId}", method = RequestMethod.GET)
	public <T> T viewEmpAPI(@PathVariable("EmpId") String EmpId) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("charset=utf-8", null);
		return employeeservice.getEmpById(EmpId);
	}

//	@PostMapping(value = "/emp", produces = "application/json")
//	public ResponseEntity<ObjectString> addEmp(@RequestBody EmployeeDTO emp) {
//		// HttpHeaders responseHeaders = new HttpHeaders();
//		// responseHeaders.add("Content-Type", "application/text; charset=utf-8");
//		System.out.println(emp.getTen());
//		String result = employeeservice.addEmp(emp);
//		ObjectString objStr = new ObjectString();
//		objStr.setValue(result);
//		if (!result.equals("ADD SUCCESSFUL"))
//			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
//		else
//			return new ResponseEntity<>(objStr, HttpStatus.OK);
//	}
	
	
//	@PostMapping(value = "/emp", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public String addEmp(@RequestBody String json) {
//		// HttpHeaders responseHeaders = new HttpHeaders();
//		// responseHeaders.add("Content-Type", "application/text; charset=utf-8");
//		System.out.println(json);
//		
//		return "";
//	}
	
	
//	@PostMapping(value = "/product", produces = "application/json" )
//	public ResponseEntity<ObjectString> addEmp1(@RequestBody FileDTO product) {
//		  HttpHeaders responseHeaders = new HttpHeaders();
//		 responseHeaders.add("ContentType", "application/text; charset=utf-8");
//		System.out.println("------------->>>>>>>>>>>>>>>>>>");
//		System.out.println(product);
//		
//		ObjectString objStr = new ObjectString();
//		objStr.setValue("1");
//		if (!objStr.getValue().equals("1"))
//			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
//		else
//			return new ResponseEntity<>(objStr, HttpStatus.OK);
//	}

	

	@PutMapping(value = "/emp", produces = "application/json")
	public ResponseEntity<ObjectString> editEmp(@RequestBody EmployeeDTO emp) {

		String result = employeeservice.updateEmp(emp);
		ObjectString objStr = new ObjectString();
		objStr.setValue(result);
		if (!result.equals("Update Successful"))
			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(objStr, HttpStatus.OK);
	}

	@DeleteMapping(value = "/emp")
	public void deleteEmp(@RequestBody EmployeeDTO emp) {
		employeeservice.deleteEmp(emp.getManhanvien());
	}

}
