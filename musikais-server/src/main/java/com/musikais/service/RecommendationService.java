package com.musikais.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.musikais.model.Geoloc;
import com.musikais.model.Recommendation;
import com.musikais.util.DBUtility;

public class RecommendationService {
	
	public final String SUCCESS = "Sucesso!";

	private Connection connection;
	
	public RecommendationService(){
		connection = DBUtility.getConnection();
	}
	
	public Recommendation getMusicRecommendation(Geoloc location){
		Recommendation recommendation = new Recommendation();
		
		try{
			//PALEATIVO
			int id = Integer.parseInt(""+Math.round(Math.random()*10 + 1));
			
			PreparedStatement preparedStatement = connection.prepareStatement("select nome from musica where id_musica = ?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				recommendation.setMusicName(rs.getString("nome"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return recommendation;
	}
	
	public String rateMusicRecommendation(int idRecommendation, String vote){
		
		//TODO: adicionar voto na tabela de recomendações
	
		return this.SUCCESS;
	}
	
	public List<Recommendation> getRegionRecommendation(int idRegion){
		List<Recommendation> recommendations = new ArrayList<Recommendation>();
		
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select nome from musica");
			
			while(rs.next()){
				Recommendation recommendation = new Recommendation();
				recommendation.setMusicName(rs.getString("nome"));
				
				recommendations.add(recommendation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recommendations;
	}
}
