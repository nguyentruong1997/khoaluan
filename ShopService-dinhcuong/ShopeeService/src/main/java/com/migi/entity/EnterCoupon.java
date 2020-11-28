package com.migi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

 
 

@Entity(name = "enter_coupon")
public class EnterCoupon {
	
	@javax.persistence.Id
	private String maPhieuNhap;

	@Column(name = "ngaynhap")
	private Date ngayNhap;

	public EnterCoupon() {
		super();
	}

	public String getMaPhieuNhap() {
		return maPhieuNhap;
	}

	public void setMaPhieuNhap(String maPhieuNhap) {
		this.maPhieuNhap = maPhieuNhap;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

}
