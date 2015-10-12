package com.musikais.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.musikais.model.Motorista;
import com.musikais.model.Onibus;
import com.musikais.service.UtilService;

@RestController
public class UtilController {
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	UtilService service = new UtilService();
	
	@RequestMapping(value = "/util/list/motorista", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Motorista> listMotoristas() {
		LOGGER.info("Obtendo lista de motorista");
		return service.findMotoristas();
	}
	
	@RequestMapping(value = "/util/list/onibus", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Onibus> listOnibus() {
		LOGGER.info("Obtendo lista de onibus");
		return service.findOnibus();
	}
	
	@RequestMapping(value = "/util/list/imagens/idRegiao={idRegiao}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<String> findImagensByRegiao(@PathVariable int idRegiao){
		LOGGER.info("Obtendo lista de images para a região "+idRegiao);
		return service.findImagensByRegiao(idRegiao);
	}
}
