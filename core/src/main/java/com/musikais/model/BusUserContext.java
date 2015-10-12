package com.musikais.model;

import net.aksingh.owmjapis.CurrentWeather;

public class BusUserContext {
	private Regiao regiao;
	private Clima clima;
	private CurrentWeather climaRest;
	private Periodo periodo;
	private Music musica;

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public Clima getClima() {
		return clima;
	}

	public void setClima(Clima clima) {
		this.clima = clima;
	}

	public CurrentWeather getClimaRest() {
		return climaRest;
	}

	public void setClimaRest(CurrentWeather climaRest) {
		this.climaRest = climaRest;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Music getMusica() {
		return musica;
	}

	public void setMusica(Music musica) {
		this.musica = musica;
	}

	@Override
	public String toString() {
		return "BusUserContext [regiao=" + regiao + ", clima=" + clima
				+ ", climaRest=" + climaRest + ", periodo=" + periodo
				+ ", musica=" + musica + "]";
	}

}
