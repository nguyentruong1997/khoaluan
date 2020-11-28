package com.migi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.AdminImportInvoiceDao;
import com.migi.dao.AdminProductDao;
import com.migi.dao.AdminTypeDao;
import com.migi.dao.AuthenticateDao;
import com.migi.dao.ProductDao;
import com.migi.dao.SupplierDao;
import com.migi.entity.User;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.ObjectString;
import com.migi.model.ProductDTO;
import com.migi.model.ProductTypeDTO;
import com.migi.model.SupplierDTO;
import com.migi.model.UserDTO;
import com.migi.service.AdminProductService;
import com.migi.service.AuthenticateService;

@Service
@Transactional
public class AdminProductServiceImpl implements AdminProductService {

	private static final String DEFAULT_BRAND = "No Brand";

	@Autowired
	private AdminProductDao adminProductDao;
	@Autowired
	private AuthenticateDao authenticateDao;
	@Autowired
	private AdminImportInvoiceDao adminImportInvoiceDao;
	@Autowired
	private AdminTypeDao adminTypeDao;
	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private ProductDao productDao;
	
	@Override
	public ObjectString importProduct(List<ProductDTO> listImport) {
		ProductDTO enterCoupon = listImport.get(0);
		UserDTO user = new UserDTO();
		user.setUserID(enterCoupon.getUserId()); // get UserId de authorize admin
		ObjectString rs = new ObjectString();
		rs.setCodeValue("success", "Nhập sản phẩm thành công!");
		if (authenticateDao.checkRole(user, "admin")) {
			String importInvoiceCode = adminImportInvoiceDao.addImportInvoiceProduct(); // Get hoadonNhapId
			float priceSum = 0;                 
			for (ProductDTO item : listImport) {        //list tung product
				item.setImportBillCode(importInvoiceCode); // set hoadonnhapID
				ProductTypeDTO typeDto = new ProductTypeDTO();
				typeDto.setTypeId(item.getTypeId());
				SupplierDTO supplier = new SupplierDTO();
				supplier.setMaNhaSanXuat(item.getBrandCode());
				if (adminTypeDao.checkProductTypeExists(typeDto) &&(item.getBrandCode()==-1 || supplierDao.checkSupplierExists(supplier))) { // check loai sp ton tai
					// if(item.getBrandCode()==-1) item.setBrandName(DEFAULT_BRAND); //set default
					
					if (!adminProductDao.checkProductExisted(item)) {
						List<ClassifyDTO> listClassify = item.getClassifiedList(); 
						int soluongton = 0;
						for (int j = 0; j < listClassify.size();   j++) {
							soluongton+=listClassify.get(j).getNumber();
						}
						item.setSoluongton(soluongton);
						String rs1 = adminProductDao.addProductInfo(item); //update soluongton
						if (rs1.equals("success") && adminProductDao.checkProductExisted(item)) {
							System.out.println("-------Them chi tiet hoa don nhap");
							String rs2 = adminImportInvoiceDao.addImportInvoiceDetails(item);
							if (("success").equals(rs2)) {
								System.out.println("-------Them phan loai SP--");
								String rs4 = adminProductDao.addClassifiedProduct(item); // add list phanloaiSP
								if("success".equals(rs4)) {
									int sumNumber = 0;  
									for(ClassifyDTO classObj : item.getClassifiedList()) {
										sumNumber += classObj.getNumber();
									}
									//item.setSum(sumNumber*item.getPriceFirst());
									priceSum += sumNumber*item.getPriceFirst();
									//String rs5 = adminImportInvoiceDao.updatePriceImportInvoice(item);
									 
								}
							}
						} else {
							System.out.println(">>>>>>>>>>>>>>Spham chua ton tai!");
							rs.setCodeValue("fail", "Nhập không không thành công!");
							return rs;
						}
					} else {
						System.out.println(">>>>>>>>>>>>>>Spham có mã đã tồn tai!");
						rs.setCodeValue("fail", "Lỗi nhập mã sản phẩm đã tồn tại");
						return rs;
					}

				} else {
					System.out.println(">>>>>>>>>>>>>>Loai sp chua ton tai!");
					rs.setCodeValue("fail", "Loại sản phẩm hoặc hãng chưa tồn tại!!");
					return rs;
				}
				
			}
			EnterCouponDTO ec = new EnterCouponDTO();
			ec.setPriceSum(priceSum);
			ec.setImportBillCode(importInvoiceCode);
			adminImportInvoiceDao.updatePriceImportInvoice(ec); //update tongthanhtoan
			// rs.setCodeValue("success", "Nhập sản phẩm thành công!");
		}
 
		return rs;
	}

