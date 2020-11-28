package com.migi.model;

import java.util.Date;

public class ClassifyDTO {
	private int id;
	private String size;
	private String color;
	private int number;
	private String productCode;
	private int plus;
	
	
	public ClassifyDTO(int number ) {
		super();
		this.number = number;
	}
	public ClassifyDTO() {
		super();
	}
	public int getPlus() {
		return plus;
	}
	public void setPlus(int plus) {
		this.plus = plus;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	 
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+"/"+size+"/"+color+"/"+number+"/"+productCode;
	}
	
	 
}
