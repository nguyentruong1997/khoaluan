package com.migi.model;

public class SupplierDTO {
	private int maNhaSanXuat;
	private String maSanPham;
	private String tenNhaSanXuat;
	private String tenGiaoDich;
	private String diaChi;
	private String soDienThoai;
	private String email;
	private int userId;
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public SupplierDTO() {
		super();
	}

	public SupplierDTO(int maNhaSanXuat, String maSanPham, String tenNhaSanXuat, String tenGiaoDich, String diaChi,
			String soDienThoai, String email) {
		super();
		this.maNhaSanXuat = maNhaSanXuat;
		this.maSanPham = maSanPham;
		this.tenNhaSanXuat = tenNhaSanXuat;
		this.tenGiaoDich = tenGiaoDich;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.email = email;
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

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
