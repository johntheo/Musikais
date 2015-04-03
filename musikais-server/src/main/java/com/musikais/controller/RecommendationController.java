package com.musikais.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.musikais.model.Geoloc;
import com.musikais.model.Recommendation;
import com.musikais.service.RecommendationService;

@RestController
public class RecommendationController {

	RecommendationService recommendationService = new RecommendationService();

	@RequestMapping(value = "/recommendation/get/{longitude}/{latitude}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Recommendation getMusicRecommendation(
			@PathVariable String longitude, @PathVariable String latitude) {
		Geoloc location = new Geoloc(Integer.parseInt(longitude),
				Integer.parseInt(latitude));

		// DUMMY
		// Recommendation recommendation = new Recommendation();
		// recommendation.setMusicName("TESTANDO");
		return recommendationService.getMusicRecommendation(location);
	}
	
	@RequestMapping(value = "/recommendation/rate/{id}/{vote}", method = RequestMethod.POST, headers = "Accept=application/json")
	public String rateMusicRecommendation(
			@PathVariable int id, @PathVariable String vote) {

		// DUMMY
		// Recommendation recommendation = new Recommendation();
		// recommendation.setMusicName("TESTANDO");
		return recommendationService.rateMusicRecommendation(id, vote);
	}
	
	@RequestMapping(value = "/recommendation/get/{idRegion}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Recommendation> getRegionMusicRecommendation(
			@PathVariable int idRegion) {

		// DUMMY
		// Recommendation recommendation = new Recommendation();
		// recommendation.setMusicName("TESTANDO");
		return recommendationService.getRegionRecommendation(idRegion);
	}
	
}
