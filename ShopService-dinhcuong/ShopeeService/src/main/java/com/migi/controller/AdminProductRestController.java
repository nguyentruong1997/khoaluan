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
import com.migi.service.AdminInvoiceUserService;
import com.migi.service.AdminProductService;

@RestController
public class AdminProductRestController {

	@Autowired
	private AdminProductService adminProductService;
	@Autowired
	private AdminInvoiceUserService adminInvoiceUserService;

	// API tra ve list Employee
	// @GetMapping(value = "/authen")
	@PostMapping(value = "/importProduct", produces = "application/json")
	public ResponseEntity<ObjectString> importProduct(@RequestBody List<ProductDTO> list) {
		ObjectString objStr = adminProductService.importProduct(list);

		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
	}
	@PutMapping(value = "/importProduct", produces = "application/json")
	public ResponseEntity<ObjectString> addingNumberProduct(@RequestBody ProductDTO productDTO) {
		ObjectString objStr = adminProductService.addingNumberProduct(productDTO);

		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
	}
	@PostMapping(value = "/getProductFilter", produces = "application/json")
	public ResponseEntity<List<ProductDTO>> getProductList(@RequestBody ProductDTO obj) {
		List<ProductDTO> list = adminProductService.getProductList(obj);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PutMapping(value = "/updateProduct", produces = "application/json")
	public ResponseEntity<ObjectString> updateProduct(@RequestBody ProductDTO obj) {
		ObjectString objStr = adminProductService.updateProduct(obj);

		if ("success".equals(objStr.getCode()))
			return new ResponseEntity<>(objStr, HttpStatus.OK);
		else
			return new ResponseEntity<>(objStr, HttpStatus.ACCEPTED);
	}
	@PostMapping(value = "/getAdminProductClassify", produces = "application/json" )
	public ResponseEntity<List<ClassifyDTO>> getProductClassify(@RequestBody ClassifyDTO obj) {
		List<ClassifyDTO> list = adminProductService.getProductClassify(obj);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
 
	@PostMapping(value = "/fileupload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public ResponseEntity<ObjectString> uploadFile(@RequestParam(name = "file") MultipartFile file) {
		// HttpHeaders responseHeaders = new HttpHeaders();
		// responseHeaders.add("Content-Type", "application/text; charset=utf-8");
		String result = null;
//		Path currentWorkingDir = Paths.get("").toAbsolutePath();
//        System.out.println(currentWorkingDir.normalize().toString());
		ObjectString objStr = new ObjectString();
		if (file != null) {
			System.out.println(file.getOriginalFilename());
			result = file.getOriginalFilename();

			try {
				File newFile = new File(
						"D:\\DEVELOPER\\js_react\\shopapp\\public\\asset\\" + file.getOriginalFilename());
				boolean kt = newFile.exists();
				if (kt == true) {
					System.out.println("File da ton tai");
				} else {
					System.out.println("File chua ton tai");
					FileOutputStream fileOutputStream = new FileOutputStream(newFile);
					fileOutputStream.write(file.getBytes()); // upload success
					fileOutputStream.close();
				}
				objStr.setValue(result);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (objStr.getValue() == null)
			return new ResponseEntity<>(objStr, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(objStr, HttpStatus.OK);

	}

}
