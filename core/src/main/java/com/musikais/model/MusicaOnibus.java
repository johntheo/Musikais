package com.musikais.model;

import java.sql.Timestamp;

public class MusicaOnibus {
	private String idOnibus;
	private int idMotorista;
	private String idMusica;
	private float latitude;
	private float longitude;
	private Timestamp data;

	public String getIdOnibus() {
		return idOnibus;
	}

	public void setIdOnibus(String idOnibus) {
		this.idOnibus = idOnibus;
	}

	public int getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(int idMotorista) {
		this.idMotorista = idMotorista;
	}

	public String getIdMusica() {
		return idMusica;
	}

	public void setIdMusica(String idMusica) {
		this.idMusica = idMusica;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "MusicaOnibus [idOnibus=" + idOnibus + ", idMotorista="
				+ idMotorista + ", idMusica=" + idMusica + ", latitude="
				+ latitude + ", longitude=" + longitude + ", data=" + data
				+ "]";
	}

}
