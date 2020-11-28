package com.migi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.migi.model.InvoicesDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.ProductTypeDTO;
import com.migi.model.SupplierDTO;
import com.migi.model.UserDTO;
import com.migi.service.SupplierService;
import com.migi.service.UserService;

@RestController
public class SupplierRestController {

	@Autowired
	private SupplierService supplierService;
	
	@PostMapping(value = "/getListSupplier", produces = "application/json" )
	public ResponseEntity<List<SupplierDTO>> getListSupplier(@RequestBody SupplierDTO obj) {
		List<SupplierDTO> list = supplierService.getListSupplier(obj);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@PostMapping(value = "/deleteSupplier", produces = "application/json" )
	public  ResponseEntity<ObjectString> deleteProductType(@RequestBody SupplierDTO obj) {
			ObjectString objStr = supplierService.removeSupplier(obj);
			
			if ("success".equals(objStr.getCode()))
				return new ResponseEntity<>(objStr, HttpStatus.OK);
			else
				return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
//		else
//			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
	}
	@PutMapping(value = "/addSupplier", produces = "application/json" )
	public ResponseEntity<ObjectString> updateSupplier(@RequestBody SupplierDTO obj) {
		ObjectString objStr = supplierService.updateSupplier(obj);
		
		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
			
	}
	@PostMapping(value = "/addSupplier", produces = "application/json" )
	public ResponseEntity<ObjectString> addSupplier(@RequestBody SupplierDTO obj) {
		ObjectString objStr = supplierService.addSupplier(obj);
		
		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
			
	}
	
 

}
