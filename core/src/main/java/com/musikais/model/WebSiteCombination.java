package com.musikais.model;

public class WebSiteCombination {
	
	private int idMusica; 
	private String nomeMusica; 
	private String caminhoMusica;
	private int idEmocao; 
	private String nomeEmocao; 
	private String descricaoEmocao;
	private String fonteMusica;
	private String imagem; 
	private int idRegiao; 
	private String regiao;
	private int idTipo;
	private String tipo;
	private int idClima;
	private String clima;
	private int idPeriodo;
	private String periodo;
	
	public int getIdMusica() {
		return idMusica;
	}
	public void setIdMusica(int idMusica) {
		this.idMusica = idMusica;
	}
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
	public int getIdEmocao() {
		return idEmocao;
	}
	public void setIdEmocao(int idEmocao) {
		this.idEmocao = idEmocao;
	}
	public String getNomeEmocao() {
		return nomeEmocao;
	}
	public void setNomeEmocao(String nomeEmocao) {
		this.nomeEmocao = nomeEmocao;
	}
	public String getDescricaoEmocao() {
		return descricaoEmocao;
	}
	public void setDescricaoEmocao(String descricaoEmocao) {
		this.descricaoEmocao = descricaoEmocao;
	}
	public String getFonteMusica() {
		return fonteMusica;
	}
	public void setFonteMusica(String fonteMusica) {
		this.fonteMusica = fonteMusica;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public int getIdRegiao() {
		return idRegiao;
	}
	public void setIdRegiao(int idRegiao) {
		this.idRegiao = idRegiao;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getIdClima() {
		return idClima;
	}
	public void setIdClima(int idClima) {
		this.idClima = idClima;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public int getIdPeriodo() {
		return idPeriodo;
	}
	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	@Override
	public String toString() {
		return "WebSiteCombination [idMusica=" + idMusica + ", nomeMusica="
				+ nomeMusica + ", caminhoMusica=" + caminhoMusica
				+ ", idEmocao=" + idEmocao + ", nomeEmocao=" + nomeEmocao
				+ ", descricaoEmocao=" + descricaoEmocao + ", fonteMusica="
				+ fonteMusica + ", imagem=" + imagem + ", idRegiao=" + idRegiao
				+ ", regiao=" + regiao + ", idTipo=" + idTipo + ", tipo="
				+ tipo + ", idClima=" + idClima + ", clima=" + clima
				+ ", idPeriodo=" + idPeriodo + ", periodo=" + periodo + "]";
	}
	
}
