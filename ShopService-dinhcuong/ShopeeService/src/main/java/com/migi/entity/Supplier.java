package com.migi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "supplier")
public class Supplier {
	@Column(name = "nhasanxuat")
	private int maNhaSanXuat;
	
	@Id
	private String maSanPham;

	@Column(name = "tennhasanxuat")
	private String tenNhaSanXuat;

	@Column(name = "tengiaodich")
	private String tenGiaoDich;

	@Column(name = "diachi")
	private String diaChi;

	@Column(name = "sodienthoai")
	private int soDienThoai;

	@Column(name = "email")
	private String email;

	public Supplier() {
		super();
	}

	public int getMaNhaSanXuat() {
		return maNhaSanXuat;
	}

	public void setMaNhaSanXuat(int maNhaSanXuat) {
		this.maNhaSanXuat = maNhaSanXuat;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenNhaSanXuat() {
		return tenNhaSanXuat;
	}

	public void setTenNhaSanXuat(String tenNhaSanXuat) {
		this.tenNhaSanXuat = tenNhaSanXuat;
	}

	public String getTenGiaoDich() {
		return tenGiaoDich;
	}

	public void setTenGiaoDich(String tenGiaoDich) {
		this.tenGiaoDich = tenGiaoDich;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public int getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(int soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
