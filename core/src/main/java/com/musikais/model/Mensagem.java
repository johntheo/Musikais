package com.musikais.model;

public class Mensagem {
	public static final int SUCCESS_CODE = 0;
	public static final int ERROR_CODE = -1;
	public static final String SUCESS_MESSAGE = "SUCESSO!";
	public static final String SUCESSO_MESSAGE_APP = "Nossos androids conseguiram aprender com seu voto. Muito Obrigado!";
	public static final String ERROR_MESSAGE = "ERRO!";
	
	private int code;
	private String message;

	public Mensagem(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Mensagem [code=" + code + ", message=" + message + "]";
	}

}
