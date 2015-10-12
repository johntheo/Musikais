package com.musikais.model;

public class Ponto {
	float energia;
	float valencia;
	float raio = (float) 0.1;
	String descricao;
	
	public Ponto() {
		super();
	}
	public Ponto(float energia, float valencia) {
		super();
		this.energia = energia;
		this.valencia = valencia;
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
	public float getRaio() {
		return raio;
	}
	public void setRaio(float raio) {
		this.raio = raio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Ponto [energia=" + energia + ", valencia=" + valencia
				+ ", raio=" + raio + ", descricao=" + descricao + "]";
	}
	
}
