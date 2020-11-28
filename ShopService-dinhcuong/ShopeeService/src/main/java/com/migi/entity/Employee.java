package com.migi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
  

@Entity(name="nhanvien")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String manhanvien;
	@Column(name="diachi")
	private String diachi;
	@Column(name="dienthoai")
	private String dienthoai;
 
	private String ho;
 
	private BigDecimal luongcoban;
	private BigDecimal phucap;
	private String ten;
	@Temporal(TemporalType.DATE)
	private Date ngaylamviec;

	@Temporal(TemporalType.DATE)
	private Date ngaysinh;

	public Employee() {
	}

	public String getManhanvien() {
		return this.manhanvien;
	}

	public void setManhanvien(String manhanvien) {
		this.manhanvien = manhanvien;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getDienthoai() {
		return dienthoai;
	}

	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public BigDecimal getLuongcoban() {
		return luongcoban;
	}

	public void setLuongcoban(BigDecimal luongcoban) {
		this.luongcoban = luongcoban;
	}

	public Date getNgaylamviec() {
		return ngaylamviec;
	}

	public void setNgaylamviec(Date ngaylamviec) {
		this.ngaylamviec = ngaylamviec;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public BigDecimal getPhucap() {
		return phucap;
	}

	public void setPhucap(BigDecimal phucap) {
		this.phucap = phucap;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}
	
	

}