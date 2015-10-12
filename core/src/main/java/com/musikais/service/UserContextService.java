package com.musikais.service;

import net.aksingh.owmjapis.CurrentWeather;

import org.jboss.logging.Logger;

import com.musikais.dao.RecommendationDAO;
import com.musikais.model.BusUserContext;
import com.musikais.model.Clima;
import com.musikais.model.Geoloc;
import com.musikais.model.Mensagem;
import com.musikais.model.Music;
import com.musikais.model.MusicaOnibus;
import com.musikais.model.Periodo;
import com.musikais.model.Regiao;
import com.musikais.util.ServiceUtil;

public class UserContextService {
	public final String SUCCESS = "Sucesso!";
	public final String SUCCESS_VOTE = "Nosso androids aprenderam com o seu voto. Muito obrigado!";
	private static final Logger LOGGER = Logger
			.getLogger(UserContextService.class);
	private RecommendationDAO dao;

	public UserContextService() {
		dao = new RecommendationDAO();
	}

	@SuppressWarnings("deprecation")
	public BusUserContext getBusUserContext(String idOnibus) {
		BusUserContext contexto = new BusUserContext();

		MusicaOnibus onibus = dao.getMusicaOnibusByOnibus(idOnibus);

		Geoloc location = new Geoloc(onibus.getLongitude(),
				onibus.getLatitude());
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

		int hora = onibus.getData().getHours();
		Periodo periodo = ServiceUtil.getPeriodoByHora(hora);

		Music musica = dao.getCurrentMusicOnBus(idOnibus);

		contexto.setRegiao(regiao);
		contexto.setClima(clima);
		contexto.setClimaRest(climaRest);
		contexto.setPeriodo(periodo);
		contexto.setMusica(musica);

		return contexto;
	}

	public Mensagem rateBusUserContext(String idOnibus, int idVoto) {
		String voto = "Legal";
		if (idVoto != 1) {
			voto = "Absurdo";
		}

		BusUserContext contexto = getBusUserContext(idOnibus);

		dao.insertCombinacao(contexto.getRegiao().getIdTipoRegiao(), contexto.getClima()
				.getId(), contexto.getPeriodo().getId(), idVoto, contexto
				.getMusica().getEnergia(), contexto.getMusica().getValencia());

		dao.insertVotoHistorico(contexto.getMusica().getNomeMusica(), contexto
				.getPeriodo().getNome(), contexto.getClima().getNome(),
				contexto.getRegiao().getNome(), contexto.getRegiao()
						.getTipoRegiao(), voto, contexto.getRegiao()
						.getLatitude(), contexto.getRegiao().getLongitude(),
				contexto.getMusica().getEnergia(), contexto.getMusica()
						.getValencia());

		return new Mensagem(Mensagem.SUCCESS_CODE,Mensagem.SUCESSO_MESSAGE_APP);
	}
}
