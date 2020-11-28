package com.migi.model;

import java.util.Date;
import java.util.List;

public class CartItemDTO {
	private int cartId;
	private Date dateUpdate;
	private float itemPrice;
	private String productID; 
	private String itemName;
	private String image;
	private ClassifyDTO classify;
	private int sl;
	private int status;
	private float  priceSum;
	private int userID;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public ClassifyDTO getClassify() {
		return classify;
	}
	public void setClassify(ClassifyDTO classify) {
		this.classify = classify;
	}
	public int getSl() {
		return sl;
	}
	public void setSl(int sl) {
		this.sl = sl;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getPriceSum() {
		return priceSum;
	}
	public void setPriceSum(float priceSum) {
		this.priceSum = priceSum;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
 
 
	 
}
