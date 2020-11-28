package com.migi.model;

public class EntryFormDetailsDTO {
	private String maPhieuNhap;
	private String maLoaiSanPham;
	private String maSanPham;
	private int soLuong;
	private String giaNhap;

	public EntryFormDetailsDTO() {
		super();
	}

	public EntryFormDetailsDTO(String maPhieuNhap, String maLoaiSanPham, String maSanPham, int soLuong,
			String giaNhap) {
		super();
		this.maPhieuNhap = maPhieuNhap;
		this.maLoaiSanPham = maLoaiSanPham;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
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
