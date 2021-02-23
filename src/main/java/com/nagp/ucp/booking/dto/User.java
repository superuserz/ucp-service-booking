package com.nagp.ucp.booking.dto;

import com.nagp.ucp.common.enums.UserTypeEnum;

public class User {

	private int id;

	private String name;

	private String type;

	private String email;

	private String contact;

	private String city;

	private String state;

	private int pincode;

	private double walletBalance;

	private boolean isPremium;

	public User() {

	}

	public User(int id, String name, String type, String email, String contact, String city, String state, int pincode,
			double walletBalance, boolean isPremium) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.email = email;
		this.contact = contact;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.walletBalance = walletBalance;
		this.isPremium = isPremium;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserTypeEnum getType() {
		return UserTypeEnum.parse(type);
	}

	public void setType(final UserTypeEnum type) {
		this.type = type.getValue();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", type=" + type + ", email=" + email + ", contact=" + contact
				+ ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", walletBalance=" + walletBalance
				+ ", isPremium=" + isPremium + "]";
	}

}
