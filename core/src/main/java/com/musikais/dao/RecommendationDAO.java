package com.musikais.dao;

import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.musikais.model.Clima;
import com.musikais.model.Combination;
import com.musikais.model.Geoloc;
import com.musikais.model.Music;
import com.musikais.model.MusicaOnibus;
import com.musikais.model.Periodo;
import com.musikais.model.Ponto;
import com.musikais.model.Regiao;
import com.musikais.util.DBUtility;

public class RecommendationDAO {
    private static Connection connection;
    private static final Logger LOGGER = Logger
    .getLogger(RecommendationDAO.class);
    
    private static final String ALL_REGIONS = "select r.*,tr.nome as tipoRegiao from regiao r join tipo_regiao tr on tr.id = r.idTipoRegiao";
    private static final String GET_MUSIC_BY_PONTO = "SELECT id, nomeArtista, nomeMusica, energia, valencia FROM musikais_curitiba.musica_echonest where energia > ? and energia < ? and valencia > ? and valencia < ?";
    private static final String GET_CLIMA_BY_REST = "select cc.id, cc.nome from clima cc	join clima_rest cr on cr.idCLima = cc.id where cr.id = ?";
    private static final String INSERT_MUSIC_ON_BUS = "insert into musica_onibus_historico values (?,?,?,?,?,?)";
    private static final String CURRENT_MUSIC_ON_BUS = "SELECT me.* from musica_onibus_historico moh JOIN musica_echonest me on me.id = moh.idMusica where idOnibus = ? order by data desc limit 1";
    private static final String CURRENT_MUSICA_ONIBUS = "SELECT * from musica_onibus_historico where idOnibus = ? order by data desc limit 1";
    private static final String INSERT_COMBINACAO = "insert into combinacoes(idRegiao,idClima,idPeriodo,idVoto,energiaMusica,valenciaMusica,data) values (?,?,?,?,?,?,?)";
    private static final String INSERT_VOTO_HISTORICO = "insert into voto_historico(musica,periodo,clima,regiao,tipoRegiao,voto,latitude,longitude,energiaMusica,valenciaMusica,data) values (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_BY_CONTEXT = "SELECT energia,valencia,legal,absurdo FROM"
    + "	(SELECT T1.regiao,T1.clima,T1.periodo,T1.nomeArtista,T1.nomeMusica, T1.energia, T1.valencia, T1.total as legal, (case when T2.total is null then 0 else T2.total end) as absurdo FROM"
    + "		(SELECT tr.nome regiao, cr.nome clima, pr.nome periodo, tv.nome voto, me.nomeArtista, me.nomeMusica, me.energia, me.valencia, count(idVoto) total"
    + "			FROM musikais_curitiba.combinacoes cc"
    + "			JOIN musikais_curitiba.tipo_regiao tr ON tr.id = cc.idRegiao"
    + "			JOIN musikais_curitiba.clima cr ON cr.id = cc.idClima"
    + "			JOIN musikais_curitiba.periodo pr ON pr.id = cc.idPeriodo"
    + "			JOIN musikais_curitiba.tipo_voto tv ON tv.id = cc.idVoto"
    + "			JOIN musikais_curitiba.musica_echonest me on me.energia = cc.energiaMusica and cc.valenciaMusica"
    + "				WHERE cc.idVoto = 1"
    + "				group by cc.idRegiao,cc.idClima,cc.idPeriodo, cc.idVoto, me.nomeMusica, me.nomeArtista) T1"
    + "		left JOIN"
    + "		(SELECT tr.nome regiao, cr.nome clima, pr.nome periodo, tv.nome voto, me.nomeArtista, me.nomeMusica, me.energia, me.valencia, count(idVoto) total"
    + "			FROM musikais_curitiba.combinacoes cc"
    + "			JOIN musikais_curitiba.tipo_regiao tr ON tr.id = cc.idRegiao"
    + "			JOIN musikais_curitiba.clima cr ON cr.id = cc.idClima"
    + "			JOIN musikais_curitiba.periodo pr ON pr.id = cc.idPeriodo"
    + "			JOIN musikais_curitiba.tipo_voto tv ON tv.id = cc.idVoto"
    + "			JOIN musikais_curitiba.musica_echonest me on me.energia = cc.energiaMusica and cc.valenciaMusica"
    + "				WHERE cc.idVoto = 2"
    + "				group by cc.idRegiao,cc.idClima,cc.idPeriodo, cc.idVoto, me.nomeMusica, me.nomeArtista) T2"
    + "		ON T1.regiao = T2.regiao and T1.clima = T2.clima and T1.periodo = T2.periodo and T1.nomeMusica = T2.nomeMusica"
    + "		UNION"
    + "		SELECT T2.regiao,T2.clima,T2.periodo,T2.nomeArtista,T2.nomeMusica, T2.energia, T2.valencia, (case when T1.total is null then 0 else T1.total end) as legal, T2.total as absurdo  FROM"
    + "		(SELECT tr.nome regiao, cr.nome clima, pr.nome periodo, tv.nome voto, me.nomeArtista, me.nomeMusica, me.energia, me.valencia, count(idVoto) total"
    + "			FROM musikais_curitiba.combinacoes cc"
    + "			JOIN musikais_curitiba.tipo_regiao tr ON tr.id = cc.idRegiao"
    + "			JOIN musikais_curitiba.clima cr ON cr.id = cc.idClima"
    + "			JOIN musikais_curitiba.periodo pr ON pr.id = cc.idPeriodo"
    + "			JOIN musikais_curitiba.tipo_voto tv ON tv.id = cc.idVoto"
    + "			JOIN musikais_curitiba.musica_echonest me on me.energia = cc.energiaMusica and cc.valenciaMusica"
    + "				WHERE cc.idVoto = 1"
    + "				group by cc.idRegiao,cc.idClima,cc.idPeriodo, cc.idVoto, me.nomeMusica, me.nomeArtista) T1"
    + "		RIGHT JOIN"
    + "		(SELECT tr.nome regiao, cr.nome clima, pr.nome periodo, tv.nome voto, me.nomeArtista, me.nomeMusica, me.energia, me.valencia, count(idVoto) total"
    + "			FROM musikais_curitiba.combinacoes cc"
    + "			JOIN musikais_curitiba.tipo_regiao tr ON tr.id = cc.idRegiao"
    + "			JOIN musikais_curitiba.clima cr ON cr.id = cc.idClima"
    + "			JOIN musikais_curitiba.periodo pr ON pr.id = cc.idPeriodo"
    + "			JOIN musikais_curitiba.tipo_voto tv ON tv.id = cc.idVoto"
    + "			JOIN musikais_curitiba.musica_echonest me on me.energia = cc.energiaMusica and cc.valenciaMusica"
    + "				WHERE cc.idVoto = 2"
    + "				group by cc.idRegiao,cc.idClima,cc.idPeriodo, cc.idVoto, me.nomeMusica, me.nomeArtista) T2"
    + "		ON T1.regiao = T2.regiao and T1.clima = T2.clima and T1.periodo = T2.periodo and T1.nomeMusica = T2.nomeMusica"
    + "	) AS FINAL where legal > absurdo and regiao = ? and clima = ? and periodo = ?  order by legal desc";
    private static final String GET_BY_REGION = "SELECT energia, valencia, legal, absurdo FROM "
    + "			(SELECT T1.regiao,T1.clima,T1.periodo,T1.nomeArtista,T1.nomeMusica, T1.energia, T1.valencia, T1.total as legal, (case when T2.total is null then 0 else T2.total end) as absurdo FROM"
    + "					(SELECT tr.nome regiao, cr.nome clima, pr.nome periodo, tv.nome voto, me.nomeArtista, me.nomeMusica, me.energia, me.valencia, count(idVoto) total"
    + "						FROM musikais_curitiba.combinacoes cc"
    + "						JOIN musikais_curitiba.tipo_regiao tr ON tr.id = cc.idRegiao"
    + "						JOIN musikais_curitiba.clima cr ON cr.id = cc.idClima"
    + "						JOIN musikais_curitiba.periodo pr ON pr.id = cc.idPeriodo"
    + "						JOIN musikais_curitiba.tipo_voto tv ON tv.id = cc.idVoto"
    + "						JOIN musikais_curitiba.musica_echonest me on me.energia = cc.energiaMusica and cc.valenciaMusica"
    + "							WHERE cc.idVoto = 1"
    + "							group by cc.idRegiao,cc.idClima,cc.idPeriodo, cc.idVoto, me.nomeMusica, me.nomeArtista) T1"
    + "					left JOIN"
    + "					(SELECT tr.nome regiao, cr.nome clima, pr.nome periodo, tv.nome voto, me.nomeArtista, me.nomeMusica, me.energia, me.valencia, count(idVoto) total"
    + "						FROM musikais_curitiba.combinacoes cc"
    + "						JOIN musikais_curitiba.tipo_regiao tr ON tr.id = cc.idRegiao"
    + "						JOIN musikais_curitiba.clima cr ON cr.id = cc.idClima"
    + "						JOIN musikais_curitiba.periodo pr ON pr.id = cc.idPeriodo"
    + "						JOIN musikais_curitiba.tipo_voto tv ON tv.id = cc.idVoto"
    + "						JOIN musikais_curitiba.musica_echonest me on me.energia = cc.energiaMusica and cc.valenciaMusica"
    + "							WHERE cc.idVoto = 2"
    + "							group by cc.idRegiao,cc.idClima,cc.idPeriodo, cc.idVoto, me.nomeMusica, me.nomeArtista) T2"
    + "					ON T1.regiao = T2.regiao and T1.clima = T2.clima and T1.periodo = T2.periodo and T1.nomeMusica = T2.nomeMusica"
    + "					UNION"
    + "					SELECT T2.regiao,T2.clima,T2.periodo,T2.nomeArtista,T2.nomeMusica, T2.energia, T2.valencia, (case when T1.total is null then 0 else T1.total end) as legal, T2.total as absurdo  FROM"
    + "					(SELECT tr.nome regiao, cr.nome clima, pr.nome periodo, tv.nome voto, me.nomeArtista, me.nomeMusica, me.energia, me.valencia, count(idVoto) total"
    + "						FROM musikais_curitiba.combinacoes cc"
    + "						JOIN musikais_curitiba.tipo_regiao tr ON tr.id = cc.idRegiao"
    + "						JOIN musikais_curitiba.clima cr ON cr.id = cc.idClima"
    + "						JOIN musikais_curitiba.periodo pr ON pr.id = cc.idPeriodo"
    + "						JOIN musikais_curitiba.tipo_voto tv ON tv.id = cc.idVoto"
    + "						JOIN musikais_curitiba.musica_echonest me on me.energia = cc.energiaMusica and cc.valenciaMusica"
    + "							WHERE cc.idVoto = 1"
    + "							group by cc.idRegiao,cc.idClima,cc.idPeriodo, cc.idVoto, me.nomeMusica, me.nomeArtista) T1"
    + "					RIGHT JOIN"
    + "					(SELECT tr.nome regiao, cr.nome clima, pr.nome periodo, tv.nome voto, me.nomeArtista, me.nomeMusica, me.energia, me.valencia, count(idVoto) total"
    + "						FROM musikais_curitiba.combinacoes cc"
    + "						JOIN musikais_curitiba.tipo_regiao tr ON tr.id = cc.idRegiao"
    + "						JOIN musikais_curitiba.clima cr ON cr.id = cc.idClima"
    + "						JOIN musikais_curitiba.periodo pr ON pr.id = cc.idPeriodo"
    + "						JOIN musikais_curitiba.tipo_voto tv ON tv.id = cc.idVoto"
    + "						JOIN musikais_curitiba.musica_echonest me on me.energia = cc.energiaMusica and cc.valenciaMusica"
    + "							WHERE cc.idVoto = 2"
    + "							group by cc.idRegiao,cc.idClima,cc.idPeriodo, cc.idVoto, me.nomeMusica, me.nomeArtista) T2"
    + "					ON T1.regiao = T2.regiao and T1.clima = T2.clima and T1.periodo = T2.periodo and T1.nomeMusica = T2.nomeMusica"
    + "				) AS FINAL where legal > absurdo and regiao = ? order by legal desc";
    
