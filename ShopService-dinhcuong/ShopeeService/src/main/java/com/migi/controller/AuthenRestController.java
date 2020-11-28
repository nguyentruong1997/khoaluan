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
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;
import com.migi.service.AuthenticateService;
import com.migi.service.EmployeeService;

@RestController
public class AuthenRestController {

	@Autowired
	private AuthenticateService authenticateService;

	// API tra ve list Employee
	//@GetMapping(value = "/authen")
	@PostMapping(value = "/login", produces = "application/json" )
	public ResponseEntity<UserDTO>  login(@RequestBody UserDTO user) {
		UserDTO userDto = authenticateService.login(user);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	@PostMapping(value = "/logout", produces = "application/json" )
	public ResponseEntity<UserDTO> logout(@RequestBody UserDTO user) {
		UserDTO userDto = authenticateService.login(user);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	@PostMapping(value = "/register", produces = "application/json" )
	public ResponseEntity<ObjectString> register(@RequestBody UserDTO user) {
		ObjectString objStr = authenticateService.register(user);
 
		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else if("fail".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
		else 
			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
	}
	// API tra ve 1 Employee theo ID
//	@RequestMapping(value = "/emp/{EmpId}", method = RequestMethod.GET)
//	public <T> T viewEmpAPI(@PathVariable("EmpId") String EmpId) {
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.add("charset=utf-8", null);
//		return employeeservice.getEmpById(EmpId);
//	}
 

}
