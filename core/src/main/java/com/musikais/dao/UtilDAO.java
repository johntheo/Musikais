package com.musikais.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.musikais.model.Motorista;
import com.musikais.model.Onibus;
import com.musikais.util.DBUtility;

public class UtilDAO {
	private static Connection connection;
	private static final Logger LOGGER = Logger
			.getLogger(RecommendationDAO.class);
	
	private static final String FIND_MOTORISTAS = "select * from motorista";
	private static final String FIND_ONIBUS = "select * from onibus";
	private static final String FIND_IMAGES_BY_REGIAO = "select nome from imagem_regiao where idRegiao=?";
	
	public UtilDAO() {
		try {
			connection = DBUtility.dataSource().getConnection();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Motorista> findMotoristas(){
		List<Motorista> motoristas = new ArrayList<Motorista>();
		
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_MOTORISTAS);

			while (rs.next()) {
				Motorista motorista = new Motorista();
				motorista.setId(rs.getInt(1));
				motorista.setNome(rs.getString(2));
				motorista.setAvatar(rs.getString(3));
				
				motoristas.add(motorista);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		return motoristas;
	}
	
	public List<Onibus> findOnibus(){
		List<Onibus> onibus = new ArrayList<Onibus>();
		
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_ONIBUS);

			while (rs.next()) {
				Onibus bus = new Onibus();
				bus.setId(rs.getString(1));
				
				onibus.add(bus);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		return onibus;
	}
	
	public List<String> findImagensByRegiao(int idRegiao){
		List<String> imagens = new ArrayList<String>();
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(FIND_IMAGES_BY_REGIAO);
			preparedStatement.setInt(1, idRegiao);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				imagens.add(rs.getString(1));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getStackTrace());
		}
		
		return imagens;
	}
}
