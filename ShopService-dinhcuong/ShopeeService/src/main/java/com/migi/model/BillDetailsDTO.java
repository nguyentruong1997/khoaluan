package com.migi.model;

public class BillDetailsDTO {
	private String maPhieuXuat;
	private String maLoaiSanPham;
	private String maSanPham;
	private int soLuong;

	public BillDetailsDTO() {
		super();
	}

	public BillDetailsDTO(String maPhieuXuat, String maLoaiSanPham, String maSanPham, int soLuong) {
		super();
		this.maPhieuXuat = maPhieuXuat;
		this.maLoaiSanPham = maLoaiSanPham;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
	}

	public String getMaPhieuXuat() {
		return maPhieuXuat;
	}

	public void setMaPhieuXuat(String maPhieuXuat) {
		this.maPhieuXuat = maPhieuXuat;
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

}
