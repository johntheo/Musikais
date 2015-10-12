package com.musikais.model;

public class Periodo {
	int id;
	String nome;
	public static final int MANHA = 1;
	public static final int TARDE = 2;
	public static final int NOITE = 3;
	public Periodo(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
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
	@Override
	public String toString() {
		return "Periodo [id=" + id + ", nome=" + nome + "]";
	}
}
