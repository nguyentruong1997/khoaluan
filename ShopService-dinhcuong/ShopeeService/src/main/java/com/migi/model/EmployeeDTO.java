package com.migi.model;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeDTO {
	
	private String manhanvien;
	private String ho;
	private String ten;
	private String diachi;
	private float luongcoban;
	private BigDecimal phucap;
	private Date ngaylamviec;
	private String dienthoai;
	
	public BigDecimal getPhucap() {
		return phucap;
	}

	public void setPhucap(BigDecimal phucap) {
		this.phucap = phucap;
	}

	public Date getNgaylamviec() {
		return ngaylamviec;
	}

	public void setNgaylamviec(Date ngaylamviec) {
		this.ngaylamviec = ngaylamviec;
	}

	public String getDienthoai() {
		return dienthoai;
	}

	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}
	
	public String getManhanvien() {
		return manhanvien;
	}

	public void setManhanvien(String manhanvien) {
		this.manhanvien = manhanvien;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public float getLuongcoban() {
		return luongcoban;
	}

	public void setLuongcoban(float luongcoban) {
		this.luongcoban = luongcoban;
	}
}
