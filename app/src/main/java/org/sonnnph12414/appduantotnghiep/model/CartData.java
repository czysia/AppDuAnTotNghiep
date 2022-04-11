package org.sonnnph12414.appduantotnghiep.model;

import java.util.List;

public class CartData{
	private int total;
	private String userId;
	private int V;
	private String id;
	private List<com.example.appduantotnghiep.model.ProductsItem> products;

	public int getTotal(){
		return total;
	}

	public String getUserId(){
		return userId;
	}

	public int getV(){
		return V;
	}

	public String getId(){
		return id;
	}

	public List<com.example.appduantotnghiep.model.ProductsItem> getProducts(){
		return products;
	}
}