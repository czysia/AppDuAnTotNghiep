package com.example.app_ban_hang_tot_nghiep.model;

import java.util.List;

public class UserRespone{
	private String address;
	private boolean active;
	private String permission;
	private boolean isAdmin;
	private String avatar;
	private String token;
	private String password;
	private String phone;
	private int V;
	private String name;
	private String id;
	private List<Object> favorite;
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