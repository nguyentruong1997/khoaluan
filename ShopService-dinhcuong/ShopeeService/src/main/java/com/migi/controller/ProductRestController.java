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

import com.migi.model.CartItemDTO;
import com.migi.model.ClassifyDTO;
import com.migi.model.EmployeeDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.FileDTO;
import com.migi.model.InvoicesDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.ProductTypeDTO;
import com.migi.model.UserDTO;
import com.migi.service.AdminProductService;
import com.migi.service.AdminTypeService;
import com.migi.service.AuthenticateService;
import com.migi.service.EmployeeService;
import com.migi.service.ProductService;

@RestController
public class ProductRestController {

	@Autowired
	private AdminProductService adminProductService;
	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/getProductList", produces = "application/json" )
	public ResponseEntity<List<ProductDTO>> getProductList(@RequestBody ProductDTO obj) {
		List<ProductDTO> list = adminProductService.getProductList(obj);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@PostMapping(value = "/getProductDetail", produces = "application/json" )
	public ResponseEntity<ProductDTO> getProductDetail(@RequestBody ProductDTO obj) {
		ProductDTO product = productService.getProductDetail(obj);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	@PostMapping(value = "/getProductClassify", produces = "application/json" )
	public ResponseEntity<List<ClassifyDTO>> getProductClassify(@RequestBody ClassifyDTO obj) {
		List<ClassifyDTO> list = productService.getProductClassify(obj);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@PostMapping(value = "/getProductClassifyNumber", produces = "application/json" )
	public ResponseEntity<ClassifyDTO> getProductClassifyNumber(@RequestBody ClassifyDTO obj) {
		ClassifyDTO classifyobj = productService.getProductClassifyNumber(obj);
		return new ResponseEntity<>(classifyobj, HttpStatus.OK);
	}
	@PostMapping(value = "/verifyCart", produces = "application/json" )
	public ResponseEntity<ObjectString> verifyCart(@RequestBody List<InvoicesDTO> obj) {
		ObjectString objStr = productService.verifyCart(obj);
		
		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
	}
	@PutMapping(value = "/saveCart", produces = "application/json" )
	public ResponseEntity<ObjectString> saveCart(@RequestBody List<CartItemDTO> obj) {
		ObjectString objStr = productService.saveCart(obj);
		
		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
	}
	@PutMapping(value = "/removeCart", produces = "application/json" )
	public ResponseEntity<ObjectString> removeCart(@RequestBody CartItemDTO obj) {
		ObjectString objStr = productService.deleteCart(obj);
		
		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
	}
	@PostMapping(value = "/getCart", produces = "application/json" )
	public ResponseEntity<List<CartItemDTO>> getCart(@RequestBody CartItemDTO obj) {
		List<CartItemDTO> list = productService.getCartList(obj);
		 return new ResponseEntity<>(list, HttpStatus.OK);
	 
	}
 
 

}
