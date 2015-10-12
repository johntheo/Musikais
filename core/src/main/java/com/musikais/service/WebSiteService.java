package com.musikais.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.musikais.model.Recommendation;
import com.musikais.model.WebSiteCombination;
import com.musikais.util.DBUtility;

public class WebSiteService {

	public final String SUCCESS = "Sucesso!";
	
	private static final Logger LOGGER = Logger.getLogger(WebSiteService.class);

	private Connection connection;

	private final String GET_MUSICA = "select musica.id as idMusica, "
			+ "musica.nome as nomeMusica, "
			+ "musica.caminho as caminhoMusica, "
			+ "emocao_musica.id as idEmocao, "
			+ "emocao_musica.nome as nomeEmocao, "
			+ "emocao_musica.descricao as descricaoEmocao, "
			+ "fonte_musica.descricao as fonteMusica from musica "
			+ "join emocao_musica on musica.idEmocao = emocao_musica.id "
			+ "join fonte_musica on musica.idFonte = fonte_musica.id "
			+ "order by rand() limit 1";

	private final String GET_REGIAO = "select imagem_regiao.nome as imagem, "
			+ "regiao.id as idRegiao, regiao.nome as regiao,"
			+ "tipo_regiao.id as idTipo, tipo_regiao.nome as tipo, "
			+ "clima.id as idClima, clima.nome as clima,"
			+ "periodo.id as idPeriodo, periodo.nome as periodo "
			+ "from imagem_regiao "
			+ "join regiao on imagem_regiao.idRegiao = regiao.id "
			+ "join tipo_regiao on imagem_regiao.idTipo = tipo_regiao.id "
			+ "join clima on imagem_regiao.idClima = clima.id "
			+ "join periodo on imagem_regiao.idPeriodo = periodo.id "
			+ "order by rand() limit 1";

	public WebSiteService() {
		try {
			connection = DBUtility.dataSourceOld().getConnection();
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public WebSiteCombination getCombination() {
		WebSiteCombination combination = new WebSiteCombination();

		putMusicOnCombination(combination);
		putRegionOnCombination(combination);
		
		LOGGER.info("RESULTADO: \n"+combination.toString());
		
		return combination;
	}

	private void putMusicOnCombination(WebSiteCombination combination) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(GET_MUSICA);
			
			LOGGER.info("GET MUSICA: "+ GET_MUSICA);
			
			if (rs.next()) {
				combination.setIdMusica(rs.getInt("idMusica"));
				combination.setNomeMusica(rs.getString("nomeMusica"));
				combination.setCaminhoMusica(rs.getString("caminhoMusica"));
				combination.setIdEmocao(rs.getInt("idEmocao"));
				combination.setNomeEmocao(rs.getString("nomeEmocao"));
				combination.setDescricaoEmocao(rs.getString("descricaoEmocao"));
				combination.setFonteMusica(rs.getString("fonteMusica"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void putRegionOnCombination(WebSiteCombination combination) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(GET_REGIAO);
			
			LOGGER.info("GET REGIAO:"+GET_REGIAO);
			if (rs.next()) {
				
				combination.setImagem(rs.getString("imagem"));
				combination.setIdRegiao(rs.getInt("idRegiao"));
				combination.setRegiao(rs.getString("regiao"));
				combination.setIdTipo(rs.getInt("idTipo"));
				combination.setTipo(rs.getString("tipo"));
				combination.setIdClima(rs.getInt("idClima"));
				combination.setClima(rs.getString("clima"));
				combination.setIdPeriodo(rs.getInt("idPeriodo"));
				combination.setPeriodo(rs.getString("periodo"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String rateMusicRecommendation(int idRecommendation, String vote) {

		// TODO: adicionar voto na tabela de recomendações

		return this.SUCCESS;
	}

	public List<Recommendation> getRegionRecommendation(int idRegion) {
		List<Recommendation> recommendations = new ArrayList<Recommendation>();

		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select nome from musica");

			while (rs.next()) {
				Recommendation recommendation = new Recommendation();
				recommendation.setNomeMusica(rs.getString("nome"));

				recommendations.add(recommendation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recommendations;
	}
}
