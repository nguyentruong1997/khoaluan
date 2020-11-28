package com.migi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Columns;
 
 

@Entity(name = "staff")
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	private String maNhanVien;

	@Column(name = "honhanvien")
	private String hoNhanVien;

	@Column(name = "tennhanvien")
	private String tenNhanVien;

	@Column(name = "gioitinh")
	private String gioiTinh;

	@Column(name = "ngaysinh")
	private Date ngaySinh;

	@Column(name = "sodienthoai")
	private int soDienThoai;

	public Staff() {
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoNhanVien() {
		return hoNhanVien;
	}

	public void setHoNhanVien(String hoNhanVien) {
		this.hoNhanVien = hoNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public int getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(int soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

}
