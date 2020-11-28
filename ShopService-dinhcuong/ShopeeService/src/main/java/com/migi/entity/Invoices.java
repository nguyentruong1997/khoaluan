package com.migi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "invoices")
public class Invoices {
	@Id
	private String maHoaDon;

	@Column(name = "makhachhang")
	private String maKhachHang;

	@Column(name = "manhanvien")
	private String maNhanVien;

	@Column(name = "ngaylap")
	private Date ngayLap;

	@Column(name = "ngaynhan")
	private Date nganhan;

	@Column(name = "giaban")
	private String giaban;

	public Invoices() {
		super();
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public Date getNganhan() {
		return nganhan;
	}

	public void setNganhan(Date nganhan) {
		this.nganhan = nganhan;
	}

	public String getGiaban() {
		return giaban;
	}

	public void setGiaban(String giaban) {
		this.giaban = giaban;
	}

}
