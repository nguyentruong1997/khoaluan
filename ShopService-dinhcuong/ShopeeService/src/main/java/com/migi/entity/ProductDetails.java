package com.migi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Product_Details")
public class ProductDetails {
	@Id
	private String maSanPham;

	@Column(name = "kieudang")
	private String kieuDang;

	@Column(name = "color")
	private String Color;

	@Column(name = "size")
	private char Size;

	@Column(name = "soluong")
	private int soLuong;

	public ProductDetails() {
		super();
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getKieuDang() {
		return kieuDang;
	}

	public void setKieuDang(String kieuDang) {
		this.kieuDang = kieuDang;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public char getSize() {
		return Size;
	}

	public void setSize(char size) {
		Size = size;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

}
