package com.musikais.model;

public class Geoloc {
	private Float latitude;
	private Float longitude;

	public Geoloc(Float longitude, Float latitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Geoloc [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}

}
