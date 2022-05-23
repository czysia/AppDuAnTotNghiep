package com.example.app_ban_hang_tot_nghiep.model;

import java.io.Serializable;
import java.util.List;

public class ResponeBill implements Serializable {
	private String date;
	private boolean transporting;
	private String finishAt;
	private String userAddress;
	private boolean payment_status;
	private String startAt;
	private boolean bill_status;
	private List<ItemProductCart> products;
	private String feedback;
	private int total;
	private String paymentType;
	private String userId;
	private String verifier;
	private Object transporter;
	private int V;
	private String _id;
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
		return payment_status;
	}

	public String getStartAt(){
		return startAt;
	}

	public boolean isBillStatus(){
		return bill_status;
	}

	public List<ItemProductCart> getProducts(){
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
		return _id;
	}

	public String getUsername(){
		return username;
	}
}