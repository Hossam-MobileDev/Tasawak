package com.hossam.elabda3.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeliveryAddressListRoot {

	@SerializedName("data")
	private List<DeliveryAddress> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<DeliveryAddress> getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public boolean isStatus() {
		return status;
	}
}