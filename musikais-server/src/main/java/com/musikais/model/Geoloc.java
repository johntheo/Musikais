package com.musikais.model;

public class Geoloc {
	private int longitude;
	private int latitude;
	
	public Geoloc(int longitude, int latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public int getLongitude() {
		return longitude;
	}
	
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
	public int getLatitude() {
		return latitude;
	}
	
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString() {
		return "Geoloc [longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}
}
