package com.musikais.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.musikais.model.BusRecommendation;
import com.musikais.model.Geoloc;
import com.musikais.model.Mensagem;
import com.musikais.model.Music;
import com.musikais.model.Ponto;
import com.musikais.model.Recommendation;
import com.musikais.model.Regiao;
import com.musikais.service.RecommendationService;

@RestController
public class RecommendationController {
	private static final Logger LOGGER = Logger
			.getLogger(RecommendationService.class);

	RecommendationService recommendationService = new RecommendationService();

	@RequestMapping(value = "/recommendation/get/{longitude}/{latitude}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Recommendation getMusicRecommendation(
			@PathVariable String longitude, @PathVariable String latitude) {
		Geoloc location = new Geoloc(Float.parseFloat(longitude),
				Float.parseFloat(latitude));

		// DUMMY
		// Recommendation recommendation = new Recommendation();
		// recommendation.setMusicName("TESTANDO");
		return recommendationService.getMusicRecommendation(location);
	}

	@RequestMapping(value = "/recommendation/get/lat={latitude}&lon={longitude}&horario={horario}", method = RequestMethod.GET, headers = "Accept=application/json")
	public BusRecommendation getMusicRecommendation(
			@PathVariable String horario, @PathVariable String longitude,
			@PathVariable String latitude) {
		LOGGER.info("latitude=" + latitude);
		LOGGER.info("longitude=" + longitude);
		Geoloc location = new Geoloc(Float.parseFloat(longitude),
				Float.parseFloat(latitude));

		LOGGER.info("SERVIÇO getMusicRecommendation /recommendation/get/lat="
				+ latitude + "&lon=" + longitude + "&horario=" + horario);
		return recommendationService.getBusRecommendation(horario, location);
	}

	@RequestMapping(value = "/recommendation/get/pontos", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Ponto> getPontosContexto() {
		List<Ponto> pontos = new ArrayList<Ponto>();
		List<String> regioes = new ArrayList<String>(Arrays.asList("moderno",
				"antigo", "selvagem", "urbano", "parque", "litoral"));
		List<String> climas = new ArrayList<String>(Arrays.asList("ensolarado",
				"nublado", "chuvoso"));
		List<String> periodos = new ArrayList<String>(Arrays.asList("manhã",
				"tarde", "noite"));

		for (String regiao : regioes) {
			for (String clima : climas) {
				for (String periodo : periodos) {
					pontos.add(recommendationService.getPontoParaContexto(
							regiao, clima, periodo));
				}
			}
		}

		return pontos;
	}

	@RequestMapping(value = "/recommendation/set/idOnibus={idOnibus}&idMotorista={idMotorista}&idMusica={idMusica}&lat={latitude}&lon={longitude}&timestamp={timestamp}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Mensagem setMusicBus(@PathVariable String idOnibus,
			@PathVariable int idMotorista, @PathVariable String idMusica,
			@PathVariable float latitude, @PathVariable float longitude,
			@PathVariable long timestamp) {
		return recommendationService.setMusicOnBus(idOnibus, idMotorista,
				idMusica, latitude, longitude, timestamp);
	}

	@RequestMapping(value = "/recommendation/get/idOnibus={idOnibus}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Music getCurrentMusicOnBus(@PathVariable String idOnibus) {
		return recommendationService.getCurrentMusicOnBus(idOnibus);
	}

	@RequestMapping(value = "/recommendation/get/regions", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Regiao> getRegions() {
		return recommendationService.getRegioes();
	}

}
