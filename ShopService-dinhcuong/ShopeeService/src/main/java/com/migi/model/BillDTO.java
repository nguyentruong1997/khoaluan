package com.migi.model;

import java.util.Date;

public class BillDTO {
	private String maPhieuXuat;
	private Date ngayXuat;
	private int soLuong;
	private int trangThai;

	public BillDTO() {
		super();
	}

	public BillDTO(String maPhieuXuat, Date ngayXuat, int soLuong, int trangThai) {
		super();
		this.maPhieuXuat = maPhieuXuat;
		this.ngayXuat = ngayXuat;
		this.soLuong = soLuong;
		this.trangThai = trangThai;
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
