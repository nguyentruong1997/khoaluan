package com.migi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.migi.model.InvoicesDTO;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;
import com.migi.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/getUserInfo", produces = "application/json" )
	public ResponseEntity<UserDTO> getUserInfo(@RequestBody  UserDTO  obj) {
		UserDTO user = userService.getUserInfo(obj);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@PostMapping(value = "/getOrderWaiting", produces = "application/json" )
	public ResponseEntity<List<InvoicesDTO>> getListOrderWaiting(@RequestBody UserDTO obj) {
		List<InvoicesDTO> list = userService.getListOrderWaiting(obj);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}     
 

}