    public RecommendationDAO() {
        try {
            connection = DBUtility.dataSource().getConnection();
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public Regiao getRegiaoMaisProxima(Geoloc location) {
        Point2D ponto = new Point2D.Double(location.getLatitude(),
                                           location.getLongitude());
        List<Regiao> regioes;
        Regiao regiao = new Regiao();
        regioes = getRegioes();
        double distancia = 999999999;
        
        // TODO: REFAZER DE FORMA MAIS SOFISTICADA PARA QUANDO HOUVER MAIS
        // PONTOS!
        for (Regiao regiaoBase : regioes) {
            double distanciaAux = ponto.distance(regiaoBase.getLatitude(),
                                                 regiaoBase.getLongitude());
            if (distanciaAux < distancia) {
                distancia = distanciaAux;
                regiao = regiaoBase;
            }
        }
        
        return regiao;
    }
    
    public List<Regiao> getRegioes() {
        List<Regiao> regioes = new ArrayList<Regiao>();
        
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(ALL_REGIONS);
            
            while (rs.next()) {
                Regiao regiao = new Regiao();
                regiao.setId(rs.getInt(1));
                regiao.setNome(rs.getString(2));
                regiao.setLatitude(rs.getFloat(3));
                regiao.setLongitude(rs.getFloat(4));
                regiao.setRaio(rs.getInt(5));
                regiao.setIdTipoRegiao(rs.getInt(6));
                regiao.setDescricao(rs.getString(7));
                regiao.setTipoRegiao(rs.getString(8));
                
                regioes.add(regiao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return regioes;
    }
    
    public Clima getClimaByClimaRest(int idClimaRest) {
        Clima clima = new Clima();
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement(GET_CLIMA_BY_REST);
            preparedStatement.setInt(1, idClimaRest);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                clima.setId(rs.getInt(1));
                clima.setNome(rs.getString(2));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace());
        }
        return clima;
    }
    
    public List<Combination> getCombinacoes(Regiao regiao, Clima clima,
                                            Periodo periodo) {
        LOGGER.info("OBTENDO AS COMBINACOES PARA O CONTEXTO: ["
                    + regiao.getTipoRegiao() + " | " + clima.getNome() + " | "
                    + periodo.getNome() + "]");
        List<Combination> combinacoes = new ArrayList<Combination>();
        
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement(GET_BY_CONTEXT);
            preparedStatement.setString(1, regiao.getTipoRegiao());
            preparedStatement.setString(2, clima.getNome());
            preparedStatement.setString(3, periodo.getNome());
            
            LOGGER.info("QUERY A SER EXECUTADA:\n"+preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            LOGGER.info("RESULTADO: " + rs.toString());
            while (rs.next()) {
                Combination combination = new Combination();
                combination.setEnergia(rs.getFloat(1));
                combination.setValencia(rs.getFloat(2));
                combination.setLegal(rs.getInt(3));
                combination.setAbsurdo(rs.getInt(4));
                
                combinacoes.add(combination);
            }
            
            // TODO: melhorar o tratamento para quando nao existe resultado para
            // o contexto
            if (combinacoes.isEmpty()) {
                preparedStatement = connection.prepareStatement(GET_BY_REGION);
                preparedStatement.setString(1, regiao.getTipoRegiao());
                
                rs = preparedStatement.executeQuery();
                LOGGER.info("RESULTADO: " + rs.toString());
                while (rs.next()) {
                    Combination combination = new Combination();
                    combination.setEnergia(rs.getFloat(1));
                    combination.setValencia(rs.getFloat(2));
                    combination.setLegal(rs.getInt(3));
                    combination.setAbsurdo(rs.getInt(4));
                    
                    combinacoes.add(combination);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace());
        }
        
        return combinacoes;
    }
    
    public List<Combination> getCombinacoes(String tipoRegiao, String clima,
                                            String periodo) {
        LOGGER.info("OBTENDO AS COMBINACOES PARA O CONTEXTO: [" + tipoRegiao
                    + " | " + clima + " | " + periodo + "]");
        List<Combination> combinacoes = new ArrayList<Combination>();
        
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement(GET_BY_CONTEXT);
            preparedStatement.setString(1, tipoRegiao);
            preparedStatement.setString(2, clima);
            preparedStatement.setString(3, periodo);
            
            LOGGER.info("QUERY A SER EXECUTADA: \n" +preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            LOGGER.info("RESULTADO: " + rs.toString());
            while (rs.next()) {
                Combination combination = new Combination();
                combination.setEnergia(rs.getFloat(1));
                combination.setValencia(rs.getFloat(2));
                combination.setLegal(rs.getInt(3));
                combination.setAbsurdo(rs.getInt(4));
                
                combinacoes.add(combination);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace());
        }
        
        return combinacoes;
    }
    
    public List<Music> getMusicsByPonto(Ponto ponto) {
        List<Music> musicas = new ArrayList<Music>();
        
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement(GET_MUSIC_BY_PONTO);
            preparedStatement.setFloat(1, ponto.getEnergia() - ponto.getRaio());
            preparedStatement.setFloat(2, ponto.getEnergia() + ponto.getRaio());
            preparedStatement
            .setFloat(3, ponto.getValencia() - ponto.getRaio());
            preparedStatement
            .setFloat(4, ponto.getValencia() + ponto.getRaio());
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Music musica = new Music();
                musica.setId(rs.getString(1));
                musica.setNomeArtista(rs.getString(2));
                musica.setNomeMusica(rs.getString(3));
                musica.setEnergia(rs.getFloat(4));
                musica.setValencia(rs.getFloat(5));
                
                musicas.add(musica);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace());
        }
        
        return musicas;
    }
    
    public void insertMusicOnBus(String idOnibus, int idMotorista,
                                 String idMusica, float latitude, float longitude,
                                 Timestamp timestamp) {
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement(INSERT_MUSIC_ON_BUS);
            preparedStatement.setString(1, idOnibus);
            preparedStatement.setInt(2, idMotorista);
            preparedStatement.setString(3, idMusica);
            preparedStatement.setFloat(4, latitude);
            preparedStatement.setFloat(5, longitude);
            preparedStatement.setTimestamp(6, timestamp);
            
            LOGGER.info("QUERY A SER EXECUTADA: "
                        + preparedStatement.toString());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("ERRO: " + e.getMessage());
        }
    }
    
