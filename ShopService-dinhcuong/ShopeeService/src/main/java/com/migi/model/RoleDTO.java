package com.migi.model;

public class RoleDTO {
	private int id;
	private String Name;
	private int Code;

	public RoleDTO() {
		super();
	}

	public RoleDTO(int id, String name, int code) {
		super();
		this.id = id;
		Name = name;
		Code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getCode() {
		return Code;
	}

	public void setCode(int code) {
		Code = code;
	}

}
