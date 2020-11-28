package com.migi.model;

import java.util.Date;

public class PromotionDTO {
	private String maKhuyenMai;
	private String tenKhuyenMai;
	private Date tuNgay;
	private Date denNgay;
	private String maSanPham;

	public PromotionDTO() {
		super();
	}

	public PromotionDTO(String maKhuyenMai, String tenKhuyenMai, Date tuNgay, Date denNgay, String maSanPham) {
		super();
		this.maKhuyenMai = maKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.tuNgay = tuNgay;
		this.denNgay = denNgay;
		this.maSanPham = maSanPham;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public Date getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(Date tuNgay) {
		this.tuNgay = tuNgay;
	}

	public Date getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(Date denNgay) {
		this.denNgay = denNgay;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

}
