package com.migi.model;

import java.util.ArrayList;

//import org.springframework.web.multipart.MultipartFile;

public class FileDTO {
	public String productName;
	public String image;
	public float price;
	public float priceFirst;
	public int number;
	public ArrayList<String> listSize;
	public ArrayList<String> listColor;
	public String description;
	@Override
	public String toString() {
		return productName+"-"+image+"-"+price+"-"+listSize;
		
	}
	 
//	public MultipartFile image;
//	public String path;
}