    public Music getCurrentMusicOnBus(String idOnibus) {
        Music musica = new Music();
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement(CURRENT_MUSIC_ON_BUS);
            preparedStatement.setString(1, idOnibus);
            
            LOGGER.info("OBTENDO MUSICA ATUAL: " + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            LOGGER.info("RESULTADO: " + rs.toString());
            
            while (rs.next()) {
                musica.setId(rs.getString(1));
                musica.setNomeArtista(rs.getString(3));
                musica.setNomeMusica(rs.getString(4));
                musica.setEnergia(rs.getFloat(6));
                musica.setValencia(rs.getFloat(7));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace());
        }
        
        return musica;
    }
    
    public MusicaOnibus getMusicaOnibusByOnibus(String idOnibus) {
        MusicaOnibus onibus = new MusicaOnibus();
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement(CURRENT_MUSICA_ONIBUS);
            preparedStatement.setString(1, idOnibus);
            
            LOGGER.info("OBTENDO ONIBUS ATUAL: " + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            LOGGER.info("RESULTADO: " + rs.toString());
            
            while (rs.next()) {
                onibus.setIdOnibus(rs.getString(1));
                onibus.setIdMotorista(rs.getInt(2));
                onibus.setIdMusica(rs.getString(3));
                onibus.setLatitude(rs.getFloat(4));
                onibus.setLongitude(rs.getFloat(5));
                onibus.setData(rs.getTimestamp(6));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace());
        }
        
        return onibus;
    }
    
