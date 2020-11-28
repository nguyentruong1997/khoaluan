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
import com.migi.model.EnterCouponDTO;
import com.migi.model.FileDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductTypeDTO;
import com.migi.model.UserDTO;
import com.migi.service.AdminProductService;
import com.migi.service.AdminTypeService;
import com.migi.service.AuthenticateService;
import com.migi.service.EmployeeService;

@RestController
public class AdminTypeRestController {

	@Autowired
	private AdminTypeService adminTypeService;

	// API tra ve list Employee
	//@GetMapping(value = "/authen")
	@PostMapping(value = "/addProductType", produces = "application/json" )
	public ResponseEntity<ObjectString> addProductType(@RequestBody ProductTypeDTO obj) {
		ObjectString objStr = adminTypeService.addProductType(obj);
		
		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else
			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
			
	}
	@GetMapping(value = "/getAllTypeList", produces = "application/json" )
	public ResponseEntity<List<ProductTypeDTO>> getProductType() {
		List<ProductTypeDTO> list = adminTypeService.getProductTypeList();
		 
		//return new ResponseEntity<>(obj, HttpStatus.OK);
		 
		return new ResponseEntity<>(list, HttpStatus.OK);
//		else
//			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
			
	}
	@PostMapping(value = "/getAllTypeFilter", produces = "application/json" )
	public  ResponseEntity<List<ProductTypeDTO>> getProductTypeFilter(@RequestBody ProductTypeDTO obj) {
		List<ProductTypeDTO> list = adminTypeService.getProductTypeListBySearch(obj);
		//return new ResponseEntity<>(obj, HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
//		else
//			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
	}
	@PostMapping(value = "/deleteType", produces = "application/json" )
	public  ResponseEntity<ObjectString> deleteProductType(@RequestBody ProductTypeDTO obj) {
			ObjectString objStr = adminTypeService.deleteProductType(obj);
			
			if ("success".equals(objStr.getCode()))
				return new ResponseEntity<>(objStr, HttpStatus.OK);
			else
				return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
//		else
//			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
	}
	@PutMapping(value = "/addProductType", produces = "application/json" )
	public ResponseEntity<ObjectString> updateProductType(@RequestBody ProductTypeDTO obj) {
		ObjectString objStr = adminTypeService.updateProductType(obj);
		
		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else
			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
			
	}
 
 
 

}
