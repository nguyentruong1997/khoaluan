package com.migi.model;

import java.util.Date;
import java.util.List;

public class ProductDTO {
	private String importBillCode;
	private Date importDate;
	private int typeId;
	private String productCode;
	private String productName;
	private float priceFirst;
	private float price;
	private String image;
	private String description;
	private int userId;
	private int number;
	private int brandCode;
	private String brandName;
	private List<ClassifyDTO> classifiedList;
	private float sum;
	private float priceKM;
	private int soluongton;
	
	public int getSoluongton() {
		return soluongton;
	}
	public void setSoluongton(int soluongton) {
		this.soluongton = soluongton;
	}
	public float getPriceKM() {
		return priceKM;
	}
	public void setPriceKM(float priceKM) {
		this.priceKM = priceKM;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public int getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(int brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public List<ClassifyDTO> getClassifiedList() {
		return classifiedList;
	}
	public void setClassifiedList(List<ClassifyDTO> classifiedList) {
		this.classifiedList = classifiedList;
	}
	public float getPriceFirst() {
		return priceFirst;
	}
	public float getPrice() {
		return price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setPriceFirst(float priceFirst) {
		this.priceFirst = priceFirst;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	 
	public String getImportBillCode() {
		return importBillCode;
	}
	public void setImportBillCode(String importBillCode) {
		this.importBillCode = importBillCode;
	}
	public Date getImportDate() {
		return importDate;
	}
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
 
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 
	@Override
	public String toString() {
		 
		return productName+"/"+productCode+"/"+price+"/"+priceFirst+"/"+classifiedList+"/"+userId;
	}

}
