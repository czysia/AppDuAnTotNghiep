package com.example.app_ban_hang_tot_nghiep.model;

import java.util.List;

public class CartData{
	private int total;
	private String userId;
	private int V;
	private String id;
	private List<ProductsItem> products;

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

	public List<ProductsItem> getProducts(){
		return products;
	}
}