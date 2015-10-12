package com.musikais.service;

import java.util.List;

import org.jboss.logging.Logger;

import com.musikais.dao.UtilDAO;
import com.musikais.model.Motorista;
import com.musikais.model.Onibus;

public class UtilService {
	public final String SUCCESS = "Sucesso!";
	private static final Logger LOGGER = Logger
			.getLogger(UserContextService.class);
	private UtilDAO dao;

	public UtilService() {
		dao = new UtilDAO();
	}
	
	public List<Motorista> findMotoristas(){
		LOGGER.info("buscando motoristas");
		return dao.findMotoristas();
	}
	
	public List<Onibus> findOnibus(){
		LOGGER.info("buscando onibus");
		return dao.findOnibus();
	}
	
	public List<String> findImagensByRegiao(int idRegiao){
		LOGGER.info("buscando imagens da regiao "+idRegiao);
		return dao.findImagensByRegiao(idRegiao);
	}
}
