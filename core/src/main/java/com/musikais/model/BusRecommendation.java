package com.musikais.model;

import java.util.List;

import net.aksingh.owmjapis.CurrentWeather;

public class BusRecommendation {
	
	private Regiao regiao;
	private CurrentWeather climaRest;
	private Clima clima;
	private List<Music> musicas;
	public Regiao getRegiao() {
		return regiao;
	}
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	public CurrentWeather getClimaRest() {
		return climaRest;
	}
	public void setClimaRest(CurrentWeather climaRest) {
		this.climaRest = climaRest;
	}
	public Clima getClima() {
		return clima;
	}
	public void setClima(Clima clima) {
		this.clima = clima;
	}
	public List<Music> getMusicas() {
		return musicas;
	}
	public void setMusicas(List<Music> musicas) {
		this.musicas = musicas;
	}
	@Override
	public String toString() {
		return "BusRecommendation [regiao=" + regiao + ", climaRest="
				+ climaRest + ", clima=" + clima + ", musicas=" + musicas + "]";
	}

}
