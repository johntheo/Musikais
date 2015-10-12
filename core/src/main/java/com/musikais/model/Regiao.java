package com.musikais.model;

public class Regiao {
	private int id;
	private String nome;
	private float latitude;
	private float longitude;
	private int raio;
	private String descricao;
	private int idTipoRegiao;
	private String tipoRegiao;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public int getRaio() {
		return raio;
	}

	public void setRaio(int raio) {
		this.raio = raio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getIdTipoRegiao() {
		return idTipoRegiao;
	}

	public void setIdTipoRegiao(int idTipoRegiao) {
		this.idTipoRegiao = idTipoRegiao;
	}

	public String getTipoRegiao() {
		return tipoRegiao;
	}

	public void setTipoRegiao(String tipoRegiao) {
		this.tipoRegiao = tipoRegiao;
	}

	@Override
	public String toString() {
		return "Regiao [id=" + id + ", nome=" + nome + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", raio=" + raio
				+ ", idTipoRegiao=" + idTipoRegiao + ", tipoRegiao="
				+ tipoRegiao + "]";
	}
		
}
