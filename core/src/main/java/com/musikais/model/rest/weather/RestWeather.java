package com.musikais.model.rest.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestWeather {
	private Coord cood;
	private Main main;
	private Weather weather;
	public Coord getCood() {
		return cood;
	}
	public void setCood(Coord cood) {
		this.cood = cood;
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	@Override
	public String toString() {
		return "RestWeather [cood=" + cood + ", main=" + main + ", weather="
				+ weather + "]";
	}
	
}
