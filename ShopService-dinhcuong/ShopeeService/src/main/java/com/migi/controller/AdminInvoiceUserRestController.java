package com.migi.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.migi.model.ClassifyDTO;
import com.migi.model.InvoicesDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;
import com.migi.service.AdminInvoiceUserService;
import com.migi.service.AdminProductService;

@RestController
public class AdminInvoiceUserRestController {

	@Autowired
	private AdminProductService adminProductService;
	@Autowired
	private AdminInvoiceUserService adminInvoiceUserService;
 

	@PostMapping(value = "/verifyInvoice", produces = "application/json" )
	public ResponseEntity<ObjectString> verifyInvoice(@RequestBody InvoicesDTO obj) {
		ObjectString objStr = adminInvoiceUserService.finishInvoice(obj);

		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else if("fail".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
		else 
			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
	}	
	@PostMapping(value = "/getAdminListOrder", produces = "application/json" )
	public ResponseEntity<List<InvoicesDTO>> getAdminListOrder(@RequestBody InvoicesDTO item) {
		List<InvoicesDTO> list = adminInvoiceUserService.getListOrderAdminFilter(item);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}  
//	@GetMapping(value = "/getAllOrderFinish", produces = "application/json" )
//	public ResponseEntity<List<InvoicesDTO>> getListOrderFinish() {
//		List<InvoicesDTO> list = adminInvoiceUserService.getListOrderWaitingOrFinish(1);
//		return new ResponseEntity<>(list, HttpStatus.OK);
//	}  
	@PostMapping(value = "/getDetailOrderById", produces = "application/json" )
	public ResponseEntity<List<InvoicesDTO>> getDetailOrderById(@RequestBody InvoicesDTO item) {
		List<InvoicesDTO> list = adminInvoiceUserService.getDetailOrderById(item);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}  
	@PostMapping(value = "/discardOrder", produces = "application/json" )
	public ResponseEntity<ObjectString> discardOrder(InvoicesDTO item) {
		ObjectString objStr = adminInvoiceUserService.discardInvoice(item);
		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
//		else if("fail".equals(objStr.getCode()))
//			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
		else 
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
	} 

}
