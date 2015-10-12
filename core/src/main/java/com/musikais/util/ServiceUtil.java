package com.musikais.util;

import java.util.List;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import net.aksingh.owmjapis.OpenWeatherMap.Language;
import net.aksingh.owmjapis.OpenWeatherMap.Units;

import org.jboss.logging.Logger;

import com.musikais.model.Combination;
import com.musikais.model.Geoloc;
import com.musikais.model.Periodo;
import com.musikais.model.Ponto;

public class ServiceUtil {
	private static final Logger LOGGER = Logger.getLogger(ServiceUtil.class);

	public static Ponto getPontoMedio(List<Combination> combinacoes) {
		LOGGER.info("FAZENDO CALCULO DO PONTO MEDIO"+combinacoes.toString());
		Ponto ponto = new Ponto();
		float somatorioGeometricoEnergia = 0;
		float somatorioGeometricoValencia = 0;
		int somatorioDeltas = 0;

		for (Combination comb : combinacoes) {
			LOGGER.info(comb);
			int delta = comb.getLegal() - comb.getAbsurdo();
			somatorioDeltas += delta;
			somatorioGeometricoEnergia += comb.getEnergia() * delta;
			somatorioGeometricoValencia += comb.getValencia() * delta;
			LOGGER.info("[ somatorioGeometricoEnergia= "
					+ somatorioGeometricoEnergia
					+ " | somatorioGeometricoValencia= "
					+ somatorioGeometricoValencia + " | somatorioDeltas= "
					+ somatorioDeltas + "]");
		}

		ponto.setEnergia(somatorioGeometricoEnergia / somatorioDeltas);
		ponto.setValencia(somatorioGeometricoValencia / somatorioDeltas);

		return ponto;
	}
	
	public static CurrentWeather getClimaByGeo(Geoloc location) {
		LOGGER.info("Chamando o servico de metereologia para a localizacao: "
				+ location);

		OpenWeatherMap owm = new OpenWeatherMap("");
		owm.setLang(Language.PORTUGUESE);
		owm.setUnits(Units.METRIC);
		// CurrentWeather cwd = owm.currentWeatherByCityName("Curitiba");
		CurrentWeather cwd = owm.currentWeatherByCoordinates(
				location.getLatitude(), location.getLongitude());

		return cwd;
	}

	public static Periodo getPeriodoByHora(int hora) {
		if (hora >= 5 && hora < 12) {
			return new Periodo(Periodo.MANHA, "manhã");
		} else if (hora >= 12 && hora < 18) {
			return new Periodo(Periodo.TARDE, "tarde");
		} else {
			return new Periodo(Periodo.NOITE, "noite");
		}
	}
}
