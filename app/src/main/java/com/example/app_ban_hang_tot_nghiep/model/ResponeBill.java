package com.example.app_ban_hang_tot_nghiep.model;

import java.util.List;

public class ResponeBill{
	private String date;
	private boolean transporting;
	private String finishAt;
	private String userAddress;
	private boolean paymentStatus;
	private String startAt;
	private boolean billStatus;
	private List<ProductsItem> products;
	private String feedback;
	private int total;
	private String paymentType;
	private String userId;
	private String verifier;
	private Object transporter;
	private int V;
	private String id;
	private String username;

	public String getDate(){
		return date;
	}

	public boolean isTransporting(){
		return transporting;
	}

	public String getFinishAt(){
		return finishAt;
	}

	public String getUserAddress(){
		return userAddress;
	}

	public boolean isPaymentStatus(){
		return paymentStatus;
	}

	public String getStartAt(){
		return startAt;
	}

	public boolean isBillStatus(){
		return billStatus;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}

	public String getFeedback(){
		return feedback;
	}

	public int getTotal(){
		return total;
	}

	public String getPaymentType(){
		return paymentType;
	}

	public String getUserId(){
		return userId;
	}

	public String getVerifier(){
		return verifier;
	}

	public Object getTransporter(){
		return transporter;
	}

	public int getV(){
		return V;
	}

	public String getId(){
		return id;
	}

	public String getUsername(){
		return username;
	}
}