package com.migi.model;

import java.util.Date;

public class StaffDTO {
	private String maNhanVien;
	private String hoNhanVien;
	private String tenNhanVien;
	private String gioiTinh;
	private Date ngaySinh;
	private int soDienThoai;

	public StaffDTO() {
		super();
	}

	public StaffDTO(String maNhanVien, String hoNhanVien, String tenNhanVien, String gioiTinh, Date ngaySinh,
			int soDienThoai) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoNhanVien = hoNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
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
