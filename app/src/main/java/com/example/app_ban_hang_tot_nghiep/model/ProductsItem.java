package com.example.app_ban_hang_tot_nghiep.model;

public class ProductsItem{
	private int amount;
	private int price;
	private String variant_id;
	private String product_name;

	public int getAmount(){
		return amount;
	}

	public int getPrice(){
		return price;
	}

	public String getProductId(){
		return variant_id;
	}

	public String getProductName(){
		return product_name;
	}
}
