package com.musikais.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.musikais.model.WebSiteCombination;
import com.musikais.service.WebSiteService;

@RestController
public class WebSiteController {

	WebSiteService webSiteService = new WebSiteService();

	@RequestMapping(value = "/combination", method = RequestMethod.GET, headers = "Accept=application/json", produces = "text/plain;charset=UTF-8")
	public WebSiteCombination getMusicRecommendation(){
		return webSiteService.getCombination();
	}
	
}
