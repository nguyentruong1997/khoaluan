package com.migi.model;

public class ProductDetailsDTO {
	private String maSanPham;
	private String kieuDang;
	private String Color;
	private char Size;
	private int soLuong;

	public ProductDetailsDTO() {
		super();
	}

	public ProductDetailsDTO(String maSanPham, String kieuDang, String color, char size, int soLuong) {
		super();
		this.maSanPham = maSanPham;
		this.kieuDang = kieuDang;
		Color = color;
		Size = size;
		this.soLuong = soLuong;
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
