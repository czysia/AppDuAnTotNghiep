package com.example.app_ban_hang_tot_nghiep.model;

import java.util.List;

public class Product{
	private String date;
	private List<String> image;
	private String origin;
	private String certificate;
	private String weight;
	private String packaging;
	private String preserve;
	private String source;
	private int importPrice;
	private int price;
	private int V;
	private String name;
	private String ingredients;
	private String warning;
	private String _id;
	private String detail;
	private int quantily;
	private String category;

	public String getDate(){
		return date;
	}

	public List<String> getImage(){
		return image;
	}

	public String getOrigin(){
		return origin;
	}

	public String getCertificate(){
		return certificate;
	}

	public String getWeight(){
		return weight;
	}

	public String getPackaging(){
		return packaging;
	}

	public String getPreserve(){
		return preserve;
	}

	public String getSource(){
		return source;
	}

	public int getImportPrice(){
		return importPrice;
	}

	public int getPrice(){
		return price;
	}

	public int getV(){
		return V;
	}

	public String getName(){
		return name;
	}

	public String getIngredients(){
		return ingredients;
	}

	public String getWarning(){
		return warning;
	}

	public String getId(){
		return _id;
	}

	public String getDetail(){
		return detail;
	}

	public int getQuantily(){
		return quantily;
	}

	public String getCategory(){
		return category;
	}
}