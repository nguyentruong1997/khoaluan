package com.migi.model;

public class CommentDTO {
	private int id;
	private String maSanPham;
	private String Context;

	public CommentDTO() {
		super();
	}

	public CommentDTO(int id, String maSanPham, String context) {
		super();
		this.id = id;
		this.maSanPham = maSanPham;
		Context = context;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getContext() {
		return Context;
	}

	public void setContext(String context) {
		Context = context;
	}

}
