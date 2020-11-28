package com.migi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "product")
public class Product {
	@Id
	private String maSP;

	@Column(name = "tensanpham")
	private String tenSP;

	@Column(name = "dvt")
	private String dvt;

	@Column(name = "giahientai")
	private String giaHienTai;

	@Column(name = "maloaisanpham")
	private String maLoaiSP;

	@Column(name = "chatluong")
	private String chatLuong;

	@Column(name = "kieudang")
	private String kieuDang;

	@Column(name = "color")
	private String color;

	@Column(name = "size")
	private char size;

	@Column(name = "soluong")
	private String soLuong;

	public Product() {
		super();
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getDvt() {
		return dvt;
	}

	public void setDvt(String dvt) {
		this.dvt = dvt;
	}

	public String getGiaHienTai() {
		return giaHienTai;
	}

	public void setGiaHienTai(String giaHienTai) {
		this.giaHienTai = giaHienTai;
	}

	public String getMaLoaiSP() {
		return maLoaiSP;
	}

	public void setMaLoaiSP(String maLoaiSP) {
		this.maLoaiSP = maLoaiSP;
	}

	public String getChatLuong() {
		return chatLuong;
	}

	public void setChatLuong(String chatLuong) {
		this.chatLuong = chatLuong;
	}

	public String getKieuDang() {
		return kieuDang;
	}

	public void setKieuDang(String kieuDang) {
		this.kieuDang = kieuDang;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public char getSize() {
		return size;
	}

	public void setSize(char size) {
		this.size = size;
	}

	public String getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}

}
