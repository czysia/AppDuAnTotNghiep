package com.example.app_ban_hang_tot_nghiep.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserInfo{

	@SerializedName("address")
	private String address;

	@SerializedName("active")
	private boolean active;

	@SerializedName("permission")
	private String permission;

	@SerializedName("isAdmin")
	private boolean isAdmin;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("token")
	private String token;

	@SerializedName("password")
	private String password;

	@SerializedName("phone")
	private String phone;

	@SerializedName("__v")
	private int V;

	@SerializedName("name")
	private String name;

	@SerializedName("_id")
	private String id;

	@SerializedName("favorite")
	private List<Object> favorite;

	@SerializedName("email")
	private String email;

	public String getAddress(){
		return address;
	}

	public boolean isActive(){
		return active;
	}

	public String getPermission(){
		return permission;
	}

	public boolean isIsAdmin(){
		return isAdmin;
	}

	public String getAvatar(){
		return avatar;
	}

	public String getToken(){
		return token;
	}

	public String getPassword(){
		return password;
	}

	public String getPhone(){
		return phone;
	}

	public int getV(){
		return V;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public List<Object> getFavorite(){
		return favorite;
	}

	public String getEmail(){
		return email;
	}
}