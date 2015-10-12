package com.musikais.model;

public class Dummy {
	private String userId;
	private String subscriberId;
	
	public Dummy(){
		this.userId = "1105";
		this.subscriberId = "TV-CTA-123456-050";
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	
	
}
