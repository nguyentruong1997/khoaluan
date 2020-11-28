package com.migi.model;

public class ProductTypeDTO {
	private int typeId;
	private String name;
	private int userId;
	private int parentId;
	private int codeSearch;
	private String nameSearch;
	private String parentName;
	
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getNameSearch() {
		return nameSearch;
	}
	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}
	public int getCodeSearch() {
		return codeSearch;
	}
	public void setCodeSearch(int codeSearch) {
		this.codeSearch = codeSearch;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	 

}