	@Override
	public List<ProductDTO> getProductList(ProductDTO obj) {
		List<ProductDTO> list = adminProductDao.getProductList(obj);
		return list;
	}

	@Override
	public List<ClassifyDTO> getProductClassify(ClassifyDTO obj) {
		return adminProductDao.getProductClassify(obj);
	}

	@Override
	public ObjectString updateProduct(ProductDTO obj) {
		UserDTO user = new UserDTO();
		user.setUserID(obj.getUserId());
		ObjectString rs = new ObjectString();
		if (authenticateDao.checkRole(user, "admin")) { // co quyen admin
			if (adminProductDao.checkProductExisted(obj)) { // co ton tai
				String rs1 = adminProductDao.updateProductInfo(obj);
				if ("success".equals(rs1)) { // && supplierDao.checkSupplierExists(obj)
					System.out.println("Update  sản phẩm thành công!");
					rs.setCodeValue("success", "Update thông tin sản phẩm thành công!");
				} else {
					rs.setCodeValue("fail", "Update thông tin sản phẩm thất bại!");
				}
			} else {
				rs.setCodeValue("fail", "Sản phẩm chưa tồn tại");
			}
		}
		return rs;
	}

	@Override
	public ObjectString deleteProduct(ProductDTO obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectString addingNumberProduct(ProductDTO item) {
//
		UserDTO user = new UserDTO();
		user.setUserID(item.getUserId()); // get UserId de authorize admin
		ObjectString rs = new ObjectString();
		rs.setCodeValue("success", "Nhập sản phẩm thành công!");
		if (authenticateDao.checkRole(user, "admin")) {
			 
			String importInvoiceCode = adminImportInvoiceDao.addImportInvoiceProduct(); // Get hoadonNhapId
			item.setImportBillCode(importInvoiceCode); // set hoadonnhapID
			ProductTypeDTO typeDto = new ProductTypeDTO();
			typeDto.setTypeId(item.getTypeId());
			if (adminTypeDao.checkProductTypeExists(typeDto)) { // check loai sp ton tai

				if (adminProductDao.checkProductExisted(item)) { // check sp ton tai ko if yes ok
					System.out.println("-------Them chi tiet hoa don nhap");
					//ProductDTO item2 = new ProductDTO();
					ProductDTO productInDb = productDao.getProductDetail(item);
					List<ClassifyDTO> classLst = new ArrayList<>();
					for(ClassifyDTO o : item.getClassifiedList()) {
						classLst.add(new ClassifyDTO(o.getPlus()));
					}
					productInDb.setImportBillCode(item.getImportBillCode());
					//item2.setPriceFirst(item.getPriceFirst()); code and price  //fix gia nhap , gia ban
					productInDb.setClassifiedList(classLst);
					String rs2 = adminImportInvoiceDao.addImportInvoiceDetails(productInDb);
					if (("success").equals(rs2)) {
						int soluongton = 0;
						float priceSum = 0;
						for(ClassifyDTO obj :  item.getClassifiedList()) {
							obj.setNumber(obj.getNumber() + obj.getPlus());  //ko an toan
							soluongton+= obj.getNumber();
							System.out.println("-------Update phan loai SP--: "+obj);
							String rs3 = adminProductDao.updateClassifiedProduct(obj); // update so luong phanloaiSP
							if(!"success".equals(rs3)) {
								rs.setCodeValue("fail", "Update phân loại sản phẩm thất bại!");
								return rs;
							}
							priceSum+=item.getPriceFirst()*obj.getPlus(); //tinh tong hoa don cong them
						}
						EnterCouponDTO ec = new EnterCouponDTO();
						ec.setPriceSum(priceSum);
						ec.setImportBillCode(importInvoiceCode);
						adminImportInvoiceDao.updatePriceImportInvoice(ec); //update tongthanhtoan
						adminProductDao.updateProductNumber(productInDb.getProductCode(), soluongton);  //update product table
					}

				} else {
					System.out.println(">>>>>>>>>>>>>>Spham chưa ton tai!");
					rs.setCodeValue("fail", "Sản phẩm chưa tồn tại");
					return rs;
				}
			} else {
				System.out.println(">>>>>>>>>>>>>>Loai sp chua ton tai!");
				rs.setCodeValue("fail", "Loai sp chua ton tai!!");
				return rs;
			}
			
		}
  	return rs;
	}

}
