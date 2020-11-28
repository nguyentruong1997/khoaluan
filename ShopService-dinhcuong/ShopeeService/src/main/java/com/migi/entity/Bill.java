package com.migi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "bill")
public class Bill {
	@Id
	private String maPhieuXuat;

	@Column(name = "ngayxuat")
	private Date ngayXuat;

	@Column(name = "soluong")
	private int soLuong;

	@Column(name = "trangthai")
	private int trangThai;

	public Bill() {
		super();
	}

	public String getMaPhieuXuat() {
		return maPhieuXuat;
	}

	public void setMaPhieuXuat(String maPhieuXuat) {
		this.maPhieuXuat = maPhieuXuat;
	}

	public Date getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(Date ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

}
