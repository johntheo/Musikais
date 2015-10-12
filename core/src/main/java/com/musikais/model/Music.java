package com.musikais.model;

public class Music {
	private String id;
	private String nomeArtista;
	private String nomeMusica;
	private float energia;
	private float valencia;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomeArtista() {
		return nomeArtista;
	}
	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
	}
	public String getNomeMusica() {
		return nomeMusica;
	}
	public void setNomeMusica(String nomeMusica) {
		this.nomeMusica = nomeMusica;
	}
	public float getEnergia() {
		return energia;
	}
	public void setEnergia(float energia) {
		this.energia = energia;
	}
	public float getValencia() {
		return valencia;
	}
	public void setValencia(float valencia) {
		this.valencia = valencia;
	}
	@Override
	public String toString() {
		return "Music [id=" + id + ", nomeArtista=" + nomeArtista
				+ ", nomeMusica=" + nomeMusica + ", energia=" + energia
				+ ", valencia=" + valencia + "]";
	}

}
