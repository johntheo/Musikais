package com.musikais.model;

public class Motorista {
	private int id;
	private String nome;
	private String avatar;

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "Motorista [id=" + id + ", nome=" + nome + ", avatar=" + avatar
				+ "]";
	}

}