    public void insertCombinacao(int idRegiao, int idClima, int idPeriodo,
                                 int idVoto, float energiaMusica, float valenciaMusica) {
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement(INSERT_COMBINACAO);
            preparedStatement.setInt(1, idRegiao);
            preparedStatement.setInt(2, idClima);
            preparedStatement.setInt(3, idPeriodo);
            preparedStatement.setInt(4, idVoto);
            preparedStatement.setFloat(5, energiaMusica);
            preparedStatement.setFloat(6, valenciaMusica);
            preparedStatement.setTimestamp(7, DBUtility.getCurrentTimeStamp());
            
            LOGGER.info("INSERINDO COMBINAÇÃO: " + preparedStatement.toString());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("ERRO: " + e.getMessage());
        }
    }
    
    public void insertVotoHistorico(String musica, String periodo,
                                    String clima, String regiao, String tipoRegiao, String voto,
                                    float latitude, float longitude, float energiaMusica,
                                    float valenciaMusica) {
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement(INSERT_VOTO_HISTORICO);
            preparedStatement.setString(1,musica);
            preparedStatement.setString(2,periodo);
            preparedStatement.setString(3,clima);
            preparedStatement.setString(4,regiao);
            preparedStatement.setString(5,tipoRegiao);
            preparedStatement.setString(6,voto);
            preparedStatement.setFloat(7,latitude);
            preparedStatement.setFloat(8,longitude);
            preparedStatement.setFloat(9,energiaMusica);
            preparedStatement.setFloat(10,valenciaMusica);
            preparedStatement.setTimestamp(11, DBUtility.getCurrentTimeStamp());
            
            LOGGER.info("INSERINDO VOTO HISTÓRICO: " + preparedStatement.toString());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("ERRO: " + e.getMessage());
        }
    }
    
    public void rateRecommendation(int idEmocao, int idRegiao, int idVoto,
                                   int idClima, int idPeriodo, String fonte) {
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement("insert into votos (idEmocao, idRegiao, idVoto, idClima, idPeriodo, data, fonte) values (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, idEmocao);
            preparedStatement.setInt(2, idRegiao);
            preparedStatement.setInt(3, idVoto);
            preparedStatement.setInt(4, idClima);
            preparedStatement.setInt(5, idPeriodo);
            preparedStatement.setTimestamp(6, DBUtility.getCurrentTimeStamp());
            preparedStatement.setString(7, fonte);
            
            LOGGER.info("QUERY A SER EXECUTADA: "
                        + preparedStatement.toString());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("ERRO: " + e.getMessage());
        }
    }
    
    public Periodo getPeriodoByHora(String hora) {
        if (Integer.parseInt(hora) >= 5 && Integer.parseInt(hora) < 12) {
            return new Periodo(Periodo.MANHA, "manhã");
        } else if (Integer.parseInt(hora) >= 12 && Integer.parseInt(hora) < 18) {
            return new Periodo(Periodo.TARDE, "tarde");
        } else {
            return new Periodo(Periodo.NOITE, "noite");
        }
    }
    
}
