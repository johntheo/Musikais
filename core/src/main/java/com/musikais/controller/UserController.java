package com.musikais.controller;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.musikais.model.BusUserContext;
import com.musikais.model.Mensagem;
import com.musikais.service.UserContextService;

@RestController
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	UserContextService service = new UserContextService();

	@RequestMapping(value = "/busUserContext/idOnibus={idOnibus}", method = RequestMethod.GET, headers = "Accept=application/json")
	public BusUserContext getBusUserContext(@PathVariable String idOnibus) {
		LOGGER.info("Obtendo contexto de usuário para o ônibus " + idOnibus);
		return service.getBusUserContext(idOnibus);
	}
	
	@RequestMapping(value = "/busUserContext/rate/idOnibus={idOnibus}&idVoto={idVoto}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Mensagem rateBusUserContext(@PathVariable String idOnibus, @PathVariable int idVoto) {
		LOGGER.info("Inserindo voto para o contexto" + idOnibus);
		return service.rateBusUserContext(idOnibus, idVoto);
	}
	
}
