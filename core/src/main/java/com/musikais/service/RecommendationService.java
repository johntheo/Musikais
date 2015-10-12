package com.musikais.service;

import java.sql.Timestamp;
import java.util.List;

import net.aksingh.owmjapis.CurrentWeather;

import org.jboss.logging.Logger;

import com.musikais.dao.RecommendationDAO;
import com.musikais.model.BusRecommendation;
import com.musikais.model.Clima;
import com.musikais.model.Combination;
import com.musikais.model.Geoloc;
import com.musikais.model.Mensagem;
import com.musikais.model.Music;
import com.musikais.model.Periodo;
import com.musikais.model.Ponto;
import com.musikais.model.Recommendation;
import com.musikais.model.Regiao;
import com.musikais.util.ServiceUtil;

public class RecommendationService {

	public final String SUCCESS = "Sucesso!";
	private static final Logger LOGGER = Logger
			.getLogger(RecommendationService.class);

	private RecommendationDAO dao;

	public RecommendationService() {
		dao = new RecommendationDAO();
	}

	public Recommendation getMusicRecommendation(Geoloc location) {
		Recommendation recommendation = new Recommendation();

		// TODO: fazer o serviço para quando nao for onibus
		recommendation.setNomeMusica("DUMMY DUMMYD DUMMY");
		return recommendation;
	}

	public BusRecommendation getBusRecommendation(String horario,
			Geoloc location) {
		BusRecommendation recommendation = new BusRecommendation();
		Regiao regiao = dao.getRegiaoMaisProxima(location);
		LOGGER.info("REGIAO OBTIDA: " + regiao);

		CurrentWeather climaRest = ServiceUtil.getClimaByGeo(location);
		LOGGER.info("CLIMA OBTIDO: "
				+ climaRest.getWeatherInstance(0).getWeatherName() + " | "
				+ climaRest.getWeatherInstance(0).getWeatherDescription()
				+ " | " + climaRest.getWeatherInstance(0).getWeatherIconName());
		Clima clima = dao.getClimaByClimaRest(climaRest.getWeatherInstance(0)
				.getWeatherCode());
		LOGGER.info("CLIMA OBTIDO: " + clima);

		Periodo periodo = dao.getPeriodoByHora(horario);
		LOGGER.info("PERIODO OBTIDO: " + periodo);

		List<Combination> combinacoes = dao.getCombinacoes(regiao, clima,
				periodo);
		if (combinacoes == null) {
			LOGGER.error("NENHUMA COMBINAÇAO DE MUSICA ENCONTRADA!");
			return null;
		}
		Ponto ponto = ServiceUtil.getPontoMedio(combinacoes);
		ponto.setDescricao("[" + regiao.getTipoRegiao() + "|" + clima.getNome()
				+ "|" + clima.getNome() + "]");
		LOGGER.info("PONTO MEDIO OBTIDO: " + ponto);

		List<Music> musicas = dao.getMusicsByPonto(ponto);

		recommendation.setRegiao(regiao);
		recommendation.setClimaRest(climaRest);
		recommendation.setClima(clima);
		recommendation.setMusicas(musicas);

		return recommendation;
	}

	public Ponto getPontoParaContexto(String tipoRegiao, String clima,
			String periodo) {
		Ponto ponto = new Ponto();

		List<Combination> combinacoes = dao.getCombinacoes(tipoRegiao, clima,
				periodo);
		ponto = ServiceUtil.getPontoMedio(combinacoes);
		ponto.setDescricao("[" + tipoRegiao + "|" + clima + "|" + periodo + "]");

		return ponto;
	}

	public Mensagem setMusicOnBus(String idOnibus, int idMotorista,
			String idMusica, float latitude, float longitude, long timestamp) {
		dao.insertMusicOnBus(idOnibus, idMotorista, idMusica, latitude,
				longitude, new Timestamp(timestamp));

		return new Mensagem(Mensagem.SUCCESS_CODE,Mensagem.SUCESS_MESSAGE);
	}

	public Music getCurrentMusicOnBus(String idOnibus) {
		return dao.getCurrentMusicOnBus(idOnibus);
	}

	public String rateMusicRecommendation(int idEmocao, int idRegiao,
			int idVoto, int idClima, int idPeriodo, String fonte) {

		// TODO: fazer o rate da forma correta
		dao.rateRecommendation(idEmocao, idRegiao, idVoto, idClima, idPeriodo,
				fonte);

		return this.SUCCESS;
	}

	public List<Regiao> getRegioes() {
		return dao.getRegioes();
	}

}
