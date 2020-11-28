package com.migi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "entry_form_details")
public class EntryFormDetails {
	@Id
	private String maPhieuNhap;

	@Column(name = "maloaisanpham")
	private String maLoaiSanPham;

	@Column(name = "masanpham")
	private String maSanPham;

	@Column(name = "soluong")
	private int soLuong;

	@Column(name = "gianhap")
	private String giaNhap;

	public EntryFormDetails() {
		super();
	}

	public String getMaPhieuNhap() {
		return maPhieuNhap;
	}

	public void setMaPhieuNhap(String maPhieuNhap) {
		this.maPhieuNhap = maPhieuNhap;
	}

	public String getMaLoaiSanPham() {
		return maLoaiSanPham;
	}

	public void setMaLoaiSanPham(String maLoaiSanPham) {
		this.maLoaiSanPham = maLoaiSanPham;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(String giaNhap) {
		this.giaNhap = giaNhap;
	}

}
