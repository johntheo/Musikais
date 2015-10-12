package com.musikais.model;

public class Recommendation {
	private String nomeMusica;
	private String caminhoMusica;
	private String nomeRegiao;
	private String tipoRegiao;
	
	public String getNomeMusica() {
		return nomeMusica;
	}
	public void setNomeMusica(String nomeMusica) {
		this.nomeMusica = nomeMusica;
	}
	public String getCaminhoMusica() {
		return caminhoMusica;
	}
	public void setCaminhoMusica(String caminhoMusica) {
		this.caminhoMusica = caminhoMusica;
	}
	public String getNomeRegiao() {
		return nomeRegiao;
	}
	public void setNomeRegiao(String nomeRegiao) {
		this.nomeRegiao = nomeRegiao;
	}
	public String getTipoRegiao() {
		return tipoRegiao;
	}
	public void setTipoRegiao(String tipoRegiao) {
		this.tipoRegiao = tipoRegiao;
	}
	@Override
	public String toString() {
		return "Recommendation [nomeMusica=" + nomeMusica + ", caminhoMusica="
				+ caminhoMusica + ", nomeRegiao=" + nomeRegiao
				+ ", tipoRegiao=" + tipoRegiao + "]";
	}

}
